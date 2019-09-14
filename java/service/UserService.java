package service;

import DAO.UserDaoFactory;
import DAO.UserDaoFactoryImpl;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private static UserService service;
    private UserDaoFactory factory;

    private UserService() {
        factory = UserDaoFactoryImpl.getInstance();
    }

    public static UserService getInstance() {
        if (service == null){
            service = new UserService();
        }
        return service;
    }

    public List<User> getAllUsers() throws SQLException {
        return factory.getUserDao().getAllUsers();
    }

    public void addUser(String name, String work, int age) throws SQLException {
        factory.getUserDao().addUser(name, work, age);
    }

    public void deleteUser(long id) throws SQLException {
        factory.getUserDao().deleteUser(id);
    }

    public void editUser(long id, String name, String work, int age) throws SQLException {
        factory.getUserDao().editUser(id, name, work, age);
    }

    public User getUserByName(String name) {
        try {
            return factory.getUserDao().getUserByName(name);
        } catch (SQLException e) {
            return null;
        }
    }


}
