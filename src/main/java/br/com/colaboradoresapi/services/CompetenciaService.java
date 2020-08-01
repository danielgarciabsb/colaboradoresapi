package br.com.colaboradoresapi.services;

import br.com.colaboradoresapi.components.Message;
import br.com.colaboradoresapi.dto.ResponseDTO;
import br.com.colaboradoresapi.persistence.entities.Competencia;
import br.com.colaboradoresapi.persistence.repositories.CompetenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenciaService {

    private final Message messages;
    private final CompetenciaRepository competenciaRepository;

    @Autowired
    public CompetenciaService(Message messages,
                              CompetenciaRepository competenciaRepository) {
        this.messages = messages;
        this.competenciaRepository = competenciaRepository;
    }

    public ResponseDTO<Iterable<Competencia>> getAllCompetencias() {
        return ResponseDTO.<Iterable<Competencia>> builder()
                .status(messages.get(Message.Type.SUCESSO))
                .data(competenciaRepository.findAll())
                .build();
    }

    public ResponseDTO<Competencia> addNewCompetencia(Competencia competencia) {
        return ResponseDTO.<Competencia> builder()
                .status(messages.get(Message.Type.SUCESSO))
                .data(competenciaRepository.save(competencia))
                .build();
    }

    public ResponseDTO<Iterable<Competencia>> addCompetenciaList(List<Competencia> competencias) {
        return ResponseDTO.<Iterable<Competencia>> builder()
                .status(messages.get(Message.Type.SUCESSO))
                .data(competenciaRepository.saveAll(competencias))
                .build();
    }
}
