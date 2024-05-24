package Vue;
import Controller.Chambre;
import Controller.Exceptions.Exception_text;
import Controller.Main;
import Controller.Reservation;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.table.*;

public class Access_reservation extends JFrame {
    private DefaultTableModel model;
    public Access_reservation() {
        //here we costumize the frame
        setTitle("Accès réservations");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);

        //le tableau à partir des données
        List<Object[]> data = Controller.Admin.accesRéservation();
        String[] columnNames = {"Client", "Check-in", "Check-out", "Total", "Chambres", "Confirmation"};
        model = new DefaultTableModel(data.toArray(new Object[0][]), columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : super.getColumnClass(columnIndex);
            }
        };
        JTable table = new JTable(model);
        table.getColumnModel().getColumn(5).setCellRenderer(createCheckBoxRenderer());

        // here i ll customize the table
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setBackground(new Color(89, 116, 141));
        ((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
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

        // Custom cell renderer for the room number and type column
        table.getColumnModel().getColumn(4).setCellRenderer(new MultiLineTableCellRenderer());

        // i add this to be able to scroll on the table to see all the reservations
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(600, 300));

        //here i created boutons to make the admin able to make modifications
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton acceptButton = new JButton("Accepter");
        acceptButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.Admin.accepterRéservation(model);
                dispose();
                JOptionPane.showMessageDialog(null,"Les réservations sont confirmée avec succès.");
                Vue.Admin_view secondWindow = new Vue.Admin_view();
                secondWindow.setVisible(true);
            }
        });
        JButton declineButton = new JButton("Rejeter");
        declineButton.addActionListener(new ActionListener () {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.Admin.rejeterRéservation(model);
                dispose();
                JOptionPane.showMessageDialog(null,"Les réservations sont rejetée avec succès.");
                new Admin_view();
            }
        });

        // this is for customize buttons appearance
        Font buttonFont = new Font("SansSerif", Font.BOLD, 15);
        acceptButton.setFont(buttonFont);
        declineButton.setFont(buttonFont);
        acceptButton.setBackground(new Color(89, 116, 141));
        declineButton.setBackground(new Color(89, 116, 141));
        acceptButton.setForeground(Color.WHITE);
        declineButton.setForeground(Color.WHITE);

        // i did put the buttons on a panel to adjuste them
        buttonPanel.add(acceptButton);
        buttonPanel.add(declineButton);

        // here i add the table and buttons to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private TableCellRenderer createCheckBoxRenderer() {
        return (table, value, isSelected, hasFocus, row, column) -> {
            JCheckBox checkBox = new JCheckBox();
            checkBox.setSelected(value != null && (Boolean) value);
            checkBox.setHorizontalAlignment(SwingConstants.CENTER);
            return checkBox;
        };
    }

    // Custom TableCellRenderer for multi-line cells
    static class MultiLineTableCellRenderer extends JTextArea implements TableCellRenderer {

        public MultiLineTableCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value != null ? value.toString() : "");

            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }

            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);

            if (table.getRowHeight(row) != getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }
            return this;
        }
    }
}