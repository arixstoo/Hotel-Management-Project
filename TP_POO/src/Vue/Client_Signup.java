package Vue;

import Controller.Client;
import Controller.Exceptions.Exception_text;
import Controller.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

class Client_Signup extends JFrame {
    public Client_Signup() {
        Main.charger();
        setTitle("Client Signup");
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
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                g2d.setColor(new Color(119, 132, 143, 180)); // Translucent blue
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // More rounded corners
            }

            {
                setBackground(new Color(4, 19, 78));
                setLayout(new GridBagLayout());
                setPreferredSize(new Dimension(400, 290));
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

        // nom field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel nomLabel = new JLabel("Nom :");
        nomLabel.setForeground(Color.WHITE);
        mainPanel.add(nomLabel, gbc);

        gbc.gridx = 1;
        JTextField nomField = new JTextField(20);
        mainPanel.add(nomField, gbc);

        /////////
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel prénomLabel = new JLabel("Prénom :");
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

        //phone
        gbc.gridx = 0;
        gbc.gridy = 4;
        JLabel phoneLabel = new JLabel("Numéro de téléphone :");
        phoneLabel.setForeground(Color.WHITE);
        mainPanel.add(phoneLabel, gbc);

        gbc.gridx = 1;
        JTextField phoneField = new JTextField(20);
        mainPanel.add(phoneField, gbc);

        // Username field
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel usernameLabel = new JLabel("Nom d'utilisateur :");
        usernameLabel.setForeground(Color.WHITE);
        mainPanel.add(usernameLabel, gbc);

        gbc.gridx = 1;
        JTextField usernameField = new JTextField(20);
        mainPanel.add(usernameField, gbc);

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
        JButton submitButton = new JButton("S'inscrire");
        submitButton.setBackground(new Color(89, 116, 141)); // Deep blue color
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14)); // Larger and bold font
        submitButton.setPreferredSize(new Dimension(100, 30)); // Smaller size
        submitButton.setFocusPainted(false); // Remove focus border
        int borderRadius = 10; // Adjust the radius as needed
        submitButton.setBorder(new RoundedBorder(borderRadius));
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CLASS CLIENT SIGNUP
                try {
                    //Verification de l'unicité du compte (l'email)
                    Boolean continuer=true;
                    try{
                        Iterator<Map.Entry<String, Client>> iterator = Main.clients.entrySet().iterator();
                        while(iterator.hasNext()){
                            Map.Entry<String, Client> actuel = iterator.next();
                            Client client = actuel.getValue();
                            if(Objects.equals(client.getEmail(), emailField.getText())){
                                throw new Exception_text("Vous avez déjà un compte.");
                            }
                        }
                    }catch (Exception_text ex) {
                        // Afficher un message d'erreur si le mot de passe est invalide
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                        continuer=false;
                        dispose();
                        Client_Login nextWindow = new Client_Login();
                        nextWindow.setVisible(true);
                    }
                    if(continuer==true){
                        //Création d'un compte normal
                        Boolean validé = Client.ClientSignUp(nomField.getText(), prénomField.getText(), emailField.getText(), phoneField.getText(), usernameField.getText(), passwordField.getText());
                        if(validé){
                            JOptionPane.showMessageDialog(null, "Merci de nous avoir fait confiance !", "Accès réussi", JOptionPane.INFORMATION_MESSAGE);
                        }
                        dispose();
                        Client_view nextWindow = new Client_view(usernameField.getText());
                        nextWindow.setVisible(true);
                    }
                } catch (Exception_text ex) {
                    // Afficher un message d'erreur si le mot de passe est invalide
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                } catch (NullPointerException ex){
                    JOptionPane.showMessageDialog(null, "Vous devez entrer quelque chose !", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
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
}
