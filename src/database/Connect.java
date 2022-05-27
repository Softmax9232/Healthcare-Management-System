package database;

import java.sql.*;

public class Connect {

    public static Connection connection;
    
    public Connection initialize() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/h.m.s?zeroDateTimeBehavior=convertToNull", "root", "");           
        } catch (SQLException ex) {
             new Utilities.utility().make_Alert(ex.getMessage());  
        } catch (ClassNotFoundException ex) {
            new Utilities.utility().make_Alert("Connection with Database is failed");
        }catch(Exception e){
           new Utilities.utility().make_Alert(e.getMessage());   
        }
        return connection;
    }
    
    public ResultSet select(String query)  {
        ResultSet rs = null;
        try {
            initialize();         
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
           
        }catch (SQLException ex) {
             new Utilities.utility().make_Alert(ex.getMessage());  
        }catch(Exception e){
           new Utilities.utility().make_Alert(e.getMessage());   
        }
         return rs;
    }
    
    public void Delete(String query){
        try {
            Connection con = initialize();
            PreparedStatement preparedStatement=null;
            preparedStatement = con.prepareStatement(query);    
            preparedStatement.executeUpdate();
        }catch (SQLException ex) {
             new Utilities.utility().make_Alert(ex.getMessage());  
        }catch(Exception e){
           new Utilities.utility().make_Alert(e.getMessage());   
        }
    }  
}
       
        
        