package utility;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;

public abstract class Controller{
    public int index;
    alerts alert = new alerts(); 
    
    public ObservableList<String> drugList = FXCollections.observableArrayList("ACETAMINOPHEN", "ANDROCUR", "ANTACIDS", "ANTI HISTAMINES", "ANTIPSYCHOTIC", "ASPIRIN", "AVIL", "BENZODIAZEPINES", "BETA-BLOCKER", "BETADIN", "CANESTEN", "CIPROXIN", "CROCINE", "CYPROSTAT", "DESTOLIT", "DETTOL", "DICLOFENAC", "GLUCOSE", "HIDRASEC", "LIVER-52", "METHYLPHENIDATE", "NAMOSLATE", "OMEE", "OMNI GEL", "ORS"),
            genderList = FXCollections.observableArrayList("Male", "Female"),
            diseaseList = FXCollections.observableArrayList("ALLERGY", "INTENSIVECARE", "ANESTHESIOLOGY", "CARDIOLOGY", "ENT", "NEUROLOGY", "ORTHOPEDICS", "PATHOLOGY", "RADIOLOGY", "SURGERY"),
            testList = FXCollections.observableArrayList("BIOPSY", "BLOOD TEST", "CBC", "CT-SCAN", "ENDOSCOPY", "MRI SCAN", "ULTRASOUND", "URINE TEST", "X-RAY"),
            bloodGroupList = FXCollections.observableArrayList("A+", "A-", "B+", "B-", "O+", "O-", "AB+", "AB-"),
            time = FXCollections.observableArrayList("00:00", "00:30", "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00", "20:30", "21:00", "21:30", "22:00", "22:30", "23:00", "23:30");

    public void insertData() throws InterruptedException {
        if (!checkButton()) return;
        if (isNotValidInput()) {
            alert.makeInfoAlert("Please Fill All DATA\nNote: Spaces as input are not valid!");
            return;
        }

        insertQuery();
        addDataInTable();
        TimeUnit.SECONDS.sleep(1);
        cleanFields();
    }

    private boolean checkButton(){
        if (index < 0) {
            alert.makeInfoAlert("No data has been entered to deal with!");
            return false;
        }
        return true;
    }
    
    public void updateData() throws InterruptedException {
        if (!checkButton()) return;
        if (isNotValidInput()) {
            alert.makeInfoAlert("Please Fill All DATA\nNote: Spaces as input are not valid!");
            return;
        }

        updateQuery();
        updateDataInTable();
        TimeUnit.SECONDS.sleep(1);
        cleanFields();
    }

    public abstract void updateQuery();

    public void deleteData() throws InterruptedException {
        if (!checkButton())  return;
        Optional<ButtonType> action = alert.makeInfoAlert("Are You Sure?");
        if (action.get() == ButtonType.OK) {
            deleteQuery();
            cleanFields();
            TimeUnit.SECONDS.sleep(1);
            deleteDateFromTable();
        }
    }

    public abstract void LoadDataInTable();

    public abstract void getSelectedData();

    public abstract boolean isNotValidInput();

    public abstract void cleanFields();

    public abstract Object prepareData();

    public abstract void insertQuery();

    public abstract void addDataInTable();

    public abstract void deleteQuery();

    public abstract void updateDataInTable();

    public abstract void deleteDateFromTable();

    public abstract void search();

    public abstract void logout();
    
    
}
