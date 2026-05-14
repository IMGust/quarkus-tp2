package org.gustavo.tp2.model.converterjpa;

import org.gustavo.tp2.model.TipoSuperCharger;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoSuperChargerConverter implements AttributeConverter<TipoSuperCharger, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoSuperCharger tipoSuperCharger) {
        return tipoSuperCharger == null ? null : tipoSuperCharger.getId();
    }

    @Override
    public TipoSuperCharger convertToEntityAttribute(Integer id) {
        return TipoSuperCharger.valueOf(id);
    }
}
