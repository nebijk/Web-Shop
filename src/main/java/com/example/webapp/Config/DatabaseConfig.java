package com.example.webapp.Config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {
    public Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find db.properties");
                return null;
            }
            // Ladda egenskaper från filen
            props.load(input);
            System.out.println("watsapp");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return props;
    }
}
