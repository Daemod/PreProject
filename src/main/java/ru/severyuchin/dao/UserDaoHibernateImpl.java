package ru.severyuchin.dao;

import ru.severyuchin.entitys.User;

import javax.persistence.EntityManager;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private EntityManager entityManager;

    public UserDaoHibernateImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        return entityManager.createNamedQuery(User.GET_ALL_USER, User.class).getResultList();
    }

    @Override
    public void addUser(String name, String password, String work, int age) {
        User user = new User(name, password, work, age);
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = entityManager.createNamedQuery(User.GET_USER_BY_ID, User.class).setParameter("id", id).getSingleResult();
        entityManager.remove(user);
    }

    @Override
    public void editUser(long id, String name, String work, int age) {
        User user = entityManager.createNamedQuery(User.GET_USER_BY_ID, User.class).setParameter("id", id).getSingleResult();
        user.setName(name);
        user.setWork(work);
        user.setAge(age);
        entityManager.merge(user);
    }
}
