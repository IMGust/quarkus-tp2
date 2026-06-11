package org.gustavo.tp2.dto;

import org.gustavo.tp2.model.Veiculo;
import java.util.List;

public record VeiculoResponseDTO(
        Long id,
        String nome,
        String modelo,
        Integer ano,
        List<ArquivoResponseDTO> imagens) {

    public static VeiculoResponseDTO valueOf(Veiculo veiculo) {
        return new VeiculoResponseDTO(
                veiculo.getId(),
                veiculo.getNome(),
                veiculo.getModelo(),
                veiculo.getAno(),
                veiculo.getImagens() != null
                    ? veiculo.getImagens().stream().map(ArquivoResponseDTO::valueOf).toList()
                    : List.of()
        );
    }
}
