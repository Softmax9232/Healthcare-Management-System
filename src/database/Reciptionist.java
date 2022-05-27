package database;

import Utilities.ModelTable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.collections.ObservableList;

public class Reciptionist {

    // Inpatient Data
    public ObservableList<ModelTable> select_inpatient(ObservableList<ModelTable> list) {
        try {
            ResultSet rs1 = new Connect().select("SELECT `ID`, `SSN`, `Name`, `Gender`, `Email`, `Address`,`Illness_Issue`, `Date_of_birth`,`Room_id` ,`Employee_Id`,`patient`.`Phone`, `Nurse_id` ,`Relative_name` FROM `patient` ,`inpatient`,`employee` ,`relative` ,`room` WHERE `patient`.`ID`=`inpatient`.`In_id` and `relative`.`inp_id`=`inpatient`.`In_id` and `room`.`R_id`=`inpatient`.`Room_id` and `inpatient`.`Nurse_id`=`employee`.`Employee_Id`");
            while (rs1.next()) {
                
                list.add((new ModelTable(
                        rs1.getString("Name"),
                        rs1.getString("ID"),
                        rs1.getString("Gender"),
                        rs1.getString("Email"),
                        rs1.getString("Address"),
                        rs1.getLong("phone"),
                        rs1.getString("Relative_name"),
                        rs1.getString("Employee_Id"),
                        rs1.getString("Illness_Issue"),
                        rs1.getString("Nurse_id"),
                        rs1.getString("Room_id"),
                        rs1.getDate("Date_of_birth"),
                        rs1.getLong("SSN") ))
                );
            }
        } catch (SQLException ex) {
            new Utilities.utility().make_Alert(ex.getMessage());  
        }
        return list;
    }

    private PreparedStatement database(String query) {
         PreparedStatement preparedStatement=null;
        try {         
            Connection connection = new Connect().initialize();
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException ex) {
            new Utilities.utility().make_Alert(ex.getMessage());  
        }
          return preparedStatement;
    }

    static int P_id;

    public void add_inpatient(ArrayList<ModelTable> inpatientdata) {
        try {
            PreparedStatement preparedStatement = database("INSERT INTO `patient`(`SSN`, `Name`, `Gender`, `Email`, `Address`, `Blood_Group`, `Weight`, `Height`,`Illness_Issue`, `Date_of_birth`) VALUES (?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setLong(1, inpatientdata.get(0).getSsn());
            preparedStatement.setString(2, inpatientdata.get(0).getPatientName());
            preparedStatement.setString(3, inpatientdata.get(0).getGender());
            preparedStatement.setString(4, inpatientdata.get(0).getEmail());
            preparedStatement.setString(5, inpatientdata.get(0).getAddress());
            preparedStatement.setString(6, null);
            preparedStatement.setString(7, null);
            preparedStatement.setString(8, null);
            preparedStatement.setString(9, inpatientdata.get(0).getIllnessIssue());
            preparedStatement.setString(10, inpatientdata.get(0).getBirhtdate().toString());
            preparedStatement.executeUpdate();
            
            ResultSet rs = new Connect().select("SELECT `ID` FROM `patient` WHERE  `patient`.`SSN`=" + inpatientdata.get(0).getSsn());
            while (rs.next()) {
                P_id = rs.getInt("ID");
            }
            
            PreparedStatement preparedStatement3 = database("INSERT INTO `inpatient`(`In_id`, `Room_id`, `Doctor_id`, `Nurse_id`, `Room_Assigned`, `duration`, `date_admitted`) VALUES (?,?,?,?,?,?,?)");
            
            preparedStatement3.setInt(1, P_id);
            preparedStatement3.setString(2, inpatientdata.get(0).getRoomAssigned());
            preparedStatement3.setString(3, inpatientdata.get(0).getId3());
            preparedStatement3.setString(4, inpatientdata.get(0).getNurseAssigned());
            preparedStatement3.setString(5, null);
            preparedStatement3.setString(6, null);
            preparedStatement3.setString(7, inpatientdata.get(0).getDate().toString());
            preparedStatement3.executeUpdate();
            
            PreparedStatement preparedStatement2 = database("INSERT INTO `relative`(`Relative_name`, `inp_id`, `phone`, `Relationship`) VALUES (?,?,?,?)");
            preparedStatement2.setString(1, inpatientdata.get(0).getRelativeName());
            preparedStatement2.setInt(2, P_id);
            preparedStatement2.setLong(3, inpatientdata.get(0).getRelativePhone());
            preparedStatement2.setString(4, null);
            preparedStatement2.executeUpdate();
        } catch (SQLException ex) {
            new Utilities.utility().make_Alert(ex.getMessage());  
        }
    }

    public void update_inpatient(ArrayList<ModelTable> inpatientdata, String id){

        try {
            PreparedStatement preparedStatement = database("UPDATE `patient` SET `SSN`=?,`Name`=?,`Gender`=?,`Email`=?,`Address`=?,`Illness_Issue`=?,`Date_of_birth`=? WHERE `ID`=" + id);
            preparedStatement.setLong(1, inpatientdata.get(0).getSsn());
            preparedStatement.setString(2, inpatientdata.get(0).getPatientName());
            preparedStatement.setString(3, inpatientdata.get(0).getGender());
            preparedStatement.setString(4, inpatientdata.get(0).getEmail());
            preparedStatement.setString(5, inpatientdata.get(0).getAddress());
            preparedStatement.setString(6, inpatientdata.get(0).getIllnessIssue());
            preparedStatement.setString(7, inpatientdata.get(0).getBirhtdate().toString());
            
            preparedStatement.executeUpdate();
            
            PreparedStatement preparedStatement2 = database("UPDATE `relative` SET `Relative_name`=? ,`phone`=? WHERE `inp_id`=" + id);
            preparedStatement2.setString(1, inpatientdata.get(0).getRelativeName());
            preparedStatement2.setLong(2, inpatientdata.get(0).getRelativePhone());
            preparedStatement2.executeUpdate();
            
            PreparedStatement preparedStatement3 = database("UPDATE `inpatient` SET `Room_id`=?,`Doctor_id`=?,`Nurse_id`=?,`date_admitted`=? WHERE `In_id`=" + id);
            preparedStatement3.setString(1, inpatientdata.get(0).getRoomAssigned());
            preparedStatement3.setString(2, inpatientdata.get(0).getId3());
            preparedStatement3.setString(3, inpatientdata.get(0).getNurseAssigned());
            preparedStatement3.setString(4, inpatientdata.get(0).getDate().toString());
            preparedStatement3.executeUpdate();
        } catch (SQLException ex) {
           new Utilities.utility().make_Alert(ex.getMessage());  
        }
    }

    public void delete_inpatient(){
        try {
            PreparedStatement preparedStatement = database("DELETE FROM `relative` WHERE `inp_id`=" + P_id);
            preparedStatement.executeUpdate();
            
            PreparedStatement preparedStatement1 = database(" DELETE FROM `inpatient` WHERE `In_id`=" + P_id);
            preparedStatement1.executeUpdate();
        } catch (SQLException ex) {
            new Utilities.utility().make_Alert(ex.getMessage());  
        }
    }

    // Outpatient Data
    public ObservableList<ModelTable> select_outpatient(ObservableList<ModelTable> list){
        try {
            ResultSet rs = new Connect().select("SELECT `ID`,`SSN`, `Address`,`Name`, `Gender`, `Email`,`Illness_Issue`, `doc_id`, `Date_of_birth` ,`Phone`,`Appointment` FROM `patient`,`patient appointments`,`employee` WHERE `patient`.`ID`=`patient appointments`.`patient_id` and `patient appointments`.`doc_id`=`employee`.`Employee_Id`");
            while (rs.next()) {
                list.add((new ModelTable(
                        rs.getString("ID"),
                        rs.getString("Name"),
                        rs.getString("Gender"),
                        rs.getString("Email"),
                        rs.getString("Address"),
                        rs.getLong("Phone"),
                        rs.getString("Illness_Issue"),
                        rs.getString("doc_id"),
                        rs.getDate("Date_of_birth"),
                        rs.getDate("Appointment"),
                        rs.getLong("SSN")))
                );
            }
        } catch (SQLException ex) {
            new Utilities.utility().make_Alert(ex.getMessage());  
        }
         return list;
    }

    static int O_id;

    public void add_outpatient(ArrayList<ModelTable> outpatientdata) {
        try {
            PreparedStatement preparedStatement = database("INSERT INTO `patient`(`SSN`, `Name`, `Gender`, `Email`, `Address`,`Blood_Group`, `Weight`, `Height`, `Illness_Issue`, `Date_of_birth`,`phone`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setLong(1, outpatientdata.get(0).getSsn());
            preparedStatement.setString(2, outpatientdata.get(0).getPatientName());
            preparedStatement.setString(3, outpatientdata.get(0).getGender());
            preparedStatement.setString(4, outpatientdata.get(0).getEmail());
            preparedStatement.setString(5, outpatientdata.get(0).getAddress());
            preparedStatement.setString(6, null);
            preparedStatement.setString(7, null);
            preparedStatement.setString(8, null);
            preparedStatement.setString(9, outpatientdata.get(0).getIllnessIssue());
            preparedStatement.setString(10, outpatientdata.get(0).getBirhtdate().toString());
            preparedStatement.setLong(11, outpatientdata.get(0).getPhone());
            preparedStatement.executeUpdate();
            
            ResultSet rs = new Connect().select("SELECT `ID` FROM `patient` WHERE  `patient`.`SSN`=" + outpatientdata.get(0).getSsn());
            while (rs.next()) {
                O_id = rs.getInt("ID");
            }
            
            PreparedStatement preparedStatement3 = database("INSERT INTO `patient appointments`(`patient_id`, `doc_id`, `Appointment`, `Appointment_time`, `diagnosis`, `next_appointment`, `Next_Time`) VALUES (?,?,?,?,?,?,?)");
            preparedStatement3.setInt(1, O_id);
            preparedStatement3.setString(2, outpatientdata.get(0).getId3());
            preparedStatement3.setString(3, outpatientdata.get(0).getAppointment().toString());
            preparedStatement3.setString(4, null);
            preparedStatement3.setString(5, null);
            preparedStatement3.setString(6, null);
            preparedStatement3.setString(7, null);
            
            preparedStatement3.executeUpdate();
        } catch (SQLException ex) {
             new Utilities.utility().make_Alert(ex.getMessage());  
        }
    }

    public void update_outpatient(ArrayList<ModelTable> outpatientdata){

        try {
            PreparedStatement preparedStatement1 = database("UPDATE `patient` SET `SSN`=?,`Name`=?,`Gender`=?,`Email`=?,`Address`=?,`Illness_Issue`=?,`Date_of_birth`=?,`Phone`=? WHERE `SSN`=" + outpatientdata.get(0).getSsn());
            preparedStatement1.setLong(1, outpatientdata.get(0).getSsn());
            preparedStatement1.setString(2, outpatientdata.get(0).getPatientName());
            preparedStatement1.setString(3, outpatientdata.get(0).getGender());
            preparedStatement1.setString(4, outpatientdata.get(0).getEmail());
            preparedStatement1.setString(5, outpatientdata.get(0).getAddress());
            preparedStatement1.setString(6, outpatientdata.get(0).getIllnessIssue());
            preparedStatement1.setString(7, outpatientdata.get(0).getBirhtdate().toString());
            preparedStatement1.setLong(8, outpatientdata.get(0).getPhone());
            preparedStatement1.executeUpdate();
            
            ResultSet rs = new Connect().select("SELECT `ID` FROM `patient` WHERE  `patient`.`SSN`=" + outpatientdata.get(0).getSsn());
            while (rs.next()) {
                O_id = rs.getInt("ID");
            }
            
            PreparedStatement preparedStatement2 = database("UPDATE `patient appointments` SET `doc_id`=?,`Appointment`=? WHERE `patient_id`=" + O_id + " and `Appointment`='" + outpatientdata.get(0).getAppointment().toString() + "'");
            preparedStatement2.setString(1, outpatientdata.get(0).getId3());
            preparedStatement2.setString(2, outpatientdata.get(0).getAppointment().toString());
            preparedStatement2.executeUpdate();
        } catch (SQLException ex) {
             new Utilities.utility().make_Alert(ex.getMessage());  
        }
    }

    public void delete_outpatient(ArrayList<ModelTable> outpatientdata) {
        try {
            ResultSet rs = new Connect().select("SELECT `ID` FROM `patient` WHERE  `patient`.`SSN`=" + outpatientdata.get(0).getSsn());
            while (rs.next()) {
                O_id = rs.getInt("ID");
            }
            PreparedStatement preparedStatement3 = database("DELETE FROM `patient appointments` WHERE `patient_id`=" + O_id + " and `Appointment`='" + outpatientdata.get(0).getAppointment()+"'" );
            preparedStatement3.executeUpdate();
        } catch (SQLException ex) {
             new Utilities.utility().make_Alert(ex.getMessage());  
        }
    }
    
    // BILL
    public float add_bill(ArrayList<ModelTable> billdata, String patient){
        float Total = 0;
        try {
            PreparedStatement preparedStatement = database("INSERT INTO `bill`( `Patient_Id`, `Payment_date`, `Examination_cost`, `medicine_cost`, `test_cost`, `room_cost`,`Total`) VALUES (?,?,?,?,?,?,?)");
            preparedStatement.setString(1, billdata.get(0).getId());
            preparedStatement.setDate(2,  billdata.get(0).getDate());
            preparedStatement.setFloat(3, billdata.get(0).getExaminationCost());
            preparedStatement.setFloat(4, billdata.get(0).getDrugCost());
            preparedStatement.setFloat(5, billdata.get(0).getTestCost());
            preparedStatement.setFloat(6,  billdata.get(0).getRoomCost());
            preparedStatement.setFloat(7, billdata.get(0).getExaminationCost()+billdata.get(0).getDrugCost()+billdata.get(0).getTestCost()+billdata.get(0).getRoomCost());
            preparedStatement.executeUpdate();
            
            Total =billdata.get(0).getExaminationCost()+billdata.get(0).getDrugCost()+billdata.get(0).getTestCost()+billdata.get(0).getRoomCost();        
        } catch (SQLException ex) {
             new Utilities.utility().make_Alert(ex.getMessage());  
        }
         return Total;
    }
}
