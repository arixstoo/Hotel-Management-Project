package Vue;

import javax.swing.*;
import java.awt.*;

class reservation extends JFrame {
    public reservation() {
        super("Make Reservation");
        setSize(700, 500);
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocation(screenWidth / 4, screenHeight / 4);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Image img = tk.getImage("IconHotel.png");
        setIconImage(img);
        JLabel label = new JLabel("  veuillez choisir les détails de votre réservation");
        label.setForeground(Color.WHITE); // Set text color to be visible on the background
        label.setFont(new Font("Arial", Font.PLAIN, 17));


        JTextField dateDebutField = new JTextField(10);
        JTextField dateFinField = new JTextField(10);
        JLabel dateDebutLabel = new JLabel("Date Début:");
        JLabel dateFinLabel = new JLabel("Date Fin:");
        dateDebutLabel.setForeground(new Color(225, 225, 225, 200));
        dateFinLabel.setForeground(new Color(225, 225, 225, 200));
        JButton b = new JButton("continuer");

        JPanel mainPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(68, 68, 79, 180));
                g.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 8);
            }
        };

        mainPanel.setOpaque(false); // Make the panel transparent
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);

        mainPanel.setLayout(new GridBagLayout()); // Changed layout to GridBagLayout to manage component positions

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.anchor = GridBagConstraints.NORTHWEST;

        // Add label at the top
        constraints.gridx = 0;
        constraints.gridy = 0;
        mainPanel.add(label, constraints);

        // Add chambre selection panel with scroll bar
        constraints.gridy = 1;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        JScrollPane scrollPane = createChambrePanel(); // Create JScrollPane containing chambrePanel
        mainPanel.add(scrollPane, constraints);

        // Add date input fields and their labels
        JPanel datePanel = new JPanel(new GridBagLayout());
        datePanel.setOpaque(false);

        GridBagConstraints dateGbc = new GridBagConstraints();
        dateGbc.insets = new Insets(5, 5, 5, 5);
        dateGbc.anchor = GridBagConstraints.WEST;

        // Add "date debut" label and text field
        dateGbc.gridx = 0;
        dateGbc.gridy = 0;
        datePanel.add(dateDebutLabel, dateGbc);
        dateGbc.gridx = 1;
        datePanel.add(dateDebutField, dateGbc);

        // Add "date fin" label and text field
        dateGbc.gridx = 0;
        dateGbc.gridy = 1;
        datePanel.add(dateFinLabel, dateGbc);
        dateGbc.gridx = 1;
        datePanel.add(dateFinField, dateGbc);

        constraints.gridy = 2;
        constraints.weighty = 0.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(datePanel, constraints);

        // Add button for "continuer"
        constraints.gridy = 3;
        mainPanel.add(b, constraints);

        setContentPane(mainPanel);

        // Create the background panel
        ImageIcon backgroundImage = new ImageIcon("POUT.png");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setLayout(new GridBagLayout());

        // Add mainPanel to the backgroundLabel
        backgroundLabel.add(mainPanel);
        setContentPane(backgroundLabel);

        setVisible(true);
    }

    private JScrollPane createChambrePanel() {
        JPanel chambrePanel = new JPanel();
        chambrePanel.setLayout(new BoxLayout(chambrePanel, BoxLayout.Y_AXIS));

        // Add multiple room lines, each with a checkbox and characteristics
        for (int i = 1; i <= 50; i++) {
            JPanel ligneChambre = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JCheckBox checkBox = new JCheckBox("            numero        |");
            JLabel labelTaille = new JLabel("    Taille : Double      |");
            // JLabel labelNumero = new JLabel("Numéro : " + i);
            JLabel labelPrix = new JLabel("    Prix : 10da");

            ligneChambre.add(checkBox);
            ligneChambre.add(labelTaille);
            // ligneChambre.add(labelNumero);
            ligneChambre.add(labelPrix);

            chambrePanel.add(ligneChambre);
        }

        // Create a JScrollPane and add the chambrePanel
        JScrollPane scrollPane = new JScrollPane(chambrePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 100)); // Fixed size for the scroll pane

        return scrollPane;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(reservation::new);
    }
}
