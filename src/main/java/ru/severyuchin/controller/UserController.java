package ru.severyuchin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.severyuchin.model.User;
import ru.severyuchin.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;
    @RequestMapping(method = RequestMethod.GET)
    String getUserPage(@RequestParam(name = "login") String login,
            Model model){
        model.addAttribute("user", (User) service.loadUserByUsername(login));
        return "userPage";
    }

}
