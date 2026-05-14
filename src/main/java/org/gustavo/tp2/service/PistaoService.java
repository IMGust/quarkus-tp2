package org.gustavo.tp2.service;

import java.util.List;
import org.gustavo.tp2.dto.PistaoDTO;
import org.gustavo.tp2.dto.PistaoResponseDTO;

public interface PistaoService {

    public List<PistaoResponseDTO> getAll(int page, int pageSize);

    public PistaoResponseDTO findById(Long id);

    public PistaoResponseDTO create(PistaoDTO dto);

    public PistaoResponseDTO update(Long id, PistaoDTO dto);

    public void delete(Long id);

    public List<PistaoResponseDTO> findByMarca(String marca, int page, int pageSize);

    public long count();

    public long countMarca(String marca);
}
