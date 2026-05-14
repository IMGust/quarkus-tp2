package org.gustavo.tp2.service;

import java.util.List;
import org.gustavo.tp2.dto.VeiculoDTO;
import org.gustavo.tp2.dto.VeiculoResponseDTO;

public interface VeiculoService {

    public List<VeiculoResponseDTO> getAll(int page, int pageSize);

    public VeiculoResponseDTO findById(Long id);

    public VeiculoResponseDTO create(VeiculoDTO dto);

    public VeiculoResponseDTO update(Long id, VeiculoDTO dto);

    public void delete(Long id);

    public List<VeiculoResponseDTO> findByNome(String nome, int page, int pageSize);

    public long count();

    public long countNome(String nome);
}
