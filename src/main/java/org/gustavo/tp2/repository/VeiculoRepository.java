package org.gustavo.tp2.repository;

import org.gustavo.tp2.model.Veiculo;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VeiculoRepository implements PanacheRepository<Veiculo> {

    @Override
    public PanacheQuery<Veiculo> findAll() {
        return find("SELECT v FROM Veiculo v ORDER BY v.nome ");
    }

    public PanacheQuery<Veiculo> findByNome(String nome) {
        if (nome == null)
            return null;
        return find("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }

    public long countNome(String nome) {
        return count("UPPER(nome) LIKE ?1 ", "%" + nome.toUpperCase() + "%");
    }

}
