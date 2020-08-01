package br.com.colaboradoresapi.services;

import br.com.colaboradoresapi.components.MessageComponent;
import br.com.colaboradoresapi.dto.ResponseDTO;
import br.com.colaboradoresapi.persistence.entities.Cargo;
import br.com.colaboradoresapi.persistence.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return ResponseDTO.<Iterable<Cargo>> builder()
                .status(messages.get(MessageComponent.Type.SALVO_SUCESSO))
                .data(cargoRepository.findAll())
                .build();
    }

    public ResponseDTO<Cargo> addNewCargo(Cargo cargo) {
        return ResponseDTO.<Cargo> builder()
                .status(messages.get(MessageComponent.Type.SALVO_SUCESSO))
                .data(cargoRepository.save(cargo))
                .build();
    }

}
