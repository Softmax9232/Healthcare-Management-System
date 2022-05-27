package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Analysis {

    int pie_ENT, pie_INTENSIVECARE, pie_ANESTHESIOLOGY, pie_CARDIOLOGY, pie_ORTHOPEDICS,
            pie_PATHOLOGY, pie_RADIOLOGY, pie_SURGERY, pie_NURSE, pie_SECURITY, pie_CLEANER,
            pie_Laboratory, pie_Pharmacy, pie_Administration, pie_Reciptionist, pie_ALLERGY, pie_NEUROLOGY, pie_ACCOUNTS;

    int Tnum, Dnum, Mnum, Enum;
    float salary1, salary2, salary3;

    public ArrayList<Integer> list = new ArrayList<Integer>();
    public ArrayList<Float> list2 = new ArrayList<Float>();

    public ArrayList<Integer> BarChart() {

        try {
            ResultSet rs = new Connect().select("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='ENT'");
            while (rs.next()) {
                pie_ENT = rs.getInt("COUNT(`Employee_Id`)");
            }
            list.add(0, pie_ENT);

            ResultSet rs1 = new Connect().select("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='INTENSIVECARE'");
            while (rs1.next()) {
                pie_INTENSIVECARE = rs1.getInt("COUNT(`Employee_Id`)");
            }
            list.add(1, pie_INTENSIVECARE);

            ResultSet rs2 = new Connect().select("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='ANESTHESIOLOGY'");
            while (rs2.next()) {
                pie_ANESTHESIOLOGY = rs2.getInt("COUNT(`Employee_Id`)");
            }
            list.add(2, pie_ANESTHESIOLOGY);

            ResultSet rs3 = new Connect().select("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='CARDIOLOGY'");
            while (rs3.next()) {
                pie_CARDIOLOGY = rs3.getInt("COUNT(`Employee_Id`)");
            }
            list.add(3, pie_CARDIOLOGY);

            ResultSet rs4 = new Connect().select("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='ORTHOPEDICS'");
            while (rs4.next()) {
                pie_ORTHOPEDICS = rs4.getInt("COUNT(`Employee_Id`)");
            }
            list.add(4, pie_ORTHOPEDICS);

            ResultSet rs5 = new Connect().select("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='PATHOLOGY'");
            while (rs5.next()) {
                pie_PATHOLOGY = rs5.getInt("COUNT(`Employee_Id`)");
            }
            list.add(5, pie_PATHOLOGY);

            ResultSet rs6 = new Connect().select("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='RADIOLOGY'");
            while (rs6.next()) {
                pie_RADIOLOGY = rs6.getInt("COUNT(`Employee_Id`)");
            }
            list.add(6, pie_RADIOLOGY);

            ResultSet rs7 = new Connect().select("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='SURGERY'");
            while (rs7.next()) {
                pie_SURGERY = rs7.getInt("COUNT(`Employee_Id`)");
            }
            list.add(7, pie_SURGERY);

            ResultSet rs15 = new Connect().select("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='ALLERGY'");
            while (rs15.next()) {
                pie_ALLERGY = rs15.getInt("COUNT(`Employee_Id`)");
            }
            list.add(8, pie_ALLERGY);

            ResultSet rs16 = new Connect().select("Select COUNT(`Employee_Id`)FROM `employee` WHERE `E_Department_Name`='NEUROLOGY'");
            while (rs16.next()) {
                pie_NEUROLOGY = rs16.getInt("COUNT(`Employee_Id`)");
            }
            list.add(9, pie_NEUROLOGY);
        } catch (SQLException ex) {
            new Utilities.utility().make_Alert(ex.getMessage());
        }
        return list;
    }

    public ArrayList<Integer> Counts() {
        try {
            ResultSet rs_1 = new Connect().select("SELECT COUNT(`Test_name`)FROM  `test`");
            while (rs_1.next()) {
                Tnum = rs_1.getInt("COUNT(`Test_name`)");
            }
            list.add(0, Tnum);

            ResultSet rs_2 = new Connect().select("SELECT COUNT(`Medicine_name`) FROM `medicine`");
            while (rs_2.next()) {
                Mnum = rs_2.getInt("COUNT(`Medicine_name`)");
            }
            list.add(1, Mnum);

            ResultSet rs_3 = new Connect().select("SELECT COUNT(`Employee_Id`)FROM `employee`");
            while (rs_3.next()) {
                Enum = rs_3.getInt("COUNT(`Employee_Id`)");
            }
            list.add(2, Enum);

            ResultSet rs_4 = new Connect().select("SELECT COUNT(`Department_name`)FROM `department`");
            while (rs_4.next()) {
                Dnum = rs_4.getInt("COUNT(`Department_name`)");
            }
            list.add(3, Dnum);
        } catch (SQLException ex) {
            new Utilities.utility().make_Alert(ex.getMessage());
        }
        return list;
    }

    public ArrayList<Float> Piechart() {

        try {
            ResultSet RS1 = new Connect().select("SELECT COUNT(`Employee_Id`)FROM `employee`WHERE `Salary`>2000 and `Salary`<5000");
            while (RS1.next()) {
                salary1 = RS1.getFloat("COUNT(`Employee_Id`)");
            }
            list2.add(0, salary1);

            ResultSet RS2 = new Connect().select("SELECT COUNT(`Employee_Id`)FROM `employee`WHERE `Salary`>5000 and `Salary`<10000");
            while (RS2.next()) {
                salary2 = RS2.getFloat("COUNT(`Employee_Id`)");
            }
            list2.add(0, salary2);

            ResultSet RS3 = new Connect().select("SELECT COUNT(`Employee_Id`)FROM `employee`WHERE `Salary`>10000");
            while (RS3.next()) {
                salary3 = RS3.getFloat("COUNT(`Employee_Id`)");
            }
            list2.add(0, salary3);
        } catch (SQLException ex) {
            new Utilities.utility().make_Alert(ex.getMessage());
        }
        return list2;
    }
}
