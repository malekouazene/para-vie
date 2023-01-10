package application;




import java.sql.*;
import java.sql.DriverManager;

public class connexionmysql {
    public Connection cnx;
      public static Connection connexionDB() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection cnx=DriverManager.getConnection("jdbc:mysql://localhost:3306/paravie","root","");
            System.out.println("connexion reussite");
            return cnx;
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("connexion echouee");
            e.printStackTrace();
            return null;
        }
        
    }
}