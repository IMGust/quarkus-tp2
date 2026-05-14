package org.gustavo.tp2.dto;

import org.gustavo.tp2.model.Veiculo;

public record VeiculoResponseDTO(
        Long id,
        String nome,
        String modelo,
        Integer ano) {

    public static VeiculoResponseDTO valueOf(Veiculo veiculo) {
        return new VeiculoResponseDTO(
                veiculo.getId(),
                veiculo.getNome(),
                veiculo.getModelo(),
                veiculo.getAno()
        );
    }
}
