package service;

import DAO.HibernateUserDao;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService service;
    private SessionFactory factory;

    private UserService(SessionFactory factory) {
        this.factory = factory;
    }

    public static UserService getInstance() {
        if (service == null){
            service = new UserService(DBHelper.getSessionFactory());
        }
        return service;
    }

    public List<User> getAllUsers() throws SQLException {
        return new HibernateUserDao(factory.openSession()).getAllUsers();
    }

    public void addUser(String name, String work, int age) throws SQLException {
        new HibernateUserDao(factory.openSession()).addUser(name, work, age);
    }

    public void deleteUser(long id) throws SQLException {
        new HibernateUserDao(factory.openSession()).deleteUser(id);
    }

    public void editUser(long id, String name, String work, int age) throws SQLException {
        new HibernateUserDao(factory.openSession()).editUser(id, name, work, age);
    }

    private void createTable(){
        try {
            new HibernateUserDao(factory.openSession()).createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
