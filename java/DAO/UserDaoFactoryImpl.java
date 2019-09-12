package DAO;

import util.DBHelper;
import util.PropertyReader;


public class UserDaoFactoryImpl implements UserDaoFactory {
    private PropertyReader propertyReader;

    private static UserDaoFactory factory;
    private static DBHelper dbHelper = DBHelper.getInstance();


    private UserDaoFactoryImpl() {
        propertyReader = new PropertyReader();
    }


    public static UserDaoFactory getInstance(){
        if(factory == null){
            factory = new UserDaoFactoryImpl();
        }
        return factory;
    }

    public UserDao getUserDao() {
        switch (propertyReader.getProperty()){
            case "JDBC": return new UserDaoJDBCImpl(dbHelper.getConnection());
            case "Hibernate": return new UserDaoHibernateImpl(dbHelper.getConfiguration());
            default: System.out.println("Configuration error"); return null;
        }
    }


}
