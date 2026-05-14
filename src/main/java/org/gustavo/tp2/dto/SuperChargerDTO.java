package org.gustavo.tp2.dto;

import org.gustavo.tp2.model.TipoSuperCharger;

public record SuperChargerDTO(
        Long id,
        TipoSuperCharger tipoSupercharger,
        Double tamanhoPolia,
        String padraoCorreia,
        Integer idTipoAcionamento,
        String fabricante) {
}
