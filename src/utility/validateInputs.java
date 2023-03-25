package utility;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class validateInputs {
    
    alerts alert = new alerts();
    
    public void numericAndFloatingOnly(TextField field) {
        field.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed (ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[0-9.]")) 
                    field.setText(newValue.replaceAll("[^0-9.]", ""));
                
            }
        });
    }
    
    public void numericOnly(TextField field) {
        field.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed (ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[0-9]")) 
                    field.setText(newValue.replaceAll("[^0-9]", ""));
                
            }
        });
    } 
    
    public void lettersOnly(TextField field) {
        field.textProperty().addListener(new ChangeListener<String>() {
            
            @Override
            public void changed (ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("[A-Za-z]")) 
                    field.setText(newValue.replaceAll("[^A-Za-z]", ""));
                
            }
        });
    } 
    
    public boolean isValidInput(String input) {
        if (input.trim().isEmpty()) {
            alert.makeInfoAlert("Please Fill Data!");
            return false;
        }
        return true;
    }
    
    public boolean isValidInput(String user, String pass) {
        if (user.trim().isEmpty() || pass.trim().isEmpty()) {
            alert.makeInfoAlert("username and password can't be empty!\nNote: Spaces as input are not valid!");
            return false;
        }
        return true;
    }
}
