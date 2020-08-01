package br.com.colaboradoresapi.services;

import br.com.colaboradoresapi.components.MessageComponent;
import br.com.colaboradoresapi.dto.ResponseDTO;
import br.com.colaboradoresapi.persistence.entities.User;
import br.com.colaboradoresapi.persistence.repositories.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final MessageComponent messages;
    private final UserCrudRepository userRepository;

    @Autowired
    public UserService(MessageComponent messages,
                       UserCrudRepository userRepository) {
        this.messages = messages;
        this.userRepository = userRepository;
    }

    public ResponseDTO<User> addNewUser(User user) {
        return ResponseDTO.<User> builder()
                .status(messages.get(MessageComponent.Type.SALVO_SUCESSO))
                .data(userRepository.save(user))
                .build();
    }

    public ResponseDTO<Iterable<User>> getAllUsers() {
        return ResponseDTO.<Iterable<User>> builder()
                .status(messages.get(MessageComponent.Type.SALVO_SUCESSO))
                .data(userRepository.findAll())
                .build();
    }
}
