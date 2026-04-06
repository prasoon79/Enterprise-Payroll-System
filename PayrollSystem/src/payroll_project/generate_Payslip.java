package payroll_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class generate_Payslip extends JFrame implements ActionListener {

    JTextField t1;
    JTextArea area;
    JButton b1,b2;

    generate_Payslip(){

        setTitle("Generate Payslip");
        setSize(600,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel topPanel = new JPanel(new GridLayout(1,3,10,10));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));

        topPanel.add(new JLabel("Employee ID"));

        t1 = new JTextField();
        topPanel.add(t1);

        b1 = new JButton("Generate");
        topPanel.add(b1);

        area = new JTextArea();
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));
        area.setEditable(false);

        JScrollPane sp = new JScrollPane(area);

        b2 = new JButton("Close");
        b2.setBackground(Color.RED);
        b2.setForeground(Color.WHITE);
        
        JLabel title = new JLabel("Employee Payslip");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));

        add(title, BorderLayout.NORTH);
        topPanel.setBorder(BorderFactory.createEmptyBorder(15,20,10,20));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        bottomPanel.add(b2);

        add(topPanel, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        b1.addActionListener(this);
        b2.addActionListener(this);
        
        JButton printBtn = new JButton("Print");
        bottomPanel.add(printBtn);

        printBtn.addActionListener(e -> {
            try {
                area.print();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == b1){

            try{
                int eid = Integer.parseInt(t1.getText());

                ConnectionClass obj = new ConnectionClass();

                String empQuery = "SELECT * FROM new_employee WHERE eid="+eid;
                String salQuery = "SELECT * FROM salary WHERE eid="+eid;
                
                Statement stmt1 = obj.conn.createStatement();
                Statement stmt2 = obj.conn.createStatement();

                ResultSet empRs = stmt1.executeQuery(empQuery);
                ResultSet salRs = stmt2.executeQuery(salQuery);

                if(empRs.next() && salRs.next()){

                    String name = empRs.getString("name");
                    String email = empRs.getString("email");
                    String phone = empRs.getString("phone");

                    double basic = salRs.getDouble("basic");
                    double hra = salRs.getDouble("hra");
                    double da = salRs.getDouble("da");
                    double bonus = salRs.getDouble("bonus");
                    double deduction = salRs.getDouble("deduction");
                    double total = salRs.getDouble("total");
                    double tax = salRs.getDouble("tax");
                    double netSalary = salRs.getDouble("net_salary");

                    area.setText(
                            "************ VITB Pvt Ltd ************\n"+
                            "         Payroll Department\n\n"+

                            "Employee ID : " + eid + "\n"+
                            "Name        : " + name + "\n"+
                            "Email       : " + email + "\n"+
                            "Phone       : " + phone + "\n\n"+

                            "-------------------------------------------\n"+
                            "Basic       : ₹ " + String.format("%,.0f", basic) + "\n"+
                            "HRA         : ₹ " + String.format("%,.0f", hra) + "\n"+
                            "DA          : ₹ " + String.format("%,.0f", da) + "\n"+
                            "Bonus       : ₹ " + String.format("%,.0f", bonus) + "\n"+
                            "Deduction   : ₹ " + String.format("%,.0f", deduction) + "\n"+
                            "-------------------------------------------\n"+
                            "Gross Salary: ₹ " + String.format("%,.0f", total) + "\n"+
                            "Tax         : ₹ " + String.format("%,.0f", tax) + "\n"+
                            "-------------------------------------------\n"+
                            "Net Salary  : ₹ " + String.format("%,.0f", netSalary) + "\n"+
                            "==========================================="
                    );
                    
                    area.setBackground(new Color(245,245,245));
                    area.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));
                    area.setFont(new Font("Monospaced", Font.BOLD, 15));

                } else {
                    JOptionPane.showMessageDialog(null,"Record not found");
                }

            } catch(Exception ex){
                ex.printStackTrace();
            }
        }

        if(e.getSource() == b2){
            setVisible(false);
        }
    }

    public static void main(String args[]){
        new generate_Payslip().setVisible(true);
    }
}