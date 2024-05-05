package GUI;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import javax.swing.table.*;

class Acces_reservation extends JFrame {
    public Acces_reservation() {
        //here we costumize the frame
        setTitle("acces reservations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // here i created the table and added the reservation from data base
        JTable table = new JTable(new DefaultTableModel(
                new Object[][]{
                        {"108", "Single", "Zarif akram", "05-05-2024", "15-05-2024"},
                        {"111", "Double", "Tabakh ahlem", "06-05-2024", "08-05-2024"},
                        {"101", "Double", "Allou yacine", "08-05-2024", "15-05-2024"},
                        {"103", "Suite",  "Gribi ahlem", "11-05-2024", "14-05-2024"},
                        {"22",  "Single", "Habchi lyna", "21-05-2024", "27-05-2024"},
                        {"208", "Double", "Kamiri wassim","24-05-2024", "26-05-2024"},
                        {"102", "Double", "Denni ryad", "27-05-2024", "03-06-2024"},
                        {"241", "Single", "Kara nabil", "27-05-2024", "30-05-2024"},
                        {"250", "Suite",  "Charif hiba", "01-06-2024", "07-06-2024"},
                        {"75",  "Single", "Benhaick meriem", "02-06-2024", "09-06-2024"},
                        {"156", "Double", "Chemli ayoub","04-06-2024", "08-06-2024"},
                        {"382", "single", "hadjkadour islam", "07-06-2024", "09-06-2024"},
                        {"158", "Double", "Kendour dalia", "08-06-2024", "15-06-2024"},
                        {"215", "Suite",  "Lagab lyna", "12-06-2024", "16-06-2024"},
                        {"354", "Single", "Mokeddem mohamed", "14-06-2024", "18-06-2024"},
                        {"315", "Single", "Soualmia ilyes", "15-06-2024", "20-06-2024"},
                        {"368", "Double", "Toubal hana","22-06-2024", "25-06-2024"},
                        {"281", "Single", "Belhocine chakib", "23-06-2024", "29-05-2024"},
                        {"400", "Suite", "Mesgi alaa", "02-07-2024", "10-07-2024"},

                },
                new String[]{"Room Number", "Room Type", "Client Name", "Check-in Date", "Check-out Date"}
        ));

        // here i ll customize the table
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setBackground(new Color(89, 116, 141));
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer())
                .setHorizontalAlignment(SwingConstants.CENTER);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (row % 2 == 0) {
                    c.setBackground(new Color(224, 224, 224)); // Light gray
                } else {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        });//here i made the table colorful with 2 colors


        // i add this to be able to scroll on the table to see all the reservations
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 300));

        //here i created boutons to make the admin able to make modifications
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton acceptButton = new JButton("Accept");
        JButton declineButton = new JButton("Decline");
        JButton editButton = new JButton("Edit");

        // this is for customize buttons appearance
        Font buttonFont = new Font("SansSerif", Font.BOLD, 15);
        acceptButton.setFont(buttonFont);
        declineButton.setFont(buttonFont);
        editButton.setFont(buttonFont);
        acceptButton.setBackground(new Color(89, 116, 141));
        declineButton.setBackground(new Color(89, 116, 141));
        editButton.setBackground(new Color(89, 116, 141));
        acceptButton.setForeground(Color.WHITE);
        declineButton.setForeground(Color.WHITE);
        editButton.setForeground(Color.WHITE);

        // i did put the buttons on a panel to adjuste them
        buttonPanel.add(acceptButton);
        buttonPanel.add(declineButton);
        buttonPanel.add(editButton);

        // here i add the table and buttons to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Acces_reservation().setVisible(true);
            }
        });
    }
}
