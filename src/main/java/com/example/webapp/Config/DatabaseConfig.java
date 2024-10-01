package com.example.webapp.Config;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfig {

    private static Connection connection;

    // Metod för att skapa och returnera en databasanslutning utan db.properties
    public Connection getConnection() {
        if (connection == null) {
            try {
                // Hårdkodad information för databasanslutning
                String url = "jdbc:mysql://127.0.0.1:3306/webshop";  // Ange din databas-URL
                String username = "root";  // Ange ditt databas-användarnamn
                String password = "Coola145!";  // Ange ditt databas-lösenord

                // Ladda JDBC-drivrutinen
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Skapa anslutningen
                connection = DriverManager.getConnection(url, username, password);
                System.out.println("Connection established!");  // Bekräfta anslutning i konsolen
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}