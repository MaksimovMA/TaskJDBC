package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String LOCALHOST = "localhost";
    private static final String DB_NAME = "Test";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "12345678";

    // Connect to MySQL
    public Connection getMySQLConnection() throws SQLException {
        return getMySQLConnection(LOCALHOST, DB_NAME, USER_NAME, PASSWORD);
    }

    public Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException {
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?serverTimezone=Europe/Moscow&useSSL=false";
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }
}
