package Controllers;

import Utilities.ModelTable;
import Utilities.utility;
import database.Connect;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class laboratory_technicianController implements Initializable {

    @FXML
    private TabPane mainpane;

    @FXML
    private ComboBox TestCombo;
    private final ObservableList<String> tests = FXCollections.observableArrayList("BIOPSY", "BLOOD TEST", "CBC", "CT-SCAN", "ENDOSCOPY", "MRI SCAN", "ULTRASOUND", "URINE TEST", "X-RAY");

    @FXML
    private TableView<ModelTable> PatientTest;
    @FXML
    private TableColumn<ModelTable, String> PT_id;
    @FXML
    private TableColumn<ModelTable, String> PTdate;
    @FXML
    private TableColumn<ModelTable, String> PTname;

    private final ObservableList<ModelTable> ptest = FXCollections.observableArrayList();

    @FXML
    private TableView<ModelTable> TestTable;
    @FXML
    private TableColumn<ModelTable, String> Tname;
    @FXML
    private TableColumn<ModelTable, Float> Tcost;

    private final ObservableList<ModelTable> test = FXCollections.observableArrayList();

    @FXML
    private TextField testname;
    @FXML
    private TextField testcost;
    @FXML
    private TextField id;
    @FXML
    private DatePicker date;

    @FXML
    private TextField LaboratorySearch;
    @FXML
    private TextField PatientSearch;

    String Testname;
    float cost;
    static int index;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TestCombo.setItems(tests);
        try {
            T_loadData();
            T_loadData2();
        } catch (ClassNotFoundException | SQLException ex) {
            new Utilities.utility().make_Alert(ex.getMessage());  
        }
    }

    private void T_loadData() {
        try {
            ptest.clear();          
            ResultSet rs = new Connect().select("SELECT * FROM `patient's tests`");
            
            while (rs.next()) {
                ptest.add((new ModelTable(
                        rs.getString("P_id"),
                        rs.getString("PTest_name"),
                        rs.getDate("Recieving_date"))));
                
                PatientTest.setItems(ptest);            
                PT_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                PTdate.setCellValueFactory(new PropertyValueFactory<>("date"));
                PTname.setCellValueFactory(new PropertyValueFactory<>("Test"));            
            }
        } catch (SQLException ex) {
            new Utilities.utility().make_Alert(ex.getMessage());  
        }
    }

    private void T_loadData2() throws ClassNotFoundException, SQLException {
        test.clear();
        ResultSet rs1 = new Connect().select("SELECT * FROM `test`");
        while (rs1.next()) {
            test.add((new ModelTable(
                    rs1.getString("Test_name"),
                    rs1.getFloat("Tcost")))
            );
            TestTable.setItems(test);
            Tname.setCellValueFactory(new PropertyValueFactory<>("Test"));
            Tcost.setCellValueFactory(new PropertyValueFactory<>("TestCost"));
        }
    }

    @FXML
    private void getSelected() {
        index = TestTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        testname.setText(Tname.getCellData(index));
        testcost.setText(Tcost.getCellData(index).toString());
    }

    private PreparedStatement database(String query) {
        PreparedStatement preparedStatement = null;
        try {
            Connection connection = new database.Connect().initialize();
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException ex) {
           new Utilities.utility().make_Alert(ex.getMessage());  
        }
        return preparedStatement;
    }

    private void getData() {
        Testname = testname.getText();
        cost = Float.parseFloat(testcost.getText());
    }

    private void clean() {
        testname.setText("");
        testcost.setText("");
    }

    @FXML
    private void clear() {
        clean();
    }

    @FXML
    private void updateTest() {
        getData();

        if (Testname.isEmpty() || String.valueOf(cost).isEmpty()) {
            new utility().make_Alert("Please Fill All DATA");
        } else {
            try {
                PreparedStatement preparedStatement = database("UPDATE `test` SET `Test_name`=?,`TCost`=? WHERE `test`.`Test_name`='" + Tname.getCellData(index) + "'");
                preparedStatement.setString(1, Testname);
                preparedStatement.setFloat(2, cost);
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                new Utilities.utility().make_Alert(ex.getMessage());  
            }

            test.set(index, new ModelTable(Testname, cost));
            clean();
        }
    }

    @FXML
    private void addTest() {
        getData();

        if (Testname.isEmpty() || String.valueOf(cost).isEmpty()) {
            new utility().make_Alert("Please Fill All DATA");
        } else {

            try {
                PreparedStatement preparedStatement = database("INSERT INTO `test`(`Test_name`, `TCost`) VALUES (?,?)");
                preparedStatement.setString(1, Testname);
                preparedStatement.setFloat(2, cost);
                preparedStatement.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(laboratory_technicianController.class.getName()).log(Level.SEVERE, null, ex);
            }
            TestTable.getItems().add(new ModelTable(Testname, cost));
            clean();
        }
    }

    @FXML
    private void addPatientTest() {
        String Tid = id.getText();
        String Ptest = TestCombo.getSelectionModel().getSelectedItem().toString();
        String pdate = date.getValue().toString();

        if (Ptest.isEmpty() || pdate.isEmpty() || String.valueOf(id).isEmpty()) {
            new utility().make_Alert("Please Fill All DATA");
        } else {

            try {
                PreparedStatement preparedStatement1 = database("INSERT INTO `patient's tests`(`P_id`, `PTest_name`, `Recieving_date`) VALUES (?,?,?)");
                preparedStatement1.setString(1, Tid);
                preparedStatement1.setString(2, Ptest);
                preparedStatement1.setString(3, pdate);
                preparedStatement1.executeUpdate();

            } catch (SQLException ex) {
                new Utilities.utility().make_Alert(ex.getMessage());  
            }
            PatientTest.getItems().add(new ModelTable(Tid, Ptest, Date.valueOf(pdate)));
            clean();
        }
    }

    @FXML
    private void Delete() {
        Optional<ButtonType> action = new Utilities.utility().make_Alert("Are You Sure");
        if (action.get() == ButtonType.OK) {
            try {
                PreparedStatement preparedStatement = database("DELETE FROM `test` WHERE `test`.`Test_name`='" + Tname.getCellData(index) + "'");
                preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                new Utilities.utility().make_Alert(ex.getMessage());  
            }
            if (index <= -1) {
                return;
            }
            TestTable.getItems().remove(index);
            index = -1;
            clean();
        }
    }

    @FXML
    private void L_search() {
        new utility().Search(LaboratorySearch, TestTable, test);
    }

    @FXML
    private void P_search() {
        new utility().Search(PatientSearch, PatientTest, ptest);
    }

    @FXML
    private void logout() {
        new utility().LogOut(mainpane);
    }
}
