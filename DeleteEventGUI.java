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

        JButton DeleteButton = createStyledButton("Delete Event");
        DeleteButton.addActionListener(e -> deleteEvent());
        add(DeleteButton, BorderLayout.SOUTH);


        setVisible(true);
    }


    private void deleteEvent() {

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
