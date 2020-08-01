package br.com.colaboradoresapi.persistence.repository;

import br.com.colaboradoresapi.persistence.entity.Cargo;
import org.springframework.data.repository.CrudRepository;

public interface CargoRepository extends CrudRepository<Cargo, Integer> {

    Cargo findByName(String name);
}
