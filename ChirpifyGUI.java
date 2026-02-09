import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChirpifyGUI extends JFrame {
    private JLabel titleLabel;
    private JButton eventsButton;
    private JButton projectsButton;
    private JButton tasksButton;
    public static ArrayList<Event> eventList = new ArrayList<>();

    public ChirpifyGUI() {
        // Set up the main window
        setTitle("Chirpify");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null); // Center the window
        setResizable(false); // Fixed size for consistent layout
        
        // Create and set up the main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        setContentPane(mainPanel);
        
        // Create title panel
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titleLabel = new JLabel("Chirpify");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(70, 130, 180)); // Steel blue color
        titlePanel.add(titleLabel);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));
        
        // Create center panel (can be used for welcome message or app description)
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        
        // Add welcome message
        JLabel welcomeLabel = new JLabel("Welcome to Chirpify - Your Event Manager", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        centerPanel.add(welcomeLabel, BorderLayout.CENTER);
        
        // Create description label
        JLabel descriptionLabel = new JLabel("Manage your events, projects, and tasks in one place", SwingConstants.CENTER);
        descriptionLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        descriptionLabel.setForeground(Color.GRAY);
        descriptionLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        centerPanel.add(descriptionLabel, BorderLayout.SOUTH);
        
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        // Create buttons with custom styling
        eventsButton = createStyledButton("Events");
        projectsButton = createStyledButton("Projects");
        tasksButton = createStyledButton("Tasks");
        
        // Add action listeners
        eventsButton.addActionListener(new NavigationListener());
        projectsButton.addActionListener(new NavigationListener());
        tasksButton.addActionListener(new NavigationListener());
        
        // Add buttons to button panel
        buttonPanel.add(eventsButton);
        buttonPanel.add(projectsButton);
        buttonPanel.add(tasksButton);
        
        // Add panels to main panel
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Make the window visible
        setVisible(true);
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
    
    // Action listener for navigation buttons
    private class NavigationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            if (source == eventsButton) {
                // Open the Event Creator GUI
                SwingUtilities.invokeLater(() -> new UpcomingEventsGUI());
            } else if (source == projectsButton) {
                SwingUtilities.invokeLater(() ->  new ProjectManagerGUI());
            } else if (source == tasksButton) {
                JOptionPane.showMessageDialog(ChirpifyGUI.this,
                        "Tasks management will be implemented here",
                        "Tasks", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    
    // Main method to run the application
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Set system look and feel - CORRECTED METHOD CALL
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new ChirpifyGUI();
            }
        });
    }
}
