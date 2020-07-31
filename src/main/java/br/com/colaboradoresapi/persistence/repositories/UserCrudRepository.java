package br.com.colaboradoresapi.persistence.repositories;
import br.com.colaboradoresapi.persistence.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<User, Integer> {

}