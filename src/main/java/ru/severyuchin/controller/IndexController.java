package ru.severyuchin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.severyuchin.services.UserService;

import java.sql.SQLException;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    UserService service;

    @RequestMapping(method = RequestMethod.GET)
    String getPage(Model model) {
        try {
            model.addAttribute("users", service.getAllUsers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "admin";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    String addUser(@RequestParam(name = "name") String name,
                   @RequestParam(name = "password") String password,
                   @RequestParam(name = "work") String work,
                   @RequestParam(name = "age") int age,
                   Model model) throws SQLException {
        service.addUser(name, password, work, age);
        model.addAttribute("users", service.getAllUsers());
        return "admin";
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    String editUser(@RequestParam(name = "id") Long id,
                    @RequestParam(name = "name") String name,
                    @RequestParam(name = "work") String work,
                    @RequestParam(name = "age") int age,
                    Model model) throws SQLException {
        service.editUser(id, name, work, age);
        model.addAttribute("users", service.getAllUsers());
        return "admin";
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    String deleteUser(@RequestParam(name = "id") Long id,
                      Model model) throws SQLException {
        service.deleteUser(id);
        model.addAttribute("users", service.getAllUsers());
        return "admin";
    }

}
