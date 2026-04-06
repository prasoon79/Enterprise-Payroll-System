package payroll_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class salary extends JFrame implements ActionListener {

    JLabel l1,l2,l3,l4,l5,l6,l7;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JButton b1,b2;
    JPanel p1;
    Font f;

    salary(){
        super("Salary");

        setSize(500,400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20,20));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        f = new Font("Arial", Font.BOLD, 14);

        l1 = new JLabel("Employee ID");
        l2 = new JLabel("Basic Salary");
        l3 = new JLabel("HRA");
        l4 = new JLabel("DA");
        l5 = new JLabel("Bonus");
        l6 = new JLabel("Deduction");
        l7 = new JLabel("Total");

        l1.setFont(f); l2.setFont(f); l3.setFont(f);
        l4.setFont(f); l5.setFont(f); l6.setFont(f); l7.setFont(f);

        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
        t6 = new JTextField();

        t1.setFont(f); t2.setFont(f); t3.setFont(f);
        t4.setFont(f); t5.setFont(f); t6.setFont(f);

        b1 = new JButton("Calculate & Save");
        b2 = new JButton("Close");

        b1.addActionListener(this);
        b2.addActionListener(this);

        p1 = new JPanel(new GridLayout(8,2,10,10));
        p1.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        p1.add(l1); p1.add(t1);
        p1.add(l2); p1.add(t2);
        p1.add(l3); p1.add(t3);
        p1.add(l4); p1.add(t4);
        p1.add(l5); p1.add(t5);
        p1.add(l6); p1.add(t6);
        t7 = new JTextField();
        t7.setEditable(false);
        p1.add(l7); p1.add(t7);
        p1.add(b1); p1.add(b2);

        add(p1, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource() == b1){

            try{
                int eid = Integer.parseInt(t1.getText());
                double basic = Double.parseDouble(t2.getText());
                double hra = Double.parseDouble(t3.getText());
                double da = Double.parseDouble(t4.getText());
                double bonus = Double.parseDouble(t5.getText());
                double deduction = Double.parseDouble(t6.getText());

                double total = basic + hra + da + bonus - deduction;
                t7.setText(String.valueOf(total));

                // ===== TAX CALCULATION =====
                double tax = 0;

                if(total <= 250000) tax = 0;
                else if(total <= 500000) tax = total * 0.05;
                else if(total <= 1000000) tax = total * 0.10;
                else tax = total * 0.20;

                // ===== NET SALARY =====
                double netSalary = total - tax;

                ConnectionClass obj = new ConnectionClass();

                String q = "INSERT INTO salary VALUES("+eid+","+basic+","+hra+","+da+","+bonus+","+deduction+","+total+","+tax+","+netSalary+")";

                obj.stmt.executeUpdate(q);

                JOptionPane.showMessageDialog(null,
                        "Salary Saved\nTotal: "+total+
                        "\nTax: "+tax+
                        "\nNet Salary: "+netSalary
                );

            } catch(Exception ex){
                ex.printStackTrace();
            }
        }

        if(e.getSource() == b2){
            setVisible(false);
        }
    }

    public static void main(String args[]){
        new salary().setVisible(true);
    }
}
