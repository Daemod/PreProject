package util;

import DAO.UserDaoFactoryImpl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.util.Properties;

public class PropertyReader {
    private String connector;

    public PropertyReader() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try {
            InputStream inputStream = loader.getResourceAsStream("config.properties");
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        connector = properties.getProperty("connector");
    }

    public String getProperty() {
        return connector;
    }
}
