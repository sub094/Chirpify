import java.io.Serializable;

public class Input implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String location;
    private String details;
    private int time;
    private DateC date;

    public Input() {
        this.name = "";
        this.location = "";
        this.details = "";
        this.time = 0;
        this.date = new DateC();
    }

    public Input(String name, String location, String details, int time, DateC date) {
        this.name = name;
        this.location = location;
        this.details = details;
        this.time = time;
        this.date = date;
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getDetails() { return details; }
    public int getTime() { return time; }
    public DateC getDate() { return date; }

    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
    public void setDetails(String details) { this.details = details; }
    public void setTime(int time) { this.time = time; }
    public void setDate(DateC date) { this.date = date; }

    @Override
    public String toString() {
        return name;
    }
}
