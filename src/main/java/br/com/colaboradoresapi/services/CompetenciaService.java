package br.com.colaboradoresapi.services;

import br.com.colaboradoresapi.persistence.entities.Competencia;
import br.com.colaboradoresapi.persistence.repositories.CompetenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenciaService {

    private final CompetenciaRepository competenciaRepository;

    @Autowired
    public CompetenciaService(CompetenciaRepository competenciaRepository) {
        this.competenciaRepository = competenciaRepository;
    }

    public Iterable<Competencia> getAllCompetencias() {
        return competenciaRepository.findAll();
    }

    public Competencia addNewCompetencia(Competencia competencia) {
        return competenciaRepository.save(competencia);
    }

    public Iterable<Competencia> addCompetenciaList(List<Competencia> competencias) {
        return competenciaRepository.saveAll(competencias);
    }
}
