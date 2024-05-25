package Vue;

import javax.swing.*;
import java.awt.*;

class Admin_view extends JFrame {
    public Admin_view() {

        setTitle("Vue d'admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();

                g2d.setColor(new Color(85, 54, 35, 180)); // Translucent blue
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            }


            {
                setBackground(new Color(4, 19, 78));
                setLayout(new GridBagLayout());
                setPreferredSize(new Dimension(400, 270));
            }
        };

//this line is to make the panel transparent
        mainPanel.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        GridBagConstraints ttext = new GridBagConstraints();
        ttext.insets = new Insets(10, 10, 10, 10);
        ttext.gridx = 0;
        ttext.gridy = 0;
        ttext.anchor = GridBagConstraints.NORTH;



        GridBagConstraints iop = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create the background panel
        ImageIcon backgroundImage = new ImageIcon("img_1.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new GridBagLayout());

        // Create the blue panel in the center

        // Add text at the beginning and center of the blue panel

       // secondPanel.add(textLabel);

        // Create the panel for the buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(new Color(85, 54, 35, 80));
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS)); // Layout vertical
        buttonsPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrer horizontalement
        buttonsPanel.setPreferredSize(new Dimension(300, 300)); // Ajuster la taille du panneau

// Espace entre les boutons
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Button 1
        JButton button1 = new JButton("      Accéder reservations   ");
        button1.setBackground(new Color(186, 149, 89));
        button1.setForeground(Color.WHITE);
        button1.setPreferredSize(new Dimension(250, 200)); // Ajuster la taille du bouton
        buttonsPanel.add(button1);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Button 2
        JButton button2 = new JButton("        Modifier chambre        ");
        button2.setBackground(new Color(186, 149, 89));
        button2.setForeground(Color.WHITE);
        button2.setPreferredSize(new Dimension(250, 60)); // Ajuster la taille du bouton
        buttonsPanel.add(button2);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Button 3
        JButton button3 = new JButton("          ajouter chambre        ");
        button3.setBackground(new Color(186, 149, 89));
        button3.setForeground(Color.WHITE);
        button3.setPreferredSize(new Dimension(250, 60)); // Ajuster la taille du bouton
        buttonsPanel.add(button3);
        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Button 4
        JButton button4 = new JButton("        suprimer chambre       ");
        button4.setBackground(new Color(186, 149, 89));
        button4.setForeground(Color.WHITE);
        button4.setPreferredSize(new Dimension(250, 60));
        buttonsPanel.add(button4);



        mainPanel.add(buttonsPanel);

        backgroundLabel.add(mainPanel, gbc);
        add(backgroundLabel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Admin_view().setVisible(true);
            }
        });
    }
}
