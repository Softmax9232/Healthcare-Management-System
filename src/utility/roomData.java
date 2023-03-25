package utility;

import java.time.LocalDate;

public class roomData {
    
    private float cost;
    private LocalDate date;
    private String type;
    private int duration;

    public roomData(float cost, LocalDate date, String type, int duration) {
        this.cost = cost;
        this.date = date;
        this.type = type;
        this.duration = duration;
    }
    public float getCost() {
        return cost;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getDuration() {
        return duration;
    }

    
    
}
