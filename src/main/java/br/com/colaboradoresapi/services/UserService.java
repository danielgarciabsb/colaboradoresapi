package br.com.colaboradoresapi.services;

import br.com.colaboradoresapi.persistence.entities.User;
import br.com.colaboradoresapi.persistence.repositories.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserCrudRepository userRepository;

    @Autowired
    public UserService(UserCrudRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
