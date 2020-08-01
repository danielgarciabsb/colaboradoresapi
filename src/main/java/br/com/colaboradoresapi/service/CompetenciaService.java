package br.com.colaboradoresapi.service;

import br.com.colaboradoresapi.component.MessageComponent;
import br.com.colaboradoresapi.dto.ResponseDTO;
import br.com.colaboradoresapi.persistence.entity.Competencia;
import br.com.colaboradoresapi.persistence.repository.CompetenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class CompetenciaService {

    private final MessageComponent messages;
    private final CompetenciaRepository competenciaRepository;

    @Autowired
    public CompetenciaService(MessageComponent messages,
                              CompetenciaRepository competenciaRepository) {
        this.messages = messages;
        this.competenciaRepository = competenciaRepository;
    }

    public ResponseDTO<Iterable<Competencia>> getAllCompetencias() {
        Iterable<Competencia> competencias = competenciaRepository.findAll();
        return ResponseDTO.<Iterable<Competencia>> builder()
            .status(
                messages.get(
                    MessageComponent.Type.OBTIDOS_SUCESSO,
                    new String[]{
                        String.valueOf(StreamSupport.stream(competencias.spliterator(), false).count()),
                        Competencia.class.getSimpleName()
                    }
                )
            )
            .data(competenciaRepository.findAll())
            .build();
    }

    public ResponseDTO<Competencia> addNewCompetencia(final Competencia competencia) {
        return ResponseDTO.<Competencia> builder()
            .status(
                messages.get(
                    MessageComponent.Type.SALVO_SUCESSO,
                    new String[]{Competencia.class.getSimpleName()}
                )
            )
            .data(competenciaRepository.save(competencia))
            .build();
    }

    public ResponseDTO<Iterable<Competencia>> addCompetenciaList(final List<Competencia> competencias) {
        Iterable<Competencia> competenciasSaved = competenciaRepository.saveAll(competencias);
        return ResponseDTO.<Iterable<Competencia>> builder()
            .status(
                messages.get(
                    MessageComponent.Type.SALVOS_SUCESSO,
                    new String[]{
                        String.valueOf(StreamSupport.stream(competenciasSaved.spliterator(), false).count()),
                        Competencia.class.getSimpleName()
                    }
                )
            )
            .data(competenciasSaved)
            .build();
    }
}
