package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    public static Connection getMySQLConnection() throws SQLException {
        String hostName = "localhost";
        String dbName = "javalearn";
        String userName = "mysql";
        String password = "mysql";
        return getMySQLConnection(hostName, dbName, userName, password);
    }
    private static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException {
        String conUrl = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        return DriverManager.getConnection(conUrl, userName, password);
    }
}
