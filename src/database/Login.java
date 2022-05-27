package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Login {
    
    public ArrayList<String> list = new ArrayList<String>();

    public ArrayList<String> CheckLogin(String username, String pass) {
        try {
            ResultSet rs = new database.Connect().select("SELECT `Username`, `E_password`,`E_Department_Name`,`E_Name`,`Employee_Id` FROM `employee`");
            while (rs.next()) {
                if (username.equals(rs.getString(1)) && pass.equals(rs.getString(2))) {
                    list.add(0, rs.getString(1));
                    list.add(1, rs.getString(2));
                    list.add(2, rs.getString(3));
                    list.add(3, rs.getString(4));
                    list.add(4, rs.getString(5)); 
                }
            }         
        } catch (SQLException ex) {
            new Utilities.utility().make_Alert(ex.getMessage());  
        }
         return list;
    }
    
    public ResultSet Check_Username() {
        ResultSet rs = new database.Connect().select("SELECT `Username`, `recovery`, `Employee_Id` FROM `employee`");
        return rs;
    }

     public ResultSet Check_answer(String id) {
        ResultSet rs = new database.Connect().select("SELECT `answer` from `employee` WHERE `Employee_Id` =" + id);
        return rs;
    }
     
    public void update_Password(String pass, String id){
        try {
           Connection conn =  new database.Connect().initialize();
           Statement stmt = conn.createStatement();
           stmt.executeUpdate("UPDATE `employee` SET `E_password` = '"+pass+"' WHERE `Employee_Id`="+id);
        } catch (SQLException ex) {
           new Utilities.utility().make_Alert(ex.getMessage()); 
        }
    }
    
}
