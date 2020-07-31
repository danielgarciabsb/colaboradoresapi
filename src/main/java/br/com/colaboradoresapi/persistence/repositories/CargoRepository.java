package br.com.colaboradoresapi.persistence.repositories;

import br.com.colaboradoresapi.persistence.entities.Cargo;
import org.springframework.data.repository.CrudRepository;

public interface CargoRepository extends CrudRepository<Cargo, Integer> {

    Cargo findByName(String name);
}
