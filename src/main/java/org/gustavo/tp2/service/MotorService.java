package org.gustavo.tp2.service;

import java.util.List;

import org.gustavo.tp2.dto.MotorDTO;
import org.gustavo.tp2.dto.MotorResponseDTO;

public interface MotorService {

    public List<MotorResponseDTO> getAll(int page, int pageSize);

    public MotorResponseDTO findById(Long id);

    public MotorResponseDTO create(MotorDTO motorDTO);

    public MotorResponseDTO update(Long id, MotorDTO motorDTO);

    public void delete(Long id);

    // recursos extras
    public List<MotorResponseDTO> findByNome(String nome, int page, int pageSize);

    public long count();

    public long countNome(String nome);
}
