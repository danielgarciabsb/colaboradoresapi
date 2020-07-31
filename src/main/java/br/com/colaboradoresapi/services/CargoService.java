package br.com.colaboradoresapi.services;

import br.com.colaboradoresapi.persistence.entities.Cargo;
import br.com.colaboradoresapi.persistence.repositories.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;

    @Autowired
    public CargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public Iterable<Cargo> getAllCargos() {
        return cargoRepository.findAll();
    }

    public Cargo addNewCargo(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

}
