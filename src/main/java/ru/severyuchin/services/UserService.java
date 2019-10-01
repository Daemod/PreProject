package ru.severyuchin.services;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.severyuchin.dao.UserDaoHibernateImpl;
import ru.severyuchin.entitys.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("jpaUserService")
@Transactional
public class UserService {
    @PersistenceContext
    private EntityManager entityManager;

    public UserService() {
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return new UserDaoHibernateImpl(entityManager).getAllUsers();
    }

    public void addUser(String name, String password, String work, int age) {
        new UserDaoHibernateImpl(entityManager).addUser(name,password,work,age);
    }

    public void deleteUser(long id) {
        new UserDaoHibernateImpl(entityManager).deleteUser(id);
    }

    public void editUser(long id, String name, String work, int age) {
        new UserDaoHibernateImpl(entityManager).editUser(id, name, work, age);
    }


}
