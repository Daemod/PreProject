package ru.severyuchin.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.severyuchin.entitys.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import java.sql.SQLException;
import java.util.List;

@Transactional
@Repository("userDao")
public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionFactory;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserDaoHibernateImpl() {

    }

    public List<User> getAllUsers() throws SQLException {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User", User.class);
        List<User> users = query.list();
        session.close();
        return users;
    }

    public void addUser(String name, String password, String work, int age) throws SQLException {
        User user = new User(name, password, work, age);
        Session session = sessionFactory.openSession();
        session.save(user);
        session.close();
    }

    public User getUserById(long id) throws SQLException {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User user where user.id = :id");
        query.setParameter("id", id);
        User user = (User) query.uniqueResult();
        return user;
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User user where user.name = :name");
        query.setParameter("name", name);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    public void editUser(long id, String name, String work, int age) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("update User set name = :name, " +
                "work = :work, " +
                "age = :age where id = :id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.setParameter("work", work);
        query.setParameter("age", age);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public void deleteUser(long id) throws SQLException {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where id = :id");
        query.setParameter("id", id);
        User user = (User) query.uniqueResult();
        session.delete(user);
        transaction.commit();
        session.close();
    }

}
