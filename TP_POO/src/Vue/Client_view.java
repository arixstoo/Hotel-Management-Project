package Vue;

import javax.swing.*;
import java.awt.*;

class ClientView extends JFrame {
    public ClientView() {
        // Set up the frame
        setTitle("Client View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 450); // Adjusted frame size
        setLocationRelativeTo(null);

        // Create the grey panel in the center
        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                g2d.setColor(new Color(220, 220, 220, 180)); // Translucent grey
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // More rounded corners
            }

            {
                setBackground(new Color(240, 240, 240)); // Light grey background
                setLayout(new GridBagLayout());
                setPreferredSize(new Dimension(400, 270));
            }
        };



        mainPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

   //here i creat the background panel
        ImageIcon backgroundImage = new ImageIcon("image8.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new GridBagLayout());

        // and here the panel for the buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(220, 220, 220, 80)); // Semi-transparent grey
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS)); // Layout vertical
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        buttonsPanel.setPreferredSize(new Dimension(300, 300)); // Adjust panel size

        // Space between buttons
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

         // Button 1
        JButton button1 = new JButton("     Faire une Reservation    ");
        button1.setBackground(new Color(100, 100, 100)); // Dark grey background
        button1.setForeground(Color.WHITE);
        button1.setPreferredSize(new Dimension(250, 60)); // Adjust button size
        buttonsPanel.add(button1);

        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Button 2 (Edit Reservation)
        JButton button2 = new JButton("   Modifier ta Reservation   ");
        button2.setBackground(new Color(100, 100, 100)); // Dark grey background
        button2.setForeground(Color.WHITE);
        button2.setPreferredSize(new Dimension(250, 60)); // Adjust button size
        buttonsPanel.add(button2);

        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Button 3 (Check-in / Check-out)
        JButton button3 = new JButton("    Effacer ta Reservation    ");
        button3.setBackground(new Color(100, 100, 100)); // Dark grey background
        button3.setForeground(Color.WHITE);
        button3.setPreferredSize(new Dimension(250, 60)); // Adjust button size
        buttonsPanel.add(button3);
        mainPanel.add(buttonsPanel);

        backgroundLabel.add(mainPanel, gbc);
        add(backgroundLabel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ClientView().setVisible(true);
            }
        });
    }
}

