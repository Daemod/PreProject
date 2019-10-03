package ru.severyuchin.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.severyuchin.entitys.User;

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
        return entityManager.createNamedQuery(User.GET_ALL_USER, User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public void deleteUser(long id) {
        User user = entityManager.createNamedQuery(User.GET_USER_BY_ID, User.class).setParameter("id", id).getSingleResult();
        entityManager.remove(user);
    }

    @Override
    public void editUser(User user) {
        User oldUser = entityManager.createNamedQuery(User.GET_USER_BY_ID, User.class).setParameter("id", user.getId()).getSingleResult();
        oldUser.setAge(user.getAge());
        oldUser.setName(user.getName());
        oldUser.setWork(user.getWork());
        entityManager.merge(oldUser);
    }
}
