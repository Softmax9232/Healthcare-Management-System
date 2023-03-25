package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Analysis {

    PreparedStatement ps = null;
    ResultSet rs = null;
    String query;
    ArrayList<Integer> list = new ArrayList<>();
    employee e = new employee();
    
    public ArrayList<Integer> BarChart() throws ClassNotFoundException {
        try {       
            list.add(0, e.countEmployees("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='ENT'"));
            list.add(1, e.countEmployees("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='INTENSIVECARE'"));
            list.add(2, e.countEmployees("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='ANESTHESIOLOGY'"));
            list.add(3, e.countEmployees("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='CARDIOLOGY'"));
            list.add(4, e.countEmployees("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='ORTHOPEDICS'"));
            list.add(5, e.countEmployees("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='PATHOLOGY'"));
            list.add(6, e.countEmployees("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='RADIOLOGY'"));
            list.add(7, e.countEmployees("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='SURGERY'"));
            list.add(8, e.countEmployees("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='ALLERGY'"));
            list.add(9, e.countEmployees("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='NEUROLOGY'"));
                
        } catch (SQLException ex) {
            new utility.alerts().makeInfoAlert(ex.getMessage());
        }
        return list;
    }
    
    public ArrayList<Integer> Counts() {
        try {
            list.add(0, new Test().countTests());
            list.add(1, new Drug().countDrugs());
            list.add(2, e.countEmployees());
            list.add(3, e.countDepartments());
            
        } catch (SQLException ex) {
            Logger.getLogger(Analysis.class.getName()).log(Level.SEVERE, null, ex);
        }
         return list;
    }  

    public ArrayList<Float> Piechart(){
        ArrayList<Float> list = new ArrayList<>();        
        try {    
            list.add(0, e.countSalary("SELECT COUNT(`Employee_Id`)FROM `employee`WHERE `Salary`>2000 and `Salary`<5000")); 
            list.add(1, e.countSalary("SELECT COUNT(`Employee_Id`)FROM `employee`WHERE `Salary`>5000 and `Salary`<10000"));
            list.add(2, e.countSalary("SELECT COUNT(`Employee_Id`)FROM `employee`WHERE `Salary`>10000"));
        } catch (SQLException ex) {
            new utility.alerts().makeInfoAlert(ex.getMessage());
        }
        return list;
    }
}