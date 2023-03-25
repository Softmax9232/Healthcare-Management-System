package Pharmacist;

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
import javafx.scene.layout.AnchorPane;
import utility.*;

public class PatientDrugsTab extends Controller implements Initializable {

    @FXML
    private AnchorPane anch1;
    @FXML
    private ComboBox drugCombo;
    @FXML
    private TableView<DrugsData> PatientDrugs;
    @FXML
    private TableColumn<DrugsData, Integer> id;
    @FXML
    private TableColumn<DrugsData, LocalDate> dates;
    @FXML
    private TableColumn<DrugsData, String> drugs;
    @FXML
    private TableColumn<DrugsData, Integer> quantities;
    @FXML
    private TextField patient_id, quantity, patientSearch;
    @FXML
    private DatePicker date;

    validateInputs input = new validateInputs();
    alerts alert = new alerts();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drugCombo.setItems(drugList);
        LoadDataInTable();
    }

    @Override
    public void LoadDataInTable() {
        try {
            PatientDrugs.setItems(new database.patient().selectPatientDrug());
            id.setCellValueFactory(new PropertyValueFactory<>("patientId"));
            dates.setCellValueFactory(new PropertyValueFactory<>("date"));
            drugs.setCellValueFactory(new PropertyValueFactory<>("name"));
            quantities.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    @FXML
    public void getSelectedData() {
        index = PatientDrugs.getSelectionModel().getSelectedIndex();
        patient_id.setText(id.getCellData(index).toString());
        date.setValue(java.time.LocalDate.parse(dates.getCellData(index).toString()));
        quantity.setText(quantities.getCellData(index).toString());
        drugCombo.setValue(drugs.getCellData(index));

    }

    @Override
    public DrugsData prepareData() {
        return new DrugsData.DrugsDataBuiler().setName(drugCombo.getValue().toString()).setDate(date.getValue())
                .setPatientId(Integer.parseInt(patient_id.getText())).setQuantity(Integer.parseInt(quantity.getText())).build();
    }

    @Override
    public boolean isNotValidInput() {
        return (prepareData().getDate().toString().trim().isEmpty() || prepareData().getName().trim().isEmpty() || 
                String.valueOf(prepareData().getQuantity()).trim().isEmpty() || String.valueOf(prepareData().getPatientId()).trim().isEmpty());
    }

    @Override
    @FXML
    public void cleanFields() {
        patient_id.setText("");
        date.setValue(LocalDate.now());
        quantity.setText("");
        drugCombo.setValue("");
    }

    @Override
    public void insertQuery() {
        try {
            new database.patient().insertPatientDrug(prepareData());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    public void addDataInTable() {
        PatientDrugs.getItems().add(
            new DrugsData.DrugsDataBuiler().setName(prepareData().getName()).setDate(prepareData().getDate())
                .setPatientId(prepareData().getPatientId()).setQuantity(prepareData().getQuantity()).build());
    }

    @Override
    @FXML
    public void search() {
        try {
            new Search().drugSearch(patientSearch, PatientDrugs, new database.patient().selectPatientDrug());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    @FXML
    public void logout() {
        new pageControl().LogOut(anch1);
    }
    
    @FXML
    public void validateIdText(){
        input.numericAndFloatingOnly(patient_id);
    }
    
    @FXML
    public void validateQuantityText(){
        input.numericAndFloatingOnly(quantity);
    }
    
    @Override
    public void updateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateDataInTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteDateFromTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}