package org.gustavo.tp2.service;

import java.util.List;
import java.util.Set;

import org.gustavo.tp2.dto.PistaoDTO;
import org.gustavo.tp2.dto.PistaoResponseDTO;
import org.gustavo.tp2.model.Pistao;
import org.gustavo.tp2.model.Motor;
import org.gustavo.tp2.repository.PistaoRepository;
import org.gustavo.tp2.repository.MotorRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PistaoServiceImpl implements PistaoService {

    @Inject
    PistaoRepository repository;

    @Inject
    MotorRepository motorRepository;

    @Inject
    Validator validator;

    @Override
    public List<PistaoResponseDTO> getAll(int page, int pageSize) {
        List<Pistao> list = repository.findAll().page(page, pageSize).list();
        return list.stream().map(PistaoResponseDTO::valueOf).toList();
    }

    @Override
    public PistaoResponseDTO findById(Long id) {
        Pistao entity = repository.findById(id);
        if (entity == null) throw new NotFoundException("Pistão não encontrado.");
        return PistaoResponseDTO.valueOf(entity);
    }

    @Override
    public List<PistaoResponseDTO> findByMarca(String marca, int page, int pageSize) {
        List<Pistao> list = repository.findByMarca(marca).page(page, pageSize).list();
        return list.stream().map(PistaoResponseDTO::valueOf).toList();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public long countMarca(String marca) {
        return repository.countMarca(marca);
    }

    @Override
    @Transactional
    public PistaoResponseDTO create(PistaoDTO dto) throws ConstraintViolationException {
        validar(dto);
        Pistao entity = new Pistao();
        apply(dto, entity);
        repository.persist(entity);
        return PistaoResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public PistaoResponseDTO update(Long id, PistaoDTO dto) throws ConstraintViolationException {
        validar(dto);
        Pistao entity = repository.findById(id);
        if (entity == null) throw new NotFoundException("Pistão não encontrado.");
        apply(dto, entity);
        return PistaoResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        boolean removed = repository.deleteById(id);
        if (!removed) throw new NotFoundException("Pistão não encontrado.");
    }

    private void validar(PistaoDTO dto) throws ConstraintViolationException {
        Set<ConstraintViolation<PistaoDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

    private void apply(PistaoDTO dto, Pistao entity) {
        entity.setMaterial(safeTrim(dto.material()));
        entity.setDiametro(dto.diametro());
        entity.setCurso(dto.curso());
        entity.setVolumeDomo(dto.volumeDomo());
        entity.setMarca(safeTrim(dto.marca()));
        entity.setPreco(dto.preco());

        if (dto.idMotor() != null) {
            Motor motor = motorRepository.findById(dto.idMotor());
            if (motor == null) throw new NotFoundException("Motor não encontrado.");
            entity.setMotor(motor);
        } else {
            entity.setMotor(null);
        }
    }

    private String safeTrim(String s) {
        return s == null ? null : s.trim();
    }
}
