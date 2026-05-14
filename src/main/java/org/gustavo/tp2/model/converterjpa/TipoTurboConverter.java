package org.gustavo.tp2.model.converterjpa;

import org.gustavo.tp2.model.TipoTurbo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoTurboConverter implements AttributeConverter<TipoTurbo, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoTurbo tipoTurbo) {
        return tipoTurbo == null ? null : tipoTurbo.getId();
    }

    @Override
    public TipoTurbo convertToEntityAttribute(Integer id) {
        return TipoTurbo.valueOf(id);
    }
}
