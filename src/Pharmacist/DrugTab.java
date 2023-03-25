package Pharmacist;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utility.*;

public class DrugTab extends Controller implements Initializable{

    @FXML
    private AnchorPane anch2;
    @FXML
    private TableView<DrugsData> pharmacyTable;
    @FXML
    private TableColumn<DrugsData, String> drugname , company;
    @FXML
    private TableColumn<DrugsData, Integer> Quantity;
    @FXML
    private TableColumn<DrugsData, Float> cost;
    @FXML
    private TableColumn<DrugsData, LocalDate> date;
    @FXML
    private TextField cost_per_item , quantity , drugcompany , drug_name , pharmacysearch;
    @FXML
    private DatePicker ReceivingDate;
        
    validateInputs input = new validateInputs();
    alerts alert = new alerts();
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LoadDataInTable();
    }

    @Override
    public void LoadDataInTable() {
        try {
            pharmacyTable.setItems(new database.Drug().getDrugsData());
            drugname.setCellValueFactory(new PropertyValueFactory<>("name"));
            company.setCellValueFactory(new PropertyValueFactory<>("company"));
            Quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    public void getSelectedData() {
        index = pharmacyTable.getSelectionModel().getSelectedIndex();
        drug_name.setText(drugname.getCellData(index));
        ReceivingDate.setValue(java.time.LocalDate.parse(date.getCellData(index).toString()));
        drugcompany.setText(company.getCellData(index));
        quantity.setText(Quantity.getCellData(index).toString());
        cost_per_item.setText(cost.getCellData(index).toString());
    }

    @Override
    public DrugsData prepareData() {
        return new DrugsData.DrugsDataBuiler().setName(drug_name.getText()).setCompany(drugcompany.getText()).setDate(ReceivingDate.getValue())
                .setCost(Float.parseFloat(cost_per_item.getText())).setQuantity(Integer.parseInt(quantity.getText())).build();
    }

    @Override
    public boolean isNotValidInput() {
        return (prepareData().getName().trim().isEmpty() || prepareData().getCompany().trim().isEmpty() || 
                prepareData().getDate().toString().trim().isEmpty()  || String.valueOf(prepareData().getCost()).trim().isEmpty()  || 
                String.valueOf(prepareData().getQuantity()).trim().isEmpty());
    }

    @Override
    public void cleanFields() {
        drug_name.setText("");
        drugcompany.setText("");
        quantity.setText("");
        cost_per_item.setText("");
        ReceivingDate.setValue(LocalDate.now());
    }

   
    @Override
    public void insertQuery() {
        try {    
            new database.Drug().inserQuery(prepareData());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    public void addDataInTable() {
        pharmacyTable.getItems().add(
                new DrugsData.DrugsDataBuiler().setName(prepareData().getName()).setCompany(prepareData().getCompany()).setDate(prepareData().getDate())
                .setCost(prepareData().getCost()).setQuantity(prepareData().getQuantity()).build());
    }

    @Override
    public void updateQuery() {
        try {
            new database.Drug().updateQuery(prepareData(), drugname.getCellData(index));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    public void deleteQuery() {
        try {
            new database.Drug().deleteQuery(prepareData(), drugname.getCellData(index));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }

    }

    @Override
    public void updateDataInTable() {
        pharmacyTable.getItems().set(index, 
                new DrugsData.DrugsDataBuiler().setName(prepareData().getName()).setCompany(prepareData().getCompany()).setDate(prepareData().getDate())
                .setCost(prepareData().getCost()).setQuantity(prepareData().getQuantity()).build());
    }

    @Override
    public void deleteDateFromTable() {
        pharmacyTable.getItems().remove(index);
    }

    @Override
    @FXML
    public void search() {
        try {
            new Search().drugSearch(pharmacysearch , pharmacyTable , new database.Drug().getDrugsData());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    @FXML
    public void logout() {
        new pageControl().LogOut(anch2);
    }
    
    @FXML
    public void validateCostText(){
        input.numericAndFloatingOnly(cost_per_item);
    }
    
    @FXML
    public void validateQuantityText(){
        input.numericOnly(quantity);
    }
    @FXML
    public void validateNameText(){
        input.lettersOnly(drug_name);
    }
}