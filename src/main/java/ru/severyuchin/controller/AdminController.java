package ru.severyuchin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.severyuchin.model.User;
import ru.severyuchin.service.UserService;

import java.util.Collection;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    String getPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    String addUser(@RequestParam(name = "name") String name,
                   @RequestParam(name = "password") String password,
                   @RequestParam(name = "work") String work,
                   @RequestParam(name = "age") int age,
                   @RequestParam(name = "roles") Collection roles,
                   Model model) {
        userService.addUser(new User(name,password,work,age));
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    String editUser(@RequestParam(name = "id") Long id,
                    @RequestParam(name = "name") String name,
                    @RequestParam(name = "password") String password,
                    @RequestParam(name = "work") String work,
                    @RequestParam(name = "age") int age,
                    Model model){
        userService.editUser(new User(id,name,password,work,age));
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    String deleteUser(@RequestParam(name = "id") Long id,
                      Model model){
        userService.deleteUser(id);
        model.addAttribute("users", userService.getAllUsers());
        return "admin";
    }

}
