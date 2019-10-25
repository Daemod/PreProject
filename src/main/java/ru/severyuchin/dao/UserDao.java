package ru.severyuchin.dao;

import ru.severyuchin.model.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();

    public User getUserByName(String name);

    public void addUser(User user);

    public void deleteUser(long id);

    public void editUser(User user);
}
