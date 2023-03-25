package database;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.userData;

public class outpatient{

    ObservableList<userData> outpatient = FXCollections.observableArrayList();
    PreparedStatement ps = null;
    ResultSet rs = null;
    String query;

    private Connection getConnection() throws SQLException{
        Connection con = DatabaseConnection.getConnection();  
        return con;
    }
    
    public ObservableList<userData> getOutpatientData() throws SQLException {
        query = "SELECT `ID`,`P_SSN`, `Address`,`Name`, `Gender`, `Email`,`Illness_Issue`, `doc_id`, `Date_of_birth` ,`Phone`,`Appointment` FROM `patient`,`patient appointments`,"
                + "`employee` WHERE `patient`.`ID`=`patient appointments`.`patient_id` and `patient appointments`.`doc_id`=`employee`.`Employee_Id`";
       
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next())
                outpatient.add(new userData.UserBuilder().setPatientId(rs.getInt("ID")).setName(rs.getString("Name")).setGender(rs.getString("Gender")).setEmail(rs.getString("Email"))
                .setAddress(rs.getString("Address")).setPhone(rs.getLong("Phone")).setDepartment(rs.getString("Illness_Issue")).setEmployeetId(rs.getInt("doc_id"))
                .setBirhtdate(rs.getDate("Date_of_birth").toLocalDate()).setAppointment(rs.getDate("Appointment").toLocalDate()).setSsn(rs.getLong("P_SSN")).build());
        return outpatient;
    }

    public void insertData(userData data) throws SQLException {
        int id = 0;
        
        ps = getConnection().prepareStatement("INSERT INTO `patient`(`P_SSN`, `Name`, `Gender`, `Email`, `Address`,`Blood_Group`, `Weight`, `Height`, `Illness_Issue`, `Date_of_birth`,`phone`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
        ps.setLong(1, data.getSsn());
        ps.setString(2, data.getName());
        ps.setString(3, data.getGender());
        ps.setString(4, data.getEmail());
        ps.setString(5, data.getAddress());
        ps.setString(6, null);
        ps.setString(7, null);
        ps.setString(8, null);
        ps.setString(9, data.getDepartment());
        ps.setString(10, data.getBirhtdate().toString());
        ps.setLong(11, data.getPhone());
        ps.executeUpdate();

        query = "SELECT `ID` FROM `patient` WHERE  `patient`.`SSN`=" + data.getSsn();
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) 
            id = rs.getInt("ID");
          
        ps = getConnection().prepareStatement("INSERT INTO `patient appointments`(`patient_id`, `doc_id`, `Appointment`, `Appointment_time`, `diagnosis`, `next_appointment`, `Next_Time`) VALUES (?,?,?,?,?,?,?)");
        ps.setInt(1, id);
        ps.setInt(2, data.getEmployeeId());
        ps.setString(3, data.getAppointment().toString());
        ps.setString(4, null);
        ps.setString(5, null);
        ps.setString(6, null);
        ps.setString(7, null);
        ps.executeUpdate();
    }

    public void deleteData(userData data) throws SQLException {
        int id  = 0;
        query = "SELECT `ID` FROM `patient` WHERE  `patient`.`P_SSN`=" + data.getSsn();
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) 
            id = rs.getInt("ID");
        
        query = "DELETE FROM `patient appointments` WHERE `patient_id`=" + id + " and `Appointment`='" + data.getAppointment() + "'";
        ps = getConnection().prepareStatement(query);
        ps.executeUpdate();
    }

    public void updateData(userData data , String appointment) throws SQLException {
        int id = 0;
       
        ps = getConnection().prepareStatement("UPDATE `patient` SET `P_SSN`=?,`Name`=?,`Gender`=?,`Email`=?,`Address`=?,`Illness_Issue`=?,`Date_of_birth`=?,`Phone`=? WHERE `P_SSN`=" + data.getSsn());
        ps.setLong(1, data.getSsn());
        ps.setString(2, data.getName());
        ps.setString(3, data.getGender());
        ps.setString(4, data.getEmail());
        ps.setString(5, data.getAddress());
        ps.setString(6, data.getDepartment());
        ps.setString(7, data.getBirhtdate().toString());
        ps.setLong(8, data.getPhone());
        ps.executeUpdate();

        query = "SELECT `ID` FROM `patient` WHERE  `patient`.`P_SSN`=" + data.getSsn();
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next())
            id = rs.getInt("ID");
         

        ps = getConnection().prepareStatement("UPDATE `patient appointments` SET `doc_id`=?,`Appointment`=? WHERE `patient_id`=" + id + " and `Appointment`='" + appointment + "'");
        ps.setInt(1, data.getEmployeeId());
        ps.setString(2, data.getAppointment().toString());
        ps.executeUpdate();
    }
}
