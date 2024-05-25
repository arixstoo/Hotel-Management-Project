package Vue;

import Controller.*;
import Controller.Exceptions.Exception_text;
import Model.MongoFunctions;
import com.mongodb.client.MongoCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class Client_view extends JFrame {

    // Variable pour suivre l'état de l'annulation de la réservation
    public static final boolean[] reservationAnnulee = {false};
    public static final boolean[] reservationModifiee = {false};

    public static ArrayList<Integer> chambres = new ArrayList<>();
    public static Reservation res = new Reservation();

    public Client_view(String usernameClient) {
        Main.charger();
        // Set up the frame
        setTitle("Bienvenue à notre hôtel !");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image img = tk.getImage("src/Vue/IconHotel.png");
        setIconImage(img);
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
        ImageIcon backgroundImage = new ImageIcon("src/Vue/image8.jpg");
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
        JButton button1 = new JButton("       Faire une réservation      ");
        button1.setBackground(new Color(100, 100, 100)); // Dark grey background
        button1.setForeground(Color.WHITE);
        button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try{
                    //vérifier si cet utilisateur a déjà une réservation
                    Boolean trouve = false;
                    if(Main.reservations.containsKey(usernameClient)){
                        trouve=true;
                    }
                    if(trouve==true){
                        throw new Exception_text("Vous avez fait une reservation déjà, vous ne pouvez pas faire une autre pour le moment juste la modifier.");
                    } else{
                        dispose();
                        reservation secondWindow = new reservation(usernameClient,0);
                        secondWindow.setVisible(true);
                    }
                } catch(Exception_text ex){
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button1.setPreferredSize(new Dimension(250, 60)); // Adjust button size
        buttonsPanel.add(button1);

        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Button 2 (Edit Reservation)
        JButton button2 = new JButton("     Modifier la réservation       ");
        button2.setBackground(new Color(100, 100, 100)); // Dark grey background
        button2.setForeground(Color.WHITE);
        button2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    // Vérifiez si cet utilisateur a déjà annulé la réservation
                    if (Client_view.reservationModifiee[0]) {
                        JOptionPane.showMessageDialog(null,"Vous avez déjà modifié votre réservation.","Erreur",JOptionPane.ERROR_MESSAGE);
                        return; // Sortez de la méthode car l'annulation a déjà été faite
                    }

                    //vérifier si cet utilisateur a déjà une réservation
                    Boolean trouvé = false;
                    if(Main.reservations.containsKey(usernameClient)){
                        trouvé=true;
                    }

                    if(trouvé) {
                        Client.modifierRéservation(usernameClient);
                    } else{
                        throw new Exception_text("Vous n'avez fait aucune reservation, vous ne pouvez rien modifier pour le moment.");
                    }
                } catch (Exception_text ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage(),"Erreur",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        button2.setPreferredSize(new Dimension(250, 60)); // Adjust button size
        buttonsPanel.add(button2);

        buttonsPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Button 3 (Check-in / Check-out)
        JButton button3 = new JButton("       Annuler la réservation      ");
        button3.setBackground(new Color(100, 100, 100)); // Dark grey background
        button3.setForeground(Color.WHITE);
        button3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                Client.supprimerRéservation(usernameClient);
                Main.charger();
            }
        });
        button3.setPreferredSize(new Dimension(250, 60)); // Adjust button size
        buttonsPanel.add(button3);

        mainPanel.add(buttonsPanel);

        backgroundLabel.add(mainPanel, gbc);
        add(backgroundLabel, BorderLayout.CENTER);
    }
    public static void garderInfos(String usernameClient){
        for(int j=0;j<Main.reservations.get(usernameClient).getChambre().size();j++){
            Client_view.chambres.add(Main.reservations.get(usernameClient).getChambre().get(j).numéroChambre);
        }
        Client_view.res = Main.reservations.get(usernameClient);
    }
}

