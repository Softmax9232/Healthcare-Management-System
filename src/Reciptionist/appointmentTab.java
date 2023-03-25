package Reciptionist;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utility.alerts;
import utility.userData;

public class appointmentTab implements Initializable {

    @FXML
    private ComboBox combo;
    private final ObservableList<String> disease = FXCollections.observableArrayList("ALLERGY", "INTENSIVECARE", "ANESTHESIOLOGY", "CARDIOLOGY", "ENT", "NEUROLOGY", "ORTHOPEDICS", "PATHOLOGY", "RADIOLOGY", "SURGERY");

    @FXML
    private TableView<userData> appointmentTable;
    @FXML
    private TableColumn<userData, String> docName, appoint, appoint1;
    @FXML
    private TableColumn<userData, Integer>  docID;

    alerts alert = new alerts();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combo.setItems(disease);
    }

    @FXML
    private void searchDoctor() throws ClassNotFoundException {
        try {
            appointmentTable.setItems(new database.employee().searchForDoctors(combo.getValue().toString()));
            docName.setCellValueFactory(new PropertyValueFactory<>("name"));
            appoint.setCellValueFactory(new PropertyValueFactory<>("time1"));
            appoint1.setCellValueFactory(new PropertyValueFactory<>("time2"));
            docID.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }
}