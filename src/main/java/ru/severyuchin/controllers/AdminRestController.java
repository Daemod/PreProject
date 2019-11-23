package ru.severyuchin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.severyuchin.model.Role;
import ru.severyuchin.model.User;
import ru.severyuchin.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api")
public class AdminRestController {
    @Autowired
    UserService service;

    @RequestMapping(value = "/addUser",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    public void addUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(service.getRoleByName(user.getRoles().iterator().next().getRole())); //todo remake
        service.save(user);
    }

    @RequestMapping(value = "/openModalById/{id}", method = RequestMethod.GET, headers = {"Content-type=application/json"})
    @ResponseBody
    public User getUser(@PathVariable("id") long id) {
        return service.getUserById(id);
    }

    @RequestMapping(value = "/editUser",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public User editUser(@RequestBody User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(service.getRoleByName(user.getRoles().iterator().next().getRole()));
        service.save(user);
        return user;
    }


    @RequestMapping(value = "/getUsers",
            method = RequestMethod.GET,
            headers = {"Content-type=application/json"})
    @ResponseBody
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    @RequestMapping(value = "/deleteUserById/{id}",
            method = RequestMethod.POST,
            headers = {"Content-type=application/json"})
    public String deleteUser(@PathVariable("id") long id) {
        boolean haveUser = service.getUserById(id) != null;
        if (haveUser) {
            service.deleteUserById(id);
            return "true";
        }else {
            return "false";
        }
    }

    @RequestMapping(value = "/getUser",
    method = RequestMethod.POST,
    headers = {"Content-type=application/json"})
    @ResponseBody
    public User getUser(@RequestBody User user){
        return (User) service.loadUserByUsername(user.getName());
    }

    @RequestMapping(value = "/getUserByName/{name}", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseBody
    public User loginUser(@PathVariable String name) {
        User user = (User) service.loadUserByUsername(name);
        return user;
    }

    @RequestMapping(value = "/getRoleByName/{name}", method = RequestMethod.POST, headers = {"Content-type=application/json"})
    @ResponseBody
    public Set<Role> getRoles(@PathVariable String name) {
        Set<Role> roles = service.getRoleByName(name);
        return roles;
    }


}
