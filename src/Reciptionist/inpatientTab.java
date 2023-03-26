package Reciptionist;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import utility.*;

public class inpatientTab extends Controller implements Initializable {

    @FXML
    private TableView<userData> INPatientTable;
    @FXML
    private TableColumn<userData, String> In_name, in_address, in_gender, in_email, in_illness, in_RelativeName;
    @FXML
    private TableColumn<userData, LocalDate> in_dob, in_dateArriving;
    @FXML
    private TableColumn<userData, Integer> in_id , in_roomId , in_docid , in_Nid;
    @FXML
    private TableColumn<userData, Long> in_ssn, in_RelativePhone;
    @FXML
    private TextField name, ssn, email, address, nurse_id, room_id, doc_id, search, relative_name, relative_phone;
    @FXML
    private DatePicker birthday;
    @FXML
    private ComboBox disease, gender;
    
    validateInputs input = new validateInputs();
    alerts alert = new alerts();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        disease.setItems(new Reciptionist.outpatientTab().diseaseList);
        gender.setItems(new Reciptionist.outpatientTab().genderList);
        LoadDataInTable();
    }

    @Override
    public void LoadDataInTable() {
        try {
            INPatientTable.setItems(new database.inpatient().getInpatientData());
            
            In_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            in_address.setCellValueFactory(new PropertyValueFactory<>("address"));
            in_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            in_dob.setCellValueFactory(new PropertyValueFactory<>("birhtdate"));
            in_ssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
            in_email.setCellValueFactory(new PropertyValueFactory<>("email"));
            in_illness.setCellValueFactory(new PropertyValueFactory<>("department"));
            in_dateArriving.setCellValueFactory(new PropertyValueFactory<>("date"));
            in_docid.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
            in_Nid.setCellValueFactory(new PropertyValueFactory<>("employeeId2"));
            in_roomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
            in_RelativeName.setCellValueFactory(new PropertyValueFactory<>("relativeName"));
            in_RelativePhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            in_id.setCellValueFactory(new PropertyValueFactory<>("patientId"));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    public void getSelectedData() {
        index = INPatientTable.getSelectionModel().getSelectedIndex();

        name.setText(In_name.getCellData(index));
        address.setText(in_address.getCellData(index));
        gender.setValue(in_gender.getCellData(index));
        ssn.setText(in_ssn.getCellData(index).toString());
        email.setText(in_email.getCellData(index));
        disease.setValue(in_illness.getCellData(index));
        nurse_id.setText(in_Nid.getCellData(index).toString());
        room_id.setText(in_roomId.getCellData(index).toString());
        relative_name.setText(in_RelativeName.getCellData(index));
        doc_id.setText(in_docid.getCellData(index).toString());
        birthday.setValue(in_dob.getCellData(index));
        relative_phone.setText(in_RelativePhone.getCellData(index).toString());
    }

    @Override
    public userData prepareData() {
        return new userData.UserBuilder().setName(name.getText()).setPatientId(in_id.getCellData(index)).setGender(gender.getValue().toString())
                .setEmail(email.getText()).setAddress(address.getText()).setPhone(Long.parseLong(relative_phone.getText())).setRelativeName(relative_name.getText())
                .setEmployeetId(Integer.parseInt(doc_id.getText())).setDepartment(disease.getValue().toString()).setEmployeetId2(Integer.parseInt(nurse_id.getText()))
                .setRoomId(Integer.parseInt(room_id.getText())).setDate(birthday.getValue()).setSsn(Long.parseLong(ssn.getText())).build();
    }

    @Override
    public boolean isNotValidInput() {
        return (prepareData().getName().trim().isEmpty() || String.valueOf(prepareData().getPatientId()).trim().isEmpty() || prepareData().getGender().trim().isEmpty() || prepareData().getEmail().trim().isEmpty() || 
                prepareData().getAddress().trim().isEmpty() || String.valueOf(prepareData().getPhone()).trim().isEmpty() || prepareData().getRelativeName().trim().isEmpty() ||
                String.valueOf(prepareData().getEmployeeId()).trim().isEmpty() || prepareData().getDepartment().trim().isEmpty() || String.valueOf(prepareData().getEmployeeId2()).trim().isEmpty() || 
                String.valueOf(prepareData().getRoomId()).trim().isEmpty() || prepareData().getDate().toString().trim().isEmpty() || String.valueOf(prepareData().getSsn()).trim().isEmpty());
    }

    @Override
    @FXML
    public void cleanFields() {
        name.setText("");
        address.setText("");
        birthday.setValue(LocalDate.now());
        gender.setValue("");
        ssn.setText("");
        email.setText("");
        disease.setValue("");
        doc_id.setText("");
        nurse_id.setText("");
        room_id.setText("");
        relative_name.setText("");
        relative_phone.setText("");
    }

    @Override
    public void insertQuery() {
        try {
            new database.inpatient().insertInpatient(prepareData());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    public void addDataInTable() {
        INPatientTable.getItems().add(new userData.UserBuilder().setName(prepareData().getName()).setPatientId(prepareData().getPatientId()).setGender(prepareData().getGender())
                .setEmail(prepareData().getEmail()).setAddress(prepareData().getAddress()).setPhone(prepareData().getPhone()).setRelativeName(prepareData().getRelativeName())
                .setEmployeetId(prepareData().getEmployeeId()).setDepartment(prepareData().getDepartment()).setEmployeetId2(prepareData().getEmployeeId2())
                .setRoomId(prepareData().getRoomId()).setDate(prepareData().getDate()).setSsn(prepareData().getSsn()).build());
    }

    @Override
    public void updateQuery() {
        try {
            new database.inpatient().updateInpatient(prepareData(), in_id.getCellData(index));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    public void deleteQuery() {
        try {
            new database.inpatient().deleteInpatient(prepareData());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    public void updateDataInTable() {
        INPatientTable.getItems().set(index, new userData.UserBuilder().setName(prepareData().getName()).setPatientId(prepareData().getPatientId()).setGender(prepareData().getGender())
                .setEmail(prepareData().getEmail()).setAddress(prepareData().getAddress()).setPhone(prepareData().getPhone()).setRelativeName(prepareData().getRelativeName())
                .setEmployeetId(prepareData().getEmployeeId()).setDepartment(prepareData().getDepartment()).setEmployeetId2(prepareData().getEmployeeId2())
                .setRoomId(prepareData().getRoomId()).setDate(prepareData().getDate()).setSsn(prepareData().getSsn()).build());
    }

    @Override
    public void deleteDateFromTable() {
        INPatientTable.getItems().remove(index);
    }

    @Override
    @FXML
    public void search() {
        try {
            new Search().userSearch(search, INPatientTable, new database.inpatient().getInpatientData());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    public void logout() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @FXML
    private void validateNurseText(){
        input.numericOnly(nurse_id);
    }
    
    @FXML
    private void validateDoctorText(){
        input.numericOnly(doc_id);
    }
    
    @FXML
    private void validateRoomText(){
        input.numericOnly(room_id);
    }
    
    @FXML
    private void validateSsnText(){
        input.numericOnly(ssn);
    }
    
    @FXML
    private void validatePhoneText(){
        input.numericOnly(relative_phone);
    }
    
   @FXML
    private void validatePatientName(){
        input.lettersOnly(name);
    }
    
    @FXML
    private void validateRelativeName(){
        input.lettersOnly(name);
    }
 
}
