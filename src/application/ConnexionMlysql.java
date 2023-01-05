package application;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnexionMlysql {
    public Connection cn = null;

    public static Connection connexionBD() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/para vie", "root", "");
            System.out.println("connexion reussite");

            return cn;

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("connexion echouée");
            e.printStackTrace();
            return null;
        }
    }
}