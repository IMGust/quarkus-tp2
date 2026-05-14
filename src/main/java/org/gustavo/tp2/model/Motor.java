package org.gustavo.tp2.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Motor extends DefaultEntity {

    private String nome;
    private Double cilindrada;
    private Integer potencia;
    private Double torque;
    private Double taxaCompressao;
    private Integer rpmMax;
    private Double preco;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "motor_arquivo", joinColumns = @JoinColumn(name = "motor_id"), inverseJoinColumns = @JoinColumn(name = "arquivo_id", unique = true))
    private List<Arquivo> imagens;


    @OneToMany(mappedBy = "motor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Radiador> radiadores;

    @ManyToMany
    @JoinTable(name = "motor_veiculo", joinColumns = @JoinColumn(name = "motor_id"), inverseJoinColumns = @JoinColumn(name = "veiculo_id"))
    private List<Veiculo> veiculosCompativeis;

    @OneToMany(mappedBy = "motor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pistao> pistoes;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sobrealimentacao_id")
    private Sobrealimentacao sobrealimentacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(Double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public Integer getPotencia() {
        return potencia;
    }

    public void setPotencia(Integer potencia) {
        this.potencia = potencia;
    }

    public Double getTorque() {
        return torque;
    }

    public void setTorque(Double torque) {
        this.torque = torque;
    }

    public Double getTaxaCompressao() {
        return taxaCompressao;
    }

    public void setTaxaCompressao(Double taxaCompressao) {
        this.taxaCompressao = taxaCompressao;
    }

    public Integer getRpmMax() {
        return rpmMax;
    }

    public void setRpmMax(Integer rpmMax) {
        this.rpmMax = rpmMax;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }


    public List<Radiador> getRadiadores() {
        return radiadores;
    }

    public void setRadiadores(List<Radiador> radiadores) {
        this.radiadores = radiadores;
    }

    public List<Veiculo> getVeiculosCompativeis() {
        return veiculosCompativeis;
    }

    public void setVeiculosCompativeis(List<Veiculo> veiculosCompativeis) {
        this.veiculosCompativeis = veiculosCompativeis;
    }

    public List<Pistao> getPistoes() {
        return pistoes;
    }

    public void setPistoes(List<Pistao> pistoes) {
        this.pistoes = pistoes;
    }

    public Sobrealimentacao getSobrealimentacao() {
        return sobrealimentacao;
    }

    public void setSobrealimentacao(Sobrealimentacao sobrealimentacao) {
        this.sobrealimentacao = sobrealimentacao;
    }

    public List<Arquivo> getImagens() {
        return imagens;
    }

    public void setImagens(List<Arquivo> imagens) {
        this.imagens = imagens;
    }

    public void addImagem(Arquivo arquivo) {
        if (arquivo == null) {
            return;
        }
        if (this.imagens == null) {
            this.imagens = new java.util.ArrayList<>();
        }
        this.imagens.add(arquivo);
    }

    public void removeImagem(Arquivo arquivo) {
        if (arquivo == null || this.imagens == null) {
            return;
        }
        this.imagens.remove(arquivo);
    }

}
