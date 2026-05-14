package org.gustavo.tp2.dto;

import java.math.BigDecimal;

public record PistaoDTO(
        String material,
        Double diametro,
        Double curso,
        Double volumeDomo,
        String marca,
        BigDecimal preco,
        Long idMotor) {
}
