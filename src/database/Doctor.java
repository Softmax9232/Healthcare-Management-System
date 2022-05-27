package database;

import Utilities.ModelTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public class Doctor {

    public ObservableList<ModelTable> select(ObservableList<ModelTable> list, String id) throws SQLException, ClassNotFoundException {
        ResultSet rs = new Connect().select("SELECT `ID`,`Name`,`Gender`,`Email`,`Blood_Group`,`Weight`,`Height`,`Date_of_birth`,`Phone`,`Appointment`, `Appointment_time`, `diagnosis`, `next_appointment`, `Next_Time` FROM `patient`,`patient appointments`,`employee`WHERE `patient appointments`.`patient_id`=`patient`.`ID` and `patient appointments`.`doc_id`=`employee`.`Employee_Id`and `employee`.`Employee_Id`=`patient appointments`.`doc_id` and `employee`.`Employee_Id`=" + id);

        while (rs.next()) {
            list.add((new ModelTable(
                    rs.getString("ID"),
                    rs.getString("Name"),
                    rs.getString("Gender"),
                    rs.getString("Email"),
                    rs.getDate("appointment"),
                    rs.getString("Blood_Group"),
                    rs.getFloat("Weight"),
                    rs.getFloat("Height"),
                    rs.getDate("Date_of_birth"),
                    rs.getLong("Phone"),
                    rs.getString("diagnosis"),
                    rs.getDate("next_appointment"),
                    rs.getString("Appointment_time"),
                    rs.getString("Next_Time")))
            );
        }
        return list;
    }
    
    public void update_patient(ArrayList<ModelTable> patientData,String id,String Appointment) throws ClassNotFoundException, SQLException {
 
            PreparedStatement preparedStatement = null;
            Connection connection = new Connect().initialize();

            
            PreparedStatement preparedStatement1 = connection.prepareStatement("UPDATE `patient` SET `Blood_Group`=?,`Weight`=?,`Height`=?  WHERE `patient`.`ID`="+id);
            preparedStatement1.setString(1, patientData.get(0).getBloodGroup());
            preparedStatement1.setFloat(2, patientData.get(0).getWeight());
            preparedStatement1.setFloat(3, patientData.get(0).getHeight());
            preparedStatement1.executeUpdate();
            
            PreparedStatement preparedStatement2 = connection.prepareStatement("UPDATE `patient appointments` SET `diagnosis`=?,`next_appointment`=?,`Next_Time`=? WHERE `patient appointments`.`patient_id`="+id+" and `patient appointments`.`Appointment`='"+Appointment+"'");
            preparedStatement2.setString(1, patientData.get(0).getPrescription());
            preparedStatement2.setString(2, patientData.get(0).getDate2().toString());
            preparedStatement2.setString(3, patientData.get(0).getTime1());
            preparedStatement2.executeUpdate();
            
            
        } 
    
    
    public void delete_patient(String id,String Appointment ) throws SQLException, ClassNotFoundException{
         new Connect().Delete("DELETE FROM `patient appointments` WHERE `patient appointments`.`patient_id`="+id+" and `patient appointments`.`appointment`='"+Appointment+"'");
    }
    
    public String prescription(String id,String Appointment) throws SQLException, ClassNotFoundException{
        ResultSet rs = new Connect().select("SELECT `E_Name` FROM `employee`,`patient appointments`,`patient`WHERE `employee`.`Employee_Id`=`patient appointments`.`doc_id`and `patient appointments`.`patient_id`=`patient`.`ID`and `patient appointments`.`Appointment`='" + Appointment + "' and `patient appointments`.`patient_id`=" + id);
        
        String pre_doctor="";
        while (rs.next()) {
            pre_doctor = rs.getString(1);
        }
        return pre_doctor;
    }
}
