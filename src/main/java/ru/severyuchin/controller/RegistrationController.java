package ru.severyuchin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.severyuchin.model.User;
import ru.severyuchin.service.UserService;


@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    String getRegPage() {
        return "registerPage";
    }

    @RequestMapping(method = RequestMethod.POST)
    String regNewUser(@RequestParam String login,
                      @RequestParam String password,
                      @RequestParam String work,
                      @RequestParam int age) {
        String encode = new BCryptPasswordEncoder().encode(password);
        User user = new User(login, encode, work, age);
        user.addRole("USER");
        userService.addUser(user);
        return "index";
    }
}
