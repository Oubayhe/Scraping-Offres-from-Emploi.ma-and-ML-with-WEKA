// Customize the variables overhere based on your Database

package system;

import java.sql.*;

public class ConnectionManager {
    private static final String PROTOCOL = "jdbc:mysql:";
    private static final String IP = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "scrapingdb"; 
    private static final String USERNAME = "root";
    private static final String PASSWORD = "admin";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionString = PROTOCOL + "//" + IP + ":" + PORT + "/" + DATABASE;
            return DriverManager.getConnection(connectionString, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Database driver not found.", e);
        }
    }
}
