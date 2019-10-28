package ru.severyuchin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.severyuchin.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService service;

    @RequestMapping(method = RequestMethod.GET)
    String getAdminPage(Model model){
        model.addAttribute("users", service.getAllUsers());
        return "adminPanel";
    }
}
