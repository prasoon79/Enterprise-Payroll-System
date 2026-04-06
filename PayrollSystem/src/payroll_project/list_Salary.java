package payroll_project;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class list_Salary extends JFrame {

    JTable table;

    list_Salary(){

        setTitle("Salary Records");
        setSize(900,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ===== MAIN PANEL =====
        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,20,15,20));

        // ===== TITLE =====
        JLabel title = new JLabel("Salary Details");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);

        // ===== TABLE =====
        table = new JTable();

        try{
            ConnectionClass obj = new ConnectionClass();

            String q = "SELECT * FROM salary";

            ResultSet rs = obj.stmt.executeQuery(q);

            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch(Exception e){
            e.printStackTrace();
        }

        JScrollPane sp = new JScrollPane(table);

        // ===== ADD =====
        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(sp, BorderLayout.CENTER);

        add(mainPanel);
    }

    public static void main(String args[]){
        new list_Salary().setVisible(true);
    }
}