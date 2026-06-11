package org.gustavo.tp2.service;

import java.io.IOException;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface VeiculoFileService {

    void salvar(Long id, FileUpload file) throws IOException;

    ArquivoDownload download(String fid);

    void remover(String fid);
}
