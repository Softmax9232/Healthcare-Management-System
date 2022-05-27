package Controllers;

import Utilities.ModelTable;
import Utilities.utility;
import database.Connect;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


public class DoctorController implements Initializable {
    
     static int Pindex;
     String Name,id,Gender,Blood,Email,Prescription,Appointment,dateofbirth,NAppointment,appoint_time,next_time;
     long Phone;
     float Weight,Height;
    
    @FXML
    private AnchorPane mainpane;

    @FXML
    private Label name;
    
    String Did = LoginController.Employee_id;


    // Doctor Table
    @FXML
    private TableView<ModelTable> DoctorTable;
    @FXML
    private TableColumn<ModelTable, String> patient_name;
    @FXML
    private TableColumn<ModelTable, Long> phone;
    @FXML
    private TableColumn<ModelTable, String> gender;
    @FXML
    private TableColumn<ModelTable, Date> dob;
    @FXML
    private TableColumn<ModelTable, String> blood;
    @FXML
    private TableColumn<ModelTable, Float> weight;
    @FXML
    private TableColumn<ModelTable, Float> height;
    @FXML
    private TableColumn<ModelTable, Date> appointment;
    @FXML
    private TableColumn<ModelTable, Date> Nappointment;
    @FXML
    private TableColumn<ModelTable, String> Atime;
    @FXML
    private TableColumn<ModelTable, String> Ntime;
    @FXML
    private TableColumn<ModelTable, String> email;
    @FXML
    private TableColumn<ModelTable, String> prescription;
    @FXML
    private TableColumn<ModelTable, String> patient_id;

    private final  ObservableList<ModelTable> doctor = FXCollections.observableArrayList();

    @FXML
    private ComboBox PBlood;
    private final ObservableList <String> bloodGroup=FXCollections.observableArrayList("A+","A-","B+","B-","O+","O-","AB+","AB-");
    
    @FXML
    private ComboBox Pgender;
    private final ObservableList <String> combogender=FXCollections.observableArrayList("Male","Female");
   
    @FXML
    private ComboBox Time;
    @FXML
    private ComboBox NTime;
    
    private final ObservableList <String> time=FXCollections.observableArrayList("00:00","00:30","01:00","01:30","02:00","02:30","03:00","03:30","04:00","04:30","05:00","05:30","06:00","06:30","07:00","07:30","08:00","08:30","09:00","09:30","10:00","10:30","11:00","11:30","12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30","17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00","22:30","23:00","23:30");
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @FXML
    private TextField patientid;
    @FXML
    private TextField patientname;
    @FXML
    private TextField patientphone;
    @FXML
    private TextField patientemail;
    @FXML
    private TextField patientweight;
    @FXML
    private TextField patientheight;
    @FXML
    private DatePicker PDATE ;
    @FXML
    private DatePicker Pappoint ;
    @FXML
    private DatePicker nextAppoint ;  
    @FXML
    private TextField Pprescription;
    
    @FXML
    private TextField search;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        D_loadData();
        name.setText(LoginController.Employee_name);
        PBlood.setItems(bloodGroup);
        Pgender.setItems(combogender);
        Time.setItems(time);
        NTime.setItems(time);
    }
   
    @FXML
    public void getSelectedData(){
       
       Pindex=DoctorTable.getSelectionModel().getSelectedIndex();
       if (Pindex<=-1)return;
       
        patientname.setText(patient_name.getCellData(Pindex));
        patientphone.setText(phone.getCellData(Pindex).toString());
        PDATE.setValue(java.time.LocalDate.parse(dob.getCellData(Pindex).toString()));
        PBlood.setValue(blood.getCellData(Pindex));
        Pgender.setValue(gender.getCellData(Pindex));
        patientweight.setText(weight.getCellData(Pindex).toString());
        patientheight.setText(height.getCellData(Pindex).toString());
        Pappoint.setValue(java.time.LocalDate.parse(appointment.getCellData(Pindex).toString()));
        patientemail.setText(email.getCellData(Pindex));
        Pprescription.setText(prescription.getCellData(Pindex));
        Time.setValue(Atime.getCellData(Pindex));
        NTime.setValue(Ntime.getCellData(Pindex));
        patientid.setText(patient_id.getCellData(Pindex));
        nextAppoint.setValue(java.time.LocalDate.parse(Nappointment.getCellData(Pindex).toString()));
    }
    private ArrayList<ModelTable> list = new ArrayList<ModelTable>();

    private  ArrayList<ModelTable> getData() {
        list.clear();
        ModelTable m = new ModelTable(patient_id.getCellData(Pindex),patientname.getText(),Pgender.getValue().toString(),patientemail.getText(),Date.valueOf(Pappoint.getValue()),PBlood.getValue().toString(),Float.parseFloat(patientweight.getText()),Float.parseFloat(patientheight.getText()),Date.valueOf(PDATE.getValue()),Long.parseLong(patientphone.getText()),Pprescription.getText(),Date.valueOf(nextAppoint.getValue()),Time.getValue().toString(),NTime.getValue().toString());
        list.add(m);
        
        return list;
    }
    private void clean() {
        patientname.setText("");
        Pgender.setValue("");
        PBlood.setValue("");
        patientemail.setText("");
        Pprescription.setText("");
        Pappoint.setValue(LocalDate.now());
        nextAppoint.setValue(LocalDate.now());
        PDATE.setValue(LocalDate.now());
        patientid.setText("");
        patientphone.setText("");
        patientweight.setText("");
        patientheight.setText("");
        Time.setValue("");
        NTime.setValue("");
    }
    
    @FXML 
    private void clear(){
       clean();
    }
    
    @FXML
    private void D_loadData(){

         try {
             doctor.clear();
             DoctorTable.setItems(new database.Doctor().select(doctor, Did));
             
             patient_name.setCellValueFactory(new PropertyValueFactory<>("patientName"));
             gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
             appointment.setCellValueFactory(new PropertyValueFactory<>("appointment"));
             email.setCellValueFactory(new PropertyValueFactory<>("email"));
             blood.setCellValueFactory(new PropertyValueFactory<>("bloodGroup"));
             weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
             height.setCellValueFactory(new PropertyValueFactory<>("height"));
             dob.setCellValueFactory(new PropertyValueFactory<>("birhtdate"));
             phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
             prescription.setCellValueFactory(new PropertyValueFactory<>("prescription"));
             patient_id.setCellValueFactory(new PropertyValueFactory<>("id"));
             Nappointment.setCellValueFactory(new PropertyValueFactory<>("date2"));
             Atime.setCellValueFactory(new PropertyValueFactory<>("time1"));
             Ntime.setCellValueFactory(new PropertyValueFactory<>("time2"));
         } catch (SQLException ex) {
             new Utilities.utility().make_Alert(ex.getMessage());  
         } catch (ClassNotFoundException ex) {
              new Utilities.utility().make_Alert(ex.getMessage());  
         }
    }
   
    @FXML
    private void patient_update(){
        if (getData().get(0).getId().isEmpty() || getData().get(0).getPatientName().isEmpty() || getData().get(0).getGender().isEmpty() || getData().get(0).getEmail().isEmpty()
                || getData().get(0).getAppointment().toString().isEmpty() || getData().get(0).getBloodGroup().isEmpty() || String.valueOf(getData().get(0).getHeight()).isEmpty()
                || String.valueOf(getData().get(0).getWeight()).isEmpty() || getData().get(0).getBirhtdate().toString().isEmpty() || String.valueOf(getData().get(0).getPhone()).isEmpty()
                || getData().get(0).getPrescription().isEmpty() || String.valueOf(getData().get(0).getDate2()).isEmpty() || getData().get(0).getTime1().isEmpty() || getData().get(0).getTime2().isEmpty()) {
            
            new utility().make_Alert("Please Fill All DATA");
        } else {
            try {
                new database.Doctor().update_patient(getData(), patient_id.getCellData(Pindex),Pappoint.getValue().toString());
                doctor.set(Pindex,
                        new ModelTable(getData().get(0).getId(), getData().get(0).getPatientName(), getData().get(0).getGender(), getData().get(0).getEmail(),
                                getData().get(0).getAppointment(), getData().get(0).getBloodGroup(), getData().get(0).getWeight(),
                                getData().get(0).getHeight(), getData().get(0).getBirhtdate(), getData().get(0).getPhone(), getData().get(0).getPrescription(), getData().get(0).getDate2(),
                                getData().get(0).getTime1(), getData().get(0).getTime2()));        
                clean();
            } catch (ClassNotFoundException ex) {
                 new Utilities.utility().make_Alert(ex.getMessage());  
            } catch (SQLException ex) {
                new Utilities.utility().make_Alert(ex.getMessage());  
            }
        }
   }
   
    @FXML
    private void PatientDelete(){
        Optional<ButtonType> action = new Utilities.utility().make_Alert("Are You Sure");
        if (action.get() == ButtonType.OK) {
            try {
                new database.Doctor().delete_patient(patient_id.getCellData(Pindex),Pappoint.getValue().toString());             
                if (Pindex <= -1)
                    return;
                DoctorTable.getItems().remove(Pindex);
                Pindex = -1;
                clean();
            } catch (SQLException ex) {
                 new Utilities.utility().make_Alert(ex.getMessage());  
            } catch (ClassNotFoundException ex) {
                new Utilities.utility().make_Alert(ex.getMessage());  
            }
        }
    }
    
     @FXML
     private void d_search(){
       new utility().Search(search , DoctorTable , doctor);
     }
    
  
    @FXML
    private void PrescriptionPrint() {
         try {
             Appointment=Pappoint.getValue().toString();
             id=patientid.getText();
             
             String pre_doctor = new database.Doctor().prescription (patient_id.getCellData(Pindex),Pappoint.getValue().toString());
             
             String path = "doctorPrescriptions.jrxml";
             
             Map<String, Object> para = new HashMap< String, Object>();
             para.put("Patient_ID", patient_id.getCellData(Pindex));
             para.put("Appointment",Pappoint.getValue().toString());
             para.put("Doctor_name", pre_doctor);
             
             Connection conn =  new Connect().initialize();
             
             JasperReport prescription = JasperCompileManager.compileReport(path);
             JasperPrint gp = JasperFillManager.fillReport(prescription, para, conn);
             JasperViewer.viewReport(gp, false);
         } catch (SQLException ex) {
             new Utilities.utility().make_Alert(ex.getMessage());  
         } catch (ClassNotFoundException ex) {
              new Utilities.utility().make_Alert(ex.getMessage());  
         } catch (JRException ex) {
              new Utilities.utility().make_Alert(ex.getMessage());  
         }
    }

    
    @FXML
    private void logout(){
       new utility().LogOut( mainpane);
    }
    
   
    
}
