package com.company.employee;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        BasicConfigurator.configure();
        new Main().displayMenu();
    }

    private void displayMenu() throws SQLException {
        Scanner userInput = new Scanner(System.in);
        String readMenu;

        String username;
        LOGGER.info("\t\t\t\t\t\t Enter Username for Database : ");
        username = userInput.nextLine();

        String password;
        LOGGER.info("\t\t\t\t\t\t Enter Password : ");
        password = userInput.nextLine();

        do {
            // Display menu graphics
            LOGGER.info("\t\t\t\t\t\t*******************************************");
            LOGGER.info("\t\t\t\t\t\t|       MySQL Management System           |");
            LOGGER.info("\t\t\t\t\t\t*******************************************");
            LOGGER.info("\t\t\t\t\t\t| Options:                                |");
            LOGGER.info("\t\t\t\t\t\t|        1. Connect With System MySql.    |");
            LOGGER.info("\t\t\t\t\t\t|        2. Connect With Database.        |");
            LOGGER.info("\t\t\t\t\t\t|        3. Exit.                         |");
            LOGGER.info("\t\t\t\t\t\t*******************************************");

            LOGGER.info("\n");

            LOGGER.info("\t\t\t\t\t\tSelect option: ");
            readMenu = userInput.nextLine();

            LOGGER.info("\n");

//display menu based on user selection
            switch (readMenu) {
                case "1":
                    new ConnectSystemMySQL(username, password);
                    break;
                case "2":
                    new ConnectDatabase(username, password);
                    break;
                case "3":
                    LOGGER.info("System exiting");
                    System.exit(0);
                    break;
                default:
                    LOGGER.info("\t\t\t\t\t\tInvalid selection");
                    break;
            }
        } while (!readMenu.equals("3"));
    }
}