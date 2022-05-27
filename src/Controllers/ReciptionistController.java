package Controllers;

import Utilities.ModelTable;
import Utilities.utility;
import database.Connect;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
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
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class ReciptionistController implements Initializable {

    @FXML
    private TabPane mainpane;

    @FXML
    private ComboBox combo1;
    @FXML
    private ComboBox I_disease;
    @FXML
    private ComboBox combo;
    private final ObservableList<String> list = FXCollections.observableArrayList("ALLERGY", "INTENSIVE CARE", "ANESTHESIOLOGY", "CARDIOLOGY", "ENT", "NEUROLOGY", "ORTHOPEDICS", "PATHOLOGY", "RADIOLOGY", "SURGERY");

    @FXML
    private ComboBox I_gender;
    @FXML
    private ComboBox Ogender;
    private final ObservableList<String> gender = FXCollections.observableArrayList("Male", "Female");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {

            OUT_loadData();
            Inpatient_loadData();

        } catch (ClassNotFoundException | SQLException ex) {
            new Utilities.utility().make_Alert(ex.getMessage());  
        }
        combo1.setItems(list);
        I_disease.setItems(list);
        Ogender.setItems(gender);
        I_gender.setItems(gender);
        combo.setItems(list);
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                   /*     inpatient tab    */
    /////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private TableView<ModelTable> INPatientTable;
    @FXML
    private TableColumn<ModelTable, String> In_name;
    @FXML
    private TableColumn<ModelTable, String> in_address;
    @FXML
    private TableColumn<ModelTable, String> in_gender;
    @FXML
    private TableColumn<ModelTable, Date> in_dob;
    @FXML
    private TableColumn<ModelTable, Long> in_ssn;
    @FXML
    private TableColumn<ModelTable, String> in_email;
    @FXML
    private TableColumn<ModelTable, String> in_illness;
    @FXML
    private TableColumn<ModelTable, Date> in_dateArriving;
    @FXML
    private TableColumn<ModelTable, String> in_docid;
    @FXML
    private TableColumn<ModelTable, String> in_Nid;
    @FXML
    private TableColumn<ModelTable, String> in_roomId;
    @FXML
    private TableColumn<ModelTable, String> in_RelativeName;
    @FXML
    private TableColumn<ModelTable, Long> in_RelativePhone;
    @FXML
    private TableColumn<ModelTable, String> in_id;

    private final ObservableList<ModelTable> inpatient = FXCollections.observableArrayList();

    @FXML
    private void Inpatient_loadData() throws ClassNotFoundException, SQLException {

        inpatient.clear();

        INPatientTable.setItems(new database.Reciptionist().select_inpatient(inpatient));

        In_name.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        in_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        in_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        in_dob.setCellValueFactory(new PropertyValueFactory<>("birhtdate"));
        in_ssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        in_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        in_illness.setCellValueFactory(new PropertyValueFactory<>("illnessIssue"));
        in_dateArriving.setCellValueFactory(new PropertyValueFactory<>("date"));
        in_docid.setCellValueFactory(new PropertyValueFactory<>("id3"));
        in_Nid.setCellValueFactory(new PropertyValueFactory<>("nurseAssigned"));
        in_roomId.setCellValueFactory(new PropertyValueFactory<>("roomAssigned"));
        in_RelativeName.setCellValueFactory(new PropertyValueFactory<>("relativeName"));
        in_RelativePhone.setCellValueFactory(new PropertyValueFactory<>("relativePhone"));
        in_id.setCellValueFactory(new PropertyValueFactory<>("id2"));
    }

    @FXML
    private TextField I_name;
    @FXML
    private TextField I_ssn;
    @FXML
    private TextField I_email;
    @FXML
    private TextField I_address;
    @FXML
    private DatePicker I_birthday;
    @FXML
    private TextField I_nurse_id;
    @FXML
    private TextField I_room_id;
    @FXML
    private TextField I_doc_id;
    @FXML
    private TextField I_search;
    @FXML
    private TextField I_relative_name;
    @FXML
    private TextField I_relative_phone;

    static int index;

    @FXML
    public void getSelectedData() {
        index = INPatientTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        I_name.setText(In_name.getCellData(index));
        I_address.setText(in_address.getCellData(index));
        I_gender.setValue(in_gender.getCellData(index));
        I_ssn.setText(in_ssn.getCellData(index).toString());
        I_email.setText(in_email.getCellData(index));
        I_disease.setValue(in_illness.getCellData(index));
        I_nurse_id.setText(in_Nid.getCellData(index));
        I_room_id.setText(in_roomId.getCellData(index));
        I_relative_name.setText(in_RelativeName.getCellData(index));
        I_doc_id.setText(in_docid.getCellData(index));
        I_birthday.setValue(java.time.LocalDate.parse(in_dob.getCellData(index).toString()));
        I_relative_phone.setText(in_RelativePhone.getCellData(index).toString());
    }

    private void cleanData() {
        I_name.setText("");
        I_address.setText("");
        I_birthday.setValue(LocalDate.now());
        I_gender.setValue("");
        I_ssn.setText("");
        I_email.setText("");
        I_disease.setValue("");
        I_doc_id.setText("");
        I_nurse_id.setText("");
        I_room_id.setText("");
        I_relative_name.setText("");
        I_relative_phone.setText("");
    }

    @FXML
    private void clear() {
        cleanData();
    }

    private ArrayList<ModelTable> list1 = new ArrayList<ModelTable>();

    private ArrayList<ModelTable> getINPatientData() {

        list1.clear();

        ModelTable m = new ModelTable(I_name.getText(), in_id.getCellData(index), I_gender.getValue().toString(), I_email.getText(), I_address.getText(), Long.parseLong(I_relative_phone.getText()), I_relative_name.getText(), I_doc_id.getText(), I_disease.getValue().toString(), I_nurse_id.getText(), I_room_id.getText(), Date.valueOf(I_birthday.getValue()), Long.parseLong(I_ssn.getText()));
        list1.add(m);
        return list1;
    }

    static int P_id;

    @FXML
    private void Add_Inpatient() {
        new database.Reciptionist().add_inpatient(getINPatientData());
        INPatientTable.getItems().add(new ModelTable(getINPatientData().get(0).getPatientName(), getINPatientData().get(0).getId2(), getINPatientData().get(0).getGender(), getINPatientData().get(0).getEmail(), getINPatientData().get(0).getAddress(), getINPatientData().get(0).getRelativePhone(), getINPatientData().get(0).getRelativeName(), getINPatientData().get(0).getId3(), getINPatientData().get(0).getIllnessIssue(), getINPatientData().get(0).getNurseAssigned(), getINPatientData().get(0).getRoomAssigned(), getINPatientData().get(0).getBirhtdate(), getINPatientData().get(0).getSsn()));
        cleanData();
    }

    @FXML
    private void update_inpatient(){
        if (getINPatientData().get(0).getPatientName().isEmpty() || getINPatientData().get(0).getId2().isEmpty() || getINPatientData().get(0).getGender().isEmpty() || getINPatientData().get(0).getEmail().isEmpty() || getINPatientData().get(0).getAddress().isEmpty() || String.valueOf(getINPatientData().get(0).getRelativePhone()).isEmpty() || getINPatientData().get(0).getRelativeName().isEmpty() || getINPatientData().get(0).getId3().isEmpty() || getINPatientData().get(0).getIllnessIssue().isEmpty() || getINPatientData().get(0).getNurseAssigned().isEmpty() || getINPatientData().get(0).getRoomAssigned().isEmpty() || getINPatientData().get(0).getBirhtdate().toString().isEmpty() || String.valueOf(getINPatientData().get(0).getSsn()).isEmpty()) {
            new utility().make_Alert("Please Fill All DATA");
        } else {
            new database.Reciptionist().update_inpatient(getINPatientData(), in_id.getCellData(index));
            inpatient.set(index, new ModelTable(getINPatientData().get(0).getPatientName(), getINPatientData().get(0).getId2(), getINPatientData().get(0).getGender(), getINPatientData().get(0).getEmail(), getINPatientData().get(0).getAddress(), getINPatientData().get(0).getRelativePhone(), getINPatientData().get(0).getRelativeName(), getINPatientData().get(0).getId3(), getINPatientData().get(0).getIllnessIssue(), getINPatientData().get(0).getNurseAssigned(), getINPatientData().get(0).getRoomAssigned(), getINPatientData().get(0).getBirhtdate(), getINPatientData().get(0).getSsn()));
            cleanData();
        }
    }

    @FXML
    private void Delete_inpatient() {
        Optional<ButtonType> action = new Utilities.utility().make_Alert("Are You Sure");
        if (action.get() == ButtonType.OK) {
            new database.Reciptionist().delete_inpatient();
            if (index <= -1) {
                return;
            }

            INPatientTable.getItems().remove(index);
            index = -1;

            cleanData();
        }
    }

    @FXML
    private void Isearch() {
        new utility().Search(I_search, INPatientTable, inpatient);
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                   /*     outpatient tab    */
    @FXML
    private TableView<ModelTable> PatientTable;
    @FXML
    private TableColumn<ModelTable, String> patient_name;
    @FXML
    private TableColumn<ModelTable, Long> phone;
    @FXML
    private TableColumn<ModelTable, String> ogender;
    @FXML
    private TableColumn<ModelTable, Date> dob;
    @FXML
    private TableColumn<ModelTable, String> address;
    @FXML
    private TableColumn<ModelTable, Long> ssn;
    @FXML
    private TableColumn<ModelTable, String> illness;
    @FXML
    private TableColumn<ModelTable, Date> appointment;
    @FXML
    private TableColumn<ModelTable, String> email;
    @FXML
    private TableColumn<ModelTable, String> doctorid;
    @FXML
    private TableColumn<ModelTable,String> Oid;
    
    private final ObservableList<ModelTable> outpatient = FXCollections.observableArrayList();
   
    @FXML
    private TextField out_name;
    @FXML
    private TextField out_ssn;
    @FXML
    private TextField out_email;
    @FXML
    private TextField out_address;
    @FXML
    private DatePicker out_birthday;
    @FXML
    private TextField out_Did;
    @FXML
    private DatePicker out_appointment ;
    @FXML
    private TextField out_phone ;
    @FXML
    private TextField out_search ;  

    static int OP_id ; 
    static int oindex;

    private void OUT_loadData(){
        outpatient.clear();

        PatientTable.setItems(new database.Reciptionist().select_outpatient(outpatient));
        Oid.setCellValueFactory(new PropertyValueFactory<>("id2"));
        patient_name.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        ogender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        appointment.setCellValueFactory(new PropertyValueFactory<>("appointment"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        ssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        illness.setCellValueFactory(new PropertyValueFactory<>("illnessIssue"));
        dob.setCellValueFactory(new PropertyValueFactory<>("birhtdate"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        doctorid.setCellValueFactory(new PropertyValueFactory<>("id3"));
    }
    
    @FXML
    public void getSelectedData2(){
       
       oindex=PatientTable.getSelectionModel().getSelectedIndex();
       if (oindex<=-1)return;
       
        out_name.setText(patient_name.getCellData(oindex));
        out_address.setText(address.getCellData(oindex));
        out_phone.setText(phone.getCellData(oindex).toString());
        out_birthday.setValue(java.time.LocalDate.parse(dob.getCellData(oindex).toString()));
        Ogender.setValue(ogender.getCellData(oindex));
        out_ssn.setText(ssn.getCellData(oindex).toString());
        out_email.setText(email.getCellData(oindex));
        combo1.setValue(illness.getCellData(oindex));
        out_Did.setText(doctorid.getCellData(oindex));
        out_appointment.setValue(java.time.LocalDate.parse(appointment.getCellData(oindex).toString()));
        
    }
    
    private ArrayList<ModelTable> list2 = new ArrayList<ModelTable>();
    private ArrayList<ModelTable> getOUTPatientData() {
        list2.clear();
       
        ModelTable m = new ModelTable(Oid.getCellData(oindex), out_name.getText(), Ogender.getValue().toString(), out_email.getText(),
                out_address.getText(), Long.parseLong(out_phone.getText()), combo1.getValue().toString(), out_Did.getText(),
                Date.valueOf(out_birthday.getValue()), Date.valueOf(out_appointment.getValue()), Long.parseLong(out_ssn.getText()));
        list2.add(m);
        return list2;
    }
    
    private void clean() {
        out_name.setText("");
        out_address.setText("");
        out_birthday.setValue(LocalDate.now());
        Ogender.setValue("");
        out_phone.setText("");
        out_ssn.setText("");
        out_appointment.setValue(LocalDate.now());
        out_email.setText("");
        combo1.setValue("");
        out_Did.setText("");
    }
    
    @FXML 
    private void clear1(){
       clean();
    }

    @FXML 
     private void Add_outpatient() {
         new database.Reciptionist().add_outpatient(getOUTPatientData());
         PatientTable.getItems().add(new ModelTable( getOUTPatientData().get(0).getId2(),getOUTPatientData().get(0).getPatientName(),getOUTPatientData().get(0).getGender(),getOUTPatientData().get(0).getEmail(),getOUTPatientData().get(0).getAddress(), getOUTPatientData().get(0).getPhone(),getOUTPatientData().get(0).getIllnessIssue(),getOUTPatientData().get(0).getId3(),getOUTPatientData().get(0).getBirhtdate(),getOUTPatientData().get(0).getAppointment(),getOUTPatientData().get(0).getSsn()));
         clean();
    }
   
     @FXML
    private void update_outpatient(){      
         if (getOUTPatientData().get(0).getId2().isEmpty() || getOUTPatientData().get(0).getPatientName().isEmpty()|| getOUTPatientData().get(0).getGender().isEmpty() || getOUTPatientData().get(0).getEmail().isEmpty() || getOUTPatientData().get(0).getAddress().isEmpty()|| String.valueOf(getOUTPatientData().get(0).getPhone()).isEmpty() || getOUTPatientData().get(0).getIllnessIssue().isEmpty() || getOUTPatientData().get(0).getId3().isEmpty() || getOUTPatientData().get(0).getBirhtdate().toString().isEmpty() || getOUTPatientData().get(0).getAppointment().toString().isEmpty() || String.valueOf(getOUTPatientData().get(0).getSsn()).isEmpty()) {          
             new utility().make_Alert("Please Fill All DATA");
         } else {
             new database.Reciptionist().update_outpatient(getOUTPatientData());
             outpatient.set(oindex, new ModelTable(getOUTPatientData().get(0).getId2(), getOUTPatientData().get(0).getPatientName(), getOUTPatientData().get(0).getGender(), getOUTPatientData().get(0).getEmail(), getOUTPatientData().get(0).getAddress(), getOUTPatientData().get(0).getPhone(), getOUTPatientData().get(0).getIllnessIssue(),getOUTPatientData().get(0).getId3(), getOUTPatientData().get(0).getBirhtdate(), getOUTPatientData().get(0).getAppointment(), getOUTPatientData().get(0).getSsn()));
             clean();
         }
    }

    @FXML
    private void Delete_outpatient(){
        Optional<ButtonType> action = new Utilities.utility().make_Alert("Are You Sure");
        if (action.get() == ButtonType.OK) {
             new database.Reciptionist().delete_outpatient(getOUTPatientData());
            if (oindex <= -1) 
                return;
            
            PatientTable.getItems().remove(oindex);
            oindex = -1;
            clean();
        }
    }
    
     @FXML
     private void Osearch(){
       new utility().Search(out_search , PatientTable , outpatient);
     }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
                                                            /*     bill table     */
   @FXML
    private TableView<ModelTable> RoomBill;
    @FXML
    private TableColumn<ModelTable, Date> roomDate;
    @FXML
    private TableColumn<ModelTable, String> RoomType;
    @FXML
    private TableColumn<ModelTable, Integer> RoomDuration;
    @FXML
    private TableColumn<ModelTable, Float> RoomCost;


    @FXML
    private TableView<ModelTable> MedicineBill;
    @FXML
    private TableColumn<ModelTable, String> PMed;
    @FXML
    private TableColumn<ModelTable, String> MedCost;
    @FXML
    private TableColumn<ModelTable, String> MedQuantity;
    @FXML
    private TableColumn<ModelTable, Date> MedDate;

    @FXML
    private TableView<ModelTable> TestBill;
    @FXML
    private TableColumn<ModelTable, String> Ptest;
    @FXML
    private TableColumn<ModelTable, String> TestCost;
    @FXML
    private TableColumn<ModelTable, Date> TestDate;
    
   @FXML
    private TextField billsearch;
   @FXML
    private TextField bill_examination;
   @FXML
    private TextField bill_medicine;
   @FXML
    private TextField bill_test;
   @FXML
    private TextField bill_room;
   @FXML
    private TextField bill_name;
   @FXML
    private TextField total;
   @FXML
    private DatePicker bill_date;

    
    public static String Patient="";
    float medCost,Test_Cost,examCost,roomCost;
    String date, name="";

    private final ObservableList<ModelTable> list3 = FXCollections.observableArrayList();
    private final ObservableList<ModelTable> list4 = FXCollections.observableArrayList();
    private final ObservableList<ModelTable> list5 = FXCollections.observableArrayList();
    
    @FXML
    private void Bill_loadData() throws ClassNotFoundException, SQLException {
        Patient = billsearch.getText();
        list3.clear();

        ResultSet rs2 = new Connect().select("SELECT `duration`,`Type`,`RCost`,`Room_Assigned` FROM `inpatient`,`room`, `patient` WHERE `inpatient`.`In_id` =`patient`.`ID` and `inpatient`.`Room_id` =`room`.`R_id` and `inpatient`.`In_id`="+Patient);
        while (rs2.next()) {
            list3.add((new ModelTable(
                    rs2.getInt("duration"),
                    rs2.getString("Type"),
                    rs2.getFloat("RCost"),
                    rs2.getDate("Room_Assigned"))));

            RoomBill.setItems(list3);

            RoomDuration.setCellValueFactory(new PropertyValueFactory<>("MQuantity"));
            RoomType.setCellValueFactory(new PropertyValueFactory<>("drug"));
            roomDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            RoomCost.setCellValueFactory(new PropertyValueFactory<>("drugCost"));
        }

        list4.clear();

        ResultSet rs4 = new Connect().select("SELECT `PTest_name`,`TCost`,`Recieving_date`FROM `patient's tests` ,`test`,`patient` WHERE `test`.`Test_name`=`patient's tests`.`PTest_name` and `patient`.`ID`=`patient's tests`.`P_id` and `patient`.`ID`="+Patient);
        while (rs4.next()) {
            list4.add((new ModelTable(
                    rs4.getString("PTest_name"),
                    rs4.getFloat("TCost"),
                    rs4.getDate("Recieving_date"))));

            TestBill.setItems(list4);

            Ptest.setCellValueFactory(new PropertyValueFactory<>("Test"));
            TestCost.setCellValueFactory(new PropertyValueFactory<>("TestCost"));
            TestDate.setCellValueFactory(new PropertyValueFactory<>("date"));


        }

        list5.clear();

        ResultSet rs5 = new Connect().select("SELECT `Drug_name`, `Quantity`,`Cost`,`Recieving_date` FROM `patient's medicines`,`medicine`,`patient` WHERE `patient's medicines`.`Drug_name`=`medicine`.`Medicine_name` and `patient's medicines`.`Patient_id`=`patient`.`ID` and `patient`.`ID`="+Patient);
        while (rs5.next()) {
            list5.add((new ModelTable(
                    rs5.getInt("Quantity"),
                    rs5.getString("Drug_name"),
                    rs5.getFloat("Cost"),
                    rs5.getDate("Recieving_date"))));

            MedicineBill.setItems(list5);

            MedQuantity.setCellValueFactory(new PropertyValueFactory<>("MQuantity"));
            PMed.setCellValueFactory(new PropertyValueFactory<>("drug"));
            MedCost.setCellValueFactory(new PropertyValueFactory<>("drugCost"));
            MedDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        }
        
        ResultSet rs6= new Connect().select("SELECT `patient`.`Name` from `patient` WHERE `patient`.`ID`="+Patient);
        while (rs6.next()){
            name=rs6.getString(1);
        }
         bill_name.setText(name);      
    }
    
    
    private ArrayList<ModelTable> list6 = new ArrayList<ModelTable>();
    private ArrayList<ModelTable> getBillData() {
        list6.clear();
        ModelTable m = new ModelTable(Patient,Date.valueOf(bill_date.getValue().toString()),Float.valueOf(bill_room.getText()),Float.valueOf(bill_examination.getText()), Float.valueOf(bill_test.getText()),Float.valueOf(bill_medicine.getText()) );
        list6.add(m);
        return list6;
    }

     
    @FXML
    private void addBill(){      
        float total_bill = new database.Reciptionist().add_bill(getBillData(),Patient);
        total.setText(String.valueOf(total_bill));
  }

    @FXML
    private void BillReport() {    
        
        try {
            Connection conn = new database.Connect().initialize();
            
            String path = "bill.jrxml";
            
            Map<String,Object>para=new HashMap<String ,Object>();
            
            para.put("id", Integer.parseInt(getBillData().get(0).getId()));
            para.put("date", getBillData().get(0).getDate());
            
            System.out.println(Integer.parseInt(getBillData().get(0).getId()));
            System.out.println(getBillData().get(0).getDate());
            
            JasperReport bill = JasperCompileManager.compileReport(path);
            JasperPrint gp = JasperFillManager.fillReport(bill, para, conn);
            JasperViewer.viewReport(gp,false);
            
            billsearch.setText("");
            bill_examination.setText("");
            bill_medicine.setText("");
            bill_test.setText("");
            bill_room.setText("");
            bill_name.setText("");
            total.setText(""); 
            bill_date.setValue(LocalDate.now());
            
        } catch (JRException ex) {
             new Utilities.utility().make_Alert(ex.getMessage());  
        }
    }
     
 /////////////////////////////////////////////////////////////////////////////////////
                         /*     appointment table     */
    @FXML
    private TableView<ModelTable> appointmentTable;
    @FXML
    private TableColumn<ModelTable, String> docName;
    @FXML
    private TableColumn<ModelTable, String> docID;
    @FXML
    private TableColumn<ModelTable, String> appoint;
    @FXML
    private TableColumn<ModelTable, String> appoint1;

    private final ObservableList<ModelTable> list7= FXCollections.observableArrayList();
    
    @FXML
    private void searchDoctor(){
        try {
            list7.clear();
            String depart = combo.getSelectionModel().getSelectedItem().toString();
            
            ResultSet rs = new Connect().select("SELECT `Employee_Id`, `E_Name`,`Arrival`, `Leaving` FROM `employee` WHERE `E_Department_Name`='" + depart + "'");
            while (rs.next()) {
                list7.add((new ModelTable(
                        rs.getString("E_Name"),
                        rs.getString("Arrival"),
                        rs.getString("Leaving"),
                        rs.getString("Employee_Id")))
                );
                appointmentTable.setItems(list7);
                docName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
                appoint.setCellValueFactory(new PropertyValueFactory<>("time1"));
                appoint1.setCellValueFactory(new PropertyValueFactory<>("time2"));
                docID.setCellValueFactory(new PropertyValueFactory<>("id2"));
            }
        } catch (SQLException ex) {
             new Utilities.utility().make_Alert(ex.getMessage());  
        }
    }
    @FXML
    private void logout() {
        new utility().LogOut(mainpane);
    }
}
