package org.gustavo.tp2.dto;

import org.gustavo.tp2.model.Motor;

public record MotorMinimoResponseDTO(
        Long id,
        String nome,
        Integer potencia) {

    public static MotorMinimoResponseDTO valueOf(Motor motor) {
        if (motor == null) {
            return null;
        }
        return new MotorMinimoResponseDTO(
                motor.getId(),
                motor.getNome(),
                motor.getPotencia()
        );
    }
}
