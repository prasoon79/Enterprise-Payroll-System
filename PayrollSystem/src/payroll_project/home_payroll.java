package payroll_project;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class home_payroll extends JFrame implements ActionListener{
    JLabel l1;
    Font f;
    home_payroll(){
        super("Home Page");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        f = new Font("arial", Font.BOLD,14);
        
            JMenuBar mb = new JMenuBar();
            JMenu m1 = new JMenu("Employee");
            JMenu m2 = new JMenu("Update");
            JMenu m3 = new JMenu("Attendance");
            JMenu m4 = new JMenu("Exit");
            m4.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }});
            
            m1.setFont(f);
            m2.setFont(f);
            m3.setFont(f);
            //m4.setFont(f);
            
            JMenuItem mt1 = new JMenuItem ("New Employee");
            ImageIcon img1 = new ImageIcon (ClassLoader.getSystemResource("payroll_project/icons/icon3.png"));
            Image image = img1.getImage().getScaledInstance (16, 16, Image.SCALE_DEFAULT);
            ImageIcon img2 = new ImageIcon(image);
            mt1.setIcon(img2);
            mt1.setMnemonic('N');
            mt1.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_N,ActionEvent.CTRL_MASK));
            mt1.addActionListener(this);
            
            JMenuItem mt2=new JMenuItem("Salary");
            ImageIcon img3=new ImageIcon (ClassLoader.getSystemResource("payroll_project/icons/icon10.png"));
            Image imagel = img3.getImage().getScaledInstance (16,16, Image.SCALE_DEFAULT);
            ImageIcon img4 = new ImageIcon(imagel);
            mt2.setIcon(img4);
            mt2.setMnemonic('S');
            mt2.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_S, ActionEvent.CTRL_MASK));
            mt2.addActionListener(this);
            
            JMenuItem mt3 = new JMenuItem("List Employee");
            ImageIcon img5 = new ImageIcon (ClassLoader.getSystemResource("payroll_project/icons/icon17.png"));
            Image image3 = img5.getImage().getScaledInstance (16,16, Image.SCALE_DEFAULT);
            ImageIcon img6 = new ImageIcon (image3);
            mt3.setIcon(img6);
            mt3.setMnemonic('L');
            mt3.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_L, ActionEvent.CTRL_MASK));
            mt3.addActionListener(this);
            
            //Update Menu........
            
            JMenuItem mt4 = new JMenuItem("Update Employee");
            ImageIcon img7 = new ImageIcon (ClassLoader.getSystemResource("payroll_project/icons/icon14.png"));
            Image image4 = img7.getImage().getScaledInstance (16,16, Image.SCALE_DEFAULT);
            ImageIcon img8 = new ImageIcon (image4);
            mt4.setIcon(img8);
            mt4.setMnemonic('U');
            mt4.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_U, ActionEvent.CTRL_MASK));
            mt4.addActionListener(this);
            
            JMenuItem mt5 = new JMenuItem("Update Salary");
            ImageIcon img9 = new ImageIcon (ClassLoader.getSystemResource("payroll_project/icons/icon16.png"));
            Image image5 = img9.getImage().getScaledInstance (16,16, Image.SCALE_DEFAULT);
            ImageIcon img10 = new ImageIcon (image5);
            mt5.setIcon(img10);
            mt5.setMnemonic('V');
            mt5.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_V, ActionEvent.CTRL_MASK));
            mt5.addActionListener(this);
            
            //Attendance Menu...........
            
            JMenuItem mt6 = new JMenuItem("Take Attendance");
            ImageIcon img11 = new ImageIcon (ClassLoader.getSystemResource("payroll_project/icons/icon4.png"));
            Image image6 = img11.getImage().getScaledInstance (16,16, Image.SCALE_DEFAULT);
            ImageIcon img12 = new ImageIcon (image6);
            mt6.setIcon(img12);
            mt6.setMnemonic('A');
            mt6.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_A, ActionEvent.CTRL_MASK));
            mt6.addActionListener(this);
            
            JMenuItem mt7 = new JMenuItem("List Attendance");
            ImageIcon img13 = new ImageIcon (ClassLoader.getSystemResource("payroll_project/icons/icon0.png"));
            Image image7 = img13.getImage().getScaledInstance (16,16, Image.SCALE_DEFAULT);
            ImageIcon img14 = new ImageIcon (image7);
            mt7.setIcon(img14);
            mt7.setMnemonic('B');
            mt7.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_B, ActionEvent.CTRL_MASK));
            mt7.addActionListener(this);
            
            JMenuItem mt8 = new JMenuItem("Generate Payslip");
            ImageIcon img15 = new ImageIcon (ClassLoader.getSystemResource("payroll_project/icons/icon7.png"));
            Image image8 = img15.getImage().getScaledInstance (16,16, Image.SCALE_DEFAULT);
            ImageIcon img16 = new ImageIcon (image8);
            mt8.setIcon(img16);
            mt8.setMnemonic('P');
            mt8.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_P, ActionEvent.CTRL_MASK));
            mt8.addActionListener(this);
            
            //JMenuItem mt9 = new JMenuItem("Exit");
            /*ImageIcon img17 = new ImageIcon (ClassLoader.getSystemResource("payroll_project/icons/cancel.png"));
            Image image9 = img17.getImage().getScaledInstance (16,16, Image.SCALE_DEFAULT);
            ImageIcon img18 = new ImageIcon (image9);
            mt9.setIcon(img18);
            mt9.setMnemonic('X');
            mt9.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_X, ActionEvent.CTRL_MASK)); */
            //mt9.addActionListener(this);
            
            JMenuItem mt10 = new JMenuItem("List Salary");
            ImageIcon img18 = new ImageIcon (ClassLoader.getSystemResource("payroll_project/icons/icon16.png"));
            Image image10 = img18.getImage().getScaledInstance (16,16, Image.SCALE_DEFAULT);
            ImageIcon img19 = new ImageIcon (image10);
            mt10.setIcon(img19);
            mt10.setMnemonic('M');
            mt10.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_M, ActionEvent.CTRL_MASK));
            mt10.addActionListener(this);
            
            mt1.setFont(f);
            mt2.setFont(f);
            mt3.setFont(f);
            mt4.setFont(f);
            mt5.setFont(f);
            mt6.setFont(f);
            mt7.setFont(f);
            mt8.setFont(f);
            //mt9.setFont(f);
            mt10.setFont(f);
            
            m1.add(mt1);
            m1.add(mt2);
            m1.add(mt3);
            m1.add(mt10);
            
            m2.add(mt4);
            m2.add(mt5);
            
            m3.add(mt6);
            m3.add(mt7);
            m3.add(mt8);
            
            //m4.add(mt9); 
            
            mb.add(m1);
            mb.add(m2);
            mb.add(m3);
            mb.add(m4);
            //mb.add(mt9);
            
            mb.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
            
            ImageIcon img20 = new ImageIcon (ClassLoader.getSystemResource("payroll_project/icons/earth.jpg"));
            Image image11 = img20.getImage().getScaledInstance (1600,690, Image.SCALE_DEFAULT);
            ImageIcon img21 = new ImageIcon (image11);
            l1 = new JLabel(img21);
            l1.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            
            setJMenuBar(mb);
            
            add(l1);
    }
    
    public void actionPerformed(ActionEvent e){
        String comnd = e.getActionCommand();
        
        if(comnd.equals("New Employee")){
            new new_Employee().setVisible(true);
        }
        else if(comnd.equals("Salary")){
            new salary().setVisible(true);
        }
        else if(comnd.equals("List Employee")){
            new list_Employee().setVisible(true);
        }
        else if(comnd.equals("List Salary")){
            new list_Salary().setVisible(true);
        }
        else if(comnd.equals("Update Employee")){
            new update_Employee().setVisible(true);
        }
        else if(comnd.equals("Update Salary")){
            new update_Salary().setVisible(true);
        }
        else if(comnd.equals("Take Attendance")){
            new take_Attendance().setVisible(true);
        }
        else if(comnd.equals("List Attendance")){
            new list_Attendance().setVisible(true);
        }
        else if(comnd.equals("Generate Payslip")){
            new generate_Payslip().setVisible(true);
        }
        else if(comnd.equals("Exit")){
            System.exit(0);
        }
        else{
            JOptionPane.showMessageDialog(null, "Sorry! You pressed wrong button");
            setVisible(false);
        }  
    }
    public static void main(String args[]){
        new home_payroll().setVisible(true);
    }
}

