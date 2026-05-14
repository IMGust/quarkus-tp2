package org.gustavo.tp2.service;

import java.util.List;
import java.util.Set;

import org.gustavo.tp2.dto.TurboDTO;
import org.gustavo.tp2.dto.TurboResponseDTO;
import org.gustavo.tp2.model.Turbo;
import org.gustavo.tp2.model.TipoSobrealimentacao;
import org.gustavo.tp2.model.TipoFlange;
import org.gustavo.tp2.model.TipoMancal;
import org.gustavo.tp2.model.TipoWastegate;
import org.gustavo.tp2.model.SistemaRefrigeracao;
import org.gustavo.tp2.repository.TurboRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TurboServiceImpl implements TurboService {

    @Inject
    TurboRepository repository;

    @Inject
    Validator validator;

    @Override
    public List<TurboResponseDTO> getAll(int page, int pageSize) {
        List<Turbo> list = repository.findAll().page(page, pageSize).list();
        return list.stream().map(TurboResponseDTO::valueOf).toList();
    }

    @Override
    public TurboResponseDTO findById(Long id) {
        Turbo entity = repository.findById(id);
        if (entity == null)
            throw new NotFoundException("Turbo não encontrado.");
        return TurboResponseDTO.valueOf(entity);
    }

    @Override
    public List<TurboResponseDTO> findByFabricante(String fabricante, int page, int pageSize) {
        List<Turbo> list = repository.findByFabricante(fabricante).page(page, pageSize).list();
        return list.stream().map(TurboResponseDTO::valueOf).toList();
    }

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public long countFabricante(String fabricante) {
        return repository.countFabricante(fabricante);
    }

    @Override
    @Transactional
    public TurboResponseDTO create(TurboDTO dto) throws ConstraintViolationException {
        validar(dto);
        Turbo entity = new Turbo();
        entity.setTipo(TipoSobrealimentacao.TURBO); // Base class field
        apply(dto, entity);
        repository.persist(entity);
        return TurboResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public TurboResponseDTO update(Long id, TurboDTO dto) throws ConstraintViolationException {
        validar(dto);
        Turbo entity = repository.findById(id);
        if (entity == null)
            throw new NotFoundException("Turbo não encontrado.");
        apply(dto, entity);
        return TurboResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        boolean removed = repository.deleteById(id);
        if (!removed)
            throw new NotFoundException("Turbo não encontrado.");
    }

    private void validar(TurboDTO dto) throws ConstraintViolationException {
        Set<ConstraintViolation<TurboDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty())
            throw new ConstraintViolationException(violations);
    }

    private void apply(TurboDTO dto, Turbo entity) {
        entity.setTipoTurbo(dto.tipoTurbo());
        entity.setPressaoBoost(dto.pressaoBoost());
        entity.setPossuiIntercooler(dto.possuiIntercooler());
        entity.setQuantidade(dto.quantidade());
        entity.setFabricante(safeTrim(dto.fabricante()));

        entity.setLadoEscape(dto.ladoEscape());
        entity.setLadoAdmissao(dto.ladoAdmissao());
        entity.setTipoFlange(TipoFlange.valueOf(dto.idTipoFlange()));
        entity.setTipoMancal(TipoMancal.valueOf(dto.idTipoMancal()));
        entity.setWastegate(TipoWastegate.valueOf(dto.idTipoWastegate()));
        entity.setSistemaRefrigeracao(SistemaRefrigeracao.valueOf(dto.idSistemaRefrigeracao()));
    }

    private String safeTrim(String s) {
        return s == null ? null : s.trim();
    }
}
