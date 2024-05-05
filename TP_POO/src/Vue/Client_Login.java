package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Client_Login extends JFrame {
    public Client_Login() {

        setTitle("Client Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);


        ImageIcon backgroundImage = new ImageIcon("image22.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new GridBagLayout());


        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(119, 132, 143, 180)); // Translucent blue
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // More rounded corners
                g2d.dispose();
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(300, 150));


        mainPanel.setOpaque(false);
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(360, 170));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);



        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel usernameLabel = new JLabel("Email:");
        usernameLabel.setForeground(Color.WHITE);
        mainPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        JTextField usernameField = new JTextField(20);
        mainPanel.add(usernameField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel passwordLabel = new JLabel("Password :");
        passwordLabel.setForeground(Color.WHITE);
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        JPasswordField passwordField = new JPasswordField(20);
        mainPanel.add(passwordField, gbc);

        // sign up button
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 1; // Set gridwidth to 1 for each button
        gbc.anchor = GridBagConstraints.EAST; // Align to the right
        JButton submitButton = new JButton("sign up");
        submitButton.setBackground(new Color(89, 116, 141));; // Deep blue color
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("JACQUES FRANCOIS", Font.BOLD, 15)); // Larger and bold font
        submitButton.setPreferredSize(new Dimension(70
                , 23)); // Smaller size
        submitButton.setFocusPainted(false); // Remove focus border
        int borderRadius = 2; // Adjust the radius as needed
        submitButton.setBorder(null);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        mainPanel.add(submitButton, gbc);

// Log In button
        gbc.gridx = 1;
        JButton loginButton = new JButton("connect");
        loginButton.setBackground(new Color(89, 116, 141)); // Deep blue color
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14)); // Larger and bold font
        loginButton.setPreferredSize(new Dimension(90, 30)); // Smaller size
        loginButton.setFocusPainted(false); // Remove focus border
        int borderRadiuss = 10;
        loginButton.setBorder(new RoundedBorder(borderRadiuss));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        mainPanel.add(loginButton, gbc);


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
                new Client_Login().setVisible(true);
            }
        });
    }
}
