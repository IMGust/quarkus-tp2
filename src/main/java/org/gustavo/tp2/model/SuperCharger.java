package org.gustavo.tp2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class SuperCharger extends Sobrealimentacao {

    @Enumerated(EnumType.STRING)
    private TipoSuperCharger tipoSupercharger;

    private Double tamanhoPolia;

    private String padraoCorreia; // Ex: 6PK, 8PK

    @Enumerated(EnumType.STRING)
    private TipoAcionamento acionamento;

    private String fabricante;

    public TipoSuperCharger getTipoSupercharger() {
        return tipoSupercharger;
    }

    public void setTipoSupercharger(TipoSuperCharger tipoSupercharger) {
        this.tipoSupercharger = tipoSupercharger;
    }

    public Double getTamanhoPolia() {
        return tamanhoPolia;
    }

    public void setTamanhoPolia(Double tamanhoPolia) {
        this.tamanhoPolia = tamanhoPolia;
    }

    public String getPadraoCorreia() {
        return padraoCorreia;
    }

    public void setPadraoCorreia(String padraoCorreia) {
        this.padraoCorreia = padraoCorreia;
    }

    public TipoAcionamento getAcionamento() {
        return acionamento;
    }

    public void setAcionamento(TipoAcionamento acionamento) {
        this.acionamento = acionamento;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }
}
