package ru.severyuchin.dao;


import org.springframework.stereotype.Service;
import ru.severyuchin.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("userDao")
public class UserDaoHibernateImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public UserDaoHibernateImpl() {
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = entityManager.createQuery("select user from User user where id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.remove(user);
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }
}
