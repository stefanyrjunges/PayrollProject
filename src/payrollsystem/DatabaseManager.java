package payrollsystem;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {

    private static String DB_URL;
    private static String DB_USERNAME;
    private static String DB_PASSWORD;

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/dbconfig.properties"));
            DB_URL = properties.getProperty("db.url");
            DB_USERNAME = properties.getProperty("db.username");
            DB_PASSWORD = properties.getProperty("db.password");
        } catch (IOException e) {
            System.out.println("Error: " + e);
            throw new RuntimeException("Failed to load database properties");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}
