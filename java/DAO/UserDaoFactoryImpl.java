package DAO;

import util.DBHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactoryImpl implements UserDaoFactory {
    private enum ConType {
        JDBC, HIBERNATE
    }

    private static UserDaoFactory factory;
    private static DBHelper dbHelper = DBHelper.getInstance();
    private static ConType typeConnection;


    private UserDaoFactoryImpl() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        Properties properties = new Properties();

        try {
            InputStream inputStream = loader.getResourceAsStream("config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (properties.getProperty("connector")) {
            case "JDBC":
                typeConnection = ConType.JDBC;
                break;
            case "Hibernate":
                typeConnection = ConType.HIBERNATE;
                break;
        }
    }


    public static UserDaoFactory getInstance(){
        if(factory == null){
            factory = new UserDaoFactoryImpl();
        }
        return factory;
    }

    public UserDao getUserDao() {
        if (typeConnection == ConType.JDBC) {
            return new UserDaoJDBCImpl(dbHelper.getConnection());
        } else if (typeConnection == ConType.HIBERNATE) {
            return new UserDaoHibernateImpl(dbHelper.getConfiguration());
        } else {
            System.out.println("Error config.properties in field \"connector\"");
            return null;
        }
    }


}
