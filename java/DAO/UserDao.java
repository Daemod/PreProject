package DAO;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

public class UserDao {
    private Session session;

    public UserDao(Session session) {
        this.session = session;
    }

    public List<User> getAllUsers() throws SQLException {
        Query query = session.createQuery("FROM User", User.class);
        List<User> users = query.list();
        session.close();
        return users;
    }

    public void addUser(String name, String work, int age) throws SQLException {
        Transaction transaction = session.beginTransaction();
        User user = new User(name,work,age);
        session.save(user);
        transaction.commit();
        session.close();
    }

    public User getUserById(long id) throws SQLException {
        Query query = session.createQuery("FROM User user where user.id = :id");
        query.setParameter("id", id);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    public void editUser(long id, String name, String work, int age) throws SQLException {
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
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User where id = :id");
        query.setParameter("id", id);
        session.delete(query.uniqueResult());
        transaction.commit();
        session.close();
    }

    public void createTable() throws SQLException {

    }
}
