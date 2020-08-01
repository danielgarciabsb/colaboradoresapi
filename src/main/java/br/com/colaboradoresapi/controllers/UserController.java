package br.com.colaboradoresapi.controllers;

import br.com.colaboradoresapi.dto.ResponseDTO;
import br.com.colaboradoresapi.persistence.entities.User;
import br.com.colaboradoresapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(
        value = "/user",
        produces = "application/json",
        method = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path="/add")
    public @ResponseBody
    ResponseDTO<User> addNewUser (@RequestBody User user) {
        return userService.addNewUser(user);
    }

    @GetMapping(path="/list")
    public @ResponseBody ResponseDTO<Iterable<User>> getAllUsers() {
        return userService.getAllUsers();
    }
}