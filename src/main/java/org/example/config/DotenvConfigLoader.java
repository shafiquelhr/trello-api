package org.example.config;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvConfigLoader {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getDbUrl() {
        return dotenv.get("DB_URL");
    }

    public static String getDbUser() {
        return dotenv.get("DB_USERNAME");
    }

    public static String getDbPassword() {
        return dotenv.get("DB_PASSWORD");
    }

    public static int getThreadPoolSize() {
        return Integer.parseInt(dotenv.get("THREAD_POOL_SIZE"));
    }
}
