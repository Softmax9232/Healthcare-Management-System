package database;

import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.*;


public class bill {
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    ResultSet rs1 = null;
    String query;
    
    private Connection getConnection() throws SQLException{
        Connection con = DatabaseConnection.getConnection();  
        return con;
    }
    
    public float add_bill(billData billdata) throws SQLException {
        float total = 0;
        
        ps = getConnection().prepareStatement("INSERT INTO `bill`( `Patient_Id`, `Payment_date`, `Examination_cost`, `medicine_cost`, `test_cost`, `room_cost`,`Total`) VALUES (?,?,?,?,?,?,?)");
        ps.setInt(1, billdata.getPatientId());
        ps.setDate(2, Date.valueOf(billdata.getDate()));
        ps.setFloat(3, billdata.getExaminationCost());
        ps.setFloat(4, billdata.getMedicineCost());
        ps.setFloat(5, billdata.getTestCost());
        ps.setFloat(6, billdata.getRoomCost());
        
        total = billdata.getExaminationCost() + billdata.getMedicineCost()+ billdata.getTestCost() + billdata.getRoomCost();
        
        ps.setFloat(7, total);
        ps.executeUpdate();
       
        return total;
    }

    public ObservableList<roomData> selectRoomData(int patientId) throws SQLException {
        ObservableList<roomData> room = FXCollections.observableArrayList();
        query = "SELECT `duration`,`Type`,`RCost`,`Room_Assigned` FROM `inpatient`,`room`, `patient` WHERE `inpatient`.`In_id` =`patient`.`ID` and `inpatient`.`Room_id` =`room`.`R_id` and `inpatient`.`In_id`=" + patientId;
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery(); 
        while (rs.next())
            room.add(new roomData( rs.getFloat("RCost"),rs.getDate("Room_Assigned").toLocalDate(),rs.getString("Type"),rs.getInt("duration")));
        return room;
    }   
}
