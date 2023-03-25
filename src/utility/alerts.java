package utility;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class alerts {

    public Optional<ButtonType> makeInfoAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> action = alert.showAndWait();
        return action;
    }

}
