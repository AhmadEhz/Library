package org.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConfig {

    private DbConfig() throws SQLException {
    }

    private static final Connection CONFIG;

    static {
        try {
            CONFIG = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Library", "postgres", "457894561");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConfig() {
        return CONFIG;
    }
}
