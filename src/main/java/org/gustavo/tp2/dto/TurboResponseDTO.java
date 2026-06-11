package org.gustavo.tp2.dto;

import org.gustavo.tp2.model.Turbo;
import org.gustavo.tp2.model.TipoTurbo;
import java.util.List;

public record TurboResponseDTO(
        Long id,
        TipoTurbo tipoTurbo,
        Double pressaoBoost,
        Boolean possuiIntercooler,
        Integer quantidade,
        String fabricante,
        Double ladoEscape,
        Double ladoAdmissao,
        String tipoFlange,
        String tipoMancal,
        String wastegate,
        String sistemaRefrigeracao,
        List<ArquivoResponseDTO> imagens) {

    public static TurboResponseDTO valueOf(Turbo turbo) {
        return new TurboResponseDTO(
                turbo.getId(),
                turbo.getTipoTurbo(),
                turbo.getPressaoBoost(),
                turbo.getPossuiIntercooler(),
                turbo.getQuantidade(),
                turbo.getFabricante(),
                turbo.getLadoEscape(),
                turbo.getLadoAdmissao(),
                turbo.getTipoFlange() != null ? turbo.getTipoFlange().getLabel() : null,
                turbo.getTipoMancal() != null ? turbo.getTipoMancal().getLabel() : null,
                turbo.getWastegate() != null ? turbo.getWastegate().getLabel() : null,
                turbo.getSistemaRefrigeracao() != null ? turbo.getSistemaRefrigeracao().getLabel() : null,
                turbo.getImagens() != null
                    ? turbo.getImagens().stream().map(ArquivoResponseDTO::valueOf).toList()
                    : List.of()
        );
    }
}
