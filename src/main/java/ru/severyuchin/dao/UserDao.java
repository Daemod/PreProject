package ru.severyuchin.dao;

import ru.severyuchin.entitys.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();

    public void addUser(User user);

    public void deleteUser(long id);

    public void editUser(User user);
}
