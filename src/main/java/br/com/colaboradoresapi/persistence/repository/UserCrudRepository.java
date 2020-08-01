package br.com.colaboradoresapi.persistence.repository;
import br.com.colaboradoresapi.persistence.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCrudRepository extends CrudRepository<User, Integer> {

}