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

    /* Following is a demo of using finalize() method to clean up db connection resources
    DbConnectionManager manager = new DbConnectionManager();
    Connection conn = manager.getConnection();

    // simulating object becoming unreachable
    manager = null;
    conn = null;

    // Hint to JVM to run GC (not guaranteed!)
    System.out.println("Requesting GC...");
    System.gc(); // Hints JVM to run Garbage Collector
    System.out.println("GC requested");


    System.out.println("End of main method");
    */
}