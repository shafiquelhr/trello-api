package org.example.utils;

import org.example.config.DBConfigLoader;
import org.example.config.DotenvConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//our db connection.
public class DBConnection {
    public static Connection connection;
//repsuh
    public static String dbUrl = DotenvConfigLoader.getDbUrl();
    public static String username = DotenvConfigLoader.getDbUser();
    public static String password = DotenvConfigLoader.getDbPassword();
    public static int poolSize = DotenvConfigLoader.getThreadPoolSize();

    public static Connection createDbConnection() {
        connection = null;

        try {
            connection = DriverManager.getConnection(
                    //dbUrl, username, password
                    DBConfigLoader.getUrl(), DBConfigLoader.getUsername(), DBConfigLoader.getPassword());
            //System.out.println("Connection Successful with: dbUrl=" + dbUrl + ", username=" + username + ", password=" + password + ", poolSize=" + poolSize);
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