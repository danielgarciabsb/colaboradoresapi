package br.com.colaboradoresapi.persistence.repositories;

import br.com.colaboradoresapi.persistence.entities.Time;
import org.springframework.data.repository.CrudRepository;

public interface TimeRepository extends CrudRepository<Time, Integer> {

    Time findByName(String name);
}
