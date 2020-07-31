package br.com.colaboradoresapi.controllers;

import br.com.colaboradoresapi.persistence.entities.Time;
import br.com.colaboradoresapi.persistence.models.Response;
import br.com.colaboradoresapi.services.TimeService;
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
    Iterable<Time> getAllTimes() {
        return timeService.getAllTimes();
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    Response<Time> addNewTime(@RequestBody Time time) {
        Time timeResponse = timeService.addNewTime(time);
        return new Response<>("Time salvo com sucesso!", timeResponse);
    }
}