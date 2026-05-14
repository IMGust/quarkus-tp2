package org.gustavo.tp2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Turbo extends Sobrealimentacao {

    @Enumerated(EnumType.STRING)
    private TipoTurbo tipoTurbo;

    private Double pressaoBoost;

    private Boolean possuiIntercooler;

    private Integer quantidade; // ex: biturbo

    private String fabricante;

    private Double ladoEscape;

    private Double ladoAdmissao;

    @Enumerated(EnumType.STRING)
    private TipoFlange tipoFlange;

    @Enumerated(EnumType.STRING)
    private TipoMancal tipoMancal;

    @Enumerated(EnumType.STRING)
    private TipoWastegate wastegate;

    @Enumerated(EnumType.STRING)
    private SistemaRefrigeracao sistemaRefrigeracao;

    public TipoTurbo getTipoTurbo() {
        return tipoTurbo;
    }

    public void setTipoTurbo(TipoTurbo tipoTurbo) {
        this.tipoTurbo = tipoTurbo;
    }

    public Double getPressaoBoost() {
        return pressaoBoost;
    }

    public void setPressaoBoost(Double pressaoBoost) {
        this.pressaoBoost = pressaoBoost;
    }

    public Boolean getPossuiIntercooler() {
        return possuiIntercooler;
    }

    public void setPossuiIntercooler(Boolean possuiIntercooler) {
        this.possuiIntercooler = possuiIntercooler;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Double getLadoEscape() {
        return ladoEscape;
    }

    public void setLadoEscape(Double ladoEscape) {
        this.ladoEscape = ladoEscape;
    }

    public Double getLadoAdmissao() {
        return ladoAdmissao;
    }

    public void setLadoAdmissao(Double ladoAdmissao) {
        this.ladoAdmissao = ladoAdmissao;
    }

    public TipoFlange getTipoFlange() {
        return tipoFlange;
    }

    public void setTipoFlange(TipoFlange tipoFlange) {
        this.tipoFlange = tipoFlange;
    }

    public TipoMancal getTipoMancal() {
        return tipoMancal;
    }

    public void setTipoMancal(TipoMancal tipoMancal) {
        this.tipoMancal = tipoMancal;
    }

    public TipoWastegate getWastegate() {
        return wastegate;
    }

    public void setWastegate(TipoWastegate wastegate) {
        this.wastegate = wastegate;
    }

    public SistemaRefrigeracao getSistemaRefrigeracao() {
        return sistemaRefrigeracao;
    }

    public void setSistemaRefrigeracao(SistemaRefrigeracao sistemaRefrigeracao) {
        this.sistemaRefrigeracao = sistemaRefrigeracao;
    }
}
