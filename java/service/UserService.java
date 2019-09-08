package service;

import DAO.UserDao;
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
        return new UserDao(factory.openSession()).getAllUsers();
    }

    public void addUser(String name, String work, int age) throws SQLException {
        new UserDao(factory.openSession()).addUser(name, work, age);
    }

    public void deleteUser(long id) throws SQLException {
        new UserDao(factory.openSession()).deleteUser(id);
    }

    public void editUser(long id, String name, String work, int age) throws SQLException {
        new UserDao(factory.openSession()).editUser(id, name, work, age);
    }

    private void createTable(){
        try {
            new UserDao(factory.openSession()).createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
