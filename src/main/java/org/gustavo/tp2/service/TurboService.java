package org.gustavo.tp2.service;

import java.util.List;
import org.gustavo.tp2.dto.TurboDTO;
import org.gustavo.tp2.dto.TurboResponseDTO;

public interface TurboService {

    public List<TurboResponseDTO> getAll(int page, int pageSize);

    public TurboResponseDTO findById(Long id);

    public TurboResponseDTO create(TurboDTO dto);

    public TurboResponseDTO update(Long id, TurboDTO dto);

    public void delete(Long id);

    public List<TurboResponseDTO> findByFabricante(String fabricante, int page, int pageSize);

    public long count();

    public long countFabricante(String fabricante);
}
