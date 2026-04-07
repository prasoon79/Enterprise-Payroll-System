package payroll_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class login_page extends JFrame{
    JLabel l1,l2,l3,l4 ;
    JButton bt1, bt2;
    JTextField tf;
    JPasswordField pf;
    JPanel p1,p2;
    Font f;
    
    login_page(){
        super("Payroll Login");

        setExtendedState(JFrame.MAXIMIZED_BOTH); // full screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ===== BACKGROUND IMAGE =====
        ImageIcon bgImg = new ImageIcon(
            ClassLoader.getSystemResource("payroll_project/icons/earth.jpg")
        );
        Image img = bgImg.getImage().getScaledInstance(
            Toolkit.getDefaultToolkit().getScreenSize().width,
            Toolkit.getDefaultToolkit().getScreenSize().height,
            Image.SCALE_SMOOTH
        );
        JLabel background = new JLabel(new ImageIcon(img));
        background.setLayout(new GridBagLayout()); // center content

        // ===== LOGIN PANEL =====
        JLabel title = new JLabel("Payroll System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(new Color(50,50,50));
        title.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        JPanel loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(380,260));
        loginPanel.setBackground(new Color(255,255,255,240)); // semi-transparent
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200,200,200), 2),
                BorderFactory.createEmptyBorder(20,20,20,20)
        ));

        Font f = new Font("Arial", Font.BOLD, 14);

        JLabel l1 = new JLabel("Username");
        JLabel l2 = new JLabel("Password");

        tf = new JTextField();
        pf = new JPasswordField();
        
        tf.setPreferredSize(new Dimension(150,30));
        pf.setPreferredSize(new Dimension(150,30));

        l1.setFont(f);
        l2.setFont(f);
        tf.setFont(f);
        pf.setFont(f);

        bt1 = new JButton("Login");
        bt2 = new JButton("Cancel");
        
        bt1.setPreferredSize(new Dimension(120,35));
        bt2.setPreferredSize(new Dimension(120,35));
        
        bt1.setBackground(new Color(0,153,76));   // darker green
        bt2.setBackground(new Color(204,0,0));    // softer red

        bt1.setFocusPainted(false);
        bt2.setFocusPainted(false);

        // ===== ADD COMPONENTS =====
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        loginPanel.add(title, gbc);

        // Username
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        loginPanel.add(l1, gbc);

        gbc.gridx = 1;
        loginPanel.add(tf, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 2;
        loginPanel.add(l2, gbc);

        gbc.gridx = 1;
        loginPanel.add(pf, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 3;
        loginPanel.add(bt1, gbc);

        gbc.gridx = 1;
        loginPanel.add(bt2, gbc);

        // ===== CENTER PANEL =====
        background.add(loginPanel);

        add(background);

        // ===== ACTIONS =====
        bt1.addActionListener(e -> {
            String u_name = tf.getText();
            String p_name = new String(pf.getPassword());

            try{
                ConnectionClass obj = new ConnectionClass();

                String q = "select * from login where username='"+u_name+"' and password='"+p_name+"'";
                ResultSet res = obj.stmt.executeQuery(q);

                if(res.next()){
                    new home_payroll().setVisible(true);
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null,"Invalid username or password");
                }

            } catch(Exception ex){
                ex.printStackTrace();
            }
        });

        bt2.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(null,"Exit?");
            if(choice == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        });

        getRootPane().setDefaultButton(bt1);
    }
    /*public void actionPerformed(ActionEvent e){
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
    }*/
    public static void main(String args[]){
        new login_page().setVisible(true);
    }
}

