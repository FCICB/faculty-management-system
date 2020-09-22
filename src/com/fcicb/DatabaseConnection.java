//Faculty Management System
//Class responsible for connecting to the database
//22-September-2020


package FMS;

import java.sql.*;


public class DatabaseConnection {

    private static volatile DatabaseConnection instance;
    private static String dbDriver;
    private static String userName;
    private static String password;
    private static String url;
    private static Connection connection;

    //default constructor accessed only by method getInstance()
    private DatabaseConnection(){
        dbDriver = "com.mysql.cj.jdbc.Driver";
        userName = "root"; //insert your username here
        password = "123456"; //insert your password here
        url = "jdbc:mysql://localhost:3306/fms";
        try {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //To get the only DatabaseConnection object existing in the system or create a new one
    public static DatabaseConnection getInstance(){
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
