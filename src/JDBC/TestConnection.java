package JDBC;

import java.sql.*;

public class TestConnection {
    public static void main(String[] args) throws SQLException {

        DatabaseConnection singleton1 = DatabaseConnection.getInstance();

        //Sample Query to test connection
        final String SELECT_QUERY =
                "SELECT lname, fname, student_id FROM Student WHERE student_id=3";
        try (Connection conn = singleton1.getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_QUERY)){
            ResultSetMetaData metaData = resultSet.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            while (resultSet.next()){
                for (int i = 1; i <= numberOfColumns; i++) {
                    System.out.printf("%-8s\t", resultSet.getObject(i));
                }
            }
        }

        //Singleton Testing
        DatabaseConnection singleton3 = DatabaseConnection.getInstance();
        DatabaseConnection singleton2 = DatabaseConnection.getInstance();
        if (singleton3.equals(singleton2)) {
            System.out.println("The Two objects are the same");
        } else {
            System.out.println("The Two objects are not the same");
        }

    }
}
