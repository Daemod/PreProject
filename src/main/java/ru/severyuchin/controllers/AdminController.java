package ru.severyuchin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.severyuchin.model.Role;
import ru.severyuchin.model.User;
import ru.severyuchin.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin/**")
public class AdminController {
    @Autowired
    UserService service;

    @RequestMapping(method = RequestMethod.GET)
    String getAdminPage(Model model) {
        User user = new User();
        model.addAttribute("tempUser", user);
        model.addAttribute("users", service.getAllUsers());
        List<User> users = service.getAllUsers();
        Set<Role> roles = users.get(0).getRoles();

        return "adminPanel";
    }

    @RequestMapping(method = RequestMethod.POST)
    String editUser(@ModelAttribute User user,
                    @RequestParam String role,
                    Model model) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(service.getRoleByName(role.toUpperCase()));
        service.save(user);
        model.addAttribute("users", service.getAllUsers());
        return "adminPanel";
    }

    @RequestMapping(path = {"/admin/addUser"}, method = RequestMethod.POST)
    String addUser(@ModelAttribute User user,
                   @RequestParam String role,
                   Model model) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(service.getRoleByName(role.toUpperCase()));
        service.save(user);
        model.addAttribute("users", service.getAllUsers());
        return "adminPanel";
    }

    @RequestMapping(path = {"/user"}, method = RequestMethod.GET)
    String userPage(Model model){
        User user = (User) service.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("user", user);
        return "userPage";
    }
}
