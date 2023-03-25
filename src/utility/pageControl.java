package utility;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class pageControl {

    public void openFxml(String module, String fxmlName) {
        try {
            Stage stage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("/" + module + "/" + fxmlName + ".fxml"));
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(pageControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LogOut(AnchorPane mainpane) {
        CloseFxml(mainpane);
        openFxml("Authentication", "Login");
    }

    public void CloseFxml(AnchorPane mainpane) {

        Stage stage = (Stage) (mainpane.getScene().getWindow());
        stage.close();

    }
}
