package org.gustavo.tp2.service;

import java.util.List;
import org.gustavo.tp2.dto.SuperChargerDTO;
import org.gustavo.tp2.dto.SuperChargerResponseDTO;

public interface SuperChargerService {

    public List<SuperChargerResponseDTO> getAll(int page, int pageSize);

    public SuperChargerResponseDTO findById(Long id);

    public SuperChargerResponseDTO create(SuperChargerDTO dto);

    public SuperChargerResponseDTO update(Long id, SuperChargerDTO dto);

    public void delete(Long id);

    public List<SuperChargerResponseDTO> findByFabricante(String fabricante, int page, int pageSize);

    public long count();

    public long countFabricante(String fabricante);
}
