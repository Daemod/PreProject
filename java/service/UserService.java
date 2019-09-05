package service;

import DAO.UserDao;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService service;

    private UserService() {
    }

    public static UserService getInstance() {
        if (service == null){
            service = new UserService();
            service.createTable();
        }
        return service;
    }

    public List<User> getAllUsers() throws SQLException {
        return new UserDao(getMysqlConnection()).getAllUsers();
    }

    public void addUser(String name, String work, int age) throws SQLException {
        new UserDao(getMysqlConnection()).addUser(name, work, age);
    }

    public void deleteUser(long id) throws SQLException {
        new UserDao(getMysqlConnection()).deleteUser(id);
    }

    public void editUser(long id, String name, String work, int age) throws SQLException {
        new UserDao(getMysqlConnection()).editUser(id, name, work, age);
    }

    private void createTable(){
        try {
            new UserDao(getMysqlConnection()).createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Connection getMysqlConnection() {
        try {

            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("db_example?").          //db name
                    append("user=root&").          //login
                    append("password=root");       //password

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

}
