package org.example.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//our db connection.
public class DBConnection {
    public static Connection connection;


    public static Connection createDbConnection() {
        connection = null;

        String url = DBConfigLoader.getUrl();
        String username = DBConfigLoader.getUsername();
        String password = DBConfigLoader.getPassword();

        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection Successful");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        return connection;
    }
}