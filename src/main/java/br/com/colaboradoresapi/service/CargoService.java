package br.com.colaboradoresapi.service;

import br.com.colaboradoresapi.component.MessageComponent;
import br.com.colaboradoresapi.dto.ResponseDTO;
import br.com.colaboradoresapi.persistence.entity.Cargo;
import br.com.colaboradoresapi.persistence.repository.CargoRepository;
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
