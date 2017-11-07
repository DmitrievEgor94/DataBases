package com.mycompany.rdbapplication;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DataSource {

    private static final Logger log = Logger.getLogger(ApplicationStarter.class);

    private static final String URL = "jdbc:postgresql://localhost:5433/publishers";
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "1";

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }

    static void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException e) {
           log.error(e);
        }
    }
}