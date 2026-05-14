package org.gustavo.tp2.model;

public enum TipoTurbo {
    SINGLE(1, "Single Turbo"),
    TWIN_PARALLEL(2, "Twin Turbo Paralelo"),
    TWIN_SEQUENCIAL(3, "Twin Turbo Sequencial"),
    TWIN_SCROLL(4, "Twin Scroll"),
    GEOMETRIA_VARIAVEL(5, "Geometria Variável"),
    ELETRICO(6, "Elétrico");

    private Integer id;
    private String label;

    TipoTurbo(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static TipoTurbo valueOf(Integer id) {
        if (id == null)
            return null;
        for (TipoTurbo tipo : TipoTurbo.values()) {
            if (tipo.getId().equals(id))
                return tipo;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
