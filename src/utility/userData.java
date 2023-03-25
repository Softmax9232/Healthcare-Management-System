package utility;

import java.time.LocalDate;

public class userData {

    private int employeeId, patientId, roomId, employeeId2;
    private String name, gender, email, address, username, password, relativeName, department, bloodGroup, prescription, time1, time2;
    private float salary, weight, height;
    private long ssn, phone;
    private LocalDate date,date2, birhtdate, appointment, nextAppointment;

    private userData(UserBuilder builder) {
        this.employeeId2 = builder.employeeId2;
        this.employeeId = builder.employeeId;
        this.patientId = builder.patientId;
        this.name = builder.name;
        this.gender = builder.gender;
        this.email = builder.email;
        this.address = builder.address;
        this.username = builder.username;
        this.password = builder.password;
        this.relativeName = builder.relativeName;
        this.department = builder.department;
        this.bloodGroup = builder.bloodGroup;
        this.prescription = builder.prescription;
        this.time1 = builder.time1;
        this.time2 = builder.time2;
        this.salary = builder.salary;
        this.weight = builder.weight;
        this.height = builder.height;
        this.ssn = builder.ssn;
        this.phone = builder.phone;
        this.date = builder.date;
        this.date2 = builder.date2;
        this.birhtdate = builder.birhtdate;
        this.appointment = builder.appointment;
        this.roomId = builder.roomId;
    }

    public int getRoomId(){ return roomId; }
    public int getEmployeeId() { return employeeId; }
    public int getPatientId(){ return patientId; }
    public int getEmployeeId2(){ return employeeId2; }
    public String getName(){ return name; }
    public String getGender(){ return gender; }
    public String getEmail(){ return email; }
    public String getAddress(){ return address; }
    public String getUsername() { return username; }
    public String getPassword(){ return password; }
    public String getRelativeName(){ return relativeName; }
    public String getDepartment(){ return department; }
    public String getBloodGroup(){ return bloodGroup; }
    public String getPrescription(){ return prescription; }
    public String getTime1(){ return time1; }
    public String getTime2(){ return time2; }
    public float getSalary(){ return salary; }
    public float getWeight(){ return weight; }
    public float getHeight(){ return height; }
    public long getSsn(){ return ssn; }
    public long getPhone(){ return phone; }
    public LocalDate getDate(){ return date; }
    public LocalDate getDate2(){ return date2; }
    public LocalDate getBirhtdate(){ return birhtdate; }
    public LocalDate getAppointment(){ return appointment; }
    public LocalDate getNextAppointment(){ return nextAppointment; }

    
    public static class UserBuilder {

        private int employeeId, patientId, roomId, employeeId2;
        private String name, gender, email, address, username, password, relativeName, department, bloodGroup, prescription, time1, time2;
        private float salary, weight, height;
        private long ssn, phone;
        private LocalDate date,date2, birhtdate, appointment,nextAppointment;

        public UserBuilder() {
        }

        public UserBuilder setPatientId(int patientId) {
            
            this.patientId = patientId;
            return this;
        }

        public UserBuilder setEmployeetId(int employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public UserBuilder setEmployeetId2(int employeeId2) {
            this.employeeId2 = employeeId2;
            return this;
        }

        public UserBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder setGender(String gender) {
            this.gender = gender;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setDepartment(String department) {
            this.department = department;
            return this;
        }

        public UserBuilder setSalary(float salary) {
            this.salary = salary;
            return this;
        }

        public UserBuilder setSsn(long ssn) {
            this.ssn = ssn;
            return this;
        }

        public UserBuilder setPhone(long phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder setBirhtdate(LocalDate birhtdate) {
            this.birhtdate = birhtdate;
            return this;
        }

        public UserBuilder setDate(LocalDate date) {
            this.date = date;
            return this;
        }
        
        public UserBuilder setDate2(LocalDate date2) {
            this.date2 = date2;
            return this;
        }

        public UserBuilder setBloodGroup(String bloodGroup) {
            this.bloodGroup = bloodGroup;
            return this;
        }

        public UserBuilder setWeight(float weight) {
            this.weight = weight;
            return this;
        }

        public UserBuilder setHight(float height) {
            this.height = height;
            return this;
        }

        public UserBuilder setPrescription(String prescription) {
            this.prescription = prescription;
            return this;
        }

        public UserBuilder setTime1(String time1) {
            this.time1 = time1;
            return this;
        }

        public UserBuilder setTime2(String time2) {
            this.time2 = time2;
            return this;
        }

        public UserBuilder setNextAppointment(LocalDate nextAppointment) {
            this.nextAppointment = nextAppointment;
            return this;
        }

        public UserBuilder setAppointment(LocalDate appointment) {
            this.appointment = appointment;
            return this;
        }

        public UserBuilder setRelativeName(String relativeName) {
            this.relativeName = relativeName;
            return this;
        }

        public UserBuilder setRoomId(int roomId) {
            this.roomId = roomId;
            return this;
        }

        public userData build() {
            return new userData(this);
        }
    }
}
