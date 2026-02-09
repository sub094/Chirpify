import javax.swing.*;
import java.awt.*;

public class EventCreatorGUI extends JFrame {
    private UpcomingEventsGUI upcomingGUI;

    // Form fields
    private JTextField nameField, locationField;
    private JTextField hourField, minuteField;
    private JComboBox<String> ampmBox;
    private JTextField startDay, startMonth, startYear;
    private JTextField endDay, endMonth, endYear;;
    private JComboBox<String> categoryCombo;
    private JTextArea detailsArea;
    private JButton saveButton;

    // Constructors
    public EventCreatorGUI() { this(null); }

    public EventCreatorGUI(UpcomingEventsGUI upcomingGUI) {
        this.upcomingGUI = upcomingGUI;

        setTitle("Create Event");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Title
        JLabel titleLabel = new JLabel("Create Event", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // Name
        c.gridx = 0; c.gridy = row; formPanel.add(new JLabel("Name:"), c);
        nameField = new JTextField(); c.gridx = 1; formPanel.add(nameField, c); row++;

        // Location
        c.gridx = 0; c.gridy = row; formPanel.add(new JLabel("Location:"), c);
        locationField = new JTextField(); c.gridx = 1; formPanel.add(locationField, c); row++;

        // Details
        c.gridx = 0; c.gridy = row; formPanel.add(new JLabel("Details:"), c);
        detailsArea = new JTextArea(4, 20);
        JScrollPane detailsScroll = new JScrollPane(detailsArea);
        c.gridx = 1; formPanel.add(detailsScroll, c); row++;

        // Time with AM/PM
        c.gridx = 0; c.gridy = row; formPanel.add(new JLabel("Time:"), c);
        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        hourField = new JTextField(2);
        minuteField = new JTextField(2);
        ampmBox = new JComboBox<>(new String[]{"AM", "PM"});
        timePanel.add(hourField); timePanel.add(new JLabel(":"));
        timePanel.add(minuteField); timePanel.add(ampmBox);
        c.gridx = 1; formPanel.add(timePanel, c); row++;

        // Start Date
        c.gridx = 0; c.gridy = row; formPanel.add(new JLabel("Start Date (DD/MM/YYYY):"), c);
        JPanel startPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        startDay = new JTextField(2); startMonth = new JTextField(2); startYear = new JTextField(4);
        startPanel.add(startDay); startPanel.add(new JLabel("/"));
        startPanel.add(startMonth); startPanel.add(new JLabel("/"));
        startPanel.add(startYear);
        c.gridx = 1; formPanel.add(startPanel, c); row++;

        // End Date
        c.gridx = 0; c.gridy = row; formPanel.add(new JLabel("End Date (DD/MM/YYYY):"), c);
        JPanel endPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        endDay = new JTextField(2); endMonth = new JTextField(2); endYear = new JTextField(4);
        endPanel.add(endDay); endPanel.add(new JLabel("/"));
        endPanel.add(endMonth); endPanel.add(new JLabel("/"));
        endPanel.add(endYear);
        c.gridx = 1; formPanel.add(endPanel, c); row++;
        // Category
        c.gridx = 0; c.gridy = row; formPanel.add(new JLabel("Category:"), c);
        categoryCombo = new JComboBox<>(new String[]{"General", "Work", "School", "Personal"});
        c.gridx = 1; formPanel.add(categoryCombo, c); row++;

        add(formPanel, BorderLayout.CENTER);

        // Save button
        saveButton = createStyledButton("Save Event");
        saveButton.addActionListener(e -> saveEvent());
        add(saveButton, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void saveEvent() {
        try {
            String name = nameField.getText();
            String location = locationField.getText();
            String details = detailsArea.getText();

            int hour = Integer.parseInt(hourField.getText());
            int minute = Integer.parseInt(minuteField.getText());
            String ampm = (String) ampmBox.getSelectedItem();
            if (ampm.equals("PM") && hour != 12) hour += 12;
            if (ampm.equals("AM") && hour == 12) hour = 0;
            int time = hour * 100 + minute;

            DateC start = new DateC(Integer.parseInt(startDay.getText()), Integer.parseInt(startMonth.getText()), Integer.parseInt(startYear.getText()));
            DateC end = new DateC(Integer.parseInt(endDay.getText()), Integer.parseInt(endMonth.getText()), Integer.parseInt(endYear.getText()));

            String category = (String) categoryCombo.getSelectedItem();

            Event event = new Event(name, location, details, time, start, location, start, end, category);
            ChirpifyGUI.eventList.add(event);

            if (upcomingGUI != null) upcomingGUI.refreshTable();

            JOptionPane.showMessageDialog(this, "Event created:\n" + event);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for time and dates", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
