package org.gustavo.tp2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;

@Entity
public class Pistao extends DefaultEntity {

    private String material; // Forjado, Fundido
    private Double diametro;
    private Double curso;
    private Double volumeDomo;
    private String marca;
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "motor_id")
    private Motor motor;

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Double getDiametro() {
        return diametro;
    }

    public void setDiametro(Double diametro) {
        this.diametro = diametro;
    }

    public Double getCurso() {
        return curso;
    }

    public void setCurso(Double curso) {
        this.curso = curso;
    }

    public Double getVolumeDomo() {
        return volumeDomo;
    }

    public void setVolumeDomo(Double volumeDomo) {
        this.volumeDomo = volumeDomo;
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
