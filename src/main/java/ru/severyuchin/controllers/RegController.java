package ru.severyuchin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.severyuchin.model.Role;
import ru.severyuchin.model.User;
import ru.severyuchin.service.UserServiceImpl;

import java.util.HashSet;
import java.util.Set;


@Controller
@RequestMapping("/reg")
public class RegController {

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(method = RequestMethod.GET)
    String getRegPage(Model model){
        model.addAttribute("user", new User());
        return "regPage";
    }

    @RequestMapping(method = RequestMethod.POST)
    String regUser(@ModelAttribute User user){
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles(userService.getRoleByName("USER"));
        userService.save(user);
        return "redirect:/login";
    }
}
