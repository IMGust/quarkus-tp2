package org.gustavo.tp2.dto;

import org.gustavo.tp2.model.Pistao;
import java.math.BigDecimal;
import java.util.List;

public record PistaoResponseDTO(
        Long id,
        String material,
        Double diametro,
        Double curso,
        Double volumeDomo,
        String marca,
        BigDecimal preco,
        Long idMotor,
        List<ArquivoResponseDTO> imagens) {

    public static PistaoResponseDTO valueOf(Pistao pistao) {
        return new PistaoResponseDTO(
                pistao.getId(),
                pistao.getMaterial(),
                pistao.getDiametro(),
                pistao.getCurso(),
                pistao.getVolumeDomo(),
                pistao.getMarca(),
                pistao.getPreco(),
                pistao.getMotor() != null ? pistao.getMotor().getId() : null,
                pistao.getImagens() != null
                    ? pistao.getImagens().stream().map(ArquivoResponseDTO::valueOf).toList()
                    : List.of()
        );
    }
}
