package payroll_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class take_Attendance extends JFrame implements ActionListener {

    JTextField t1,t2;
    Choice ch1;
    JButton b1,b2;

    take_Attendance(){

        setTitle("Take Attendance");
        setSize(500,300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(20,20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        JLabel title = new JLabel("Employee Attendance");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setHorizontalAlignment(JLabel.CENTER);

        JPanel form = new JPanel(new GridLayout(4,2,15,15));

        form.add(new JLabel("Employee ID"));
        t1 = new JTextField();
        form.add(t1);

        form.add(new JLabel("Date"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate today = LocalDate.now();

        t2 = new JTextField(today.format(formatter));
        t2.setEditable(false);
        form.add(t2);

        form.add(new JLabel("Status"));
        ch1 = new Choice();
        ch1.add("Present");
        ch1.add("Absent");
        form.add(ch1);

        b1 = new JButton("Submit");
        b2 = new JButton("Close");

        b1.setBackground(new Color(34,139,34));
        b1.setForeground(Color.WHITE);

        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);

        JPanel btnPanel = new JPanel(new GridLayout(1,2,20,10));
        btnPanel.add(b1);
        btnPanel.add(b2);

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(form, BorderLayout.CENTER);
        mainPanel.add(btnPanel, BorderLayout.SOUTH);

        add(mainPanel);

        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == b1){
            try{
                int eid = Integer.parseInt(t1.getText());
                String date = t2.getText();
                String status = ch1.getSelectedItem();

                ConnectionClass obj = new ConnectionClass();

                String q = "INSERT INTO attendance VALUES("+eid+",'"+date+"','"+status+"')";

                obj.stmt.executeUpdate(q);

                JOptionPane.showMessageDialog(null,"Attendance Saved");

            } catch(Exception ex){
                ex.printStackTrace();
            }
        }

        if(e.getSource() == b2){
            setVisible(false);
        }
    }

    public static void main(String args[]){
        new take_Attendance().setVisible(true);
    }
}