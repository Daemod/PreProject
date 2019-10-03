package ru.severyuchin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.severyuchin.model.User;
import ru.severyuchin.service.UserService;

@Controller
@RequestMapping("/")
public class IndexController {
    @Qualifier("jpaUserService")
    @Autowired
    private UserService service;

    @RequestMapping(method = RequestMethod.GET)
    String getPage(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "admin";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    String addUser(@RequestParam(name = "name") String name,
                   @RequestParam(name = "password") String password,
                   @RequestParam(name = "work") String work,
                   @RequestParam(name = "age") int age,
                   Model model) {
        service.addUser(new User(name,password,work,age));
        model.addAttribute("users", service.getAllUsers());
        return "admin";
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    String editUser(@RequestParam(name = "id") Long id,
                    @RequestParam(name = "name") String name,
                    @RequestParam(name = "password") String password,
                    @RequestParam(name = "work") String work,
                    @RequestParam(name = "age") int age,
                    Model model){
        service.editUser(new User(id,name,password,work,age));
        model.addAttribute("users", service.getAllUsers());
        return "admin";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    String deleteUser(@RequestParam(name = "id") Long id,
                      Model model){
        service.deleteUser(id);
        model.addAttribute("users", service.getAllUsers());
        return "admin";
    }

}
