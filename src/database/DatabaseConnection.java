package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    private static DatabaseConnection connection;
    
    private DatabaseConnection(){}
    
    public static DatabaseConnection getInstance(){
        if(connection == null) connection = new DatabaseConnection();
        return connection;
    }
    
    public static Connection getConnection() throws SQLException{
        Connection connection = null;
        try {         
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hms", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }
}
