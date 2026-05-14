package org.gustavo.tp2.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.gustavo.tp2.model.Radiador;

@ApplicationScoped
public class RadiadorRepository implements PanacheRepository<Radiador> {

    @Override
    public PanacheQuery<Radiador> findAll() {
        return find("SELECT r FROM Radiador r ORDER BY r.marca ");
    }

    public PanacheQuery<Radiador> findByMarca(String marca) {
        if (marca == null)
            return null;
        return find("UPPER(marca) LIKE ?1 ", "%" + marca.toUpperCase() + "%");
    }

    public long countMarca(String marca) {
        return count("UPPER(marca) LIKE ?1 ", "%" + marca.toUpperCase() + "%");
    }
}
