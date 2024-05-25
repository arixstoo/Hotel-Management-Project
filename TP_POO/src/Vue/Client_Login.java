package Vue;

import Controller.Exceptions.Exception_text;
import Controller.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client_Login extends JFrame {
    public Client_Login() {
        Main.charger();
        setTitle("Client Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("src/Vue/IconHotel.png");
        setIconImage(img);
        setSize(700, 500);
        setLocationRelativeTo(null);

        ImageIcon backgroundImage = new ImageIcon("src/Vue/image22.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new GridBagLayout());


        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(119, 132, 143, 180)); // Translucent blue
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
                g2d.dispose();
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setPreferredSize(new Dimension(550, 150));
        mainPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
        usernameLabel.setForeground(Color.WHITE);
        mainPanel.add(usernameLabel, gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JTextField usernameField = new JTextField(20);
        mainPanel.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel passwordLabel = new JLabel("Mot de passe:");
        passwordLabel.setForeground(Color.WHITE);
        mainPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        JPasswordField passwordField = new JPasswordField(20);
        mainPanel.add(passwordField, gbc);

        // sign up button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1; // Set gridwidth to 1 for each button
        gbc.anchor = GridBagConstraints.EAST; // Align to the right
        JButton submitButton = new JButton("S'inscrire");
        submitButton.setBackground(new Color(89, 116, 141));; // Deep blue color
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14)); // Larger and bold font
        submitButton.setPreferredSize(new Dimension(100, 30)); // Smaller size
        submitButton.setFocusPainted(false); // Remove focus border
        int borderRadius = 10; // Adjust the radius as needed
        submitButton.setBorder(new RoundedBorder(borderRadius));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Client_Signup secondWindow = new Client_Signup();
                secondWindow.setVisible(true);
            }
        });
        mainPanel.add(submitButton, gbc);

        // Log In button
        gbc.gridx = 2;
        JButton loginButton = new JButton("Se connecter");
        loginButton.setBackground(new Color(89, 116, 141)); // Deep blue color
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14)); // Larger and bold font
        loginButton.setPreferredSize(new Dimension(110, 30)); // Smaller size
        loginButton.setFocusPainted(false); // Remove focus border
        int borderRadiuss = 10;
        loginButton.setBorder(new RoundedBorder(borderRadiuss));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Vérifier si le mot de passe respecte les critères
                    Boolean validé = Controller.Client.Login(usernameField.getText(), passwordField.getText());
                    if(validé){
                        JOptionPane.showMessageDialog(null, "Accès réussi", "Accès réussi", JOptionPane.INFORMATION_MESSAGE);
                    }
                    dispose();
                    Client_view secondWindow = new Client_view(usernameField.getText());
                    secondWindow.setVisible(true);
                } catch (Exception_text ex) {
                    // Afficher un message d'erreur si le mot de passe est invalide
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(null, "Vous devez entrer quelque chose !", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        mainPanel.add(loginButton, gbc);

        //Admin button
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1; // Set gridwidth to 1 for each button
        gbc.anchor = GridBagConstraints.CENTER;
        JButton adminButton = new JButton("Admin");
        adminButton.setBackground(new Color(89, 116, 141));; // Deep blue color
        adminButton.setForeground(Color.WHITE);
        adminButton.setFont(new Font("Arial", Font.BOLD, 14)); // Larger and bold font
        adminButton.setPreferredSize(new Dimension(70, 30)); // Smaller size
        adminButton.setFocusPainted(false); // Remove focus border
        borderRadius = 10; // Adjust the radius as needed
        adminButton.setBorder(new RoundedBorder(borderRadius));
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showInputDialog("Code : ").equals("2024")){
                    dispose();
                    Admin_Login secondWindow = new Admin_Login();
                    secondWindow.setVisible(true);
                } else{
                    JOptionPane.showMessageDialog(null, "Vous n'êtes pas un admin !", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        mainPanel.add(adminButton, gbc);

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(backgroundLabel, BorderLayout.CENTER);
        backgroundLabel.add(mainPanel, gbc);
    }
}
