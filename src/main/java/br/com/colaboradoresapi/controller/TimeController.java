package br.com.colaboradoresapi.controller;

import br.com.colaboradoresapi.dto.ResponseDTO;
import br.com.colaboradoresapi.persistence.entity.Time;
import br.com.colaboradoresapi.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/time",
        produces = "application/json",
        method = {RequestMethod.GET, RequestMethod.POST})
public class TimeController {

    private final TimeService timeService;

    @Autowired
    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping(path = "/list")
    public @ResponseBody
    ResponseDTO<Iterable<Time>> getAllTimes() {
        return timeService.getAllTimes();
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    ResponseDTO<Time> addNewTime(@RequestBody Time time) {
        return timeService.addNewTime(time);
    }
}