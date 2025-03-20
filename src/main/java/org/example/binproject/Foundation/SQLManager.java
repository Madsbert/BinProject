package org.example.binproject.Foundation;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * a class to connect to our database
 */
public class SQLManager {

    private static final String URL = "jdbc:sqlserver://162.19.246.106:1433;databaseName=Baredygtighed;";
    private static final String USER = "Baredygtighed";
    private static final String PASS = "Gruppe1!";

    /**
     * A method to connect to the database
     * @return a connection
     * @throws Exception
     */
    public static Connection getConnection() throws Exception {

        Connection con = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("Connected to the database successfully: " + con.toString());
        return con;

    }

}
