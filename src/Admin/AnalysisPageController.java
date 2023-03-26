package Admin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import utility.alerts;
import utility.pageControl;

public class AnalysisPageController implements Initializable {

    @FXML
    private BarChart<?, ?> BarChart_doctors;
    @FXML
    private Label T_label , E_label , M_label , D_label;
    @FXML
    private PieChart piechart_salary;
    @FXML
    private AnchorPane pane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (database.DatabaseConnection.getConnection() == null){
                new utility.pageControl().openFxml("Admin", "AnalysisPage");
                new alerts().makeInfoAlert("Check the Connection with Database");
            }
            else{
            ArrayList<Integer> list, list2;
            ArrayList<Float> list3;
            
            list = new database.Analysis().BarChart();
            XYChart.Series doctors = new XYChart.Series();
            doctors.setName("Number of Employees");
            doctors.getData().add(new XYChart.Data("ENT", list.get(0)));
            doctors.getData().add(new XYChart.Data("INTENSIVECARE", list.get(1)));
            doctors.getData().add(new XYChart.Data("ANESTHESIOLOGY", list.get(2)));
            doctors.getData().add(new XYChart.Data("CARDIOLOGY", list.get(3)));
            doctors.getData().add(new XYChart.Data("ORTHOPEDICS", list.get(4)));
            doctors.getData().add(new XYChart.Data("PATHOLOGY", list.get(5)));
            doctors.getData().add(new XYChart.Data("SURGERY", list.get(6)));
            doctors.getData().add(new XYChart.Data("NEUROLOGY", list.get(7)));
            doctors.getData().add(new XYChart.Data("ALLERGY", list.get(8)));
            BarChart_doctors.getData().add(doctors);

            list2 = new database.Analysis().Counts();

            T_label.setText(String.valueOf(list2.get(0)));
            E_label.setText(String.valueOf(list2.get(1)));
            M_label.setText(String.valueOf(list2.get(2)));
            D_label.setText(String.valueOf(list2.get(3)));

            list3 = new database.Analysis().Piechart();

            ObservableList<PieChart.Data> bar_salary = FXCollections.observableArrayList(
                    new PieChart.Data("Salary (>2000 & <5000)", list3.get(0)),
                    new PieChart.Data("Salary (>5000 & <10000)", list3.get(1)),
                    new PieChart.Data("Salary >10000", list3.get(2)));
            piechart_salary.setData(bar_salary);
            }
        } catch (ClassNotFoundException ex) {
            new alerts().makeInfoAlert(ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(AnalysisPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Back() {
        new pageControl().CloseFxml(pane);
        new pageControl().openFxml("Admin","HomePage");
    }

}
