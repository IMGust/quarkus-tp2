package org.gustavo.tp2.dto;

import java.util.List;

import org.gustavo.tp2.model.Motor;

public record MotorResponseDTO(
        Long id,
        String nome,
        Double cilindrada,
        Integer potencia,
        Double torque,
        Double taxaCompressao,
        Integer rpmMax,
        Double preco,
        List<RadiadorResponseDTO> radiadores,
        List<PistaoResponseDTO> pistoes,
        List<VeiculoResponseDTO> veiculosCompativeis,
        Object sobrealimentacao,
        List<ArquivoResponseDTO> imagens) {

    public static MotorResponseDTO valueOf(Motor motor) {
        return new MotorResponseDTO(
                motor.getId(),
                motor.getNome(),
                motor.getCilindrada(),
                motor.getPotencia(),
                motor.getTorque(),
                motor.getTaxaCompressao(),
                motor.getRpmMax(),
                motor.getPreco(),
                motor.getRadiadores() != null 
                    ? motor.getRadiadores().stream().map(RadiadorResponseDTO::valueOf).toList()
                    : List.of(),
                motor.getPistoes() != null
                    ? motor.getPistoes().stream().map(PistaoResponseDTO::valueOf).toList()
                    : List.of(),
                motor.getVeiculosCompativeis() != null
                    ? motor.getVeiculosCompativeis().stream().map(VeiculoResponseDTO::valueOf).toList()
                    : List.of(),
                mapSobrealimentacao(motor.getSobrealimentacao()),
                motor.getImagens() != null
                    ? motor.getImagens().stream().map(ArquivoResponseDTO::valueOf).toList()
                    : List.of());
    }

    private static Object mapSobrealimentacao(org.gustavo.tp2.model.Sobrealimentacao s) {
        if (s == null) return null;
        if (s instanceof org.gustavo.tp2.model.Turbo) return TurboResponseDTO.valueOf((org.gustavo.tp2.model.Turbo) s);
        if (s instanceof org.gustavo.tp2.model.SuperCharger) return SuperChargerResponseDTO.valueOf((org.gustavo.tp2.model.SuperCharger) s);
        return SobrealimentacaoResponseDTO.valueOf(s);
    }
}
