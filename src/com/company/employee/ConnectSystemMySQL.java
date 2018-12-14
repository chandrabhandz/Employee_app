package com.company.employee;

import com.company.employee.dbUtils.DBUtilities;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.Scanner;

public class ConnectSystemMySQL {

    private static final Logger LOGGER = Logger.getLogger(ConnectSystemMySQL.class);

    public ConnectSystemMySQL(String username, String password) throws SQLException {
        Scanner userInput = new Scanner(System.in);

        String query;
        LOGGER.info("\t\t\t\t\t\tEnter query to execute : ");
        query = userInput.nextLine();

        if (StringUtils.isNotBlank(query)) {
            DBUtilities dbUtilities = new DBUtilities(username, password);
            dbUtilities.executeSQLStatement(query);
        }
    }
}

