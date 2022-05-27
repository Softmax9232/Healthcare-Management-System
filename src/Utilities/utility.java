package Utilities;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class utility {
    
      public void openFxml(String fxmlName) {
        try {
            Stage stage = new Stage();
            Parent parent = FXMLLoader.load(getClass().getResource("/FXMLs/" + fxmlName + ".fxml"));
            stage.setScene(new Scene(parent));
            stage.setResizable(false);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(utility.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(ex);
        }
    }
      
     public void CloseFxml(Pane mainpane) {

        Stage stage = (Stage)(mainpane.getScene().getWindow());
        stage.close();

    }
      public void CloseFxml(TabPane mainpane) {

        Stage stage = (Stage)(mainpane.getScene().getWindow());
        stage.close();

    }
       public void CloseFxml(AnchorPane mainpane) {

        Stage stage = (Stage)(mainpane.getScene().getWindow());
        stage.close();

    }
       
        public void CloseFxml(ScrollPane mainpane) {

        Stage stage = (Stage)(mainpane.getScene().getWindow());
        stage.close();

    }
      public Optional<ButtonType> make_Alert(String message){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText(message);
            Optional<ButtonType>action =alert.showAndWait();
            return action;
     }
 
     
    public void Search(TextField search, TableView table, ObservableList<ModelTable> list) {
        search.textProperty().addListener(new InvalidationListener() {
            
            @Override
            public void invalidated(Observable observable) {
                if (search.textProperty().get().isEmpty()) {
                    table.setItems(list);
                    return;
                }
                
                ObservableList<ModelTable> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<ModelTable, ?>> column = table.getColumns();

                for (int row = 0; row < list.size(); row++) {
                    for (int col = 0; col < column.size(); col++) {
                       
                        TableColumn colVar = column.get(col);
                        String cellValue = colVar.getCellData(list.get(row)).toString();
                        cellValue = cellValue.toLowerCase();
                        
                        if (cellValue.contains(search.getText().toLowerCase()) && cellValue.startsWith(search.getText().toLowerCase())) {
                            items.add(list.get(row));
                            break;
                        }
                    }
                }    
            table.setItems(items);
            }
        });
     
    }
    
    public void LogOut(AnchorPane mainpane){
        utility u=new utility();
        u.CloseFxml(mainpane);
        u.openFxml("Login");
    }
      public void LogOut(Pane mainpane){
        utility u=new utility();
        u.CloseFxml(mainpane);
        u.openFxml("Login");
    }
       public void LogOut(TabPane mainpane){
        utility u=new utility();
        u.CloseFxml(mainpane);
        u.openFxml("Login");
    }
    
}
  
