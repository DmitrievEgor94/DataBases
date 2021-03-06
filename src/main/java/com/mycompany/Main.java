package com.mycompany;

import com.mycompany.rdbapplication.ApplicationStarter;

public class Main {

    private final static String CREATION_FLAG = "-cr";

    public static void main(String[] args) {

        boolean createTables = false;

        if (args.length == 1) {
            if (CREATION_FLAG.equals(args[0].toLowerCase())) {
                createTables = true;
            }
        }

        ApplicationStarter.startApplication(createTables);
    }
}
