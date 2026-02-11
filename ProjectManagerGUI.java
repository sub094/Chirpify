import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProjectManagerGUI {

    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JButton createButton, deleteButton, manageTasksButton, editButton;
    JComboBox<String> projectDropdown;

    private ArrayList<Project> projects; // Store full Project objects

    public ProjectManagerGUI() {
        projects = new ArrayList<>();

        // ===== Frame =====
        frame = new JFrame("Project Manager");
        frame.setSize(600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // ===== Label =====
        label = new JLabel("Welcome to Project Manager!");
        label.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(label);

        // ===== Dropdown =====
        projectDropdown = new JComboBox<>();
        projectDropdown.setPreferredSize(new Dimension(400, 30));
        frame.add(projectDropdown);

        // ===== Buttons =====
        createButton = createStyledButton("Create Project");
        deleteButton = createStyledButton("Delete Project");
        manageTasksButton = createStyledButton("Manage Tasks");
        editButton = createStyledButton("Edit Project");

        // Add action listeners
        createButton.addActionListener(e -> new ProjectCreatorGUI(this));
        deleteButton.addActionListener(e -> deleteSelectedProject());
        manageTasksButton.addActionListener(e -> manageTasksForSelectedProject());
        editButton.addActionListener(e -> editSelectedProject());

        // Add buttons to frame
        frame.add(createButton);
        frame.add(deleteButton);
        frame.add(manageTasksButton);
        frame.add(editButton);

        frame.setVisible(true);
    }

    // ===== Add Project =====
    public void addProject(Project project) {
        projects.add(project);                  // Save full project
        projectDropdown.addItem(project.getName()); // Only show project name in dropdown
    }

    // ===== Delete Selected Project =====
    private void deleteSelectedProject() {
        int index = projectDropdown.getSelectedIndex();
        if (index >= 0) {
            projects.remove(index);
            projectDropdown.removeItemAt(index);
            JOptionPane.showMessageDialog(frame, "Project deleted.");
        } else {
            JOptionPane.showMessageDialog(frame, "No project selected!");
        }
    }

    // ===== Manage Tasks for Selected Project =====
    private void manageTasksForSelectedProject() {
        int index = projectDropdown.getSelectedIndex();
        if (index >= 0) {
            Project project = projects.get(index);
            new TaskManagerGUI(project); // You can create a TaskManagerGUI to handle tasks
        } else {
            JOptionPane.showMessageDialog(frame, "No project selected!");
        }
    }

    // ===== Edit Selected Project =====
    private void editSelectedProject() {
        int index = projectDropdown.getSelectedIndex();
        if (index >= 0) {
            Project project = projects.get(index);
            new ProjectEditorGUI(this, project); // You can create a ProjectEditorGUI to edit
        } else {
            JOptionPane.showMessageDialog(frame, "No project selected!");
        }
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
