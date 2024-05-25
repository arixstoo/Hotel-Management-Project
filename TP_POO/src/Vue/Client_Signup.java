package Vue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Client_Signup extends JFrame {
    public Client_Signup() {

        setTitle("Client Signup");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);


        ImageIcon backgroundImage = new ImageIcon("GUI/image456.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new GridBagLayout());

        /*JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(new Color(3, 14, 58));
        backgroundPanel.setLayout(new GridBagLayout());*/

        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                g2d.setColor(new Color(119, 132, 143, 180)); // Translucent blue
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // More rounded corners
            }


            {
                setBackground(new Color(4, 19, 78));
                setLayout(new GridBagLayout());
                setPreferredSize(new Dimension(400, 270));
            }
        };


        mainPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        GridBagConstraints gbcText = new GridBagConstraints();
        gbcText.insets = new Insets(10, 10, 10, 10);
        gbcText.gridx = 0;
        gbcText.gridy = 0;
        gbcText.anchor = GridBagConstraints.NORTH;



        GridBagConstraints gibc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);



        // nom field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel nomLabel = new JLabel("Nom complet :");
        nomLabel.setForeground(Color.WHITE);
        mainPanel.add(nomLabel, gbc);

        gbc.gridx = 1;
        JTextField nomField = new JTextField(20);
        mainPanel.add(nomField, gbc);
/////////
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel prénomLabel = new JLabel("Nationnalité :");
        prénomLabel.setForeground(Color.WHITE);
        mainPanel.add(prénomLabel, gbc);

        gbc.gridx = 1;
        JTextField prénomField = new JTextField(20);
        mainPanel.add(prénomField, gbc);


        // email field
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel emailLabel = new JLabel("Email :");
        emailLabel.setForeground(Color.WHITE);
        mainPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        JTextField emailField = new JTextField(20);
        mainPanel.add(emailField, gbc);



        gbc.gridx = 0;
        gbc.gridy = 5;
        JLabel phoneLabel = new JLabel("numero:");
        phoneLabel.setForeground(Color.WHITE);
        mainPanel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        JTextField phoneField = new JTextField(20);
        mainPanel.add(phoneField, gbc);

        // Password field
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel passwordLabel = new JLabel("mot de passe :");
        passwordLabel.setForeground(Color.WHITE);
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        mainPanel.add(passwordField, gbc);

        // Submit button
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.gridwidth = 1; // Set gridwidth to 1 for each button
        gbc.anchor = GridBagConstraints.EAST; // Align to the right
        JButton submitButton = new JButton("confirmer");
        submitButton.setBackground(new Color(89, 116, 141)); // Deep blue color
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14)); // Larger and bold font
        submitButton.setPreferredSize(new Dimension(120, 30)); // Smaller size
        submitButton.setFocusPainted(false); // Remove focus border
        int borderRadius = 10; // Adjust the radius as needed

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        mainPanel.add(submitButton, gbc);


        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        add(backgroundLabel, BorderLayout.CENTER);

        gbc.anchor = GridBagConstraints.CENTER;
        add(backgroundLabel, BorderLayout.CENTER);
        backgroundLabel.add(mainPanel, gbc);



    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Client_Signup().setVisible(true);
            }
        });
    }
}
