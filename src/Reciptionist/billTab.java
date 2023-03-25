package Reciptionist;

import database.DatabaseConnection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import utility.*;

public class billTab {

    @FXML
    private TableView<roomData> RoomBill;
    @FXML
    private TableColumn<roomData, LocalDate> roomDate;
    @FXML
    private TableColumn<roomData, String> RoomType;
    @FXML
    private TableColumn<roomData, Integer> RoomDuration;
    @FXML
    private TableColumn<roomData, Float> RoomCost;
    
    @FXML
    private TableView<DrugsData> MedicineBill;
    @FXML
    private TableColumn<DrugsData, LocalDate>  MedDate;
    @FXML
    private TableColumn<DrugsData, String> PMed, MedCost, MedQuantity;
   
    @FXML
    private TableView<TestData> TestBill;
    @FXML
    private TableColumn<TestData, LocalDate>  TestDate;
    @FXML
    private TableColumn<TestData, String>  Ptest, TestCost;

    @FXML
    private TextField billsearch, bill_examination, bill_medicine, bill_test, bill_room, patientName, total;
    @FXML
    private DatePicker bill_date;
    
    validateInputs input = new validateInputs();
    alerts alert = new alerts();

    @FXML
    private void loadData() {
        loadRoomData();
        loadTestData();
        loadDrugData();
        getPatientName();
    }
    
    private void loadRoomData() {
        try {
            RoomBill.setItems(new database.bill().selectRoomData(Integer.parseInt(billsearch.getText())));
            RoomDuration.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            RoomType.setCellValueFactory(new PropertyValueFactory<>("patientName"));
            roomDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            RoomCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }

    }
    private void loadTestData() {
        try {
            TestBill.setItems(new database.patient().selectPatientTests(billsearch.getText()));
            Ptest.setCellValueFactory(new PropertyValueFactory<>("name"));
            TestCost.setCellValueFactory(new PropertyValueFactory<>("ost"));
            TestDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }
    private void loadDrugData() {
        try {
            MedicineBill.setItems(new database.patient().selectPatientDrug(billsearch.getText()));
            MedQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
            PMed.setCellValueFactory(new PropertyValueFactory<>("name"));
            MedCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
            MedDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    private void getPatientName() {
        try {
            patientName.setText(new database.patient().getPatientName(Integer.parseInt(billsearch.getText())));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    private billData prepareBillData() {
        return new billData(Integer.parseInt(billsearch.getText()), patientName.getText(), bill_date.getValue(), Float.valueOf(bill_medicine.getText()),
                Float.valueOf(bill_test.getText()), Float.valueOf(bill_examination.getText()),Float.valueOf(bill_room.getText()));
    }

    @FXML
    private void addBill() {
        try {
            float total_bill = new database.bill().add_bill(prepareBillData());
            total.setText(String.valueOf(total_bill));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    private void cleanFields() {
        billsearch.setText("");
        bill_examination.setText("");
        bill_medicine.setText("");
        bill_test.setText("");
        bill_room.setText("");
        patientName.setText("");
        total.setText("");
        bill_date.setValue(LocalDate.now());
    }

    @FXML
    private void generateBillReport(){
        try {
            Map<String, Object> para = new HashMap<>();

            para.put("id", prepareBillData().getPatientId());
            para.put("date", prepareBillData().getDate());

            JasperReport bill = JasperCompileManager.compileReport("bill.jrxml");
            JasperPrint gp = JasperFillManager.fillReport(bill, para, DatabaseConnection.getConnection());
            JasperViewer.viewReport(gp, false);
            cleanFields();

        } catch (JRException ex) {
            new alerts().makeInfoAlert(ex.getMessage());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }
    
    @FXML
    public void validateTestText(){
        input.numericAndFloatingOnly(bill_test);
    }
    
    @FXML
    public void validateMedicineText(){
        input.numericAndFloatingOnly(bill_medicine);
    }
    
    @FXML
    public void validateExaminationText(){
        input.numericAndFloatingOnly(bill_examination);
    }
    
    @FXML
    public void validateRoomText(){
        input.numericAndFloatingOnly(bill_room);
    }
}