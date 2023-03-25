package Doctor;

import Authentication.controller;
import utility.*;
import database.*;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


public class HomePageController extends Controller implements Initializable {

    @FXML
    private AnchorPane mainpane;
    @FXML
    private Label name;
    @FXML
    private TextField patientid, patientname, patientphone, patientemail, patientweight, patientheight, Pprescription, search;
    @FXML
    private DatePicker PDATE, Pappoint, nextAppoint;
    @FXML
    private TableView<userData> DoctorTable;
    @FXML
    private TableColumn<userData, Integer> patient_id;
    @FXML
    private TableColumn<userData, Long> phone;
    @FXML
    private TableColumn<userData, Float> weight, height;
    @FXML
    private TableColumn<userData, LocalDate> appointment, dob,Nappointment;
    @FXML
    private TableColumn<userData, String> Atime, Ntime, email, prescription, gender, blood, patient_name;
    @FXML
    private ComboBox PBlood, Pgender, Time, NTime;
    
    validateInputs input = new validateInputs();
    alerts alert = new alerts();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadDataInTable();
        name.setText(controller.Employee_name);
        PBlood.setItems(bloodGroupList);
        Pgender.setItems(genderList);
        Time.setItems(time);
        NTime.setItems(time);
    }
    
    @Override
    public void LoadDataInTable() {
        try {
            DoctorTable.setItems(new database.patient().selectPatientData(controller.Employee_id));
            patient_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            appointment.setCellValueFactory(new PropertyValueFactory<>("appointment"));
            email.setCellValueFactory(new PropertyValueFactory<>("email"));
            blood.setCellValueFactory(new PropertyValueFactory<>("bloodGroup"));
            weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
            height.setCellValueFactory(new PropertyValueFactory<>("height"));
            dob.setCellValueFactory(new PropertyValueFactory<>("birhtdate"));
            phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            prescription.setCellValueFactory(new PropertyValueFactory<>("prescription"));
            patient_id.setCellValueFactory(new PropertyValueFactory<>("patientId"));
            Nappointment.setCellValueFactory(new PropertyValueFactory<>("nextAppointment"));
            Atime.setCellValueFactory(new PropertyValueFactory<>("time1"));
            Ntime.setCellValueFactory(new PropertyValueFactory<>("time2"));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }
    
    @FXML
    @Override
    public void getSelectedData() {
        index = DoctorTable.getSelectionModel().getSelectedIndex();
        patientname.setText(patient_name.getCellData(index));
        patientphone.setText(phone.getCellData(index).toString());
        PBlood.setValue(blood.getCellData(index));
        Pgender.setValue(gender.getCellData(index));
        patientweight.setText(weight.getCellData(index).toString());
        patientheight.setText(height.getCellData(index).toString());
        patientemail.setText(email.getCellData(index));
        Pprescription.setText(prescription.getCellData(index));
        patientid.setText(patient_id.getCellData(index).toString());
        Time.setValue(Atime.getCellData(index));
        NTime.setValue(Ntime.getCellData(index));
        nextAppoint.setValue(Nappointment.getCellData(index));
        PDATE.setValue(dob.getCellData(index));
        Pappoint.setValue(appointment.getCellData(index));
    }

    @Override
    public userData prepareData() {
        if(Pappoint.getValue().equals(nextAppoint.getValue())){
            alert.makeInfoAlert("The current appointment and the next appointment cannot be the same!");
            return null;
        }
        return new userData.UserBuilder().setBloodGroup(PBlood.getValue().toString())
                .setWeight(Float.parseFloat(patientweight.getText())).setHight(Float.parseFloat(patientheight.getText()))
                .setPrescription(Pprescription.getText()).setNextAppointment(nextAppoint.getValue()).
                setTime2(NTime.getValue().toString()).build();
     }

    @Override
    public void cleanFields() {
        patientname.setText("");
        Pgender.setValue("");
        PBlood.setValue("");
        patientemail.setText("");
        Pprescription.setText("");
        Pappoint.setValue(LocalDate.now());
        nextAppoint.setValue(LocalDate.now());
        PDATE.setValue(LocalDate.now());
        patientid.setText("");
        patientphone.setText("");
        patientweight.setText("");
        patientheight.setText("");
        Time.setValue("");
        NTime.setValue("");
    }
    
    @Override
    public boolean isNotValidInput() {
        return (String.valueOf(prepareData().getHeight()).trim().isEmpty()|| String.valueOf(prepareData().getWeight()).trim().isEmpty() 
                || prepareData().getPrescription().trim().isEmpty() || prepareData().getNextAppointment() == null || 
                prepareData().getTime2().trim().isEmpty());
    }

    @Override
    public void updateQuery() {
        try {
            new patient().updatePatient(prepareData(), patient_id.getCellData(index), Pappoint.getValue().toString());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    public void updateDataInTable() {
        DoctorTable.getItems().set(index,
            new userData.UserBuilder().setPatientId(prepareData().getPatientId()).setName(prepareData().getName())
                .setGender(prepareData().getGender()).setEmail(prepareData().getEmail()).setAppointment(prepareData().getAppointment())
                .setBloodGroup(prepareData().getBloodGroup()).setWeight(prepareData().getWeight())
                .setHight(prepareData().getHeight()).setBirhtdate(prepareData().getBirhtdate()).setPhone(prepareData().getPhone())
                .setPrescription(prepareData().getPrescription()).setNextAppointment(prepareData().getNextAppointment())
                .setTime1(prepareData().getTime1()).setTime2(prepareData().getTime2()).build());
    }

    @FXML
    @Override
    public void search() {
        try {
            new Search().userSearch(search, DoctorTable, new database.patient().selectPatientData(controller.Employee_id));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @FXML
    private void printPrescription() {
        String pre_doctor = getPrescriptionData();
        CreatePrescription(pre_doctor);
    }

    private void CreatePrescription(String doctorName){
        try {
            String path = "doctorPrescriptions.jrxml";
            JasperReport prescription = JasperCompileManager.compileReport(path);

            Map<String, Object> para = new HashMap<>();
            para.put("Patient_ID", patient_id.getCellData(index));
            para.put("Appointment", appointment.getCellData(index));
            para.put("Doctor_name", doctorName);
            JasperPrint gp = JasperFillManager.fillReport(prescription, para, DatabaseConnection.getConnection());

            JasperViewer.viewReport(gp, false);
        } catch (JRException ex) {
            alert.makeInfoAlert(ex.getMessage());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        } 
    }

    private String getPrescriptionData() {
        String data = "";
        try {
            data = new database.prescription().getPrescription(patient_id.getCellData(index), appointment.getCellData(index).toString());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
        return data;
    }

    @FXML
    @Override
    public void logout() {
        new pageControl().LogOut(mainpane);
    }
    
    @FXML
    public void validateWeightText(){
        input.numericAndFloatingOnly(patientweight);
    }
    
    @FXML
    public void validateHeightText(){
        input.numericAndFloatingOnly(patientheight);
    }
    
    @Override
    public void insertQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addDataInTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteDateFromTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}