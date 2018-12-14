package com.company.employee;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class ConnectDatabase {

    private static final Logger LOGGER = LogManager.getLogger(ConnectDatabase.class);
    private final String tabSpaces = "\t\t\t\t\t\t";

    public ConnectDatabase(String username, String password) {
        try {
            Scanner userInput = new Scanner(System.in);

            String dbName;
            LOGGER.info(tabSpaces + "Enter Database Name : ");
            dbName = userInput.nextLine();

            if (StringUtils.isBlank(dbName)) {
                LOGGER.info(tabSpaces + "Database name must not be empty or null.");
            } else {
                displayMenu(username, password, dbName);
            }
        } catch (Exception e) {

        }
    }

    private void displayMenu(String username, String password, String dbName) throws SQLException {
        Scanner userInput = new Scanner(System.in);
        String readMenu;

        do {
            // Display menu graphics
            LOGGER.info(tabSpaces + "*****************************************");
            LOGGER.info(tabSpaces + "|       Employee Management System      |");
            LOGGER.info(tabSpaces + "*****************************************");
            LOGGER.info(tabSpaces + "| Options:                              |");
            LOGGER.info(tabSpaces + "|        1. Create New Employee.        |");
            LOGGER.info(tabSpaces + "|        2. All Employees Details.      |");
            LOGGER.info(tabSpaces + "|        3. Update Employee Details     |");
            LOGGER.info(tabSpaces + "|        4. Delete Employee Detail.     |");
            LOGGER.info(tabSpaces + "|        5. Execute Query.              |");
            LOGGER.info(tabSpaces + "|        6. Exit.                       |");
            LOGGER.info(tabSpaces + "*****************************************");

            LOGGER.info("\n");
            LOGGER.info(tabSpaces + "Select option: ");

            readMenu = userInput.nextLine();
            LOGGER.info("\n");

//display menu based on user selection
            switch (readMenu) {
                case "1":
                    Create create = new Create(username, password, dbName);
                    break;
                case "2":
                    Read read = new Read(username, password, dbName);
                    break;
                case "3":
                    Update update = new Update(username, password, dbName);
                    break;
                case "4":
                    Delete delete = new Delete(username, password, dbName);
                    break;
                case "5":
                    Execute execute = new Execute(username, password, dbName);
                    break;
                case "6":
                    LOGGER.info("System exiting");
                    System.exit(0);
                    break;
                default:
                    LOGGER.info(tabSpaces + "Invalid selection");
                    break;
            }
        } while (!readMenu.equals("6"));
    }
}
