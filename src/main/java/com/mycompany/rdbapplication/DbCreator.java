package com.mycompany.rdbapplication;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class DbCreator {

    private static final String URL_DEFAULT = "jdbc:postgresql://localhost:5433/postgres";
    private static final String LOGIN_DEFAULT = "postgres";
    private static final String PASSWORD_DEFAULT = "1";

    private static final Logger log = Logger.getLogger(DbCreator.class);

    static Connection createDb() throws SQLException, IOException {

        Connection connection = DriverManager.getConnection(URL_DEFAULT, LOGIN_DEFAULT, PASSWORD_DEFAULT);

        String dropDB = "DROP DATABASE if exists publishers";
        String createDB = "CREATE DATABASE publishers";

        Statement statement = connection.createStatement();

        statement.execute(dropDB);
        statement.execute(createDB);
        connection.close();

        connection = DataSource.getConnection();

        statement = connection.createStatement();

        String sqlScript = ApplicationStarter.class.getResource("tablesCreator.sql").getPath();

        ScriptsExecutor.execute(statement, sqlScript);

        return connection;
    }
}
