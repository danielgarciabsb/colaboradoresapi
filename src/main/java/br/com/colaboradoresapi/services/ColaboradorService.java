package br.com.colaboradoresapi.services;

import br.com.colaboradoresapi.components.MessageComponent;
import br.com.colaboradoresapi.domain.SearchType;
import br.com.colaboradoresapi.dto.ResponseDTO;
import br.com.colaboradoresapi.persistence.entities.Cargo;
import br.com.colaboradoresapi.persistence.entities.Colaborador;
import br.com.colaboradoresapi.persistence.entities.Competencia;
import br.com.colaboradoresapi.persistence.entities.Time;
import br.com.colaboradoresapi.persistence.repositories.CargoRepository;
import br.com.colaboradoresapi.persistence.repositories.CompetenciaRepository;
import br.com.colaboradoresapi.persistence.repositories.PageableColaboradorRepository;
import br.com.colaboradoresapi.persistence.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ColaboradorService {

    private final MessageComponent messages;
    private final PageableColaboradorRepository pageableColaboradorRepository;
    private final CargoRepository cargoRepository;
    private final TimeRepository timeRepository;
    private final CompetenciaRepository competenciaRepository;

    @Autowired
    public ColaboradorService(MessageComponent messages,
                              PageableColaboradorRepository pageableColaboradorRepository,
                              CargoRepository cargoRepository,
                              TimeRepository timeRepository,
                              CompetenciaRepository competenciaRepository) {
        this.messages = messages;
        this.pageableColaboradorRepository = pageableColaboradorRepository;
        this.cargoRepository = cargoRepository;
        this.timeRepository = timeRepository;
        this.competenciaRepository = competenciaRepository;
    }

    public ResponseDTO<Optional<Colaborador>> getColaboradorById(final Integer id) {
        return ResponseDTO.<Optional<Colaborador>> builder()
            .status(
                messages.get(
                    MessageComponent.Type.OBTIDO_SUCESSO,
                    new String[]{Colaborador.class.getSimpleName()}
                )
            )
            .data(pageableColaboradorRepository.findById(id))
            .build();
    }

    public ResponseDTO<Colaborador> addNewColaborador(final Colaborador colaborador) {

        final Cargo cargoRepo = cargoRepository.findByName(colaborador.getCargo().getName());
        if(cargoRepo == null) {
            cargoRepository.save(colaborador.getCargo());
        } else {
            colaborador.getCargo().setId(cargoRepo.getId());
        }

        final Time timeRepo = timeRepository.findByName(colaborador.getTime().getName());
        if(timeRepo == null) {
            timeRepository.save(colaborador.getTime());
        } else {
            colaborador.getTime().setId(timeRepo.getId());
        }

        colaborador.getCompetencias().forEach(competencia -> {
            final Competencia competenciaRepo = competenciaRepository.findByName(competencia.getName());
            if(competenciaRepo == null) {
                competenciaRepository.save(competencia);
            } else {
                competencia.setId(competenciaRepo.getId());
            }
        });

        return ResponseDTO.<Colaborador> builder()
            .status(
                messages.get(
                    MessageComponent.Type.SALVO_SUCESSO,
                    new String[]{Colaborador.class.getSimpleName()}
                )
            )
            .data(pageableColaboradorRepository.save(colaborador))
            .build();
    }

    public ResponseDTO<Iterable<Colaborador>> getAllColaboradores(final Pageable pageable) {
        final Page<Colaborador> colaboradores = pageableColaboradorRepository.findAll(pageable);
        return ResponseDTO.<Iterable<Colaborador>> builder()
            .status(messages.get(
                MessageComponent.Type.OBTIDOS_SUCESSO,
                new String[]{
                    String.valueOf(colaboradores.getSize()),
                    Colaborador.class.getSimpleName()
                })
            )
            .data(colaboradores)
            .build();
    }

    public ResponseDTO<Page<Colaborador>> findColaboradorBy(
            final String name, final String searchType, final Pageable pageable) {

        Page<Colaborador> colaboradores;

        switch (searchType) {
            case SearchType.Colaborador.CARGO:
                colaboradores = pageableColaboradorRepository.findByCargoNameContaining(name, pageable);
                break;
            case SearchType.Colaborador.COMPETENCIA:
                colaboradores = pageableColaboradorRepository.findByCompetenciasNameContaining(name, pageable);
                break;
            case SearchType.Colaborador.TIME:
                colaboradores = pageableColaboradorRepository.findByTimeNameContaining(name, pageable);
                break;
            default:
                colaboradores = pageableColaboradorRepository.findByNameContaining(name, pageable);
        }
        return ResponseDTO.<Page<Colaborador>> builder()
            .status(
                messages.get(
                    MessageComponent.Type.OBTIDOS_SUCESSO,
                    new String[]{
                        String.valueOf(colaboradores.getSize()),
                        Colaborador.class.getSimpleName()
                    }
                )
            )
            .data(colaboradores)
            .build();
    }
}
