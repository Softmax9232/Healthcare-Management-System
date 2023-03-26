package LaboratoryTechnician;

import java.net.URL;
import java.sql.*;
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

public class patientTestsTab extends Controller implements Initializable {

    @FXML
    private AnchorPane anch1;
    @FXML
    private ComboBox TestCombo;
    @FXML
    private TextField testname, id, PatientSearch;
    @FXML
    private DatePicker date;
    @FXML
    private TableView<TestData> PatientTest;
    @FXML
    private TableColumn<TestData, String> Tname;
    @FXML
    private TableColumn<TestData, Integer> P_id;
    @FXML
    private TableColumn<TestData, Date> Tdate;

    alerts alert = new alerts();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TestCombo.setItems(testList);
        LoadDataInTable();
    }

    @Override
    public void LoadDataInTable() {
        try {
            PatientTest.setItems(new database.patient().selectPatientTests());
            P_id.setCellValueFactory(new PropertyValueFactory<>("patientId"));
            Tdate.setCellValueFactory(new PropertyValueFactory<>("receivingDate"));
            Tname.setCellValueFactory(new PropertyValueFactory<>("name"));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    public TestData prepareData() {
        return new TestData.TestDataBuilder().setPatientId(Integer.parseInt(id.getText())).setName(TestCombo.getValue().toString()).setReceivingDate(Date.valueOf(date.getValue())).build();
    }

    @Override
    public boolean isNotValidInput() {
        return (String.valueOf(prepareData().getPatientId()).trim().isEmpty() || prepareData().getName().trim().isEmpty() || 
                prepareData().getReceivingDate().toString().trim().isEmpty());
    }

    @Override
    public void insertQuery() {
        try {
            new database.patient().insertPatientTest(prepareData());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }

    }

    @Override
    public void addDataInTable() {
        PatientTest.getItems().add(new TestData.TestDataBuilder().setPatientId(prepareData().getPatientId()).
                setName(prepareData().getName()).setReceivingDate(prepareData().getReceivingDate()).build());
    }

    @FXML
    @Override
    public void cleanFields() {
        testname.setText("");
        date.setValue(LocalDate.now());
        TestCombo.setValue("");
    }

    @FXML
    @Override
    public void search() {
        try {
            new Search().testSearch(PatientSearch, PatientTest, new database.patient().selectPatientTests());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @FXML
    @Override
    public void logout() {
        new pageControl().LogOut(anch1);
    }

    @FXML
    private void validateIdText(){
        new validateInputs().numericAndFloatingOnly(id);
    }
    
    @Override
    public void updateDataInTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public void deleteDateFromTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getSelectedData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}