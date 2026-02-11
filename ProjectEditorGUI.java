import javax.swing.*;
import java.awt.*;

public class ProjectEditorGUI extends JFrame {

    private ProjectManagerGUI managerGUI; // Reference to main GUI
    private Project project;               // Project being edited

    // Form fields
    private JTextField nameField;
    private JTextField endDay, endMonth, endYear;
    private JTextArea detailsArea;
    private JButton saveButton, backButton;

    public ProjectEditorGUI(ProjectManagerGUI managerGUI, Project project) {
        this.managerGUI = managerGUI;
        this.project = project;

        setTitle("Edit Project");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== Title =====
        JLabel titleLabel = new JLabel("Edit Project", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // ===== Form Panel =====
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // Project Name
        c.gridx = 0; c.gridy = row; formPanel.add(new JLabel("Project Name:"), c);
        nameField = new JTextField(project.getName());
        c.gridx = 1; formPanel.add(nameField, c); row++;

        // Details
        c.gridx = 0; c.gridy = row; formPanel.add(new JLabel("Details:"), c);
        detailsArea = new JTextArea(4, 20);
        detailsArea.setText(project.getDetails());
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        JScrollPane detailsScroll = new JScrollPane(detailsArea);
        c.gridx = 1; formPanel.add(detailsScroll, c); row++;

        // End Date
        c.gridx = 0; c.gridy = row; formPanel.add(new JLabel("End Date (DD/MM/YYYY):"), c);
        JPanel endPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        endDay = new JTextField(2); endMonth = new JTextField(2); endYear = new JTextField(4);

        // Prefill end date
        endDay.setText(String.valueOf(project.getDateAdded().getDay()));
        endMonth.setText(String.valueOf(project.getDateAdded().getMonth()));
        endYear.setText(String.valueOf(project.getDateAdded().getYear()));

        endPanel.add(endDay); endPanel.add(new JLabel("/"));
        endPanel.add(endMonth); endPanel.add(new JLabel("/"));
        endPanel.add(endYear);
        c.gridx = 1; formPanel.add(endPanel, c); row++;

        add(formPanel, BorderLayout.CENTER);

        // ===== Buttons =====
        JPanel buttonPanel = new JPanel(new FlowLayout());
        saveButton = createStyledButton("Save Changes");
        backButton = createStyledButton("Back");

        saveButton.addActionListener(e -> saveChanges());
        backButton.addActionListener(e -> dispose());

        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void saveChanges() {
        try {
            // Update project fields
            project.setName(nameField.getText());
            project.setDetails(detailsArea.getText());

            // Parse end date
            DateC endDate = new DateC(
                    Integer.parseInt(endDay.getText()),
                    Integer.parseInt(endMonth.getText()),
                    Integer.parseInt(endYear.getText())
            );
            project.setDateAdded(endDate); // Assuming 'dateAdded' now represents end date

            // Update dropdown in manager GUI
            int index = managerGUI.projectDropdown.getSelectedIndex();
            if (index >= 0) {
                managerGUI.projectDropdown.insertItemAt(project.getName(), index);
                managerGUI.projectDropdown.removeItemAt(index + 1);
                managerGUI.projectDropdown.setSelectedIndex(index);
            }

            JOptionPane.showMessageDialog(this, "Project updated!");
            dispose();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for the end date!", "Error", JOptionPane.ERROR_MESSAGE);
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
