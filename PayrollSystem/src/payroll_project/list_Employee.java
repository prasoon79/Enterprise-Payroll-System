package payroll_project;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import javax.swing.table.JTableHeader;

public class list_Employee extends JFrame {

    JTable table;

    list_Employee(){

        setTitle("Employee List");
        setSize(900,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ===== MAIN PANEL =====
        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));

        // ===== TITLE =====
        JLabel title = new JLabel("Employee Details");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

        // ===== TABLE =====
        table = new JTable();

        try{
            ConnectionClass obj = new ConnectionClass();
            String q = "SELECT * FROM new_employee";
            ResultSet res = obj.stmt.executeQuery(q);

            table.setModel(DbUtils.resultSetToTableModel(res));

        } catch(Exception e){
            e.printStackTrace();
        }

        // ===== TABLE IMPROVEMENTS =====
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 13));

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));
        header.setBackground(Color.LIGHT_GRAY);

        JScrollPane sp = new JScrollPane(table);
        sp.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // ===== ADD =====
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(sp, BorderLayout.CENTER);

        add(mainPanel);
    }

    public static void main(String args[]){
        new list_Employee().setVisible(true);
    }
}