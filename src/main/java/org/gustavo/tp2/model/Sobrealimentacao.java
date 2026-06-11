package org.gustavo.tp2.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Sobrealimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoSobrealimentacao tipo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoSobrealimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoSobrealimentacao tipo) {
        this.tipo = tipo;
    }

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "sobrealimentacao_arquivo", joinColumns = @JoinColumn(name = "sobrealimentacao_id"), inverseJoinColumns = @JoinColumn(name = "arquivo_id", unique = true))
    private java.util.List<Arquivo> imagens;

    public java.util.List<Arquivo> getImagens() {
        return imagens;
    }

    public void setImagens(java.util.List<Arquivo> imagens) {
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
