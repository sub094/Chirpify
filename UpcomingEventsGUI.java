import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class UpcomingEventsGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public UpcomingEventsGUI() {
        setTitle("Upcoming Events");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Upcoming Events", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new String[]{"Name", "Location", "Start", "End", "Time", "Category"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Button to create new event
        JButton createButton = createStyledButton("Create New Event");
        createButton.addActionListener(e -> new EventCreatorGUI(this));
        JPanel buttonPanel = new JPanel(); buttonPanel.add(createButton);
        add(buttonPanel, BorderLayout.SOUTH);
        //Button to edit events
        JButton editButton = createStyledButton("Edit Event");
        editButton.addActionListener(e -> new EventEditorGUI(this));
        buttonPanel.add(editButton);
        //Refresh Button
        JButton RefreshButton = createStyledButton("Refresh Table");
        RefreshButton.addActionListener(ActionEvent->refreshTable());
        buttonPanel.add(RefreshButton);
        //Event Delete Button
        JButton deleteButton = createStyledButton("Delete Event");
        deleteButton.addActionListener(ActionEvent -> new DeleteEventGUI(this));
        buttonPanel.add(deleteButton);
        refreshTable();
        setVisible(true);
    }

    public void refreshTable() {
        tableModel.setRowCount(0); // clear table
        List<Event> events = ChirpifyGUI.eventList;

        for (Event event : events) {
            String start = formatDate(event.getStartDate());
            String end = formatDate(event.getEndDate());
            String time = formatTime(event.getTime());
            tableModel.addRow(new Object[]{event.getName(), event.getLocation(), start, end, time, event.getCategory()});
        }
    }

    private String formatDate(DateC date) {
        return String.format("%02d/%02d/%04d", date.getDay(), date.getMonth(), date.getYear());
    }

    private String formatTime(int time) {
        int hour = time / 100;
        int minute = time % 100;
        String ampm = (hour >= 12) ? "PM" : "AM";
        if (hour == 0) hour = 12;
        else if (hour > 12) hour -= 12;
        return String.format("%d:%02d %s", hour, minute, ampm);
    }
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
