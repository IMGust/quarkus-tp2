package org.gustavo.tp2.dto;

import java.util.List;


public record MotorDTO(
        Long id,
        String nome,
        Double cilindrada,
        Integer potencia,
        Double torque,
        Double taxaCompressao,
        Integer rpmMax,
        Double preco,
        List<Long> idRadiadores,
        List<Long> idPistoes,
        List<Long> idVeiculos,
        Long idSobrealimentacao) {
}
