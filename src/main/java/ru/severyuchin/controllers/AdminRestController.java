package ru.severyuchin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.severyuchin.model.User;
import ru.severyuchin.service.UserService;

import java.util.List;

@RestController
@RequestMapping("api")
public class AdminRestController {
    @Autowired
    UserService service;

    @RequestMapping(value = "/addUser",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public User addUser(@RequestBody User user){
        user.setRoles(service.getRoleByName(user.getRoles().iterator().next().getRole())); //todo remake
        service.save(user);
        return user;
    }

    @RequestMapping(value = "/openModalById/{id}", method = RequestMethod.GET, headers = {"Content-type=application/json"})
    public User getUser(@PathVariable("id") long id){
        return service.getUserById(id);
    }

    @RequestMapping(value = "/editUser",
        method = RequestMethod.POST,
        headers = {"Content-type=application/json"})
    @ResponseBody
    public User editUser(@RequestBody User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(service.getRoleByName(user.getRoles().iterator().next().getRole()));
        service.save(user);
        return user;
    }


    @RequestMapping(value = "/getUsers",
    method = RequestMethod.GET,
    headers = {"Content-type=application/json"})
    @ResponseBody
    public List<User> getUsers(){
        return service.getAllUsers();
    }


}
