public class Task {
    // Instance variables
    private String description;
    private boolean completed;
    private DateC dueDate;
    private DateC createdDate;
    private int priority; // 1 = high, 2 = medium, 3 = low
    
    // Default constructor
    public Task() {
        this.description = "";
        this.completed = false;
        this.dueDate = new DateC(); // Default date
        this.createdDate = new DateC(); // Current date
        this.priority = 3; // Default to low priority
    }
    
    // Parameterized constructor with description
    public Task(String description) {
        this.description = description;
        this.completed = false;
        this.dueDate = new DateC(); // Default date
        this.createdDate = new DateC(); // Current date
        this.priority = 3; // Default to low priority
    }
    
    // Full parameterized constructor
    public Task(String description, boolean completed, DateC dueDate, DateC createdDate, int priority) {
        this.description = description;
        this.completed = completed;
        this.dueDate = dueDate;
        this.createdDate = createdDate;
        this.priority = priority;
    }
    
    // Getters
    public String getDescription() {
        return description;
    }
    
    public boolean isCompleted() {
        return completed;
    }
    
    public DateC getDueDate() {
        return dueDate;
    }
    
    public DateC getCreatedDate() {
        return createdDate;
    }
    
    public int getPriority() {
        return priority;
    }
    
    // Setters
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    public void setDueDate(DateC dueDate) {
        this.dueDate = dueDate;
    }
    
    public void setCreatedDate(DateC createdDate) {
        this.createdDate = createdDate;
    }
    
    public void setPriority(int priority) {
        if (priority >= 1 && priority <= 3) {
            this.priority = priority;
        } else {
            this.priority = 3; // Default to low if invalid
        }
    }
    
    // Convenience methods
    public void markComplete() {
        this.completed = true;
    }
    
    public void markIncomplete() {
        this.completed = false;
    }
    
    public String getPriorityString() {
        switch (priority) {
            case 1: return "High";
            case 2: return "Medium";
            case 3: return "Low";
            default: return "Low";
        }
    }
    
    // Check if task is overdue (you'll need to implement comparison methods in DateC)
    public boolean isOverdue(DateC currentDate) {
        // This is a placeholder - you'll need to implement date comparison in your DateC class
        // Return false for now since we don't know how your DateC comparison works
        return false;
    }
    
    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", completed=" + completed +
                ", dueDate=" + dueDate +
                ", createdDate=" + createdDate +
                ", priority=" + getPriorityString() +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return completed == task.completed &&
                priority == task.priority &&
                description.equals(task.description) &&
                dueDate.equals(task.dueDate) &&
                createdDate.equals(task.createdDate);
    }
    
    @Override
    public int hashCode() {
        return java.util.Objects.hash(description, completed, dueDate, createdDate, priority);
    }
}
