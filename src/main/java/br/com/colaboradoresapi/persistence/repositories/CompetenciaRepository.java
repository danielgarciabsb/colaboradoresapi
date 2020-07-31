package br.com.colaboradoresapi.persistence.repositories;

import br.com.colaboradoresapi.persistence.entities.Competencia;
import org.springframework.data.repository.CrudRepository;

public interface CompetenciaRepository extends CrudRepository<Competencia, Integer> {

    Competencia findByName(String name);
}
