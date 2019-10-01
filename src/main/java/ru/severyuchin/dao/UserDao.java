package ru.severyuchin.dao;

import ru.severyuchin.entitys.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();

    public void addUser(String name, String password, String work, int age);

    public void deleteUser(long id);


    public void editUser(long id, String name, String work, int age);
}
