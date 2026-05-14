package org.gustavo.tp2.model;

public enum TipoMancal {
    BALL_BEARING(1, "Rolamentada (Ball Bearing)"),
    JOURNAL_BEARING(2, "Casquilho (Journal Bearing)");

    private Integer id;
    private String label;

    TipoMancal(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoMancal valueOf(Integer id) {
        if (id == null) return null;
        for (TipoMancal tipo : TipoMancal.values()) {
            if (tipo.getId().equals(id)) return tipo;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
