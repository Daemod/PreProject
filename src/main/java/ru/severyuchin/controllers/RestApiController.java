package ru.severyuchin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.severyuchin.model.User;
import ru.severyuchin.service.RestTemplateService;

import java.util.List;


@RestController
@RequestMapping("api")
public class RestApiController {
    @Autowired
    RestTemplateService service;

    @RequestMapping(value = "/openModalById/{id}", method = RequestMethod.GET, headers = {"Content-type=application/json"})
    @ResponseBody
    public User getUser(@PathVariable("id") long id) {
        return service.getUserById(id);
    }

    @RequestMapping(value = "/editUser",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    public void editUser(@RequestBody User user) {
        service.save(user);
    }

    @RequestMapping(value = "/getUsers",
            method = RequestMethod.GET,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public List<User> getUsers() {
        return service.getUsers();
    }

    @RequestMapping(value = "/getUser",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public User getUser(@RequestBody User user){
        return (User) service.loadUserByUsername(user.getName());
    }

    @RequestMapping(value = "/addUser",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    public void addUser(@RequestBody User user){
        service.save(user);
    }



}
