package br.com.colaboradoresapi.controllers;

import br.com.colaboradoresapi.dto.ResponseDTO;
import br.com.colaboradoresapi.persistence.entities.Colaborador;
import br.com.colaboradoresapi.services.ColaboradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(
        value = "/colaborador",
        produces = "application/json",
        method = {RequestMethod.GET, RequestMethod.POST})
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    @Autowired
    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @GetMapping(path="/", params = {"id"})
    public @ResponseBody
    ResponseDTO<Optional<Colaborador>> getColaboradorById(Integer id) {
        return colaboradorService.getColaboradorById(id);
    }

    @PostMapping(path="/add")
    public @ResponseBody
    ResponseDTO<Colaborador> addNewColaborador(@RequestBody Colaborador colaborador) {
        return colaboradorService.addNewColaborador(colaborador);
    }

    @GetMapping(path="/list", params = {"page", "size"})
    public @ResponseBody
    ResponseDTO<Iterable<Colaborador>> getAllColaboradores(Pageable pageable) {
        return colaboradorService.getAllColaboradores(pageable);
    }

    @GetMapping(path="/find", params = {"name","searchType","page","size"})
    public @ResponseBody
    ResponseDTO<Page<Colaborador>> findColaboradorBy(String name, String searchType, Pageable pageable) {
        return colaboradorService.findColaboradorBy(name, searchType, pageable);
    }
}