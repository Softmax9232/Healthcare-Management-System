package LaboratoryTechnician;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utility.*;

public class TestsTab extends Controller implements Initializable {

    @FXML
    private AnchorPane anch2;
    @FXML
    private TextField testname, testcost, LaboratorySearch;
    @FXML
    private TableView<TestData> TestTable;
    @FXML
    private TableColumn<TestData, String> Tname;
    @FXML
    private TableColumn<TestData, Float> Tcost;
    
    alerts alert = new alerts();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadDataInTable();  
    }
    
    @Override
    public void LoadDataInTable() {
        try {
            TestTable.setItems(new database.Test().getTestsData());
            Tname.setCellValueFactory(new PropertyValueFactory<>("name"));        
            Tcost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }
    
    @Override
    public TestData prepareData() {      
        return new TestData.TestDataBuilder().setName(testname.getText()).setCost(Float.parseFloat(testcost.getText())).build();
    }
    
    @Override
    @FXML
    public void getSelectedData() {
        index = TestTable.getSelectionModel().getSelectedIndex();
        testname.setText(Tname.getCellData(index));
        testcost.setText(Tcost.getCellData(index).toString());
    }
    
    @Override
    public boolean isNotValidInput() {
        return (prepareData().getName().trim().isEmpty() || String.valueOf(prepareData().getCost()).trim().isEmpty());
    }
    
    @Override
    @FXML
    public void cleanFields() {
        testcost.setText("");
        testname.setText("");
    }
   
    @Override
    public void insertQuery() {
        try {
            new database.Test().insertTest(prepareData());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }
    
    @Override
    public void addDataInTable() {
        TestTable.getItems().add(new TestData.TestDataBuilder().setName(prepareData().getName()).setCost(prepareData().getCost()).build());
    }
    
    @Override
    public void updateQuery() {
        try {
            new database.Test().updateTest(prepareData(), Tname.getCellData(index));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }
    
    @Override
    public void deleteQuery() {
        try {
            new database.Test().deleteTest(Tname.getCellData(index));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }
    
    @Override
    public void updateDataInTable() {
        TestTable.getItems().set(index, new TestData.TestDataBuilder().setName(prepareData().getName()).setCost(prepareData().getCost()).build());
    }
    
    @Override
    @FXML
    public void search() {
        try {
            new utility.Search().testSearch(LaboratorySearch, TestTable, new database.Test().getTestsData());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }
    
    @Override
    @FXML
    public void logout() {
        new pageControl().LogOut(anch2);
    }
    
    @Override
    public void deleteDateFromTable() {
        TestTable.getItems().remove(index);
    }
    
    @FXML
    private void validateCostText(){
        new validateInputs().numericAndFloatingOnly(testcost);
    }
    
    @FXML
    private void validateNameText(){
        new validateInputs().lettersOnly(testname);
    }
}