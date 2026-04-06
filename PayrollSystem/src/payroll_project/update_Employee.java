package payroll_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class update_Employee extends JFrame implements ActionListener {

    JTextField t1,t2,t3,t4,t5,t6,t7;
    Choice ch1;
    JButton b1,b2,b3;

    update_Employee(){

        setTitle("Update Employee");
        setSize(800,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // ===== MAIN PANEL WITH PADDING =====
        JPanel mainPanel = new JPanel(new BorderLayout(20,20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        // ===== TITLE =====
        JLabel title = new JLabel("Update Employee Details");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setHorizontalAlignment(JLabel.CENTER);
        mainPanel.add(title, BorderLayout.NORTH);

        // ===== FORM PANEL =====
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Edit Details"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12,20,12,20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;   // VERY IMPORTANT (fix stretching)

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
        formPanel.add(new JLabel("Name"), gbc);

        gbc.gridx = 1; gbc.gridwidth = 2;
        t2 = new JTextField();
        formPanel.add(t2, gbc);
        gbc.gridwidth = 1;

        // Row 3
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Gender"), gbc);

        gbc.gridx = 1;
        ch1 = new Choice();
        ch1.add("Male");
        ch1.add("Female");
        formPanel.add(ch1, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Address"), gbc);

        gbc.gridx = 3;
        t3 = new JTextField();
        formPanel.add(t3, gbc);

        // Row 4
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("State"), gbc);

        gbc.gridx = 1;
        t4 = new JTextField();
        formPanel.add(t4, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("City"), gbc);

        gbc.gridx = 3;
        t5 = new JTextField();
        formPanel.add(t5, gbc);

        // Row 5
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Email"), gbc);

        gbc.gridx = 1;
        t6 = new JTextField();
        formPanel.add(t6, gbc);

        gbc.gridx = 2;
        formPanel.add(new JLabel("Phone"), gbc);

        gbc.gridx = 3;
        t7 = new JTextField();
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

        // ===== ADD TO MAIN PANEL =====
        JPanel centerWrapper = new JPanel(new BorderLayout());
        centerWrapper.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        centerWrapper.add(formPanel, BorderLayout.NORTH); // 👈 KEY FIX

        mainPanel.add(centerWrapper, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);

        // ===== ACTIONS =====
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == b1){
            try{
                int eid = Integer.parseInt(t1.getText());

                ConnectionClass obj = new ConnectionClass();
                String q = "SELECT * FROM new_employee WHERE eid="+eid;

                ResultSet rs = obj.stmt.executeQuery(q);

                if(rs.next()){
                    t2.setText(rs.getString("name"));
                    ch1.select(rs.getString("gender"));
                    t3.setText(rs.getString("address"));
                    t4.setText(rs.getString("state"));
                    t5.setText(rs.getString("city"));
                    t6.setText(rs.getString("email"));
                    t7.setText(rs.getString("phone"));
                } else {
                    JOptionPane.showMessageDialog(null,"Employee not found");
                }

            } catch(Exception ex){
                ex.printStackTrace();
            }
        }

        if(e.getSource() == b2){
            try{
                int eid = Integer.parseInt(t1.getText());

                ConnectionClass obj = new ConnectionClass();

                String q = "UPDATE new_employee SET " +
                        "name='"+t2.getText()+"',"+
                        "gender='"+ch1.getSelectedItem()+"',"+
                        "address='"+t3.getText()+"',"+
                        "state='"+t4.getText()+"',"+
                        "city='"+t5.getText()+"',"+
                        "email='"+t6.getText()+"',"+
                        "phone='"+t7.getText()+"' " +
                        "WHERE eid="+eid;

                obj.stmt.executeUpdate(q);

                JOptionPane.showMessageDialog(null,"Updated Successfully");

            } catch(Exception ex){
                ex.printStackTrace();
            }
        }

        if(e.getSource() == b3){
            setVisible(false);
        }
    }

    public static void main(String args[]){
        new update_Employee().setVisible(true);
    }
}