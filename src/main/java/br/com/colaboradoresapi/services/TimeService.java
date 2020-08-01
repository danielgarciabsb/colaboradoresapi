package br.com.colaboradoresapi.services;

import br.com.colaboradoresapi.components.MessageComponent;
import br.com.colaboradoresapi.dto.ResponseDTO;
import br.com.colaboradoresapi.persistence.entities.Competencia;
import br.com.colaboradoresapi.persistence.entities.Time;
import br.com.colaboradoresapi.persistence.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.StreamSupport;

@Service
public class TimeService {

    private final MessageComponent messages;
    private final TimeRepository timeRepository;

    @Autowired
    public TimeService(MessageComponent messages,
                       TimeRepository timeRepository) {
        this.messages = messages;
        this.timeRepository = timeRepository;
    }

    public ResponseDTO<Iterable<Time>> getAllTimes() {
        Iterable<Time> times = timeRepository.findAll();
        return ResponseDTO.<Iterable<Time>> builder()
            .status(
                messages.get(
                    MessageComponent.Type.OBTIDOS_SUCESSO,
                    new String[]{
                        String.valueOf(StreamSupport.stream(times.spliterator(), false).count()),
                        Competencia.class.getSimpleName()
                    }
                )
            )
            .data(times)
            .build();
    }

    public ResponseDTO<Time> addNewTime(Time time) {
        return ResponseDTO.<Time> builder()
            .status(messages.get(MessageComponent.Type.SALVO_SUCESSO, new String[]{Time.class.getSimpleName()}))
            .data(timeRepository.save(time))
            .build();
    }
}
