package br.com.colaboradoresapi.service;

import br.com.colaboradoresapi.component.MessageComponent;
import br.com.colaboradoresapi.dto.ResponseDTO;
import br.com.colaboradoresapi.persistence.entity.Competencia;
import br.com.colaboradoresapi.persistence.entity.User;
import br.com.colaboradoresapi.persistence.repository.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

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
            .status(messages.get(MessageComponent.Type.SALVO_SUCESSO, new String[]{User.class.getSimpleName()}))
            .data(userRepository.save(user))
            .build();
    }

    public ResponseDTO<Iterable<User>> getAllUsers() {
        Iterable<User> users = userRepository.findAll();
        return ResponseDTO.<Iterable<User>> builder()
            .status(
                messages.get(
                    MessageComponent.Type.SALVOS_SUCESSO,
                    new String[]{
                        String.valueOf(StreamSupport.stream(users.spliterator(), false).count()),
                        Competencia.class.getSimpleName()
                    }
                )
            )
            .data(users)
            .build();
    }
}
