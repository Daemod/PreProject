package ru.severyuchin.dao;

import ru.severyuchin.entitys.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public void addUser(String name, String password, String work, int age) throws SQLException;
    public void editUser(long id, String name, String work, int age) throws SQLException;
    public void deleteUser(long id) throws SQLException;
    public User getUserById(long id) throws SQLException;
    public User getUserByName(String name) throws SQLException;
    public List<User> getAllUsers() throws SQLException;
}
