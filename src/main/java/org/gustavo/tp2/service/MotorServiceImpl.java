package org.gustavo.tp2.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;


import org.gustavo.tp2.dto.MotorDTO;
import org.gustavo.tp2.dto.MotorResponseDTO;
import org.gustavo.tp2.model.Sobrealimentacao;
import org.gustavo.tp2.model.Radiador;
import org.gustavo.tp2.model.Pistao;
import org.gustavo.tp2.model.Motor;
import org.gustavo.tp2.model.Veiculo;
import org.gustavo.tp2.repository.VeiculoRepository;
import org.gustavo.tp2.repository.SobrealimentacaoRepository;
import org.gustavo.tp2.repository.RadiadorRepository;
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
public class MotorServiceImpl implements MotorService {

    @Inject
    MotorRepository motorRepository;

    @Inject
    RadiadorRepository radiadorRepository;

    @Inject
    PistaoRepository pistaoRepository;

    @Inject
    SobrealimentacaoRepository sobrealimentacaoRepository;

    @Inject
    VeiculoRepository veiculoRepository;

    @Inject
    Validator validator;

    @Override
    public List<MotorResponseDTO> getAll(int page, int pageSize) {
        List<Motor> list = motorRepository.findAll().page(page, pageSize).list();
        return list.stream().map(MotorResponseDTO::valueOf).toList();
    }

    @Override
    public MotorResponseDTO findById(Long id) {
        Motor motor = motorRepository.findById(id);
        if (motor == null) throw new NotFoundException("Motor não encontrado.");
        return MotorResponseDTO.valueOf(motor);
    }

    @Override
    public List<MotorResponseDTO> findByNome(String nome, int page, int pageSize) {
        List<Motor> list = motorRepository.findByNome(nome).page(page, pageSize).list();
        return list.stream().map(MotorResponseDTO::valueOf).toList();
    }

    @Override
    public long count() {
        return motorRepository.count();
    }

    @Override
    public long countNome(String nome) {
        return motorRepository.countNome(nome);
    }

    @Override
    @Transactional
    public MotorResponseDTO create(MotorDTO dto) throws ConstraintViolationException {
        validar(dto);
        Motor entity = new Motor();
        apply(dto, entity);
        motorRepository.persist(entity);
        return MotorResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public MotorResponseDTO update(Long id, MotorDTO dto) throws ConstraintViolationException {
        validar(dto);
        Motor entity = motorRepository.findById(id);
        if (entity == null) throw new NotFoundException("Motor não encontrado.");
        apply(dto, entity);
        return MotorResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        boolean removed = motorRepository.deleteById(id);
        if (!removed) throw new NotFoundException("Motor não encontrado.");
    }

    private void validar(MotorDTO dto) throws ConstraintViolationException {
        Set<ConstraintViolation<MotorDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

    /** Copia/normaliza os campos do DTO para a entidade. */
    private void apply(MotorDTO dto, Motor entity) {
        // Texto comum: apenas trim
        entity.setNome(safeTrim(dto.nome()));
        
        // Numéricos mantidos diretamente
        entity.setCilindrada(dto.cilindrada());
        entity.setPotencia(dto.potencia());
        entity.setTorque(dto.torque());
        entity.setTaxaCompressao(dto.taxaCompressao());
        entity.setRpmMax(dto.rpmMax());
        entity.setPreco(dto.preco());

        // Radiadores (Muitos para Um - Radiador para Motor)
        if (entity.getRadiadores() != null) {
            entity.getRadiadores().forEach(r -> r.setMotor(null));
            entity.getRadiadores().clear();
        } else {
            entity.setRadiadores(new java.util.ArrayList<>());
        }

        List<Radiador> radiadores = (dto.idRadiadores() == null ? Collections.<Long>emptyList() : dto.idRadiadores())
            .stream()
            .map(id -> {
                Radiador radiadorExistente = radiadorRepository.findById(id);
                if (radiadorExistente == null) {
                    throw new NotFoundException("Radiador de ID " + id + " não encontrado.");
                }
                radiadorExistente.setMotor(entity);
                return radiadorExistente;
            })
            .toList();

        entity.getRadiadores().addAll(radiadores);

        // Pistões (Muitos para Um - Pistão para Motor)
        if (entity.getPistoes() != null) {
            entity.getPistoes().forEach(p -> p.setMotor(null));
            entity.getPistoes().clear();
        } else {
            entity.setPistoes(new java.util.ArrayList<>());
        }

        List<Pistao> pistoes = (dto.idPistoes() == null ? Collections.<Long>emptyList() : dto.idPistoes())
            .stream()
            .map(id -> {
                Pistao pistaoExistente = pistaoRepository.findById(id);
                if (pistaoExistente == null) {
                    throw new NotFoundException("Pistão de ID " + id + " não encontrado.");
                }
                pistaoExistente.setMotor(entity);
                return pistaoExistente;
            })
            .toList();

        entity.getPistoes().addAll(pistoes);

        // Sobrealimentação (Um para Um)
        if (dto.idSobrealimentacao() != null) {
            Sobrealimentacao s = sobrealimentacaoRepository.findById(dto.idSobrealimentacao());
            if (s == null) throw new NotFoundException("Sobrealimentação não encontrada.");
            entity.setSobrealimentacao(s);
        } else {
            entity.setSobrealimentacao(null);
        }

        // Veículos Compatíveis (Muitos para Muitos)
        if (entity.getVeiculosCompativeis() != null) {
            entity.getVeiculosCompativeis().clear();
        } else {
            entity.setVeiculosCompativeis(new java.util.ArrayList<>());
        }

        List<Veiculo> veiculos = (dto.idVeiculos() == null ? Collections.<Long>emptyList() : dto.idVeiculos())
            .stream()
            .map(id -> {
                Veiculo veiculoExistente = veiculoRepository.findById(id);
                if (veiculoExistente == null) {
                    throw new NotFoundException("Veículo de ID " + id + " não encontrado.");
                }
                return veiculoExistente;
            })
            .toList();

        entity.getVeiculosCompativeis().addAll(veiculos);
    }

    /** Remove espaços laterais, preservando null. */
    private String safeTrim(String s) {
        return s == null ? null : s.trim();
    }
}
