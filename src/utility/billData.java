package utility;

import java.time.LocalDate;

public class billData {

    private int patientId;
    private String patientName;
    private LocalDate date;
    private float medicineCost, testCost, examinationCost, roomCost;

    public billData(int patientId, String patientName, LocalDate data, float medicineCost, float testCost, float examinationCost, float roomCost) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.date = date;
        this.medicineCost = medicineCost;
        this.testCost = testCost;
        this.examinationCost = examinationCost;
        this.roomCost = roomCost;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public LocalDate getDate() {
        return date;
    }

    public float getMedicineCost() {
        return medicineCost;
    }

    public float getTestCost() {
        return testCost;
    }

    public float getExaminationCost() {
        return examinationCost;
    }

    public float getRoomCost() {
        return roomCost;
    }

}
