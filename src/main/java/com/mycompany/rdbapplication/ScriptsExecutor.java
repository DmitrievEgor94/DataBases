package com.mycompany.rdbapplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;

class ScriptsExecutor {
    static void execute(Statement statement, String scriptFile) throws IOException, SQLException {

        try (BufferedReader in = new BufferedReader(new FileReader(scriptFile))) {
            String str;
            StringBuilder sb = new StringBuilder();
            while ((str = in.readLine()) != null) {
                sb.append(str).append("\n ");
            }
            in.close();
            statement.executeUpdate(sb.toString());
        }
    }
}
