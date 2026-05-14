package org.gustavo.tp2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class Radiador extends DefaultEntity {

    private String tipo; // alumínio, cobre
    private Double capacidadeFluidoLitros;
    private Double dissipacaoTermicaBTU;
    private Integer fileiras;
    private String marca;
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "motor_id")
    private Motor motor;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getCapacidadeFluidoLitros() {
        return capacidadeFluidoLitros;
    }

    public void setCapacidadeFluidoLitros(Double capacidadeFluidoLitros) {
        this.capacidadeFluidoLitros = capacidadeFluidoLitros;
    }

    public Double getDissipacaoTermicaBTU() {
        return dissipacaoTermicaBTU;
    }

    public void setDissipacaoTermicaBTU(Double dissipacaoTermicaBTU) {
        this.dissipacaoTermicaBTU = dissipacaoTermicaBTU;
    }

    public Integer getFileiras() {
        return fileiras;
    }

    public void setFileiras(Integer fileiras) {
        this.fileiras = fileiras;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }
}
