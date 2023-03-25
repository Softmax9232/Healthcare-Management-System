package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.userData;

public class inpatient {

    ObservableList<userData> inpatient = FXCollections.observableArrayList();
    PreparedStatement ps = null;
    ResultSet rs = null;
    String query;
    
    private Connection getConnection() throws SQLException{
        Connection con = DatabaseConnection.getConnection();  
        return con;
    }
    
    public ObservableList<userData> getInpatientData() throws SQLException {
        query = "SELECT `ID`, `P_SSN`, `Name`, `Gender`, `Email`, `Address`,`Illness_Issue`, `Date_of_birth`,`Room_id` ,`Employee_Id`,`patient`.`Phone`, "
                + "`Nurse_id` ,`Relative_name` FROM `patient` ,`inpatient`,`employee` ,`relative` ,`room` "
                + "WHERE `patient`.`ID`=`inpatient`.`In_id` and `relative`.`inp_id`=`inpatient`.`In_id` and `room`.`R_id`=`inpatient`.`Room_id` and `inpatient`.`Nurse_id`=`employee`.`Employee_Id`";
            
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        
        while (rs.next())
            inpatient.add(new userData.UserBuilder().setName(rs.getString("Name")).setPatientId(rs.getInt("ID")).setGender(rs.getString("Gender")).setEmail(rs.getString("Email"))
            .setAddress(rs.getString("Address")).setPhone(rs.getLong("phone")).setRelativeName(rs.getString("Relative_name")).setEmployeetId(rs.getInt("Employee_Id"))
            .setDepartment(rs.getString("Illness_Issue")).setEmployeetId2(rs.getInt("Nurse_id")).setRoomId(rs.getInt("Room_id")).setBirhtdate(rs.getDate("Date_of_birth").toLocalDate())
            .setSsn(rs.getLong("P_SSN")).build());
           
        return inpatient;
    }
    
    public void insertInpatient(userData data) throws SQLException {
        int id = 0;
        ps = getConnection().prepareStatement("INSERT INTO `patient`(`P_SSN`, `Name`, `Gender`, `Email`, `Address`, `Blood_Group`, `Weight`, `Height`,`Illness_Issue`, `Date_of_birth`) VALUES (?,?,?,?,?,?,?,?,?,?)");
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
        ps.executeUpdate();

        query = "SELECT `ID` FROM `patient` WHERE  `patient`.`P_SSN`=" + data.getSsn();
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) 
            id = rs.getInt("ID");

        ps = getConnection().prepareStatement("INSERT INTO `inpatient`(`In_id`, `Room_id`, `Doctor_id`, `Nurse_id`, `Room_Assigned`, `duration`, `date_admitted`) VALUES (?,?,?,?,?,?,?)");
        ps.setInt(1, id);
        ps.setInt(2, data.getRoomId());
        ps.setInt(3, data.getPatientId());
        ps.setInt(4, data.getEmployeeId2());
        ps.setString(5, null);
        ps.setString(6, null);
        ps.setString(7, data.getDate().toString());
        ps.executeUpdate();

        ps = getConnection().prepareStatement("INSERT INTO `relative`(`Relative_name`, `inp_id`, `phone`, `Relationship`) VALUES (?,?,?,?)");
        ps.setString(1, data.getRelativeName());
        ps.setInt(2, id);
        ps.setLong(3, data.getPhone());
        ps.setString(4, null);
        ps.executeUpdate();
    }

    public void deleteInpatient(userData data) throws SQLException{
        int id = 0;
        query = "DELETE FROM `relative` WHERE `inp_id`=" + id;
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        
        query = "DELETE FROM `inpatient` WHERE `In_id`=" + id;
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
    }
    
    public void updateInpatient(userData data, int id) throws SQLException {
        ps = getConnection().prepareStatement("UPDATE `patient` SET `P_SSN`=?,`Name`=?,`Gender`=?,`Email`=?,`Address`=?,`Illness_Issue`=?,`Date_of_birth`=? WHERE `ID`=" + id);
        ps.setLong(1, data.getSsn());
        ps.setString(2, data.getName());
        ps.setString(3, data.getGender());
        ps.setString(4, data.getEmail());
        ps.setString(5, data.getAddress());
        ps.setString(6, data.getDepartment());
        ps.setString(7, data.getBirhtdate().toString()); 
        ps.executeUpdate();

        ps = getConnection().prepareStatement("UPDATE `relative` SET `Relative_name`=? ,`phone`=? WHERE `inp_id`=" + id);
        ps.setString(1, data.getRelativeName());
        ps.setLong(2, data.getPhone());
        ps.executeUpdate();

        ps = getConnection().prepareStatement("UPDATE `inpatient` SET `Room_id`=?,`Doctor_id`=?,`Nurse_id`=?,`date_admitted`=? WHERE `In_id`=" + id);
        ps.setInt(1, data.getRoomId());
        ps.setInt(2, data.getEmployeeId());
        ps.setInt(3, data.getEmployeeId2());
        ps.setString(4, data.getDate().toString());
        ps.executeUpdate();
    }
}
