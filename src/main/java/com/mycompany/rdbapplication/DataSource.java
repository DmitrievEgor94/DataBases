package com.mycompany.rdbapplication;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DataSource {

    private static final Logger log = Logger.getLogger(ApplicationStarter.class);

    private static final String DB_NAME = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5433/" + DB_NAME;
    private static final String LOGIN = "postgres";
    private static final String PASSWORD = "1";

    static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, LOGIN, PASSWORD);
    }

}