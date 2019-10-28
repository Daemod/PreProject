package ru.severyuchin.dao;

import org.springframework.data.repository.CrudRepository;
import ru.severyuchin.model.User;

public interface UserRepo extends CrudRepository<User,Long> {
    public User getUserByName(String name);
}
