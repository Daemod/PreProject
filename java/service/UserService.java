package service;

import DAO.UserDao;
import model.User;
import util.DBHelper;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService service;
    private Connection connection;

    private UserService() {
        connection = DBHelper.getConnection();
    }

    public static UserService getInstance() {
        if (service == null){
            service = new UserService();
            service.createTable();
        }
        return service;
    }

    public List<User> getAllUsers() throws SQLException {
        return new UserDao(connection).getAllUsers();
    }

    public void addUser(String name, String work, int age) throws SQLException {
        new UserDao(connection).addUser(name, work, age);
    }

    public void deleteUser(long id) throws SQLException {
        new UserDao(connection).deleteUser(id);
    }

    public void editUser(long id, String name, String work, int age) throws SQLException {
        new UserDao(connection).editUser(id, name, work, age);
    }

    private void createTable(){
        try {
            new UserDao(connection).createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
