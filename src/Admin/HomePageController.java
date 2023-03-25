package Admin;

import utility.*;
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

public class HomePageController extends Controller implements Initializable {

    @FXML
    private AnchorPane mainpane;
    @FXML
    private TableView<userData> AdminTable;
    @FXML
    private TableColumn<userData, Integer> id;
    @FXML
    private TableColumn<userData, String> name, address, gender, username, password, depart, email;
    @FXML
    private TableColumn<userData, Long> phone, ssn;
    @FXML
    private TableColumn<userData, LocalDate> dob, dateJoin;
    @FXML
    private TableColumn<userData, Float> salary;
    @FXML
    private ComboBox departmentCombo, genderCombo;
    @FXML
    private TextField Ename, Ephone, Eemail, Eaddress, Essn, Esalary, Eusername, Epassword, search;
    @FXML
    private DatePicker Ebirthday, Edate;

    validateInputs input = new validateInputs();
    alerts alert = new alerts();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        LoadDataInTable();
        departmentCombo.setItems(diseaseList);
        genderCombo.setItems(genderList);
    }

    @Override
    public void LoadDataInTable() {
        try {
            AdminTable.setItems(new database.employee().getEmployeeData());
            
            phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            id.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
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
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @FXML
    @Override
    public void getSelectedData() {
        index = AdminTable.getSelectionModel().getSelectedIndex();

        Ename.setText(name.getCellData(index));
        Ephone.setText(phone.getCellData(index).toString());
        Eemail.setText(email.getCellData(index));
        Eaddress.setText(address.getCellData(index));
        Essn.setText(ssn.getCellData(index).toString());
        Esalary.setText(salary.getCellData(index).toString());
        Eusername.setText(username.getCellData(index));
        Epassword.setText(password.getCellData(index));
        Ebirthday.setValue(dob.getCellData(index));
        Edate.setValue(dateJoin.getCellData(index));
        genderCombo.setValue(gender.getCellData(index));
        departmentCombo.setValue(depart.getCellData(index));
    }

    @Override
    public userData prepareData() {
        return new userData.UserBuilder().
                setName(Ename.getText()).setGender(genderCombo.getValue().toString()).
                setEmail(Eemail.getText()).setAddress(Eaddress.getText()).setPhone(Long.parseLong(Ephone.getText())).
                setUsername(Eusername.getText()).setPassword(Epassword.getText()).setDepartment(departmentCombo.getValue().toString()).
                setDate(Edate.getValue()).setBirhtdate(Ebirthday.getValue()).setSalary(Float.parseFloat(Esalary.getText())).
                setEmployeetId(id.getCellData(index)).setSsn(Long.parseLong(Essn.getText())).build();

    }

    @Override
    public void insertQuery() {
        try {
            new database.employee().insertEmployee(prepareData());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    public void addDataInTable() {
        AdminTable.getItems().add(new userData.UserBuilder().setName(prepareData().getName()).setGender(prepareData().getGender()).
                setEmail(prepareData().getEmail()).setAddress(prepareData().getAddress()).setPhone(prepareData().getPhone()).setUsername(prepareData().getUsername()).
                setPassword(prepareData().getPassword()).setDepartment(prepareData().getDepartment()).setDate(prepareData().getDate()).setBirhtdate(prepareData().getBirhtdate())
                .setSalary(prepareData().getSalary()).setEmployeetId(prepareData().getEmployeeId()).setSsn(prepareData().getSsn()).build());
    }

    @Override
    public void updateQuery() {
        try {
            new database.employee().updateEmployee(prepareData(), id.getCellData(index));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @Override
    public void updateDataInTable() {
        AdminTable.getItems().set(index, new userData.UserBuilder().setName(prepareData().getName()).setGender(prepareData().getGender()).
                setEmail(prepareData().getEmail()).setAddress(prepareData().getAddress()).setPhone(prepareData().getPhone()).setUsername(prepareData().getUsername()).
                setPassword(prepareData().getPassword()).setDepartment(prepareData().getDepartment()).setDate(prepareData().getDate()).setBirhtdate(prepareData().getBirhtdate())
               .setSalary(prepareData().getSalary()).setEmployeetId(prepareData().getEmployeeId()).setSsn(prepareData().getSsn()).build());
    }

    @Override
    public void deleteQuery() {
        try {
            new database.employee().deleteEmployee(id.getCellData(index));
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @FXML
    @Override
    public void cleanFields() {
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
        genderCombo.setValue("");
        departmentCombo.setValue("");
    }

    @FXML
    private void openAnalysis() {
        new pageControl().CloseFxml(mainpane);
        new pageControl().openFxml("Admin", "AnalysisPage");
    }

    @FXML
    @Override
    public void search() {
        try {
            new Search().userSearch(search, AdminTable, new database.employee().getEmployeeData());
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
    }

    @FXML
    @Override
    public void logout() {
        new pageControl().LogOut(mainpane);
    }

    @Override
    public void deleteDateFromTable() {
        AdminTable.getItems().remove(index);
    }
    
    @FXML
    public void validatePhoneText(){
        input.numericOnly(Ephone);
    }
    
    @FXML
    public void validateSsnText(){
        input.numericOnly(Essn);
    }
    
    @FXML
    public void validateSalaryText(){
        input.numericAndFloatingOnly(Esalary);
    }
    
    @FXML
    public void validateNameText(){
        input.lettersOnly(Ename);
    }
    
    @Override
    public boolean isNotValidInput() {
        return (prepareData().getUsername().trim().isEmpty() || prepareData().getPassword().trim().isEmpty() || prepareData().getGender().trim().isEmpty() || 
            prepareData().getBirhtdate().toString().trim().isEmpty() || prepareData().getDate().toString().trim().isEmpty() || 
            String.valueOf(prepareData().getPhone()).trim().isEmpty() || String.valueOf(prepareData().getSsn()).trim().isEmpty() ||
            String.valueOf(prepareData().getSalary()).trim().isEmpty());
    }
}
