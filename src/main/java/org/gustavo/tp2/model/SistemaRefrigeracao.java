package org.gustavo.tp2.model;

public enum SistemaRefrigeracao {
    OLEO(1, "Apenas Óleo"),
    OLEO_AGUA(2, "Óleo + Água");

    private Integer id;
    private String label;

    SistemaRefrigeracao(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static SistemaRefrigeracao valueOf(Integer id) {
        if (id == null) return null;
        for (SistemaRefrigeracao tipo : SistemaRefrigeracao.values()) {
            if (tipo.getId().equals(id)) return tipo;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
