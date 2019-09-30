package ru.severyuchin.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.severyuchin.dao.UserDao;
import ru.severyuchin.entitys.User;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserService {
    private UserDao userDao;
    private static UserService service;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserService() {
    }

    public static UserService getInstance() {
        if (service == null) {
            service = new UserService();
        }
        return service;
    }

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }

    public void addUser(String name, String password, String work, int age) throws SQLException {
        userDao.addUser(name, password, work, age);
    }

    public void deleteUser(long id) throws SQLException {
        userDao.deleteUser(id);
    }

    public void editUser(long id, String name, String work, int age) throws SQLException {
        userDao.editUser(id, name, work, age);
    }

    public User getUserByName(String name) {
        try {
            return userDao.getUserByName(name);
        } catch (SQLException e) {
            return null;
        }
    }


}
