package org.gustavo.tp2.resource;

import org.gustavo.tp2.dto.SuperChargerDTO;
import org.gustavo.tp2.dto.SuperChargerResponseDTO;
import org.gustavo.tp2.service.SuperChargerService;
import org.gustavo.tp2.service.SuperChargerFileService;
import org.gustavo.tp2.service.ArquivoDownload;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import jakarta.ws.rs.PATCH;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import java.io.IOException;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/supercharger")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SuperChargerResource {

    private static final int MAX_PAGE_SIZE = 100;

    @Inject
    SuperChargerService service;

    @Inject
    SuperChargerFileService fileService;

    @GET
    public Response buscarTodos(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("pageSize") @DefaultValue("100") int pageSize) {
        page = Math.max(0, page);
        pageSize = Math.min(Math.max(1, pageSize), MAX_PAGE_SIZE);

        return Response.ok(service.getAll(page, pageSize)).header("X-Page", page).header("X-Page-Size", pageSize)
                .header("X-Total-Count", service.count())
                .build();
    }

    @GET
    @Path("/{id: \\d+}")
    public Response buscarPorId(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @POST
    public Response inserir(@Valid SuperChargerDTO dto) {
        SuperChargerResponseDTO created = service.create(dto);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @PUT
    @Path("/{id: \\d+}")
    public Response alterar(@Valid SuperChargerDTO dto, @PathParam("id") Long id) {
        SuperChargerResponseDTO updated = service.update(id, dto);
        return Response.ok(updated).build();
    }

    @DELETE
    @Path("/{id: \\d+}")
    public Response apagar(@PathParam("id") Long id) {
        service.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/images/download/{fid}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("fid") String fid) {
        ArquivoDownload download = fileService.download(fid);
        Response.ResponseBuilder response = Response.ok(download.content(), download.contentType());
        response.header("Content-Disposition", "attachment; filename=\"" + download.fileName().replace("\"", "") + "\"");
        return response.build();
    }

    @PATCH
    @Path("/images/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(
            @RestForm("idSupercharger") 
            @NotNull(message = "idSupercharger é obrigatório.")
            @Min(value = 1, message = "idSupercharger deve ser maior ou igual a 1.")
            Long idSupercharger,

            @RestForm("file") 
            @NotNull(message = "Arquivo de imagem é obrigatório.")
            FileUpload file) {

        try {
            fileService.salvar(idSupercharger, file);
            return Response.noContent().build();
        } catch (IOException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    @DELETE
    @Path("/images/{fid}")
    public Response removerImagem(@PathParam("fid") String fid) {
        fileService.remover(fid);
        return Response.noContent().build();
    }
}
