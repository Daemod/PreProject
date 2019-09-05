package DAO;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public List<User> getAllUsers() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select * from users");
        List<User> users = new ArrayList<>();
        ResultSet set = statement.getResultSet();
        while (set.next()) {
            users.add(new User(set.getLong("id"),
                    set.getString("name"),
                    set.getString("work"),
                    set.getInt("age")));
        }
        set.close();
        statement.close();
        return users;
    }

    public void addUser(String name, String work, int age) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("insert into users (name,work,age) values (?, ?, ?)");
        statement.setString(1, name);
        statement.setString(2, work);
        statement.setInt(3, age);
        statement.execute();
        statement.close();
    }

    public User getUserById(long id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select * form users where id='" + id + "'");
        ResultSet set = statement.getResultSet();
        if (set.next()) {
            return new User(set.getLong("id"),
                    set.getString("name"),
                    set.getString("work"),
                    set.getInt("age"));
        }
        return null;
    }

    public void editUser(long id, String name, String work, int age) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("update users set name ='" + name + "', " +
                "work = '" + work + "', " +
                "age = '" + age + "' " +
                "where id = '" + id + "'");
        statement.close();
    }

    public void deleteUser(long id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("delete from users where id = '" + id + "'");
        statement.close();
    }

    public void createTable() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("create table if not exists users (id bigint auto_increment, name varchar(256), work varchar(256), age int, primary key (id))");
        statement.close();
    }
}
