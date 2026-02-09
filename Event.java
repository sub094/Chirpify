import java.io.Serializable;

public class Event extends Input implements Serializable {
    private static final long serialVersionUID = 1L;

    private DateC startDate;
    private DateC endDate;
    private String category;

    // Default constructor
    public Event() {
        super();
        this.startDate = new DateC();
        this.endDate = new DateC();
        this.category = "General";
    }

    // Big constructor (existing)
    public Event(String name, String location, String details, int time,
                 DateC date, String unusedLocation,
                 DateC startDate, DateC endDate,
                 String category) {
        super(name, location, details, time, date);
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
    }

    // **New simple constructor for GUI**
    public Event(String name, String location, DateC startDate, DateC endDate) {
        super(); // calls Input() default constructor
        this.setName(name);
        this.setLocation(location);
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = "General";
    }

    // Getters and setters
    public DateC getStartDate() { return startDate; }
    public DateC getEndDate() { return endDate; }
    public String getCategory() { return category; }

    public void setStartDate(DateC startDate) { this.startDate = startDate; }
    public void setEndDate(DateC endDate) { this.endDate = endDate; }
    public void setCategory(String category) { this.category = category; }
}
