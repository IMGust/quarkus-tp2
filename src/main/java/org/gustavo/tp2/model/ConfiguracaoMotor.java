package org.gustavo.tp2.model;

public enum ConfiguracaoMotor {
    LINHA(1, "Em Linha"),
    V(2, "Em V"),
    BOXER(3, "Boxer");

    private Integer id;
    private String label;

    ConfiguracaoMotor(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public static ConfiguracaoMotor valueOf(Integer id) {
        if (id == null)
            return null;
        for (ConfiguracaoMotor config : ConfiguracaoMotor.values()) {
            if (config.getId().equals(id))
                return config;
        }
        throw new IllegalArgumentException("Id inválido: " + id);
    }
}
