package ru.severyuchin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(path = {"/","/login"},method = RequestMethod.GET)
    String getIndexPage(Model model){
        return "loginPage";
    }



}
