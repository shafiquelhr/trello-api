package org.example.utils;

import org.example.config.DBConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Using the finalize() block to close the connection.
 * NOTE: NOT-RECOMMENDED and finalize() has been removed from Java 18+ onwards.
 * Use try-with-resources or Cleaner API (Java 9+) **/

public class DbConnectionManager {
    private Connection connection;

    public DbConnectionManager() {
        try {
            connection = DriverManager.getConnection(
                    DBConfigLoader.getUrl(),
                    DBConfigLoader.getUsername(),
                    DBConfigLoader.getPassword()
            );
            System.out.println("Connection opened in constructor");
        } catch (SQLException e) {
            System.out.println("Failed to open connection");
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed in finalize()");
            }
        } catch (SQLException e) {
            System.out.println("Failed to close connection in finalize()");
            e.printStackTrace();
        } finally {
            super.finalize();
        }
    }
}
