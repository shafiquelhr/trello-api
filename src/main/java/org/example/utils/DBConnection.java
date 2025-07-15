package org.example.utils;

import org.example.config.DBConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//our db connection.
public class DBConnection {
    public static Connection connection;

    public static Connection createDbConnection() {
        connection = null;

        try {
            connection = DriverManager.getConnection(
                    DBConfigLoader.getUrl(),
                    DBConfigLoader.getUsername(),
                    DBConfigLoader.getPassword()
            );
//            System.out.println("Connection Successful");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        return connection;
    }
}