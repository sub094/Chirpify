import javax.swing.*;
import java.awt.*;

public class DeleteEventGUI extends JFrame {

    private UpcomingEventsGUI upcomingGUI;

    // Event selector
    private JComboBox<Event> eventCombo;

    public DeleteEventGUI(UpcomingEventsGUI upcomingGUI) {
        this.upcomingGUI = upcomingGUI;

        setTitle("Delete Event");
        setSize(500, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Delete Event", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        eventCombo = new JComboBox<>();
        for (Event e : ChirpifyGUI.eventList) {
            eventCombo.addItem(e);
        }

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(eventCombo, BorderLayout.NORTH);
        topPanel.add(titleLabel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);

        JButton deleteButton = createStyledButton("Delete Event");
        deleteButton.addActionListener(e -> deleteEvent());
        add(deleteButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void deleteEvent() {
        // Get the selected event from the combo box
        Event selectedEvent = (Event) eventCombo.getSelectedItem();

        if (selectedEvent != null) {
            // Confirm deletion
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to delete the event \"" + selectedEvent.getName() + "\"?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE
            );

            if (confirm == JOptionPane.YES_OPTION) {
                // Remove the event from the main event list
                ChirpifyGUI.eventList.remove(selectedEvent);

                // Update the UpcomingEventsGUI if needed
                if (upcomingGUI != null) {
                    upcomingGUI.refreshTable(); // Make sure this method exists in UpcomingEventsGUI
                }

                // Remove the event from the combo box
                eventCombo.removeItem(selectedEvent);

                JOptionPane.showMessageDialog(
                        this,
                        "Event deleted successfully!",
                        "Deleted",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "No event selected.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

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
