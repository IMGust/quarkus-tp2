package org.gustavo.tp2.service;

import java.util.List;
import org.gustavo.tp2.dto.RadiadorDTO;
import org.gustavo.tp2.dto.RadiadorResponseDTO;

public interface RadiadorService {

    public List<RadiadorResponseDTO> getAll(int page, int pageSize);

    public RadiadorResponseDTO findById(Long id);

    public RadiadorResponseDTO create(RadiadorDTO dto);

    public RadiadorResponseDTO update(Long id, RadiadorDTO dto);

    public void delete(Long id);

    public List<RadiadorResponseDTO> findByMarca(String marca, int page, int pageSize);

    public long count();

    public long countMarca(String marca);
}
