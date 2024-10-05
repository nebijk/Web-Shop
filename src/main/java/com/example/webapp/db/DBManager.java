package com.example.webapp.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static Connection connection;

    // Metod för att skapa och returnera en databasanslutning utan db.properties
    public Connection getConnection() {
        if (connection == null) {
            try {
                // Hårdkodad information för databasanslutning
                String url = "jdbc:mysql://localhost/webshop";  // Ange din databas-URL
                String username = "root";  // Ange ditt databas-användarnamn
                String password = "Dekemhare145.";  // Ange ditt databas-lösenord

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