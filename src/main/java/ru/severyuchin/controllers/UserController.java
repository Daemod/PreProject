package ru.severyuchin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(method = RequestMethod.GET)
    String getUserPage(@CookieValue(value = "name") Cookie cookie, Model model){
        model.addAttribute("name", cookie.getValue());
        return "userPage";
    }
}
