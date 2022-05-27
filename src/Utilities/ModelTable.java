package Utilities;

import java.sql.Date;

public class ModelTable {

    String id3,id2,nurseAssigned,roomAssigned,id,EmployeeName,patientName,gender,Test, email, address,doctorAssign, username, password,relativeName ,employeeType, department, role,bloodGroup,illnessIssue,drug,company,roomType,prescription,doctorname,time1,time2;
    float salary,weight,height,drugCost,TestCost,RoomCost,ExaminationCost,Total;
    int quantity,duration,MQuantity,TQuantity;
    long ssn,phone,relativePhone;
    Date date,date2,date3,birhtdate,appointment;
    
    public String getPatientName() {
        return patientName;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public String getId3() {
        return id3;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Date getDate3() {
        return date3;
    }

    public String getDoctorAssign() {
        return doctorAssign;
    }

    public String getId2() {
        return id2;
    }

    public long getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public String getDepartment() {
        return department;
    }

    public String getRole() {
        return role;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getIllnessIssue() {
        return illnessIssue;
    }

    public String getDrug() {
        return drug;
    }

    public String getCompany() {
        return company;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getNurseAssigned() {
        return nurseAssigned;
    }

    public String getRoomAssigned() {
        return roomAssigned;
    }

    public Date getDate() {
        return date;
    }

    public Date getDate2() {
        return date2;
    }

    public String getPrescription() {
        return prescription;
    }

    public Date getBirhtdate() {
        return birhtdate;
    }

    public Date getAppointment() {
        return appointment;
    }

    public long getSsn() {
        return ssn;
    }

    public float getSalary() {
        return salary;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public float getDrugCost() {
        return drugCost;
    }

    public String getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public long getRelativePhone() {
        return relativePhone;
    }

    public String getRelativeName() {
        return relativeName;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public String getTest() {
        return Test;
    }

    public float getTestCost() {
        return TestCost;
    }

    public float getRoomCost() {
        return RoomCost;
    }

    public int getDuration() {
        return duration;
    }

    public int getMQuantity() {
        return MQuantity;
    }

    public int getTQuantity() {
        return TQuantity;
    }

    public float getExaminationCost() {
        return ExaminationCost;
    }

    
    public float getTotal() {
        return Total;
    }

    public String getTime1() {
        return time1;
    }

    public String getTime2() {
        return time2;
    }
    
    /* this constructor for drug store table in pharmacist module  */
    public ModelTable(String drug, String company, Date date, float drugCost, int quantity) {
        this.drug = drug;
        this.company = company;
        this.date = date;
        this.drugCost = drugCost;
        this.quantity = quantity;
    }

    /*   this constructor for patient's drugs and tests table for pharmacist module     */

    public ModelTable(String drug,Date date, String id, int quantity) {
        this.drug = drug;
        this.date = date;
        this.id = id;
        this.quantity = quantity;
    }
    

    /*   this constructor for inpatient table for reciptionist module     */
        public ModelTable(String patientName,String id2, String gender, String email, String address,long relativePhone, String relativeName, String id3, String illnessIssue, String nurseAssigned, String roomAssigned, Date birhtdate, long ssn ) {
        this.patientName = patientName;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.illnessIssue = illnessIssue;
        this.nurseAssigned = nurseAssigned;
        this.birhtdate = birhtdate;
        this.ssn = ssn;
        this.id3=id3;
        this.relativeName=relativeName;
        this.id2=id2;
        this.roomAssigned=roomAssigned;
        this.relativePhone=relativePhone;
    }

    /*   this constructor for patient table for reciptionist module     */
    public ModelTable(String id2 ,String patientName, String gender, String email, String address, long phone, String illnessIssue, String id3, Date birhtdate, Date appointment, long ssn) {
        this.patientName = patientName;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.illnessIssue = illnessIssue;
        this.id3 = id3;
        this.birhtdate = birhtdate;
        this.appointment = appointment;
        this.ssn = ssn;
        this.id2=id2;
    }
    
    /*   this constructor for employee table for admin module     */
    public ModelTable(String EmployeeName, String gender, String email, String address, long phone, String username, String password, String department, Date date, Date birhtdate, float salary, String id, long ssn) {
        this.EmployeeName = EmployeeName;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.username = username;
        this.password = password;
        this.department = department;
        this.date = date;
        this.birhtdate = birhtdate;
        this.salary = salary;
        this.id = id;
        this.ssn = ssn;
    }
    
    
    
   /*   this constructor for patients table for doctor module     */
    public ModelTable(String id,String patientName, String gender, String email, Date appointment, String bloodGroup,  float weight, float height , Date birhtdate , long phone, String prescription,Date date2,String time1,String time2) {
        this.id=id;
        this.patientName = patientName;
        this.email = email;
        this.phone = phone;
        this.bloodGroup = bloodGroup;
        this.birhtdate=birhtdate;
        this.appointment=appointment;
        this.prescription = prescription;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.date2=date2;
        this.time1=time1;
        this.time2=time2;
    }
    
    /*  this constructor for bill table for reciptionist module  */
    public ModelTable( String id, Date date, float RoomCost,float ExaminationCost,float TestCost,float drugCost ) {     
        this.date = date;
        this.id=id;
        this.TestCost=TestCost;
        this.RoomCost = RoomCost;
        this.ExaminationCost=ExaminationCost;
        this.drugCost=drugCost;
    }
  
    /*  this constructor for pharmacist module  */
    public ModelTable(String id, String Test, Date date) {
        this.Test = Test;
        this.date = date;
        this.id = id;
    }

    public ModelTable(String Test, float TestCost, Date date) {
        this.Test = Test;
        this.TestCost = TestCost;
        this.date = date;
    }

    public ModelTable(String Test, float TestCost) {
        this.Test = Test;
        this.TestCost = TestCost;
    }


    public ModelTable(int MQuantity, String drug, float drugCost,Date date) {
        this.drug = drug;
        this.MQuantity = MQuantity;
        this.drugCost = drugCost;
        this.date=date;
    }
    
     public ModelTable(int MQuantity, String drug, float drugCost) {
        this.drug = drug;
        this.MQuantity = MQuantity;
        this.drugCost = drugCost;
    }

   
    
public ModelTable(String patientName, String time1, String time2, String id2) {
        this.patientName = patientName;
        this.time1 = time1;
        this.time2 = time2;
        this.id2 = id2;
    }
}
