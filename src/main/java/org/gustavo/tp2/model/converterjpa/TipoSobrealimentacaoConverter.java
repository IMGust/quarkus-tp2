package org.gustavo.tp2.model.converterjpa;

import org.gustavo.tp2.model.TipoSobrealimentacao;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class TipoSobrealimentacaoConverter implements AttributeConverter<TipoSobrealimentacao, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TipoSobrealimentacao tipoSobrealimentacao) {
        return tipoSobrealimentacao == null ? null : tipoSobrealimentacao.getId();
    }

    @Override
    public TipoSobrealimentacao convertToEntityAttribute(Integer id) {
        return TipoSobrealimentacao.valueOf(id);
    }
}
