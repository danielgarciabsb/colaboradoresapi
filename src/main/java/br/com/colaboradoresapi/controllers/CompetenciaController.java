package br.com.colaboradoresapi.controllers;

import br.com.colaboradoresapi.persistence.entities.Competencia;
import br.com.colaboradoresapi.persistence.models.Response;
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
    Iterable<Competencia> getAllCompetencias() {
        return competenciaService.getAllCompetencias();
    }

    @PostMapping(path="/add")
    public @ResponseBody
    Response<Competencia> addNewCompetencia(@RequestBody Competencia competencia) {
        Competencia competenciaResponse = competenciaService.addNewCompetencia(competencia);
        return new Response<>("Competência salva com sucesso!", competenciaResponse);
    }

    @PostMapping(path="/addall")
    public @ResponseBody
    Response<Iterable<Competencia>> addCompetenciaList (@RequestBody List<Competencia> competencias) {
        Iterable<Competencia> competenciasResponse = competenciaService.addCompetenciaList(competencias);
        return new Response<>("Competências salvas com sucesso!", competenciasResponse);
    }
}
