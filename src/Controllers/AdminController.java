package Controllers;

import Utilities.ModelTable;
import Utilities.utility;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


public class AdminController implements Initializable {

    @FXML
    private ComboBox combo1;
    private ObservableList<String> list1 = FXCollections.observableArrayList(" ","ALLERGY", "INTENSIVE CARE", "ANESTHESIOLOGY", "CARDIOLOGY", "ENT", "NEUROLOGY", "ORTHOPEDICS", "PATHOLOGY", "RADIOLOGY", "SURGERY");

    @FXML
    private ComboBox Egender;
    private final ObservableList <String> combogender=FXCollections.observableArrayList(" ","Male","Female");
   
    // Doctor Table
    @FXML
    private TableView<ModelTable> AdminTable;
    @FXML
    private TableColumn<ModelTable, String> id;
    @FXML
    private TableColumn<ModelTable, String> name;
    @FXML
    private TableColumn<ModelTable, Long> phone;
    @FXML
    private TableColumn<ModelTable, String> address;
    @FXML
    private TableColumn<ModelTable, String> gender;
    @FXML
    private TableColumn<ModelTable, Date> dob;
    @FXML
    private TableColumn<ModelTable, String> username;
    @FXML
    private TableColumn<ModelTable, String> password;
    @FXML
    private TableColumn<ModelTable, Long> ssn;
    @FXML
    private TableColumn<ModelTable, String> depart;
    @FXML
    private TableColumn<ModelTable, Float> salary;
    @FXML
    private TableColumn<ModelTable, Date> dateJoin;
    @FXML
    private TableColumn<ModelTable, String> email;
    
    private ObservableList<ModelTable> admin = FXCollections.observableArrayList();

    @FXML
    private TextField Ename;
    @FXML
    private TextField Ephone;
    @FXML
    private TextField Eemail;
    @FXML
    private TextField Eaddress;
    @FXML
    private TextField Essn;
    @FXML
    private TextField Esalary;
    @FXML
    private TextField Eusername;
    @FXML
    private TextField Epassword;
    @FXML
    private DatePicker Ebirthday;
    @FXML
    private DatePicker Edate;
    
    
    @FXML
    private TextField search;
   
    static int index;
    
    @FXML
    private AnchorPane mainpane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            LoadData();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        combo1.setItems(list1);
        Egender.setItems(combogender);
    }
    
    
    @FXML
    private void getSelected() {

        index = AdminTable.getSelectionModel().getSelectedIndex();
        if (index <= -1) return;
       
        Ename.setText(name.getCellData(index));
        Ephone.setText(phone.getCellData(index).toString());
        Eemail.setText(email.getCellData(index));
        Eaddress.setText(address.getCellData(index));
        Essn.setText(ssn.getCellData(index).toString());
        Esalary.setText(salary.getCellData(index).toString());
        Eusername.setText(username.getCellData(index));
        Epassword.setText(password.getCellData(index));
        Ebirthday.setValue(java.time.LocalDate.parse(dob.getCellData(index).toString()));
        Edate.setValue(java.time.LocalDate.parse(dateJoin.getCellData(index).toString()));
        Egender.setValue(gender.getCellData(index));
        combo1.setValue(depart.getCellData(index));
    }

    private ArrayList<ModelTable> list = new ArrayList<ModelTable>();
  
    public ArrayList<ModelTable> getData() {
        list.clear();
        ModelTable m = new ModelTable(Ename.getText(),Egender.getValue().toString(),Eemail.getText(),Eaddress.getText(),Long.parseLong(Ephone.getText()),Eusername.getText(),Epassword.getText(),combo1.getValue().toString(),Date.valueOf(Edate.getValue()),Date.valueOf(Ebirthday.getValue()),Float.parseFloat(Esalary.getText()),String.valueOf(id.getCellData(index)),Long.parseLong(Essn.getText()));
        list.add(m);
        return list;
    }
     
    private void clean() {
        Ename.setText("");
        Ephone.setText("");
        Eemail.setText("");
        Eaddress.setText("");
        Essn.setText("");
        Esalary.setText("");
        Eusername.setText("");
        Epassword.setText("");
        Ebirthday.setValue(LocalDate.now());
        Edate.setValue(LocalDate.now());
        Egender.setValue("");
        combo1.setValue("");
    }
  
    private void LoadData() throws ClassNotFoundException, SQLException {

        admin.clear();
        AdminTable.setItems(new database.Admin().select(admin));

        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        dob.setCellValueFactory(new PropertyValueFactory<>("birhtdate"));
        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        ssn.setCellValueFactory(new PropertyValueFactory<>("ssn"));
        depart.setCellValueFactory(new PropertyValueFactory<>("department"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        dateJoin.setCellValueFactory(new PropertyValueFactory<>("date"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

    }
     @FXML 
     private void AddEmployee() throws ClassNotFoundException, SQLException {
         new database.Admin().insert_Employee(getData());
         AdminTable.getItems().add(new ModelTable(getData().get(0).getEmployeeName(),getData().get(0).getGender(),getData().get(0).getEmail(),getData().get(0).getAddress(),getData().get(0).getPhone(),getData().get(0).getUsername(),getData().get(0).getPassword(),getData().get(0).getDepartment(),getData().get(0).getDate(),getData().get(0).getBirhtdate(),getData().get(0).getSalary(),getData().get(0).getId(),getData().get(0).getSsn()));
         clean();
    }
     
    @FXML
    private void updateEmployee() throws ClassNotFoundException, SQLException {      
        if(getData().get(0).getEmployeeName().isEmpty() || getData().get(0).getDepartment().isEmpty() || getData().get(0).getEmail().isEmpty() || getData().get(0).getAddress().isEmpty() || getData().get(0).getUsername().isEmpty() || getData().get(0).getPassword().isEmpty() || getData().get(0).getGender().isEmpty() || getData().get(0).getBirhtdate().toString().isEmpty() || getData().get(0).getDate().toString().isEmpty()|| String.valueOf(getData().get(0).getPhone()).isEmpty()|| String.valueOf(getData().get(0).getSsn()).isEmpty() || String.valueOf(getData().get(0).getSalary()).isEmpty()) 
            new utility().make_Alert("Please Fill All DATA");
         else {
               new database.Admin().update_Employee(getData(),id.getCellData(index));
               admin.set(index, new ModelTable(getData().get(0).getEmployeeName(),getData().get(0).getGender(),getData().get(0).getEmail(),getData().get(0).getAddress(),getData().get(0).getPhone(),getData().get(0).getUsername(),getData().get(0).getPassword(),getData().get(0).getDepartment(),getData().get(0).getDate(),getData().get(0).getBirhtdate(),getData().get(0).getSalary(),getData().get(0).getId(),getData().get(0).getSsn()));
               clean();
        }
    }

    @FXML
    private void DeleteEmployee() throws SQLException, ClassNotFoundException {      
        Optional<ButtonType> action = new Utilities.utility().make_Alert("Are You Sure");
        if (action.get() == ButtonType.OK) {
            new database.Admin().delete_Employee(id.getCellData(index));
            if (index <= -1) 
                return;
            AdminTable.getItems().remove(index);
            index = -1;
            clean();
        }
    }
    
    @FXML 
    private void clear(){
       clean();
    }
     
    @FXML
    private void openAnalysis(){
        new utility().CloseFxml(mainpane);
        new utility().openFxml("Analysis");
    }
     @FXML
     private void search(){
       new utility().Search(search , AdminTable , admin);
     }
  
    @FXML
    private void logout(){
     new utility().LogOut( mainpane);
    }
}

