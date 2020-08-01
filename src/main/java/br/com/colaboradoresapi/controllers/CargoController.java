package br.com.colaboradoresapi.controllers;

import br.com.colaboradoresapi.persistence.entities.Cargo;
import br.com.colaboradoresapi.dto.ResponseDTO;
import br.com.colaboradoresapi.services.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/cargo",
        produces = "application/json",
        method = {RequestMethod.GET, RequestMethod.POST})
public class CargoController {

    private final CargoService cargoService;

    @Autowired
    public CargoController(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @GetMapping(path = "/list")
    public @ResponseBody
    Iterable<Cargo> getAllCargos() {
        return cargoService.getAllCargos();
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    ResponseDTO<Cargo> addNewCargo(@RequestBody Cargo cargo) {
        Cargo cargoResponse = cargoService.addNewCargo(cargo);
        return new ResponseDTO<>("Cargo salvo com sucesso!", cargoResponse);
    }
}