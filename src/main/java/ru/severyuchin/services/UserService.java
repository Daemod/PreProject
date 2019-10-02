package ru.severyuchin.services;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.severyuchin.dao.UserDaoHibernateImpl;
import ru.severyuchin.entitys.User;

import java.util.List;

@Service("jpaUserService")
@Transactional
public class UserService {


    public UserService() {
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return new UserDaoHibernateImpl().getAllUsers();
    }

    public void addUser(String name, String password, String work, int age) {
        new UserDaoHibernateImpl().addUser(name,password,work,age);
    }

    public void deleteUser(long id) {
        new UserDaoHibernateImpl().deleteUser(id);
    }

    public void editUser(long id, String name, String work, int age) {
        new UserDaoHibernateImpl().editUser(id, name, work, age);
    }


}
