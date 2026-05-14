package org.gustavo.tp2.repository;

import org.gustavo.tp2.model.Motor;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MotorRepository implements PanacheRepository<Motor> {

    @Override
    public PanacheQuery<Motor> findAll() {
        return find("SELECT m FROM Motor m ORDER BY m.nome ");
    }

    public PanacheQuery<Motor> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }

    public long countNome(String nome) {
        return count("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }

}
