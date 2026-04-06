package payroll_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class update_Salary extends JFrame implements ActionListener {

    JTextField t1,t2,t3,t4,t5,t6,t7;
    JButton b1,b2,b3;

    update_Salary(){

        setTitle("Update Salary");
        setSize(800,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ===== MAIN PANEL =====
        JPanel mainPanel = new JPanel(new BorderLayout(20,20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        // ===== TITLE =====
        JLabel title = new JLabel("Update Salary Details");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(title, BorderLayout.NORTH);

        // ===== FORM PANEL =====
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Edit Salary"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12,20,12,20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        Font f = new Font("Arial", Font.BOLD, 14);

        // Row 1
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Employee ID"), gbc);

        gbc.gridx = 1;
        t1 = new JTextField();
        formPanel.add(t1, gbc);

        gbc.gridx = 2;
        b1 = new JButton("Search");
        b1.setBackground(new Color(30,144,255));
        b1.setForeground(Color.WHITE);
        formPanel.add(b1, gbc);

        // Row 2
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Basic Salary"), gbc);

        gbc.gridx = 1;
        t2 = new JTextField();
        formPanel.add(t2, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("HRA"), gbc);

        gbc.gridx = 3;
        t3 = new JTextField();
        formPanel.add(t3, gbc);

        // Row 3
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("DA"), gbc);

        gbc.gridx = 1;
        t4 = new JTextField();
        formPanel.add(t4, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Bonus"), gbc);

        gbc.gridx = 3;
        t5 = new JTextField();
        formPanel.add(t5, gbc);

        // Row 4
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Deduction"), gbc);

        gbc.gridx = 1;
        t6 = new JTextField();
        formPanel.add(t6, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Total"), gbc);

        gbc.gridx = 3;
        t7 = new JTextField();
        t7.setEditable(false);
        formPanel.add(t7, gbc);

        // ===== BUTTON PANEL =====
        JPanel buttonPanel = new JPanel(new GridLayout(1,2,20,10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));

        b2 = new JButton("Update");
        b3 = new JButton("Close");

        b2.setBackground(new Color(34,139,34));
        b2.setForeground(Color.WHITE);

        b3.setBackground(Color.RED);
        b3.setForeground(Color.WHITE);

        buttonPanel.add(b2);
        buttonPanel.add(b3);

        // ===== ADD =====
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // ===== ACTIONS =====
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){

        // SEARCH
        if(e.getSource() == b1){
            try{
                int eid = Integer.parseInt(t1.getText());

                ConnectionClass obj = new ConnectionClass();
                String q = "SELECT * FROM salary WHERE eid="+eid;

                ResultSet rs = obj.stmt.executeQuery(q);

                if(rs.next()){
                    t2.setText(rs.getString("basic"));
                    t3.setText(rs.getString("hra"));
                    t4.setText(rs.getString("da"));
                    t5.setText(rs.getString("bonus"));
                    t6.setText(rs.getString("deduction"));
                    t7.setText(rs.getString("total"));
                } else {
                    JOptionPane.showMessageDialog(null,"Record not found");
                }

            } catch(Exception ex){
                ex.printStackTrace();
            }
        }

        // UPDATE
        if(e.getSource() == b2){
            try{
                int eid = Integer.parseInt(t1.getText());

                double basic = Double.parseDouble(t2.getText());
                double hra = Double.parseDouble(t3.getText());
                double da = Double.parseDouble(t4.getText());
                double bonus = Double.parseDouble(t5.getText());
                double deduction = Double.parseDouble(t6.getText());

                double total = basic + hra + da + bonus - deduction;
                t7.setText(String.valueOf(total));

                ConnectionClass obj = new ConnectionClass();

                String q = "UPDATE salary SET " +
                        "basic="+basic+","+
                        "hra="+hra+","+
                        "da="+da+","+
                        "bonus="+bonus+","+
                        "deduction="+deduction+","+
                        "total="+total+
                        " WHERE eid="+eid;

                obj.stmt.executeUpdate(q);

                JOptionPane.showMessageDialog(null,"Salary Updated");

            } catch(Exception ex){
                ex.printStackTrace();
            }
        }

        // CLOSE
        if(e.getSource() == b3){
            setVisible(false);
        }
    }

    public static void main(String args[]){
        new update_Salary().setVisible(true);
    }
}