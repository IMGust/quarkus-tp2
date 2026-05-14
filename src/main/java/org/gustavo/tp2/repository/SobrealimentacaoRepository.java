package org.gustavo.tp2.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.gustavo.tp2.model.Sobrealimentacao;

@ApplicationScoped
public class SobrealimentacaoRepository implements PanacheRepository<Sobrealimentacao> {
}
