package com.dao.log.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DatabaseUtil {
    private static final String CONNECTION = "jdbc:mysql://localhost:3306/modular?autoReconnect=true&useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private DatabaseUtil() {
        throw new UnsupportedOperationException("Util class");
    }

    public static Connection getConnection() {
        getClassDriver();
        try (Connection connection = DriverManager.getConnection(CONNECTION, USER, PASSWORD)) {
            System.out.println("driver manager" + DriverManager.getConnection(CONNECTION, USER, PASSWORD));
            return connection;
        } catch (SQLException e) {
            System.err.println("Get connection method: " + e.getMessage());
            return null;
        }
    }

    private static void getClassDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Get class driver method: " + e.getMessage());
        }
    }
}
