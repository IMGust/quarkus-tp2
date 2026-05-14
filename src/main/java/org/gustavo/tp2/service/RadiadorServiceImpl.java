package org.gustavo.tp2.service;

import java.util.List;
import java.util.Set;

import org.gustavo.tp2.dto.RadiadorDTO;
import org.gustavo.tp2.dto.RadiadorResponseDTO;
import org.gustavo.tp2.model.Radiador;
import org.gustavo.tp2.model.Motor;
import org.gustavo.tp2.repository.RadiadorRepository;
import org.gustavo.tp2.repository.MotorRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class RadiadorServiceImpl implements RadiadorService {

    @Inject
    RadiadorRepository repository;

    @Inject
    MotorRepository motorRepository;

    @Inject
    Validator validator;

    @Override
    public List<RadiadorResponseDTO> getAll(int page, int pageSize) {
        List<Radiador> list = repository.findAll().page(page, pageSize).list();
        return list.stream().map(RadiadorResponseDTO::valueOf).toList();
    }

    @Override
    public RadiadorResponseDTO findById(Long id) {
        Radiador entity = repository.findById(id);
        if (entity == null) throw new NotFoundException("Radiador não encontrado.");
        return RadiadorResponseDTO.valueOf(entity);
    }

    @Override
    public List<RadiadorResponseDTO> findByMarca(String marca, int page, int pageSize) {
        List<Radiador> list = repository.findByMarca(marca).page(page, pageSize).list();
        return list.stream().map(RadiadorResponseDTO::valueOf).toList();
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
    public RadiadorResponseDTO create(RadiadorDTO dto) throws ConstraintViolationException {
        validar(dto);
        Radiador entity = new Radiador();
        apply(dto, entity);
        repository.persist(entity);
        return RadiadorResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public RadiadorResponseDTO update(Long id, RadiadorDTO dto) throws ConstraintViolationException {
        validar(dto);
        Radiador entity = repository.findById(id);
        if (entity == null) throw new NotFoundException("Radiador não encontrado.");
        apply(dto, entity);
        return RadiadorResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        boolean removed = repository.deleteById(id);
        if (!removed) throw new NotFoundException("Radiador não encontrado.");
    }

    private void validar(RadiadorDTO dto) throws ConstraintViolationException {
        Set<ConstraintViolation<RadiadorDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

    private void apply(RadiadorDTO dto, Radiador entity) {
        entity.setTipo(safeTrim(dto.tipo()));
        entity.setCapacidadeFluidoLitros(dto.capacidadeFluidoLitros());
        entity.setDissipacaoTermicaBTU(dto.dissipacaoTermicaBTU());
        entity.setFileiras(dto.fileiras());
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
