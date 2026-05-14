package org.gustavo.tp2.model;

public enum TipoFlange {
    T25(1, "T25"),
    T3(2, "T3"),
    T4(3, "T4"),
    TWIN_SCROLL(4, "Twin Scroll"),
    V_BAND(5, "V-Band");

    private Integer id;
    private String label;

    TipoFlange(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoFlange valueOf(Integer id) {
        if (id == null) return null;
        for (TipoFlange tipo : TipoFlange.values()) {
            if (tipo.getId().equals(id)) return tipo;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
