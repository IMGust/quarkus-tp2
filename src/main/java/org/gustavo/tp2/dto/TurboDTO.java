package org.gustavo.tp2.dto;

import org.gustavo.tp2.model.TipoTurbo;

public record TurboDTO(
        Long id,
        TipoTurbo tipoTurbo,
        Double pressaoBoost,
        Boolean possuiIntercooler,
        Integer quantidade,
        String fabricante,
        Double ladoEscape,
        Double ladoAdmissao,
        Integer idTipoFlange,
        Integer idTipoMancal,
        Integer idTipoWastegate,
        Integer idSistemaRefrigeracao) {
}
