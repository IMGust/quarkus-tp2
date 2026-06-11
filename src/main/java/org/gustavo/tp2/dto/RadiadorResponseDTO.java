package org.gustavo.tp2.dto;

import org.gustavo.tp2.model.Radiador;
import java.math.BigDecimal;
import java.util.List;

public record RadiadorResponseDTO(
        Long id,
        String tipo,
        Double capacidadeFluidoLitros,
        Double dissipacaoTermicaBTU,
        Integer fileiras,
        String marca,
        BigDecimal preco,
        Long idMotor,
        List<ArquivoResponseDTO> imagens) {

    public static RadiadorResponseDTO valueOf(Radiador radiador) {
        return new RadiadorResponseDTO(
                radiador.getId(),
                radiador.getTipo(),
                radiador.getCapacidadeFluidoLitros(),
                radiador.getDissipacaoTermicaBTU(),
                radiador.getFileiras(),
                radiador.getMarca(),
                radiador.getPreco(),
                radiador.getMotor() != null ? radiador.getMotor().getId() : null,
                radiador.getImagens() != null
                    ? radiador.getImagens().stream().map(ArquivoResponseDTO::valueOf).toList()
                    : List.of()
        );
    }
}
