package br.com.colaboradoresapi.services;

import br.com.colaboradoresapi.persistence.entities.Cargo;
import br.com.colaboradoresapi.persistence.entities.Colaborador;
import br.com.colaboradoresapi.persistence.entities.Competencia;
import br.com.colaboradoresapi.persistence.entities.Time;
import br.com.colaboradoresapi.persistence.repositories.CargoRepository;
import br.com.colaboradoresapi.persistence.repositories.CompetenciaRepository;
import br.com.colaboradoresapi.persistence.repositories.PageableColaboradorRepository;
import br.com.colaboradoresapi.persistence.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColaboradorService {

    private final PageableColaboradorRepository pageableColaboradorRepository;
    private final CargoRepository cargoRepository;
    private final TimeRepository timeRepository;
    private final CompetenciaRepository competenciaRepository;

    @Autowired
    public ColaboradorService(PageableColaboradorRepository pageableColaboradorRepository,
                              CargoRepository cargoRepository,
                              TimeRepository timeRepository,
                              CompetenciaRepository competenciaRepository) {
        this.pageableColaboradorRepository = pageableColaboradorRepository;
        this.cargoRepository = cargoRepository;
        this.timeRepository = timeRepository;
        this.competenciaRepository = competenciaRepository;
    }

    public Optional<Colaborador> getColaboradorById(Integer id) {
        return pageableColaboradorRepository.findById(id);
    }

    public Colaborador addNewColaborador(Colaborador colaborador) {

        Cargo cargoRepo = cargoRepository.findByName(colaborador.getCargo().getName());
        if(cargoRepo == null) {
            cargoRepository.save(colaborador.getCargo());
        } else {
            colaborador.getCargo().setId(cargoRepo.getId());
        }

        Time timeRepo = timeRepository.findByName(colaborador.getTime().getName());
        if(timeRepo == null) {
            timeRepository.save(colaborador.getTime());
        } else {
            colaborador.getTime().setId(timeRepo.getId());
        }

        colaborador.getCompetencias().forEach(competencia -> {
            Competencia competenciaRepo = competenciaRepository.findByName(competencia.getName());
            if(competenciaRepo == null) {
                competenciaRepository.save(competencia);
            } else {
                competencia.setId(competenciaRepo.getId());
            }
        });
        return pageableColaboradorRepository.save(colaborador);
    }

    public Iterable<Colaborador> getAllColaboradores(Pageable pageable) {
        return pageableColaboradorRepository.findAll(pageable);
    }

    public Iterable<Colaborador> findColaboradorBy(String name, String searchType, Pageable pageable) {
        switch (searchType) {
            case "Cargo": return pageableColaboradorRepository.findByCargoNameContaining(name, pageable);
            case "Competência": return pageableColaboradorRepository.findByCompetenciasNameContaining(name, pageable);
            case "Time": return pageableColaboradorRepository.findByTimeNameContaining(name, pageable);
            default: return pageableColaboradorRepository.findByNameContaining(name, pageable);
        }
    }
}
