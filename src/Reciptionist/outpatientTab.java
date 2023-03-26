package Reciptionist;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utility.*;

public class outpatientTab extends Controller implements Initializable {

    @FXML
    private AnchorPane anch;
    @FXML
    private TableView<userData> PatientTable;
    @FXML
    private TableColumn<userData, String> p_name, p_gender, p_illness, p_email, p_address;
    @FXML
    private TableColumn<userData, Integer> p_id , p_doctorid;
    @FXML
    private TableColumn<userData, Long> p_ssn , p_phone;
    @FXML
    private TableColumn<userData, LocalDate> p_dob, p_appointment;
    @FXML
    private TextField name, ssn,email, phone, search, doctorID, address;
    @FXML
    private DatePicker Appointment, birthdate;
    @FXML
    private ComboBox Gender , Disease;
    
    validateInputs input = new  validateInputs();
    alerts alert = new alerts();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Gender.setItems(genderList);
        Disease.setItems(diseaseList);
        LoadDataInTable();
    }

    @Override
    public void LoadDataInTable() {        
        try {
            PatientTable.setItems(new database.outpatient().getOutpatientData());
            p_id.setCellValueFactory(new PropertyValueFactory<>("patientId"));
            p_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            p_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            p_appointment.setCellValueFactory(new PropertyValueFactory<>("appointment"));
            p_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            p_address.setCellValueFactory(new PropertyValueFactory<>("address"));
            p_ssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
            p_illness.setCellValueFactory(new PropertyValueFactory<>("department"));
            p_dob.setCellValueFactory(new PropertyValueFactory<>("birhtdate"));
            p_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            p_doctorid.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        } 
    }

    @Override
    @FXML
    public void getSelectedData() {
        index = PatientTable.getSelectionModel().getSelectedIndex();
        name.setText(p_name.getCellData(index));
        address.setText(p_address.getCellData(index));
        phone.setText(p_phone.getCellData(index).toString());
        birthdate.setValue(p_dob.getCellData(index));
        Gender.setValue(p_gender.getCellData(index));
        ssn.setText(p_ssn.getCellData(index).toString());
        email.setText(p_email.getCellData(index));
        Disease.setValue(p_illness.getCellData(index));
        doctorID.setText(p_doctorid.getCellData(index).toString());
        Appointment.setValue(p_appointment.getCellData(index));

    }
    
    @Override
    public userData prepareData() {
        return new userData.UserBuilder().setPatientId(p_id.getCellData(index)).setName(name.getText()).setGender(Gender.getValue().toString())
                .setEmail(email.getText()).setAddress(address.getText()).setPhone(Long.parseLong(phone.getText())).setDepartment(Disease.getValue().toString())
                .setEmployeetId(Integer.parseInt(doctorID.getText())).setBirhtdate(birthdate.getValue())
                .setAppointment(Appointment.getValue()).setSsn(Long.parseLong(ssn.getText())).build();
    }
    @Override
    public boolean isNotValidInput() {
        return (String.valueOf(prepareData().getPatientId()).trim().isEmpty() || prepareData().getName().trim().isEmpty() || prepareData().getGender().trim().isEmpty() || prepareData().getEmail().trim().isEmpty() 
                || prepareData().getAddress().trim().isEmpty() || String.valueOf(prepareData().getPhone()).trim().isEmpty() || prepareData().getDepartment().trim().isEmpty() 
                || String.valueOf(prepareData().getEmployeeId()).trim().isEmpty() || prepareData().getBirhtdate().toString().trim().isEmpty() || prepareData().getAppointment().toString().trim().isEmpty() 
                || String.valueOf(prepareData().getSsn()).trim().isEmpty());
    }

    @Override
    @FXML
    public void cleanFields() {
        name.setText("");
        address.setText("");
        birthdate.setValue(LocalDate.now());
        Gender.setValue("");
        phone.setText("");
        ssn.setText("");
        Appointment.setValue(LocalDate.now());
        email.setText("");
        Disease.setValue("");
        doctorID.setText("");
    }

    @Override
    public void insertQuery() {
        try {
            new database.outpatient().insertData(prepareData());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    public void addDataInTable() {
        PatientTable.getItems().add(new userData.UserBuilder().setPatientId(prepareData().getPatientId()).setName(prepareData().getName()).setGender(prepareData().getGender())
                .setEmail(prepareData().getEmail()).setAddress(prepareData().getEmail()).setPhone(prepareData().getPhone()).setDepartment(prepareData().getDepartment())
                .setEmployeetId(prepareData().getEmployeeId()).setBirhtdate(prepareData().getBirhtdate())
                .setAppointment(prepareData().getAppointment()).setSsn(prepareData().getSsn()).build());
    }

    @Override
    public void updateQuery() {
        try {
            new database.outpatient().updateData(prepareData(),Appointment.getValue().toString());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    public void deleteQuery() {
        try {
            new database.outpatient().deleteData(prepareData());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    public void updateDataInTable() {
        PatientTable.getItems().set(index, 
                new userData.UserBuilder().setPatientId(prepareData().getPatientId()).setName(prepareData().getName()).setGender(prepareData().getGender())
                .setEmail(prepareData().getEmail()).setAddress(prepareData().getEmail()).setPhone(prepareData().getPhone()).setDepartment(prepareData().getDepartment())
                .setEmployeetId(prepareData().getEmployeeId()).setBirhtdate(prepareData().getBirhtdate())
                .setAppointment(prepareData().getAppointment()).setSsn(prepareData().getSsn()).build());
    }

    @Override
    public void deleteDateFromTable() {
        PatientTable.getItems().remove(index);
    }

    @Override
    @FXML
    public void search() {
        try {
            new Search().userSearch(search, PatientTable, new database.outpatient().getOutpatientData());
        } catch (SQLException ex) {
           alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    @FXML
    public void logout() {
        new pageControl().LogOut(anch);
    }

    @FXML
    private void validateIdText(){
        input.numericOnly(doctorID);
    }
    
    @FXML
    private void validateSsnText(){
        input.numericOnly(ssn);
    }
    
    @FXML
    private void validatePhoneText(){
        input.numericOnly(phone);
    }
    
    @FXML
    private void validateNameText(){
        input.numericOnly(name);
    }
}
