package utility;

import java.util.Date;

public class TestData {
    private String name;
    private float cost;
    private int patientId;
    private Date receivingDate;

    private TestData(TestDataBuilder builder) {
        this.name = builder.name;
        this.cost = builder.cost;
        this.patientId = builder.patientId;
        this.receivingDate = builder.receivingDate;
    }

    public String getName(){ return name; }

    public float getCost(){ return cost; }

    public int getPatientId(){ return patientId; }

    public Date getReceivingDate(){ return receivingDate; }
    
    
      public static class TestDataBuilder {

        private String name;
        private float cost;
        private int patientId;
        private Date receivingDate;

        public TestDataBuilder() {
        }

        public TestDataBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public TestDataBuilder setCost(float cost) {
            this.cost = cost;
            return this;
        }

        public TestDataBuilder setPatientId(int patientId) {
            this.patientId = patientId;
            return this;
        }

        public TestDataBuilder setReceivingDate(Date receivingDate) {
            this.receivingDate = receivingDate;
            return this;
        }
        
        public TestData build(){
            return new TestData(this);
        }   
    }
}
