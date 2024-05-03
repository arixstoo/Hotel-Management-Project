package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Client_Signup extends JFrame {
    public Client_Signup() {
        // Set up the frame
        setTitle("Client Signup");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Create the background panel
        ImageIcon backgroundImage = new ImageIcon("image5.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new GridBagLayout());

        /*JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(new Color(3, 14, 58));
        backgroundPanel.setLayout(new GridBagLayout());*/

        // Create the blue panel in the center
        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                g2d.setColor(new Color(4, 19, 78, 180)); // Translucent blue
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5); // More rounded corners
            }


            {
                setBackground(new Color(4, 19, 78));
                setLayout(new GridBagLayout());
                setPreferredSize(new Dimension(400, 270));
            }
        };

// Add this line to make the panel transparent
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
        JLabel nomLabel = new JLabel("Full name :");
        nomLabel.setForeground(Color.WHITE);
        mainPanel.add(nomLabel, gbc);

        gbc.gridx = 1;
        JTextField nomField = new JTextField(20);
        mainPanel.add(nomField, gbc);
/////////
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel prénomLabel = new JLabel("Nationnality :");
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
        JLabel phoneLabel = new JLabel("Phone (optional):");
        phoneLabel.setForeground(Color.WHITE);
        mainPanel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        JTextField phoneField = new JTextField(20);
        mainPanel.add(phoneField, gbc);

        // Password field
        gbc.gridx = 0;
        gbc.gridy = 6;
        JLabel passwordLabel = new JLabel("Mot de passe :");
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
        JButton submitButton = new JButton("Submit");
        submitButton.setBackground(new Color(6, 83, 138)); // Deep blue color
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14)); // Larger and bold font
        submitButton.setPreferredSize(new Dimension(100, 30)); // Smaller size
        submitButton.setFocusPainted(false); // Remove focus border
        int borderRadius = 10; // Adjust the radius as needed
        submitButton.setBorder(new RoundedBorder(borderRadius));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to handle submit button click
            }
        });
        mainPanel.add(submitButton, gbc);

// Log In button
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        JButton loginButton = new JButton("Log In");
        gbc.anchor = GridBagConstraints.EAST;
        loginButton.setBackground(new Color(44, 84, 160,250)); // Deep blue color
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14)); // Larger and bold font
        loginButton.setPreferredSize(new Dimension(80, 21)); // Smaller size
        loginButton.setFocusPainted(false); // Remove focus border
        //loginButton.setBorder(new RoundedBorder(borderRadius));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to handle login button click
            }
        });
        mainPanel.add(loginButton, gbc);


        // Add the blue panel to the center of the background panel
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        add(backgroundLabel, BorderLayout.CENTER);

        gbc.anchor = GridBagConstraints.CENTER;
        add(backgroundLabel, BorderLayout.CENTER);
        backgroundLabel.add(mainPanel, gbc);
        /*backgroundPanel.add(mainPanel, gbc);

        add(backgroundPanel);*/

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Client_Signup().setVisible(true);
            }
        });
    }
}

