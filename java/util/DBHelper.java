package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static DBHelper dbHelper;
    private static Connection connection;

    private DBHelper(){
        connection = getMysqlConnection();
    }

    public static Connection getConnection() {
        return connection;
    }

    public static DBHelper getDBHelper(){
        if(dbHelper == null){
            dbHelper = new DBHelper();
        }
        return dbHelper;
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
