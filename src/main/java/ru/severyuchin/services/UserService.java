package ru.severyuchin.services;


import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.severyuchin.entitys.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("jpaUserService")
@Repository
@Transactional
public class UserService {
    @PersistenceContext
    private EntityManager entityManager;

    public UserService() {
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return entityManager.createNamedQuery(User.GET_ALL_USER, User.class).getResultList();
    }

    public void addUser(String name, String password, String work, int age) {
        User user = new User(name, password, work, age);
        entityManager.persist(user);
    }

    public void deleteUser(long id) {
        User user = entityManager.createNamedQuery(User.GET_USER_BY_ID, User.class).setParameter("id", id).getSingleResult();
        entityManager.remove(user);
    }

    public void editUser(long id, String name, String work, int age) {
        User user = entityManager.createNamedQuery(User.GET_USER_BY_ID, User.class).setParameter("id",id).getSingleResult();
        user.setName(name);
        user.setWork(work);
        user.setAge(age);
        entityManager.merge(user);
    }


}
