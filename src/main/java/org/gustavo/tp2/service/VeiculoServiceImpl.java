package org.gustavo.tp2.service;

import java.util.List;
import java.util.Set;

import org.gustavo.tp2.dto.VeiculoDTO;
import org.gustavo.tp2.dto.VeiculoResponseDTO;
import org.gustavo.tp2.model.Veiculo;
import org.gustavo.tp2.repository.VeiculoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class VeiculoServiceImpl implements VeiculoService {

    @Inject
    VeiculoRepository repository;

    @Inject
    Validator validator;

    @Override
    public List<VeiculoResponseDTO> getAll(int page, int pageSize) {
        List<Veiculo> list = repository.findAll().page(page, pageSize).list();
        return list.stream().map(VeiculoResponseDTO::valueOf).toList();
    }

    @Override
    public VeiculoResponseDTO findById(Long id) {
        Veiculo entity = repository.findById(id);
        if (entity == null) throw new NotFoundException("Veículo não encontrado.");
        return VeiculoResponseDTO.valueOf(entity);
    }

    @Override
    public List<VeiculoResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Veiculo> list = repository.findByNome(nome).page(page, pageSize).list();
        return list.stream().map(VeiculoResponseDTO::valueOf).toList();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public long countNome(String nome) {
        return repository.countNome(nome);
    }

    @Override
    @Transactional
    public VeiculoResponseDTO create(VeiculoDTO dto) throws ConstraintViolationException {
        validar(dto);
        Veiculo entity = new Veiculo();
        apply(dto, entity);
        repository.persist(entity);
        return VeiculoResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public VeiculoResponseDTO update(Long id, VeiculoDTO dto) throws ConstraintViolationException {
        validar(dto);
        Veiculo entity = repository.findById(id);
        if (entity == null) throw new NotFoundException("Veículo não encontrado.");
        apply(dto, entity);
        return VeiculoResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        boolean removed = repository.deleteById(id);
        if (!removed) throw new NotFoundException("Veículo não encontrado.");
    }

    private void validar(VeiculoDTO dto) throws ConstraintViolationException {
        Set<ConstraintViolation<VeiculoDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

    private void apply(VeiculoDTO dto, Veiculo entity) {
        entity.setNome(safeTrim(dto.nome()));
        entity.setModelo(safeTrim(dto.modelo()));
        entity.setAno(dto.ano());
    }

    private String safeTrim(String s) {
        return s == null ? null : s.trim();
    }
}
