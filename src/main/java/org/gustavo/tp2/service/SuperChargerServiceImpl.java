package org.gustavo.tp2.service;

import java.util.List;
import java.util.Set;

import org.gustavo.tp2.dto.SuperChargerDTO;
import org.gustavo.tp2.dto.SuperChargerResponseDTO;
import org.gustavo.tp2.model.SuperCharger;
import org.gustavo.tp2.model.TipoSobrealimentacao;
import org.gustavo.tp2.model.TipoAcionamento;
import org.gustavo.tp2.repository.SobrealimentacaoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class SuperChargerServiceImpl implements SuperChargerService {

    @Inject
    SobrealimentacaoRepository repository;

    @Inject
    Validator validator;

    @Override
    public List<SuperChargerResponseDTO> getAll(int page, int pageSize) {
        // We use the base repository but filter/cast as needed or just use a specific one if created
        // For simplicity, I'll use a specific query or just list and filter
        List<SuperCharger> list = repository.find("from SuperCharger").page(page, pageSize).list();
        return list.stream().map(SuperChargerResponseDTO::valueOf).toList();
    }

    @Override
    public SuperChargerResponseDTO findById(Long id) {
        SuperCharger entity = repository.getEntityManager().find(SuperCharger.class, id);
        if (entity == null) throw new NotFoundException("SuperCharger não encontrado.");
        return SuperChargerResponseDTO.valueOf(entity);
    }

    @Override
    public List<SuperChargerResponseDTO> findByFabricante(String fabricante, int page, int pageSize) {
        List<SuperCharger> list = repository.find("UPPER(fabricante) LIKE ?1", "%" + fabricante.toUpperCase() + "%").page(page, pageSize).list();
        return list.stream().map(SuperChargerResponseDTO::valueOf).toList();
    }

    @Override
    public long count() {
        return repository.count("from SuperCharger");
    }

    @Override
    public long countFabricante(String fabricante) {
        return repository.count("from SuperCharger where UPPER(fabricante) LIKE ?1", "%" + fabricante.toUpperCase() + "%");
    }

    @Override
    @Transactional
    public SuperChargerResponseDTO create(SuperChargerDTO dto) throws ConstraintViolationException {
        validar(dto);
        SuperCharger entity = new SuperCharger();
        entity.setTipo(TipoSobrealimentacao.SUPERCHARGER);
        apply(dto, entity);
        repository.persist(entity);
        return SuperChargerResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public SuperChargerResponseDTO update(Long id, SuperChargerDTO dto) throws ConstraintViolationException {
        validar(dto);
        SuperCharger entity = repository.getEntityManager().find(SuperCharger.class, id);
        if (entity == null) throw new NotFoundException("SuperCharger não encontrado.");
        apply(dto, entity);
        return SuperChargerResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        boolean removed = repository.deleteById(id);
        if (!removed) throw new NotFoundException("SuperCharger não encontrado.");
    }

    private void validar(SuperChargerDTO dto) throws ConstraintViolationException {
        Set<ConstraintViolation<SuperChargerDTO>> violations = validator.validate(dto);
        if (!violations.isEmpty()) throw new ConstraintViolationException(violations);
    }

    private void apply(SuperChargerDTO dto, SuperCharger entity) {
        entity.setTipoSupercharger(dto.tipoSupercharger());
        entity.setTamanhoPolia(dto.tamanhoPolia());
        entity.setPadraoCorreia(safeTrim(dto.padraoCorreia()));
        entity.setAcionamento(TipoAcionamento.valueOf(dto.idTipoAcionamento()));
        entity.setFabricante(safeTrim(dto.fabricante()));
    }

    private String safeTrim(String s) {
        return s == null ? null : s.trim();
    }
}
