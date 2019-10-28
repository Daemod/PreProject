package ru.severyuchin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.severyuchin.model.User;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = RequestMethod.GET)
    String getLoginPage(Model model){
        model.addAttribute("user", new User());
        return "loginPage";
    }



}
