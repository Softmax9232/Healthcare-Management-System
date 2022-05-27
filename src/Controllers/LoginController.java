package Controllers;

import Utilities.utility;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


public class LoginController implements Initializable {
    
    @FXML 
    private Pane mainpane; 
    @FXML
    private AnchorPane pane;
    @FXML
    private TextField text, answer,username;
    @FXML
    private Pane pane1,pane2,pane3;
    @FXML
    private Label question;
    @FXML
    private PasswordField password,newPassword;
    
    public static String Employee_name="";
    ResultSet rs,rs1 = null;
    public static String  id,Employee_id ="";
    utility u=new utility();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    } 
    
    @FXML
    private void logButtonClicked(){
        String user = username.getText();
        String pass = String.valueOf(password.getText());

        if (user.length() == 0 && pass.length() == 0) {
           new Utilities.utility().make_Alert("Please enter your Username and Password");
        } else if (user.length() == 0) {
           new Utilities.utility().make_Alert("Please enter your Username");
        } else if (pass.length() == 0) {
            new Utilities.utility().make_Alert("Please enter your Password");
        } else {

            ArrayList<String> list = new database.Login().CheckLogin(user, pass);
            Employee_name = list.get(3);
            Employee_id = list.get(4);
            String Department =  list.get(2);
            
            if (user.equals(list.get(0)) && pass.equals(list.get(1))) {
                if (Department.equals("Administration")) {
                    u.CloseFxml(mainpane);
                    u.openFxml("admin");

                } else if (Department.equals("Reciptionist")) {
                    u.CloseFxml(mainpane);
                    u.openFxml("reciptionist");

                } else if (Department.equals("ALLERGY") || Department.equals("INTENSIVECARE") || Department.equals("ANESTHESIOLOGY") || Department.equals("CARDIOLOGY") || Department.equals("ENT") || Department.equals("NEUROLOGY") || Department.equals("ORTHOPEDICS") || Department.equals("PATHOLOGY") ||Department.equals("RADIOLOGY") || Department.equals("SURGERY")) {
                    u.CloseFxml(mainpane);
                    u.openFxml("doctor");

                } else if (Department.equals("Pharmacy")) {
                    u.CloseFxml(mainpane);
                    u.openFxml("pharmacist");

                } else if (Department.equals("Laboratory")) {
                    u.CloseFxml(mainpane);
                    u.openFxml("laboratory_technician");
                }
            } else {
                u.make_Alert("Wrong Username/Password");
            }
        }
    }
    
    @FXML
    private void forgetClicked() throws IOException {
        u.CloseFxml(mainpane);   
        u.openFxml("forget");
    }
    @FXML
    private void loginClicked() throws IOException {
        u.CloseFxml(pane);   
        u.openFxml("Login");
    }
    
    @FXML
    private void recoveryClicked(){
        String user = text.getText();  id="";
        if (user.isEmpty() == true) {
            u.make_Alert("Please enter a username");
        }else if (user.isEmpty()==false){
            try {
                rs = new database.Login().Check_Username();
                
                while (rs.next()) {
                    String username = rs.getString(1);
                    String recover = rs.getString(2);
                    
                    if (user.equals(username)){
                        pane1.setVisible(false);
                        pane3.setVisible(false);
                        pane2.setVisible(true);
                        question.setText(recover);
                        id= rs.getString(3);
                    }
                } } catch (SQLException ex) {
                 new Utilities.utility().make_Alert(ex.getMessage());  
            }
       }else {
            u.make_Alert("Please enter a username currently in use");
       }
   }

    @FXML
    private void submit1Clicked(){
        String get_ans = answer.getText();

        if (get_ans.isEmpty() == true) {
            u.make_Alert("Please enter the Answer");
        } else if (get_ans.isEmpty() == false) {
            try {
                rs1 = new database.Login().Check_answer(id);
                
                while (rs1.next()) {
                    String user_answer = rs1.getString(1);
                    if (get_ans.equals(user_answer)) {
                        pane1.setVisible(false);
                        pane2.setVisible(false);
                        pane3.setVisible(true);
                    }
                }
            } catch (SQLException ex) {
                 new Utilities.utility().make_Alert(ex.getMessage());  
            }
        }else {
            u.make_Alert("Wrong answer");
        }
    }

    @FXML
    private void SubmitClicked() {
        String newPass = String.valueOf(newPassword.getText());

        if (newPass.isEmpty() == false) {
            new database.Login().update_Password(newPass,id);
            u.make_Alert("Password Successfully changed, Please login now!");
            u.CloseFxml(pane);
            u.openFxml("Login");
        } else {
          u.make_Alert("Password is empty!");
        }
    }

}
