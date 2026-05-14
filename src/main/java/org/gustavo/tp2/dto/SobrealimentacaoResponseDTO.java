package org.gustavo.tp2.dto;

import org.gustavo.tp2.model.Sobrealimentacao;
import org.gustavo.tp2.model.TipoSobrealimentacao;

public record SobrealimentacaoResponseDTO(
        Long id,
        TipoSobrealimentacao tipo) {

    public static SobrealimentacaoResponseDTO valueOf(Sobrealimentacao sobrealimentacao) {
        return new SobrealimentacaoResponseDTO(
                sobrealimentacao.getId(),
                sobrealimentacao.getTipo()
        );
    }
}
