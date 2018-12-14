package com.company.employee.dbUtils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBUtilities {

    private static final Logger LOG = LogManager.getLogger(DBUtilities.class);

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;
    private final String tabSpaces = "\t\t\t\t\t\t";


    public DBUtilities(String username, String password) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, username, password);
        } catch (SQLException ex) {
            System.out.println(tabSpaces+"The following error has occurred: " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            LOG.trace(tabSpaces + "The following error has occurred: " + e.getMessage());
        }
    }

    public DBUtilities(String username, String password, String dbName) throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL + dbName, username, password);
        } catch (SQLException ex) {
            System.out.println(tabSpaces + " The following error has occurred: " + ex.getMessage());
        } catch (ClassNotFoundException e) {
            LOG.trace(tabSpaces + " The following error has occurred: " + e.getMessage());
        }
    }

    public void disconnectFromDB() {

        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception ex) {
            LOG.trace(tabSpaces + " The following error has occurred: " + ex.getMessage());
        }
    }

    public ResultSet readRecords(String query) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            return resultSet;
        } catch (SQLException ex) {
            LOG.trace(tabSpaces + " The following error has occurred: " + ex.getMessage());
        }

        return resultSet;
    }

    public void executeSQLStatement(String query) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(query);
            resultSet = statement.getResultSet();
            while (this.resultSet.next()) {
                int numColumns = this.resultSet.getMetaData().getColumnCount();
                for (int i = 1; i <= numColumns; i++) {
                    LOG.info(tabSpaces + "" + this.resultSet.getObject(i));
                }
            }
        } catch (SQLException ex) {
            LOG.trace(tabSpaces + " The following error has occurred: " + ex.getMessage());
        }
    }

    public void execute(String query) {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int numColumns = resultSet.getMetaData().getColumnCount();

                for (int i = 1; i <= numColumns; i++) {
                    LOG.info(tabSpaces + "" + resultSet.getObject(i));
                }
            }
        } catch (SQLException ex) {
            LOG.trace(tabSpaces + " The following error has occurred : " + ex.getMessage());
        }
    }

}