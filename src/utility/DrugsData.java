package utility;

import java.time.LocalDate;

public class DrugsData {
    private int patientId , quantity;
    private String company , name;
    private float cost;
    private LocalDate date;

    private DrugsData(DrugsDataBuiler builder) {
        this.patientId = builder.patientId;
        this.quantity = builder.quantity;
        this.company = builder.company;
        this.name = builder.name;
        this.cost = builder.cost;
        this.date = builder.date;
    }

    public int getPatientId(){ return patientId; }
    public int getQuantity(){ return quantity; }
    public String getCompany(){ return company; }
    public String getName(){ return name; }
    public float getCost(){ return cost; }
    public LocalDate getDate(){ return date; }
   
    public static class DrugsDataBuiler {

        private int patientId, quantity;
        private String company, name;
        private float cost;
        private LocalDate date;

        public DrugsDataBuiler() {
        }

        public DrugsDataBuiler setPatientId(int patientId) {
            this.patientId = patientId;
            return this;
        }

        public DrugsDataBuiler setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public DrugsDataBuiler setCompany(String company) {
            this.company = company;
            return this;
        }

        public DrugsDataBuiler setName(String name) {
            this.name = name;
            return this;
        }

        public DrugsDataBuiler setCost(float cost) {
            this.cost = cost;
            return this;
        }

        public DrugsDataBuiler setDate(LocalDate date) {
            this.date = date;
            return this;
        }
        
        public DrugsData build(){
            return new DrugsData(this);
        }
    }
}
