package Authentication;

import java.lang.Object;
import utility.*;
import java.sql.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class controller {

    @FXML
    private AnchorPane mainpane, pane, pane1, pane2, pane3;
    @FXML
    private TextField text, answer, username;
    @FXML
    private Label question;
    @FXML
    private PasswordField password, newPassword;
    @FXML
    private Button recoverButton, answerButton;

    public static String Employee_name = "";
    public static int Employee_id, id;
    alerts alert = new alerts();
    pageControl control = new pageControl();
    validateInputs input = new validateInputs();
    userData list;

    @FXML
    private void authenticateUser() {
        String user = username.getText(), pass = password.getText();
        if (!input.isValidInput(user, pass)) {
            return;
        }
        if (!isUserVerified(user, pass))  return;
        forwardEmployee();
    }
    
    private boolean isUserVerified(String user, String pass) {
        try {
            list = new database.employee().verifyEmployee(user, pass);
            if (list == null) {
                alert.makeInfoAlert("Wrong username or password");
                return false;
            }

        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
        return true;
    }

    private void forwardEmployee() {
        Employee_name = list.getName();
        Employee_id = list.getEmployeeId();

        switch (list.getDepartment()) {
            case "Administration":
                control.CloseFxml(mainpane);
                control.openFxml("Admin", "HomePage");
                break;

            case "Reciptionist":
                control.CloseFxml(mainpane);
                control.openFxml("Reciptionist", "reciptionist");
                break;

            case "Pharmacy":
                control.CloseFxml(mainpane);
                control.openFxml("Pharmacist", "pharmacist");
                break;

            case "Laboratory":
                control.CloseFxml(mainpane);
                control.openFxml("LaboratoryTechnician", "laboratory_technician");
                break;

            case "ALLERGY":
            case "INTENSIVECARE":
            case "ANESTHESIOLOGY":
            case "CARDIOLOGY":
            case "ENT":
            case "NEUROLOGY":
            case "ORTHOPEDICS":
            case "PATHOLOGY":
            case "RADIOLOGY":
            case "SURGERY":
                control.CloseFxml(mainpane);
                control.openFxml("Doctor", "HomePage");
                break;
        }
    }

    @FXML
    private void forgetClicked() {
        control.CloseFxml(mainpane);
        control.openFxml("Authentication", "forget");
    }

    @FXML
    private void loginClicked() {
        control.CloseFxml(pane);
        control.openFxml("Authentication", "Login");
    }

    @FXML
    private void getRecoveryQuestion() throws ClassNotFoundException {
        String user = text.getText();
        if (!input.isValidInput(user)) return;
        if (!checkUser(user))  alert.makeInfoAlert("username not found, please enter correct username");
    }

    private boolean checkUser(String user) throws ClassNotFoundException {
        try {
            ResultSet rs = new database.employee().CheckUsername();
            while (rs.next()) {
                if (rs.getString(1).equals(user)) {
                    pane2.setVisible(true);
                    recoverButton.setDisable(true);
                    question.setText(rs.getString(2));
                    id = rs.getInt(3);
                    return true;
                }
            }
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
        return false;
    }

    @FXML
    private void checkAnswer() throws ClassNotFoundException {
        String ans = answer.getText();
        if (!input.isValidInput(ans))  return;
        if (!checkAnswer(ans))  alert.makeInfoAlert("Wrong answer");
    }

    private boolean checkAnswer(String answer) throws ClassNotFoundException {
        try {
            ResultSet rs = new database.employee().CheckAnswer(id);
            while (rs.next()) {
                String ans = rs.getString(1);
                if (ans.equals(answer)) {
                    pane3.setVisible(true);
                    answerButton.setDisable(true);
                    return true;
                }
            }
        } catch (SQLException ex) {
            alert.makeInfoAlert(ex.getMessage());
        }
        return false;
    }
  
    @FXML
    private void updatePassword() throws ClassNotFoundException {
        String newPass = String.valueOf(newPassword.getText());
        if (!input.isValidInput(newPass)) return;
        if (!updatePass(newPass)) {
            alert.makeInfoAlert("Password can't be updated now");
            return;
        }
        alert.makeInfoAlert("Password Successfully changed, Please login now!");
        control.CloseFxml(pane);
        control.openFxml("Authentication", "Login");
    }

    private boolean updatePass(String pass) {
        try {
            if(new database.employee().updatePassword(pass, id) == 0) return false;
        } catch (SQLException e) {
            alert.makeInfoAlert(e.getMessage());
        }
        return true;
    }
}
