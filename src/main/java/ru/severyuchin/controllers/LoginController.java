package ru.severyuchin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class LoginController {

    @RequestMapping(path = {"/", "/login"}, method = RequestMethod.GET)
    String getLoginPage() {
        return "loginPage";
    }


}
