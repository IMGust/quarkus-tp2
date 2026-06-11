package org.gustavo.tp2.dto;

import org.gustavo.tp2.model.SuperCharger;
import org.gustavo.tp2.model.TipoSuperCharger;
import java.util.List;

public record SuperChargerResponseDTO(
        Long id,
        TipoSuperCharger tipoSupercharger,
        Double tamanhoPolia,
        String padraoCorreia,
        String acionamento,
        String fabricante,
        List<ArquivoResponseDTO> imagens) {

    public static SuperChargerResponseDTO valueOf(SuperCharger sc) {
        return new SuperChargerResponseDTO(
                sc.getId(),
                sc.getTipoSupercharger(),
                sc.getTamanhoPolia(),
                sc.getPadraoCorreia(),
                sc.getAcionamento() != null ? sc.getAcionamento().getLabel() : null,
                sc.getFabricante(),
                sc.getImagens() != null
                    ? sc.getImagens().stream().map(ArquivoResponseDTO::valueOf).toList()
                    : List.of()
        );
    }
}
