import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProjectManagerGUI {

    private JFrame frame;
    private JPanel panel;
    private JLabel label;
    private JButton button;

    // Constructor to set up the GUI
    public ProjectManagerGUI() {
        // Create frame
        frame = new JFrame("Project Manager");
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create panel
        panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Create components
        label = new JLabel("Welcome to Project Manager!");
        button = createStyledButton("Create Project");

        // Add button action
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createProject();
            }
        });

        // Add components to panel
        panel.add(label);
        panel.add(button);

        // Add panel to frame
        frame.add(panel);

        // Make frame visible
        frame.setVisible(true);
    }
    public void createProject(){
        new ProjectCreatorGUI();
    }
    // Helper method to create styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 45));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180)); // Steel blue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Add hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(100, 149, 237)); // Cornflower blue
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(70, 130, 180)); // Steel blue
            }
        });

        return button;
    }
}
