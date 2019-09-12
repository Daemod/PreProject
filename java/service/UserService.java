package service;

import DAO.UserDaoFactory;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService service;

    private UserService() {

    }

    public static UserService getInstance() {
        if (service == null){
            service = new UserService();
        }
        return service;
    }

    public List<User> getAllUsers() throws SQLException {
        return UserDaoFactory.getUserDao().getAllUsers();
    }

    public void addUser(String name, String work, int age) throws SQLException {
        UserDaoFactory.getUserDao().addUser(name, work, age);
    }

    public void deleteUser(long id) throws SQLException {
        UserDaoFactory.getUserDao().deleteUser(id);
    }

    public void editUser(long id, String name, String work, int age) throws SQLException {
        UserDaoFactory.getUserDao().editUser(id, name, work, age);
    }


}
