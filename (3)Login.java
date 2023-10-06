
package JavaApp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
   
    JTextField cardTextField ;
    JPasswordField pinTextField;
    JButton login,signup,clear;
  
    Login(){
        setTitle("AUTOMATED TELLER MACHINE");
        setLayout(null);
        
        ImageIcon I1 = new ImageIcon(ClassLoader.getSystemResource("icons/news1.jpg"));
        Image I2 = I1.getImage().getScaledInstance(900 , 900, Image.SCALE_DEFAULT);
        ImageIcon I3 = new ImageIcon(I2);
        JLabel L2 = new JLabel(I3);
        L2.setBounds(0, 0, 800, 480);
        add(L2);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/logo.jpg"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel label = new JLabel(i3);
        label.setBounds(70, 10, 100, 100);
        L2.add(label);
        
        JLabel text = new JLabel("WELCOME TO ATM");
        text.setFont(new Font("Osward", Font.BOLD, 38));
        text.setBounds(200,40,450,40);
        L2.add(text);
        
        JLabel cardno= new JLabel("Card No:");
        cardno.setFont(new Font("Raleway", Font.BOLD, 28));
        cardno.setBounds(125,150,375,30);
        L2.add(cardno);
        
        cardTextField = new JTextField(15);
         cardTextField .setBounds(300,150,230,30);
         cardTextField .setFont(new Font("Arial", Font.BOLD, 14));
        L2.add( cardTextField );
        
        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway", Font.BOLD, 28));
        pin.setBounds(125,220,375,30);
        L2.add(pin);
        
        pinTextField = new JPasswordField();
        pinTextField.setFont(new Font("Arial", Font.BOLD, 14));
        pinTextField.setBounds(300,220,230,30);
        L2.add(pinTextField);
                
        login = new JButton("SIGN IN");
       login.setBounds(300,300,100,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        L2.add(login);
        
        clear = new JButton("CLEAR");
        clear.setBounds(430,300,100,30);
        clear.setBackground(Color.BLACK);
       clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        L2.add(clear);
        
         signup = new JButton("SIGN UP");
        signup.setBounds(300,350,230,30);
        signup.setBackground(Color.BLACK);
       signup.setForeground(Color.WHITE);
        signup.addActionListener(this);
        L2.add(signup);
        
        getContentPane().setBackground(Color.WHITE);
        setSize(800,480);
        setLocation(300,100);
        setVisible(true);
        
    }
    public void actionPerformed(ActionEvent ae){
                
            if(ae.getSource()==clear){
                cardTextField.setText("");
                 pinTextField.setText("");
                
                
            }else if(ae.getSource()==login){
               Conn conn = new Conn();
               String cardnumber = cardTextField.getText();
               String pin = pinTextField.getText();
               String query = "select * from login where cardnumber ='"+cardnumber+"'and pin ='"+pin+"'";
               try{
                 ResultSet rs = conn.s.executeQuery(query);
                 if(rs.next()){
                 setVisible(false);
                 new Transactions(pin).setVisible(true);   
                 }else {
                 JOptionPane.showMessageDialog(null, "Incorrect Card Number or Pin");
                 
                 }  
                                 
               
               }catch(Exception e){
                System.out.println(e);
               }
            }else if(ae.getSource()==signup){
               setVisible(false);
               new SignupOne().setVisible(true);
            }
    }
    
    public static void main(String[] args){
        new Login();
    }

    
}