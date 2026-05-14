package org.gustavo.tp2.model;

public enum TipoSuperCharger {
    ROOTS(1, "Roots"),
    TWIN_SCREW(2, "Twin-Screw"),
    CENTRIFUGO(3, "Centrífugo");

    private Integer id;
    private String label;

    TipoSuperCharger(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoSuperCharger valueOf(Integer id) {
        if (id == null)
            return null;
        for (TipoSuperCharger tipo : TipoSuperCharger.values()) {
            if (tipo.getId().equals(id))
                return tipo;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
