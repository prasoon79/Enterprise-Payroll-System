package payroll_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class login_page extends JFrame implements ActionListener{
    JLabel l1,l2,l3,l4 ;
    JButton bt1, bt2;
    JTextField tf;
    JPasswordField pf;
    JPanel p1,p2;
    Font f;
    
    login_page(){
    super ("Payroll Login");
    setSize (450,200);
    setLocation(500,250);
    setResizable (false);
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    //setUndecorated(true);
    f = new Font("arial", Font.BOLD,14);
    l1 = new JLabel("Username");
    l2 = new JLabel("Password");
    
    l1.setFont(f);
    l2.setFont(f);
    
    tf = new JTextField();
    pf = new JPasswordField();
    
    tf.setFont(f);
    pf.setFont(f);
    
    bt1 = new JButton ("Login");
    bt2 = new JButton ("Cancel");
    bt1.setBackground(Color.BLACK);
    bt1.setForeground(Color.WHITE);
    bt2.setBackground(Color.BLACK);
    bt2.setForeground(Color.WHITE);
    
    bt1.addActionListener(this);
    bt2.addActionListener(this);
    bt1.setFont(f);
    bt2.setFont(f);
    
    p1 = new JPanel();
    p1.setLayout(new GridLayout(3,2,10,10));
    
    p1.add(l1);
    p1.add(tf);
    p1.add(l2);
    p1.add(pf);
    p1.add(bt1);
    p1.add(bt2);
    
    ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("payroll_project/icons/login.png"));
    Image image = img.getImage().getScaledInstance(120,120,Image.SCALE_DEFAULT);
    ImageIcon img1 = new ImageIcon(image);
    l3 = new JLabel(img1);
    
    setLayout(new BorderLayout(20,20));
    
    add(l3,BorderLayout.WEST);
    add(p1,BorderLayout.CENTER);
    
    p1.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    l3.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
    getRootPane().setDefaultButton(bt1);
    
   }
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == bt1)
        {
            String u_name = tf.getText();
            String p_name = new String(pf.getPassword());
            
            try{
                ConnectionClass obj = new ConnectionClass();
                String q = "select * from login where username='"+u_name+"' and password='"+p_name+"'";
                ResultSet res = obj.stmt.executeQuery(q);
                if(res.next())
                {
                    new home_payroll().setVisible(true);
                    this.setVisible(false);   
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"invalid username or password");
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        if(e.getSource() == bt2)
        {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
            if(choice == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
    }
    public static void main(String args[]){
        new login_page().setVisible(true);
    }
}

