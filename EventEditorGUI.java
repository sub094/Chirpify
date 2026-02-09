import javax.swing.*;
import java.awt.*;

public class EventEditorGUI extends JFrame {

    private UpcomingEventsGUI upcomingGUI;

    // Event selector
    private JComboBox<Event> eventCombo;

    // Form fields
    private JTextField nameField, locationField;
    private JTextField hourField, minuteField;
    private JComboBox<String> ampmBox;
    private JTextField startDay, startMonth, startYear;
    private JTextField endDay, endMonth, endYear;
    private JComboBox<String> categoryCombo;
    private JTextArea detailsArea;
    private JButton updateButton;

    public EventEditorGUI(UpcomingEventsGUI upcomingGUI) {
        this.upcomingGUI = upcomingGUI;

        setTitle("Edit Event");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        /* ================= TOP ================= */
        JLabel titleLabel = new JLabel("Edit Event", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        eventCombo = new JComboBox<>();
        for (Event e : ChirpifyGUI.eventList) {
            eventCombo.addItem(e);
        }
        eventCombo.addActionListener(e -> loadSelectedEvent());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(eventCombo, BorderLayout.NORTH);
        topPanel.add(titleLabel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        /* ================= FORM ================= */
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // Name
        c.gridx = 0; c.gridy = row;
        formPanel.add(new JLabel("Name:"), c);
        nameField = new JTextField();
        c.gridx = 1;
        formPanel.add(nameField, c);
        row++;

        // Location
        c.gridx = 0; c.gridy = row;
        formPanel.add(new JLabel("Location:"), c);
        locationField = new JTextField();
        c.gridx = 1;
        formPanel.add(locationField, c);
        row++;

        // Details
        c.gridx = 0; c.gridy = row;
        formPanel.add(new JLabel("Details:"), c);
        detailsArea = new JTextArea(4, 20);
        JScrollPane detailsScroll = new JScrollPane(detailsArea);
        c.gridx = 1;
        formPanel.add(detailsScroll, c);
        row++;

        // Time
        c.gridx = 0; c.gridy = row;
        formPanel.add(new JLabel("Time:"), c);

        JPanel timePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        hourField = new JTextField(2);
        minuteField = new JTextField(2);
        ampmBox = new JComboBox<>(new String[]{"AM", "PM"});
        timePanel.add(hourField);
        timePanel.add(new JLabel(":"));
        timePanel.add(minuteField);
        timePanel.add(ampmBox);
        c.gridx = 1;
        formPanel.add(timePanel, c);
        row++;

        // Start Date
        c.gridx = 0; c.gridy = row;
        formPanel.add(new JLabel("Start Date (DD/MM/YYYY):"), c);
        JPanel startPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        startDay = new JTextField(2);
        startMonth = new JTextField(2);
        startYear = new JTextField(4);
        startPanel.add(startDay);
        startPanel.add(new JLabel("/"));
        startPanel.add(startMonth);
        startPanel.add(new JLabel("/"));
        startPanel.add(startYear);
        c.gridx = 1;
        formPanel.add(startPanel, c);
        row++;

        // End Date
        c.gridx = 0; c.gridy = row;
        formPanel.add(new JLabel("End Date (DD/MM/YYYY):"), c);
        JPanel endPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        endDay = new JTextField(2);
        endMonth = new JTextField(2);
        endYear = new JTextField(4);
        endPanel.add(endDay);
        endPanel.add(new JLabel("/"));
        endPanel.add(endMonth);
        endPanel.add(new JLabel("/"));
        endPanel.add(endYear);
        c.gridx = 1;
        formPanel.add(endPanel, c);
        row++;

        // Category
        c.gridx = 0; c.gridy = row;
        formPanel.add(new JLabel("Category:"), c);
        categoryCombo = new JComboBox<>(new String[]{"General", "Work", "School", "Personal"});
        c.gridx = 1;
        formPanel.add(categoryCombo, c);

        add(formPanel, BorderLayout.CENTER);

        /* ================= UPDATE BUTTON ================= */
        updateButton = createStyledButton("Update Event");
        updateButton.addActionListener(e -> updateEvent());
        add(updateButton, BorderLayout.SOUTH);

        // Load first event if available
        if (!ChirpifyGUI.eventList.isEmpty()) {
            eventCombo.setSelectedIndex(0);
            loadSelectedEvent();
        }

        setVisible(true);
    }

    /* ================= LOAD EVENT ================= */
    private void loadSelectedEvent() {
        Event event = (Event) eventCombo.getSelectedItem();
        if (event == null) return;

        nameField.setText(event.getName());
        locationField.setText(event.getLocation());
        detailsArea.setText(event.getDetails());

        int time = event.getTime();
        int hour = time / 100;
        int minute = time % 100;

        if (hour >= 12) {
            ampmBox.setSelectedItem("PM");
            if (hour > 12) hour -= 12;
        } else {
            ampmBox.setSelectedItem("AM");
            if (hour == 0) hour = 12;
        }

        hourField.setText(String.valueOf(hour));
        minuteField.setText(String.format("%02d", minute));

        startDay.setText(String.valueOf(event.getStartDate().getDay()));
        startMonth.setText(String.valueOf(event.getStartDate().getMonth()));
        startYear.setText(String.valueOf(event.getStartDate().getYear()));

        endDay.setText(String.valueOf(event.getEndDate().getDay()));
        endMonth.setText(String.valueOf(event.getEndDate().getMonth()));
        endYear.setText(String.valueOf(event.getEndDate().getYear()));

        categoryCombo.setSelectedItem(event.getCategory());
    }

    /* ================= UPDATE EVENT ================= */
    private void updateEvent() {
        try {
            Event event = (Event) eventCombo.getSelectedItem();
            if (event == null) return;

            event.setName(nameField.getText());
            event.setLocation(locationField.getText());
            event.setDetails(detailsArea.getText());

            int hour = Integer.parseInt(hourField.getText());
            int minute = Integer.parseInt(minuteField.getText());
            String ampm = (String) ampmBox.getSelectedItem();

            if (ampm.equals("PM") && hour != 12) hour += 12;
            if (ampm.equals("AM") && hour == 12) hour = 0;
            event.setTime(hour * 100 + minute);

            event.setStartDate(new DateC(
                    Integer.parseInt(startDay.getText()),
                    Integer.parseInt(startMonth.getText()),
                    Integer.parseInt(startYear.getText())
            ));

            event.setEndDate(new DateC(
                    Integer.parseInt(endDay.getText()),
                    Integer.parseInt(endMonth.getText()),
                    Integer.parseInt(endYear.getText())
            ));

            event.setCategory((String) categoryCombo.getSelectedItem());

            if (upcomingGUI != null) {
                upcomingGUI.refreshTable();
            }

            JOptionPane.showMessageDialog(this, "Event updated!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Invalid time or date values",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /* ================= BUTTON STYLE ================= */
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 45));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);

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
