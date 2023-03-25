package Main;

import javafx.application.Application;
import javafx.stage.Stage;
import utility.pageControl;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        new pageControl().openFxml("Authentication","Login");
    }

    public static void main(String[] args) {                
        launch(args);
    }
}
