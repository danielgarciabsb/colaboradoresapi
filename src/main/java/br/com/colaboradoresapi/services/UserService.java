package br.com.colaboradoresapi.services;

import br.com.colaboradoresapi.components.Message;
import br.com.colaboradoresapi.dto.ResponseDTO;
import br.com.colaboradoresapi.persistence.entities.User;
import br.com.colaboradoresapi.persistence.repositories.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Message messages;
    private final UserCrudRepository userRepository;

    @Autowired
    public UserService(Message messages,
                       UserCrudRepository userRepository) {
        this.messages = messages;
        this.userRepository = userRepository;
    }

    public ResponseDTO<User> addNewUser(User user) {
        return ResponseDTO.<User> builder()
                .status(messages.get(Message.Type.SUCESSO))
                .data(userRepository.save(user))
                .build();
    }

    public ResponseDTO<Iterable<User>> getAllUsers() {
        return ResponseDTO.<Iterable<User>> builder()
                .status(messages.get(Message.Type.SUCESSO))
                .data(userRepository.findAll())
                .build();
    }
}
