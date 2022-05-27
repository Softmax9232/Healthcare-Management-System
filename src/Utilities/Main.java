package Utilities;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) {
        
        utility u=new utility();
            u.openFxml("Login");
    }

    public static void main(String[] args) {
        launch(args);
    }    
}
// expections