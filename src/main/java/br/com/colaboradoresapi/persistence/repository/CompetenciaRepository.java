package br.com.colaboradoresapi.persistence.repository;

import br.com.colaboradoresapi.persistence.entity.Competencia;
import org.springframework.data.repository.CrudRepository;

public interface CompetenciaRepository extends CrudRepository<Competencia, Integer> {

    Competencia findByName(String name);
}
