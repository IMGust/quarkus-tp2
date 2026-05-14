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

    
}
