package org.gustavo.tp2.dto;

import org.gustavo.tp2.model.Radiador;
import java.math.BigDecimal;

public record RadiadorResponseDTO(
        Long id,
        String tipo,
        Double capacidadeFluidoLitros,
        Double dissipacaoTermicaBTU,
        Integer fileiras,
        String marca,
        BigDecimal preco,
        Long idMotor) {

    public static RadiadorResponseDTO valueOf(Radiador radiador) {
        return new RadiadorResponseDTO(
                radiador.getId(),
                radiador.getTipo(),
                radiador.getCapacidadeFluidoLitros(),
                radiador.getDissipacaoTermicaBTU(),
                radiador.getFileiras(),
                radiador.getMarca(),
                radiador.getPreco(),
                radiador.getMotor() != null ? radiador.getMotor().getId() : null
        );
    }
}
