package org.gustavo.tp2.dto;

import org.gustavo.tp2.model.Arquivo;

public record ArquivoResponseDTO(
    String nomeOriginal,
    String fid
) {
    public static ArquivoResponseDTO valueOf(Arquivo arquivo) {
        return new ArquivoResponseDTO(arquivo.getNomeOriginal(), arquivo.getFid());
    }
}
