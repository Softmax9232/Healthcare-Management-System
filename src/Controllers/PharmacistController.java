package Controllers;

import Utilities.ModelTable;
import Utilities.utility;
import database.Connect;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;
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

public class PharmacistController implements Initializable { 
    
    @FXML 
    private TabPane mainpane;
    static int index;
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* this comboBox for drugs in patient's drugs tab*/
    @FXML
    private ComboBox pDrug;
    private final ObservableList<String> patient_Drug = FXCollections.observableArrayList("ACETAMINOPHEN", "ANDROCUR", "ANTACIDS", "ANTI HISTAMINES", "ANTIPSYCHOTIC", "ASPIRIN", "AVIL", "BENZODIAZEPINES", "BETA-BLOCKER", "BETADIN", "CANESTEN", "CIPROXIN", "CROCINE", "CYPROSTAT", "DESTOLIT", "DETTOL", "DICLOFENAC", "GLUCOSE", "HIDRASEC", "LIVER-52", "METHYLPHENIDATE", "NAMOSLATE", "OMEE", "OMNI GEL", "ORS");
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*  pharmacyTable in pharmacy tab*/
    @FXML
    private TableView<ModelTable> pharmacyTable;
    @FXML
    private TableColumn<ModelTable, String> drugname;
    @FXML
    private TableColumn<ModelTable, String> company;
    @FXML
    private TableColumn<ModelTable, Integer> Quantity;
    @FXML
    private TableColumn<ModelTable, Float> cost;
    @FXML
    private TableColumn<ModelTable, Date> date;

    private ObservableList<ModelTable> pharmacy = FXCollections.observableArrayList();
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* PatientDrugs table in patients's drugs tab */
    @FXML
    private TableView<ModelTable> PatientDrugs;
    @FXML
    private TableColumn<ModelTable, Integer> P_id;
    @FXML
    private TableColumn<ModelTable, String> Pdate;
    @FXML
    private TableColumn<ModelTable, String> drug;
    @FXML
    private TableColumn<ModelTable, String> pQuantity;

    private final ObservableList<ModelTable> drugs = FXCollections.observableArrayList();
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* elements for pharmacy tab */
    @FXML
    private TextField cost_per_item;
    @FXML
    private TextField quantity;
    @FXML
    private TextField drugcompany;
    @FXML
    private TextField drug_name;
    @FXML
    private DatePicker ReceivingDate;

    String DrugName, Company, Recieving;
    float Cost;
    int DQuantity;
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* elements for patient's drugs tab */
    @FXML
    private TextField patient_id;
    @FXML
    private TextField Pquantity;
    @FXML
    private DatePicker PDATE;
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private TextField pharmacysearch;
    
    @FXML
    private TextField patientSearch;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pDrug.setItems(patient_Drug);
        try {
            loadData2();
            loadData();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loadData() throws ClassNotFoundException, SQLException {
        pharmacy.clear();

        ResultSet rs = new Connect().select("SELECT * FROM `medicine`");

        while (rs.next()) {
            pharmacy.add((new ModelTable(
                    rs.getString("Medicine_name"),
                    rs.getString("Company"),
                    rs.getDate("Received_date"),
                    rs.getFloat("Cost"),
                    rs.getInt("MQuantity"))));
            pharmacyTable.setItems(pharmacy);

            drugname.setCellValueFactory(new PropertyValueFactory<>("drug"));
            company.setCellValueFactory(new PropertyValueFactory<>("company"));
            Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            cost.setCellValueFactory(new PropertyValueFactory<>("drugCost"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
        }
    }
    @FXML
    private void loadData2() throws ClassNotFoundException, SQLException {

        drugs.clear();

        ResultSet rs1 = new Connect().select("SELECT * FROM `patient's medicines`");

        while (rs1.next()) {
            drugs.add((new ModelTable(
                    rs1.getString("Drug_name"),
                    rs1.getDate("Recieving_date"),
                    rs1.getString("Patient_id"),
                    rs1.getInt("Quantity")))
            );

            PatientDrugs.setItems(drugs);

            P_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            Pdate.setCellValueFactory(new PropertyValueFactory<>("date"));
            drug.setCellValueFactory(new PropertyValueFactory<>("drug"));
            pQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        }

    }

    @FXML
    private void getSelected() {

        index = pharmacyTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) return;

        drug_name.setText(drugname.getCellData(index));
        drugcompany.setText(company.getCellData(index));
        quantity.setText(Quantity.getCellData(index).toString());
        cost_per_item.setText(cost.getCellData(index).toString());
        ReceivingDate.setValue(java.time.LocalDate.parse(date.getCellData(index).toString()));
    }

    private void database(String query) {

        try {
            Connection connection = new database.Connect().initialize();

            PreparedStatement preparedStatement1 = connection.prepareStatement(query);
            preparedStatement1.setString(1, DrugName);
            preparedStatement1.setFloat(2, Cost);
            preparedStatement1.setString(3, Company);
            preparedStatement1.setInt(4, DQuantity);
            preparedStatement1.setString(5, Recieving);
            preparedStatement1.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getData() {
        DrugName = drug_name.getText();
        Company = drugcompany.getText();
        DQuantity = Integer.valueOf(quantity.getText());
        Cost = Float.valueOf(cost_per_item.getText());
        Recieving = ReceivingDate.getValue().toString();
    }

    private void clean() {
        drug_name.setText("");
        drugcompany.setText("");
        quantity.setText("");
        cost_per_item.setText("");
        ReceivingDate.setValue(LocalDate.now());
    }

     @FXML 
    private void clear(){
       clean();
    }
    
    
    @FXML
    private void updateDrug() {
        getData();

        if (DrugName.isEmpty() || Company.isEmpty() || Recieving.isEmpty() || String.valueOf(Cost).isEmpty() || String.valueOf(DQuantity).isEmpty()) {
            new utility().make_Alert("Please Fill All DATA");
        } else {

            database("UPDATE `medicine` SET `Medicine_name`=?, `Cost`=?, `Company`=?, `MQuantity`=?, `Received_date`=? where `Medicine_name`='" + drugname.getCellData(index) + "'");
            pharmacy.set(index, new ModelTable(DrugName, Company, Date.valueOf(Recieving), Cost, DQuantity));
            clean();
        }
    }

    @FXML
    private void addDrug() {
        getData();

        if (DrugName.isEmpty() || Company.isEmpty() || Recieving.isEmpty() || String.valueOf(Cost).isEmpty() || String.valueOf(DQuantity).isEmpty()) {
            new utility().make_Alert("Please Fill All DATA");
        } else {

            database("INSERT INTO `medicine`(`Medicine_name`, `Cost`, `Company`, `MQuantity`, `Received_date`) VALUES (?,?,?,?,?)");
            pharmacyTable.getItems().add(new ModelTable(DrugName, Company, Date.valueOf(Recieving), Cost, DQuantity));
            clean();
        }
    }

    @FXML
    private void addPatientDrug() {
        String id = patient_id.getText();
        int pquantity = Integer.valueOf(Pquantity.getText());
        String pdrug = pDrug.getSelectionModel().getSelectedItem().toString();
        String pdate = PDATE.getValue().toString();

        if (pdrug.isEmpty() || pdate.isEmpty() || String.valueOf(pquantity).isEmpty() || String.valueOf(id).isEmpty()) {
            new utility().make_Alert("Please Fill All DATA");
        } else {

            try {
                Connection connection = new database.Connect().initialize();

                PreparedStatement preparedStatement1 = connection.prepareStatement("INSERT INTO `patient's medicines`(`Patient_id`, `Drug_name`, `Quantity`, `Recieving_date`) VALUES (?,?,?,?)");
                preparedStatement1.setString(1, id);
                preparedStatement1.setString(2, pdrug);
                preparedStatement1.setInt(3, pquantity);
                preparedStatement1.setString(4, pdate);
                preparedStatement1.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(PharmacistController.class.getName()).log(Level.SEVERE, null, ex);
            }
            PatientDrugs.getItems().add(new ModelTable(pdrug, Date.valueOf(pdate), id, pquantity));
            clean();
        }
    }
    
    @FXML
    private void Delete() {
        Optional<ButtonType> action = new Utilities.utility().make_Alert("Are You Sure");
        if (action.get() == ButtonType.OK) {
            try {
                               Connection connection = new database.Connect().initialize();


                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM `medicine` WHERE `Medicine_name`='" + drugname.getCellData(index) + "'");
                preparedStatement.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (index <= -1) {
                return;
            }
            pharmacyTable.getItems().remove(index);
            index = -1;

            clean();
        }
    }
     @FXML
     private void PH_search(){
       new utility().Search(pharmacysearch , pharmacyTable , pharmacy);
     }
      @FXML
     private void P_search(){
       new utility().Search(patientSearch , PatientDrugs , drugs);
     }
     
     @FXML
    private void logout(){
       new utility().LogOut( mainpane);
    }
}
