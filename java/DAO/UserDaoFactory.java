package DAO;

import util.DBHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public interface UserDaoFactory {
    public UserDao getUserDao();
}
