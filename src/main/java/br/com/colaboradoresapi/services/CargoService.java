package br.com.colaboradoresapi.services;

import br.com.colaboradoresapi.components.MessageComponent;
import br.com.colaboradoresapi.dto.ResponseDTO;
import br.com.colaboradoresapi.persistence.entities.Cargo;
import br.com.colaboradoresapi.persistence.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@Service
public class CargoService {

    private final MessageComponent messages;
    private final CargoRepository cargoRepository;

    @Autowired
    public CargoService(MessageComponent messages,
                        CargoRepository cargoRepository) {
        this.messages = messages;
        this.cargoRepository = cargoRepository;
    }

    public ResponseDTO<Iterable<Cargo>> getAllCargos() {
        final Iterable<Cargo> cargos = cargoRepository.findAll();
        return ResponseDTO.<Iterable<Cargo>> builder()
            .status(
                messages.get(
                    MessageComponent.Type.OBTIDOS_SUCESSO,
                    new String[]{
                        String.valueOf(StreamSupport.stream(cargos.spliterator(), false).count()),
                        Cargo.class.getSimpleName()
                    }
                )
            )
            .data(cargoRepository.findAll())
            .build();
    }

    public ResponseDTO<Cargo> addNewCargo(final Cargo cargo) {
        return ResponseDTO.<Cargo> builder()
            .status(messages.get(MessageComponent.Type.SALVO_SUCESSO, new String[]{Cargo.class.getSimpleName()}))
            .data(cargoRepository.save(cargo))
            .build();
    }

}
