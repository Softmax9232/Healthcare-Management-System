package database;

import java.sql.*;

public class prescription {
    
    private Connection getConnection() throws SQLException{
        Connection con = DatabaseConnection.getConnection();  
        return con;
    }
    
    public String getPrescription(int id, String Appointment) throws SQLException {
        String pre_doctor = "" ;
        
        String query = "SELECT `E_Name` FROM `employee`,`patient appointments`,`patient`WHERE `employee`.`Employee_Id`=`patient appointments`.`doc_id`and `patient appointments`.`patient_id`=`patient`.`ID`and `patient appointments`.`Appointment`='" 
                + Appointment + "' and `patient appointments`.`patient_id`=" + id;

        PreparedStatement ps = getConnection().prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        
        while (rs.next()) 
            pre_doctor = rs.getString(1);       
        return pre_doctor;
    }
}
