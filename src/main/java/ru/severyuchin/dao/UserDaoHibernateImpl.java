package ru.severyuchin.dao;


import org.springframework.stereotype.Service;
import ru.severyuchin.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public User getUserByName(String name) {
        /*return (User) entityManager.createQuery("select user from User user where name = :name")
                .setParameter("name", name)
                .getSingleResult();
         */
        Query query = entityManager.createQuery("from User user where name = :name", User.class);
        query.setParameter("name", name);
        try {
            return (User) query.getResultList().get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = entityManager.createQuery("from User user where id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
        entityManager.remove(user);
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }
}
