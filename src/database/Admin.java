package database;

import Utilities.ModelTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public class Admin {

    public ObservableList<ModelTable> select(ObservableList<ModelTable> list)  {
        try {
            ResultSet rs = new Connect().select("SELECT `Employee_Id`, `E_SSN`, `E_Name`, `E_Gender`, `E_Birthday`, "
                    + "`E_Email`, `E_Address`, `E_Department_Name`, `Date_Joining`, `Salary`, `Username`, `E_password`,`E_Phone` FROM `employee`");
            while (rs.next()) {
                list.add((new ModelTable(
                        rs.getString("E_Name"),
                        rs.getString("E_Gender"),
                        rs.getString("E_Email"),
                        rs.getString("E_Address"),
                        rs.getLong("E_Phone"),
                        rs.getString("Username"),
                        rs.getString("E_password"),
                        rs.getString("E_Department_Name"),
                        rs.getDate("Date_Joining"),
                        rs.getDate("E_Birthday"),
                        rs.getFloat("Salary"),
                        rs.getString("Employee_Id"),
                        rs.getLong("E_SSN")))
                );             
            }
            
        }catch (SQLException ex) {
             new Utilities.utility().make_Alert(ex.getMessage());  
        }catch(Exception e){
           new Utilities.utility().make_Alert(e.getMessage());   
        }
        return list;
    }
    
    private void database(String query,ArrayList<ModelTable> empolyeeData) {
        try {
            PreparedStatement preparedStatement = null;
            Connection connection = new Connect().initialize();
            
            preparedStatement = connection.prepareStatement(query);
            
            preparedStatement.setLong(1, empolyeeData.get(0).getSsn());
            preparedStatement.setString(2, empolyeeData.get(0).getEmployeeName());
            preparedStatement.setString(3, empolyeeData.get(0).getGender());
            preparedStatement.setDate(4, empolyeeData.get(0).getBirhtdate());
            preparedStatement.setString(5, empolyeeData.get(0).getEmail());
            preparedStatement.setLong(6, empolyeeData.get(0).getPhone());
            preparedStatement.setString(7, empolyeeData.get(0).getAddress());
            preparedStatement.setString(8, empolyeeData.get(0).getDepartment());
            preparedStatement.setDate(9, empolyeeData.get(0).getDate());
            preparedStatement.setFloat(10, empolyeeData.get(0).getSalary());
            preparedStatement.setString(11, empolyeeData.get(0).getUsername());
            preparedStatement.setString(12, empolyeeData.get(0).getPassword());
            preparedStatement.setString(13, null);
            preparedStatement.setString(14, null);
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
             new Utilities.utility().make_Alert(ex.getMessage());  
        }catch(Exception e){
           new Utilities.utility().make_Alert(e.getMessage());   
        }
    }
    public void insert_Employee(ArrayList<ModelTable> employeedata) {       
        database("INSERT INTO `employee`( `E_SSN`, `E_Name`, `E_Gender`, `E_Birthday`, `E_Email`, `E_phone`, `E_Address`, `E_Department_Name`, `Date_Joining`, `Salary`, `Username`, `E_password`, `Arrival`, `Leaving`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)",employeedata);        
    }
    public void update_Employee(ArrayList<ModelTable> employeedata,String id){       
        database("UPDATE `employee` SET `E_SSN`=?,`E_Name`=?,`E_Gender`=?,`E_Birthday`=?,`E_Email`=?,`E_phone`=?,`E_Address`=?,`E_Department_Name`=?,`Date_Joining`=?,`Salary`=?,`Username`=?,`E_password`=?,`Arrival`=?,`Leaving`=?  WHERE `employee`.`Employee_Id`="+id ,employeedata );
    }
    
    public void delete_Employee(String id ){
        try {
            new Connect().Delete("DELETE FROM `employee` WHERE `Employee_Id`=" + id);
        }catch(Exception e){
           new Utilities.utility().make_Alert(e.getMessage());   
        }
    }
}
