package database;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.*;

public class employee {

    PreparedStatement ps = null;
    ResultSet rs = null;
    String query;
    ObservableList<userData> data = FXCollections.observableArrayList();

    private Connection getConnection() throws SQLException{
        Connection con = DatabaseConnection.getConnection();  
        return con;
    }
    
    public ObservableList<userData> getEmployeeData() throws SQLException {
        query = "SELECT `Employee_Id`, `SSN`, `E_Name`, `E_Gender`, `E_Birthday`, `E_Email`, `E_Address`, `E_Department_Name`, "
                + "`Date_Joining`, `Salary`, `Username`, `password`,`E_Phone` FROM `employee`";
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) 
            data.add(new userData.UserBuilder().
                    setName(rs.getString("E_Name")).setGender(rs.getString("E_Gender")).setEmail(rs.getString("E_Email")).setAddress(rs.getString("E_Address")).setPhone(rs.getLong("E_Phone")).
                    setUsername(rs.getString("Username")).setPassword(rs.getString("password")).setDepartment(rs.getString("E_Department_Name")).setDate(rs.getDate("Date_Joining").toLocalDate()).setBirhtdate(rs.getDate("E_Birthday").toLocalDate()).setSalary(rs.getFloat("Salary"))
                    .setEmployeetId(rs.getInt("Employee_Id")).setSsn(rs.getLong("SSN")).build());
        
        return data;
    }

    public ObservableList<userData> searchForDoctors(String department) throws SQLException {
        query = "SELECT `Employee_Id`, `E_Name`,`Arrival`, `Leaving` FROM `employee` WHERE `E_Department_Name`='" + department + "'";
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) 
            data.add(new userData.UserBuilder().setName(rs.getString("E_Name")).setTime1(rs.getString("Arrival")).setTime2(rs.getString("Leaving")).setEmployeetId(rs.getInt("Employee_Id")).build());
        
        return data;
    }

    public userData verifyEmployee(String username, String pass) throws SQLException {
        userData data = null;
        
        query = "SELECT `Username`, `password`,`E_Department_Name`,`E_Name`,`Employee_Id` FROM `employee`";
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        
        while (rs.next()) {
            if (username.equals(rs.getString(1)) && pass.equals(rs.getString(2))) {
                data = new userData.UserBuilder().setDepartment(rs.getString(3)).setName(rs.getString(4)).setEmployeetId(rs.getInt(5)).build();
                break;
            }
        }
        return data;
    }
    
    public ResultSet CheckUsername() throws SQLException {
        query = "SELECT `Username`, `recovery`, `Employee_Id` FROM `employee`";
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        return rs;
    }

     public ResultSet CheckAnswer(int id) throws  SQLException {
        query = "SELECT `answer` from `employee` WHERE `Employee_Id` =" + id;
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        return rs;
    }

    private void prepareQuery(String query, userData empolyeeData) throws SQLException {
        ps = getConnection().prepareStatement(query);
        ps.setLong(1, empolyeeData.getSsn());
        ps.setString(2, empolyeeData.getName());
        ps.setString(3, empolyeeData.getGender());
        ps.setDate(4, Date.valueOf(empolyeeData.getBirhtdate()));
        ps.setString(5, empolyeeData.getEmail());
        ps.setLong(6, empolyeeData.getPhone());
        ps.setString(7, empolyeeData.getAddress());
        ps.setString(8, empolyeeData.getDepartment());
        ps.setDate(9, Date.valueOf(empolyeeData.getDate()));
        ps.setFloat(10, empolyeeData.getSalary());
        ps.setString(11, empolyeeData.getUsername());
        ps.setString(12, empolyeeData.getPassword());
        ps.setString(13, null);
        ps.setString(14, null);
        ps.executeUpdate();
    }

    public void insertEmployee(userData employeedata) throws SQLException {
        query = "INSERT INTO `employee`( `SSN`, `E_Name`, `E_Gender`, `E_Birthday`, `E_Email`, `E_phone`, `E_Address`, "
                + "`E_Department_Name`, `Date_Joining`, `Salary`, `Username`, `password`, `Arrival`, `Leaving`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        prepareQuery(query, employeedata);
    }

    public void updateEmployee(userData employeedata, int id) throws SQLException {
        query = "UPDATE `employee` SET `SSN`=?,`E_Name`=?,`E_Gender`=?,`E_Birthday`=?,`E_Email`=?,`E_phone`=?,`E_Address`=?,"
                + "`E_Department_Name`=?,`Date_Joining`=?,`Salary`=?,`Username`=?,`password`=?,`Arrival`=?,`Leaving`=?  WHERE `employee`.`Employee_Id`=" + id;

        prepareQuery(query, employeedata);
    }

    public void deleteEmployee(int id) throws SQLException {
        query = "DELETE FROM `employee` WHERE `Employee_Id`=" + id;
        ps = getConnection().prepareStatement(query);
        ps.executeUpdate();
    }
    
    public int countEmployees(String query) throws SQLException{
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        int count = 0;
        while (rs.next())
            count = rs.getInt("COUNT(`Employee_Id`)");
        return count;
    }
    
    public int countEmployees() throws SQLException{
        query = "SELECT COUNT(`Employee_Id`)FROM `employee`";
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) 
                count = rs.getInt("COUNT(`Employee_Id`)");
            return count;
    }
    
    public int countDepartments() throws SQLException {
        query = "SELECT COUNT(`Department_name`)FROM `department`";
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        int count = 0;
        while (rs.next()) {
            count = rs.getInt("COUNT(`Department_name`)");
        }
        return count;
    }

    public float countSalary(String query) throws SQLException{
            ps = getConnection().prepareStatement(query);
            rs = ps.executeQuery();
            float count = 0;
            while (rs.next()) 
                count = rs.getInt("COUNT(`Employee_Id`)");
            return count;
    }
    
    public int updatePassword(String pass, int id) throws SQLException{
        ps = getConnection().prepareStatement("UPDATE `employee` SET `password` = '" + pass + "' WHERE `Employee_Id`=" + id);
        return ps.executeUpdate();
    }
}
