package database;

import java.sql.*;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.*;

public class patient {
    
    PreparedStatement ps = null;
    ResultSet rs = null;
    String query;
    ObservableList<userData> patient = FXCollections.observableArrayList();
    ObservableList<TestData> test = FXCollections.observableArrayList();
    ObservableList<DrugsData> drug = FXCollections.observableArrayList();

    private Connection getConnection() throws SQLException{
        Connection con = DatabaseConnection.getConnection();  
        return con;
    }
    
    public ObservableList<userData> selectPatientData(int id) throws SQLException {
        query = "SELECT `ID`,`Name`,`Gender`,`Email`,`Blood_Group`,`Weight`,`Height`,`Date_of_birth`,`Phone`,`Appointment`, `Appointment_time`, `diagnosis`, "
                + "`next_appointment`, `Next_Time` FROM `patient`,`patient appointments`,`employee`WHERE `patient appointments`.`patient_id`=`patient`.`ID` "
                + "and `patient appointments`.`doc_id`=`employee`.`Employee_Id`and `employee`.`Employee_Id`=`patient appointments`.`doc_id` and `employee`.`Employee_Id`=" + id;
        
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        LocalDate date = LocalDate.now();
        
        while (rs.next()) {           
            if(rs.getDate("next_appointment") != null)  date = rs.getDate("next_appointment").toLocalDate();
            
            patient.add(new userData.UserBuilder().setPatientId(rs.getInt("ID")).setName(rs.getString("Name"))
                .setGender(rs.getString("Gender")).setEmail(rs.getString("Email")).setAppointment(rs.getDate("appointment").toLocalDate())
                .setBloodGroup(rs.getString("Blood_Group")).setWeight(rs.getFloat("Weight"))
                .setHight(rs.getFloat("Height")).setBirhtdate(rs.getDate("Date_of_birth").toLocalDate()).setPhone(rs.getLong("Phone"))
                .setPrescription(rs.getString("diagnosis")).setNextAppointment(date)
                .setTime2(rs.getString("Next_Time")).setTime1(rs.getString("Appointment_time")).build());   
        }
        return patient;
    }
            
    public void updatePatient(userData patientData, int id, String Appointment) throws SQLException {
            ps = getConnection().prepareStatement("UPDATE `patient` SET `Blood_Group`=?,`Weight`=?,`Height`=?  WHERE `patient`.`ID`=" + id);
            ps.setString(1, patientData.getBloodGroup());
            ps.setFloat(2, patientData.getWeight());
            ps.setFloat(3, patientData.getHeight());
            ps.executeUpdate();

            ps = getConnection().prepareStatement("UPDATE `patient appointments` SET `diagnosis`=?,`next_appointment`=?,`Next_Time`=? WHERE `patient appointments`.`patient_id`=" + id + " and `patient appointments`.`Appointment`='" + Appointment + "'");
            ps.setString(1, patientData.getPrescription());
            ps.setDate(2, Date.valueOf(patientData.getNextAppointment()));
            ps.setString(3, patientData.getTime2());
            ps.executeUpdate();
    }

    public void deletePatient(String id, String Appointment) throws SQLException, ClassNotFoundException {
        query = "DELETE FROM `patient appointments` WHERE `patient appointments`.`patient_id`=" + id + 
                " and `patient appointments`.`appointment`='" + Appointment + "'";
        ps = getConnection().prepareStatement(query);
        ps.executeUpdate();
    }

    public String getPatientName(int id) throws SQLException {
        String name = "";
        query = "SELECT `patient`.`Name` from `patient` WHERE `patient`.`ID`=" + id;
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        
        while (rs.next()) 
            name = rs.getString(1);
        return name;
    }

    public ObservableList<TestData> selectPatientTests() throws SQLException {
        query = "SELECT * FROM `patient's tests`";
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next()) 
            test.add(new TestData.TestDataBuilder().setPatientId(rs.getInt("P_id")).setName(rs.getString("PTest_name")).setReceivingDate(rs.getDate("Recieving_date")).build());
        
        return test;
    }

    public ObservableList<TestData> selectPatientTests(String patient) throws SQLException {
       query = "SELECT `PTest_name`,`TCost`,`Recieving_date`FROM `patient's tests` ,`test`,`patient` WHERE `test`.`Test_name`=`patient's tests`.`PTest_name` and `patient`.`ID`=`patient's tests`.`P_id` and `patient`.`ID`=" + patient;
       ps = getConnection().prepareStatement(query);
       rs = ps.executeQuery(); 
       while (rs.next())
            test.add(
                new TestData.TestDataBuilder().setName(rs.getString("PTest_name")).setCost(rs.getFloat("TCost")).setReceivingDate(rs.getDate("Recieving_date")).build());
        
        return test;
    }

    private void prepareQuery(String query, TestData ptientTest) throws SQLException {
        ps = getConnection().prepareStatement(query);
        ps.setInt(1, ptientTest.getPatientId());
        ps.setString(2, ptientTest.getName());
        ps.setDate(3, (java.sql.Date) ptientTest.getReceivingDate());
        ps.executeUpdate();
    }

    public void insertPatientTest(TestData ptientTest) throws SQLException {
        query = "INSERT INTO `patient's tests`(`P_id`, `PTest_name`, `Recieving_date`) VALUES (?,?,?)";
        prepareQuery(query, ptientTest);
    }
    
    
    public ObservableList<DrugsData> selectPatientDrug() throws SQLException {
        query = "SELECT * FROM `patient's medicines`";
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next())
            drug.add(
                new DrugsData.DrugsDataBuiler().setName(rs.getString("Drug_name")).setDate(rs.getDate("Recieving_date").toLocalDate())
                .setPatientId(rs.getInt("Patient_id")).setQuantity(rs.getInt("Quantity")).build());
      
        return drug;
    }
    public ObservableList<DrugsData> selectPatientDrug(String patient) throws SQLException {
        query = "SELECT `Drug_name`, `Quantity`,`Cost`,`Recieving_date` FROM `patient's medicines`,`medicine`,`patient` "
                + "WHERE `patient's medicines`.`Drug_name`=`medicine`.`Medicine_name` and `patient's medicines`.`Patient_id`=`patient`.`ID` and `patient`.`ID`=" + patient;
        ps = getConnection().prepareStatement(query);
        rs = ps.executeQuery();
        while (rs.next())
            drug.add(
                new DrugsData.DrugsDataBuiler().setName(rs.getString("Drug_name")).setDate(rs.getDate("Recieving_date").toLocalDate())
                .setCost(rs.getFloat("Cost")).setQuantity(rs.getInt("Quantity")).build());
        return drug;
    }
    
    private void prepareQuery(String query, DrugsData patientDrugData) throws SQLException {
        ps = getConnection().prepareStatement(query);
        ps.setInt(1, patientDrugData.getPatientId());
        ps.setString(2, patientDrugData.getName());
        ps.setInt(3, patientDrugData.getQuantity());
        ps.setDate(4, Date.valueOf(patientDrugData.getDate()));
        ps.executeUpdate();

    }
    
    public void insertPatientDrug(DrugsData patientDrugData) throws SQLException{
        query = "INSERT INTO `patient's medicines`(`Patient_id`, `Drug_name`, `Quantity`, `Recieving_date`) VALUES (?,?,?,?)";
        prepareQuery(query , patientDrugData);
    }
}
