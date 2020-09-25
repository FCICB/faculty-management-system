//Faculty Management System
//Class responsible for connecting to the database
//22-September-2020


package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class DatabaseConnection {

    private static volatile DatabaseConnection instance;
    private static String dbDriver;
    private static String userName;
    private static String password;
    private static String url;
    private static Connection connection;

    //default constructor accessed only by method getInstance()
    private DatabaseConnection() {

        try (FileInputStream configFile = new FileInputStream("resources/config.properties"))
        {
            // Load the properties file
            Properties prop=new Properties();
            prop.load(configFile);

            // reading database connection info from properties file
            dbDriver = prop.getProperty("dbDriver");
            userName = prop.getProperty("userName");
            password = prop.getProperty("password");
            url = prop.getProperty("url");

           
            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    //To get the only DatabaseConnection object existing in the system or create a new one
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            //This is to check first if a new object will be created, in that case it will perform synchronization
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    //To keep a serialized object a singleton after performing deserialization
    private Object readResolve(){
        return instance;
    }
}
