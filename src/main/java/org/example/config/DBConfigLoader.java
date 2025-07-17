package org.example.config;

import java.io.InputStream;
import java.util.Properties;


/** Loading database configuration without @Value annotation
 * and using InputStream to read byte contents of the db.properties file.
 * **/
public class DBConfigLoader {
    private static final Properties props = new Properties();

    //reads properties from application.properites file.
    static {
        try (InputStream input = DBConfigLoader.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("Cannot find application.properties file");
            }
            props.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getUrl() {
        return props.getProperty("db.url");
    }

    public static String getUsername() {
        return props.getProperty("db.username");
    }

    public static String getPassword() {
        return props.getProperty("db.password");
    }

    public static int getThreadPoolSize() {
        return Integer.parseInt(props.getProperty("thread.pool.size"));
    }
}
