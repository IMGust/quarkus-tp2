package org.gustavo.tp2.model;

public enum TipoWastegate {
    INTERNA(1, "Interna"),
    EXTERNA(2, "Externa");

    private Integer id;
    private String label;

    TipoWastegate(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoWastegate valueOf(Integer id) {
        if (id == null) return null;
        for (TipoWastegate tipo : TipoWastegate.values()) {
            if (tipo.getId().equals(id)) return tipo;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
