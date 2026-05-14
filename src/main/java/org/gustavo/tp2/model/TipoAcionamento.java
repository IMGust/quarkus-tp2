package org.gustavo.tp2.model;

public enum TipoAcionamento {
    CORREIA_DENTADA(1, "Correia Dentada"),
    CORREIA_ACESSORIOS(2, "Correia de Acessórios"),
    ENGRENAGEM(3, "Engrenagem");

    private Integer id;
    private String label;

    TipoAcionamento(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoAcionamento valueOf(Integer id) {
        if (id == null) return null;
        for (TipoAcionamento tipo : TipoAcionamento.values()) {
            if (tipo.getId().equals(id)) return tipo;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
