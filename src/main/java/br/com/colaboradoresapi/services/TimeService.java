package br.com.colaboradoresapi.services;

import br.com.colaboradoresapi.components.Message;
import br.com.colaboradoresapi.dto.ResponseDTO;
import br.com.colaboradoresapi.persistence.entities.Time;
import br.com.colaboradoresapi.persistence.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    private final Message messages;
    private final TimeRepository timeRepository;

    @Autowired
    public TimeService(Message messages,
                       TimeRepository timeRepository) {
        this.messages = messages;
        this.timeRepository = timeRepository;
    }

    public ResponseDTO<Iterable<Time>> getAllTimes() {
        return ResponseDTO.<Iterable<Time>> builder()
                .status(messages.get(Message.Type.SUCESSO))
                .data(timeRepository.findAll())
                .build();
    }

    public ResponseDTO<Time> addNewTime(Time time) {
        return ResponseDTO.<Time> builder()
                .status(messages.get(Message.Type.SUCESSO))
                .data(timeRepository.save(time))
                .build();
    }
}
