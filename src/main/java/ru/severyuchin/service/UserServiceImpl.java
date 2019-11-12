package ru.severyuchin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.severyuchin.dao.RoleRepo;
import ru.severyuchin.dao.UserRepo;
import ru.severyuchin.model.Role;
import ru.severyuchin.model.User;

import java.util.List;
import java.util.Set;


@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleRepo roleRepo;

    public UserServiceImpl(){

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepo.getUserByName(s);
    }

    public Set<Role> getRoleByName(String role){
        return roleRepo.findRoleByRole(role);
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepo.findAll();
    }

}
