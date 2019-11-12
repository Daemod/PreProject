package ru.severyuchin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.severyuchin.model.User;
import ru.severyuchin.service.UserService;

@Controller
@RequestMapping("/user**")
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping(method = RequestMethod.GET)
    String userPage(Model model) {
        User user = (User) service.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "userPage";
    }


}
