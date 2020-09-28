package jdbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class DatabaseConnection {

    private static volatile DatabaseConnection instance;
    private static Connection connection;

    private DatabaseConnection() {

        try (FileInputStream configFile = new FileInputStream("resources/config.properties")) {
            Properties prop = new Properties();
            prop.load(configFile);

            String dbDriver = prop.getProperty("dbDriver");
            String userName = prop.getProperty("userName");
            String password = prop.getProperty("password");
            String url = prop.getProperty("url");

            connection = DriverManager.getConnection(url, userName, password);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
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

}
