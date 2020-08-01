package br.com.colaboradoresapi.persistence.repository;

import br.com.colaboradoresapi.persistence.entity.Time;
import org.springframework.data.repository.CrudRepository;

public interface TimeRepository extends CrudRepository<Time, Integer> {

    Time findByName(String name);
}
