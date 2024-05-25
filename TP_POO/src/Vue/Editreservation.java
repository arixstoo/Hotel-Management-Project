package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Editreservation extends JFrame {
    public Editreservation() {
        // Set up the frame
        setTitle("Edit reservation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Create the background panel
        ImageIcon backgroundImage = new ImageIcon("POUT.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new GridBagLayout());

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(4, 19, 78, 180)); // Translucent blue
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // More rounded corners
                g2d.dispose();
            }
        };
        mainPanel.setPreferredSize(new Dimension(360, 170));

        // Make the panel transparent
        mainPanel.setOpaque(false);
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(360, 170));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Date Debut label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel dateDebutLabel = new JLabel("date debut:");
        dateDebutLabel.setForeground(Color.WHITE);
        dateDebutLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(dateDebutLabel, gbc);

        // Date Debut text field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JTextField dateDebutField = new JTextField(10);
        dateDebutField.setBackground(new Color(21, 40, 106, 150));
        dateDebutField.setForeground(Color.WHITE);
        dateDebutField.setFont(new Font("JACQUES FRANCOIS", Font.BOLD, 15));
        mainPanel.add(dateDebutField, gbc);

        // Date Fin label
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel dateFinLabel = new JLabel("date fin:");
        dateFinLabel.setForeground(Color.WHITE);
        dateFinLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(dateFinLabel, gbc);

        // Date Fin text field
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        JTextField dateFinField = new JTextField(10);
        dateFinField.setBackground(new Color(6, 83, 138));
        dateFinField.setForeground(Color.WHITE);
        dateFinField.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(dateFinField, gbc);

        // New button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton newButton = new JButton("confirmer");
        newButton.setBackground(new Color(10, 50, 100));
        newButton.setForeground(Color.WHITE);
        newButton.setFont(new Font("Arial", Font.BOLD, 14));
        newButton.setPreferredSize(new Dimension(120, 30));
        newButton.setFocusPainted(false);

        newButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to handle new button click
            }
        });
        mainPanel.add(newButton, gbc);

        // Add the blue panel to the center of the background panel
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(backgroundLabel, BorderLayout.CENTER);
        backgroundLabel.add(mainPanel, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Editreservation().setVisible(true);
            }
        });
    }
}
