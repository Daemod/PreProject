package ru.severyuchin.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.severyuchin.model.Role;
import ru.severyuchin.model.User;

import java.util.List;
import java.util.Set;

public interface UserService extends UserDetailsService {

    public void save(User user);
    public List<User> getAllUsers();
    public Set<Role> getRoleByName(String role);
}
