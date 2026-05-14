package org.gustavo.tp2.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.gustavo.tp2.model.Pistao;

@ApplicationScoped
public class PistaoRepository implements PanacheRepository<Pistao> {

    @Override
    public PanacheQuery<Pistao> findAll() {
        return find("SELECT p FROM Pistao p ORDER BY p.marca ");
    }

    public PanacheQuery<Pistao> findByMarca(String marca) {
        if (marca == null)
            return null;
        return find("UPPER(marca) LIKE ?1 ", "%" + marca.toUpperCase() + "%");
    }

    public long countMarca(String marca) {
        return count("UPPER(marca) LIKE ?1 ", "%" + marca.toUpperCase() + "%");
    }
}
