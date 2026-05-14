package org.gustavo.tp2.model.converterjpa;

import org.gustavo.tp2.model.ConfiguracaoMotor;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ConfiguracaoMotorConverter implements AttributeConverter<ConfiguracaoMotor, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ConfiguracaoMotor configuracaoMotor) {
        return configuracaoMotor == null ? null : configuracaoMotor.getId();
    }

    @Override
    public ConfiguracaoMotor convertToEntityAttribute(Integer id) {
        return ConfiguracaoMotor.valueOf(id);
    }
}
