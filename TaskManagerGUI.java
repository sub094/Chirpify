import javax.swing.*;
import java.awt.*;

public class TaskManagerGUI {

    private JFrame frame;
    private Project project;

    private JComboBox<String> taskDropdown;
    private JButton addButton, deleteButton, editButton, toggleCompleteButton;
    private JProgressBar progressBar;

    private boolean[] completedTasks; // Tracks completion for each task

    public TaskManagerGUI(Project project) {
        this.project = project;
        this.completedTasks = new boolean[project.getTaskCount()];

        // ===== Frame =====
        frame = new JFrame("Manage Tasks for: " + project.getName());
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // ===== Label =====
        JLabel label = new JLabel("Tasks for Project: " + project.getName());
        label.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(label);

        // ===== Task Dropdown =====
        taskDropdown = new JComboBox<>();
        taskDropdown.setPreferredSize(new Dimension(400, 30));
        refreshDropdown();
        frame.add(taskDropdown);

        // ===== Buttons =====
        addButton = createStyledButton("Add Task");
        deleteButton = createStyledButton("Delete Task");
        editButton = createStyledButton("Edit Task");
        toggleCompleteButton = createStyledButton("Toggle Complete");

        addButton.addActionListener(e -> addTask());
        deleteButton.addActionListener(e -> deleteTask());
        editButton.addActionListener(e -> editTask());
        toggleCompleteButton.addActionListener(e -> toggleCompletion());

        frame.add(addButton);
        frame.add(deleteButton);
        frame.add(editButton);
        frame.add(toggleCompleteButton);

        // ===== Progress Bar =====
        progressBar = new JProgressBar(0, 100);
        progressBar.setPreferredSize(new Dimension(450, 25));
        progressBar.setStringPainted(true);
        updateProgressBar();
        frame.add(progressBar);

        frame.setVisible(true);
    }

    // ===== Refresh Dropdown =====
    private void refreshDropdown() {
        taskDropdown.removeAllItems();
        // Ensure completedTasks array matches current project tasks
        if (completedTasks.length != project.getTaskCount()) {
            boolean[] newCompleted = new boolean[project.getTaskCount()];
            System.arraycopy(completedTasks, 0, newCompleted, 0, Math.min(completedTasks.length, newCompleted.length));
            completedTasks = newCompleted;
        }
        for (int i = 0; i < project.getTaskCount(); i++) {
            String prefix = completedTasks[i] ? "✓ " : "✗ ";
            taskDropdown.addItem(prefix + project.getTask(i));
        }

        // ✅ Only update progress bar if it exists
        if (progressBar != null) {
            updateProgressBar();
        }
    }


    // ===== Add Task =====
    private void addTask() {
        String description = JOptionPane.showInputDialog(frame, "Enter task description:");
        if (description != null && !description.isEmpty()) {
            project.addTask(description);
            // Add new completion status as false
            boolean[] newCompleted = new boolean[project.getTaskCount()];
            System.arraycopy(completedTasks, 0, newCompleted, 0, completedTasks.length);
            completedTasks = newCompleted;
            refreshDropdown();
        }
    }

    // ===== Delete Task =====
    private void deleteTask() {
        int index = taskDropdown.getSelectedIndex();
        if (index >= 0) {
            project.removeTask(index);
            // Remove completion status
            boolean[] newCompleted = new boolean[project.getTaskCount()];
            for (int i = 0, j = 0; i < completedTasks.length; i++) {
                if (i != index) newCompleted[j++] = completedTasks[i];
            }
            completedTasks = newCompleted;
            refreshDropdown();
        } else {
            JOptionPane.showMessageDialog(frame, "No task selected!");
        }
    }

    // ===== Edit Task =====
    private void editTask() {
        int index = taskDropdown.getSelectedIndex();
        if (index >= 0) {
            String current = project.getTask(index);
            String updated = JOptionPane.showInputDialog(frame, "Edit task description:", current);
            if (updated != null && !updated.isEmpty()) {
                project.removeTask(index);
                project.addTask(updated);
                // Preserve completion status
                boolean[] newCompleted = new boolean[project.getTaskCount()];
                for (int i = 0, j = 0; i < completedTasks.length; i++, j++) {
                    if (i < index) newCompleted[j] = completedTasks[i];
                    else if (i == index) newCompleted[j] = completedTasks[i];
                    else newCompleted[j] = completedTasks[i - 1];
                }
                completedTasks = newCompleted;
                refreshDropdown();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "No task selected!");
        }
    }

    // ===== Toggle Completion =====
    private void toggleCompletion() {
        int index = taskDropdown.getSelectedIndex();
        if (index >= 0) {
            completedTasks[index] = !completedTasks[index];
            refreshDropdown();
        } else {
            JOptionPane.showMessageDialog(frame, "No task selected!");
        }
    }

    // ===== Update Progress Bar =====
    private void updateProgressBar() {
        int completedCount = 0;
        for (boolean b : completedTasks) if (b) completedCount++;
        int total = project.getTaskCount();
        int percent = total > 0 ? (int) ((completedCount / (double) total) * 100) : 0;
        progressBar.setValue(percent);
        progressBar.setString("Completion: " + percent + "%");
    }

    // ===== Styled Button Helper =====
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 45));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180));
            }
        });

        return button;
    }
}
