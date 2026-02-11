public class Project extends Input {
    // Additional instance variables for Project
    private DateC dateAdded;
    private String[] tasks;
    private int taskCount;
    private int maxTasks;

    // Default constructor
    public Project() {
        super(); // Call parent constructor
        this.dateAdded = new DateC(); // Set to current date
        this.maxTasks = 10;
        this.tasks = new String[maxTasks];
        this.taskCount = 0;
    }

    // Parameterized constructor
    public Project(String name, String location, String details, int time, DateC date,
                   DateC dateAdded) {
        super(name, location, details, time, date); // Call parent constructor
        this.dateAdded = dateAdded;
        this.maxTasks = 10;
        this.tasks = new String[maxTasks];
        this.taskCount = 0;
    }

    // Getter for dateAdded
    public DateC getDateAdded() {
        return dateAdded;
    }

    // Setter for dateAdded
    public void setDateAdded(DateC dateAdded) {
        this.dateAdded = dateAdded;
    }

    // Method to add a task
    public boolean addTask(String task) {
        if (taskCount < maxTasks) {
            tasks[taskCount] = task;
            taskCount++;
            return true;
        } else {
            // Expand array if needed
            expandTasksArray();
            tasks[taskCount] = task;
            taskCount++;
            return true;
        }
    }

    // Method to remove a task by index
    public boolean removeTask(int index) {
        if (index >= 0 && index < taskCount) {
            // Shift elements to fill the gap
            for (int i = index; i < taskCount - 1; i++) {
                tasks[i] = tasks[i + 1];
            }
            tasks[taskCount - 1] = null;
            taskCount--;
            return true;
        }
        return false;
    }

    // Method to get all tasks
    public String[] getTasks() {
        String[] result = new String[taskCount];
        System.arraycopy(tasks, 0, result, 0, taskCount);
        return result;
    }

    // Method to get task count
    public int getTaskCount() {
        return taskCount;
    }

    // Method to get a specific task
    public String getTask(int index) {
        if (index >= 0 && index < taskCount) {
            return tasks[index];
        }
        return null;
    }

    // Private method to expand tasks array when needed
    private void expandTasksArray() {
        maxTasks = maxTasks * 2;
        String[] newTasks = new String[maxTasks];
        System.arraycopy(tasks, 0, newTasks, 0, tasks.length);
        tasks = newTasks;
    }

    // Method to clear all tasks
    public void clearTasks() {
        for (int i = 0; i < taskCount; i++) {
            tasks[i] = null;
        }
        taskCount = 0;
    }

    // Override toString to include project-specific information
    @Override
    public String toString() {
        StringBuilder taskList = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            taskList.append(tasks[i]);
            if (i < taskCount - 1) {
                taskList.append(", ");
            }
        }

        return super.toString() +
                ", Project{" +
                "dateAdded=" + dateAdded +
                ", tasks=[" + taskList.toString() + "]" +
                ", taskCount=" + taskCount +
                '}';
    }

    // Method to check if project has tasks
    public boolean hasTasks() {
        return taskCount > 0;
    }

    // Method to check if tasks array is full
    public boolean isTasksFull() {
        return taskCount >= maxTasks;
    }
}
