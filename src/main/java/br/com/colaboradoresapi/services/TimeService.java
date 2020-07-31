package br.com.colaboradoresapi.services;

import br.com.colaboradoresapi.persistence.entities.Time;
import br.com.colaboradoresapi.persistence.repositories.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    private final TimeRepository timeRepository;

    @Autowired
    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    public Iterable<Time> getAllTimes() {
        return timeRepository.findAll();
    }

    public Time addNewTime(Time time) {
        return timeRepository.save(time);
    }
}
