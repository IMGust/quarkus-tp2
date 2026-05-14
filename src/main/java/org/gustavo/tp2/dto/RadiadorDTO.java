package org.gustavo.tp2.dto;

import java.math.BigDecimal;

public record RadiadorDTO(
        String tipo,
        Double capacidadeFluidoLitros,
        Double dissipacaoTermicaBTU,
        Integer fileiras,
        String marca,
        BigDecimal preco,
        Long idMotor) {
}
