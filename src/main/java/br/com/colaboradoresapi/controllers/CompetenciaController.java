package br.com.colaboradoresapi.controllers;

import br.com.colaboradoresapi.dto.ResponseDTO;
import br.com.colaboradoresapi.persistence.entities.Competencia;
import br.com.colaboradoresapi.services.CompetenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "/competencia",
        produces = "application/json",
        method = {RequestMethod.GET, RequestMethod.POST})
public class CompetenciaController {

    private final CompetenciaService competenciaService;

    @Autowired
    public CompetenciaController(CompetenciaService competenciaService) {
        this.competenciaService = competenciaService;
    }

    @GetMapping(path="/list")
    public @ResponseBody
    ResponseDTO<Iterable<Competencia>> getAllCompetencias() {
        return competenciaService.getAllCompetencias();
    }

    @PostMapping(path="/add")
    public @ResponseBody
    ResponseDTO<Competencia> addNewCompetencia(@RequestBody Competencia competencia) {
        return competenciaService.addNewCompetencia(competencia);
    }

    @PostMapping(path="/addall")
    public @ResponseBody
    ResponseDTO<Iterable<Competencia>> addCompetenciaList (@RequestBody List<Competencia> competencias) {
        return competenciaService.addCompetenciaList(competencias);
    }
}
