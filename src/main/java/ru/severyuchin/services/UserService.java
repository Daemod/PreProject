package ru.severyuchin.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.severyuchin.dao.UserDao;
import ru.severyuchin.dao.UserDaoHibernateImpl;
import ru.severyuchin.entitys.User;

import java.util.List;

@Service("jpaUserService")
@Transactional
public class UserService {

    @Autowired
    UserDaoHibernateImpl userDao;

    public UserService() {
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public void deleteUser(long id) {
        userDao.deleteUser(id);
    }

    public void editUser(User user) {
        userDao.editUser(user);
    }


}
