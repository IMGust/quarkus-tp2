package org.gustavo.tp2.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.gustavo.tp2.model.Turbo;

@ApplicationScoped
public class TurboRepository implements PanacheRepository<Turbo> {

    @Override
    public PanacheQuery<Turbo> findAll() {
        return find("SELECT t FROM Turbo t ORDER BY t.fabricante ");
    }

    public PanacheQuery<Turbo> findByFabricante(String fabricante) {
        if (fabricante == null)
            return null;
        return find("UPPER(fabricante) LIKE ?1 ", "%" + fabricante.toUpperCase() + "%");
    }

    public long countFabricante(String fabricante) {
        return count("UPPER(fabricante) LIKE ?1 ", "%" + fabricante.toUpperCase() + "%");
    }
}
