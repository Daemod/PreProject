package DAO;

import util.DBHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class UserDaoFactory {
    private enum ConType {
        JDBC, HIBERNATE
    }

    private static DBHelper dbHelper = DBHelper.getInstance();
    private static ConType typeConnection;


    public UserDaoFactory() {
    }

    public static UserDao getUserDao() {
        System.out.println("Init");
        init();
        System.out.println("Check: " + typeConnection.toString());
        if (typeConnection == ConType.JDBC) {
            return new UserDaoJDBCimpl(dbHelper.getConnection());
        } else if (typeConnection == ConType.HIBERNATE) {
            return new UserDaoHibernateImpl(dbHelper.getConfiguration());
        } else {
            System.out.println("Error config.properties in field \"connector\"");
            return null;
        }
    }

    private static void init() {
        if (typeConnection == null) {
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
    }


}
