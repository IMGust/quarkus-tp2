package org.gustavo.tp2.model;

public enum TipoSobrealimentacao {
    TURBO(1, "Turbo"),
    SUPERCHARGER(2, "Supercharger");

    private Integer id;
    private String label;

    TipoSobrealimentacao(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoSobrealimentacao valueOf(Integer id) {
        if (id == null)
            return null;
        for (TipoSobrealimentacao tipo : TipoSobrealimentacao.values()) {
            if (tipo.getId().equals(id))
                return tipo;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}