package JavaApp;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Date;

public class FastCash extends JFrame implements ActionListener{

    JLabel l1;
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin;
    FastCash(String pin){
        this.pin = pin;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900 , 900, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l2 = new JLabel(i3);
        l2.setBounds(0, 0, 900, 600);
        add(l2);
        
        l1 = new JLabel("SELECT WITHDRAWL AMOUNT");
        l1.setForeground(Color.WHITE);
        l1.setFont(new Font("System", Font.BOLD, 16));
        
       
        b1 = new JButton("Rs 100");
        b2 = new JButton("Rs 500");
        b3 = new JButton("Rs 1000");
        b4 = new JButton("Rs 2000");
        b5 = new JButton("Rs 5000");
        b6 = new JButton("Rs 1000");
        b7 = new JButton("BACK");
        
        setLayout(null);
        
        l1.setBounds(220,150,700,35);
        l2.add(l1);
        
        b1.setBounds(170,200,150,35);
        l2.add(b1);
        
        b2.setBounds(340,200,150,35);
        l2.add(b2);
        
        b3.setBounds(170,250,150,35);
        l2.add(b3);
        
        b4.setBounds(340,250,150,35);
        l2.add(b4);
        
        b5.setBounds(170,300,150,35);
        l2.add(b5);
        
        b6.setBounds(340,300,150,35);
        l2.add(b6);
        
        b7.setBounds(340,350,150,35);
        l2.add(b7);
        
        
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        
        
        setSize(900,600);
        setLocation(200,30);
        setUndecorated(true);
        setVisible(true);
        
        
        
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== b7){ 
                setVisible(false);
                new Transactions(pin).setVisible(true);
        } else{
          String amount = ((JButton)ae.getSource()).getText().substring(3);
          Conn c = new Conn();
          try{
          ResultSet rs = c.s.executeQuery("select * from bank where pin ='"+pin+"'");
          int balance = 0;
          while(rs.next()){
           if(rs.getString("type").equals("Deposit")) {
            balance += Integer.parseInt(rs.getString("amount"));
           }else {
           balance -= Integer.parseInt(rs.getString("amount"));
           
           }        
          }
          if(ae.getSource() != b7 && balance < Integer.parseInt(amount)) {
          JOptionPane.showMessageDialog(null,"Insufficient Balance");
          return ;
          }else{
          Date date = new Date();
          String query = "insert into bank values('"+pin+"','"+date+"','Withdrawl','"+amount+"')" ;
          c.s.executeUpdate(query);
          JOptionPane.showMessageDialog(null,"Rs "+amount+" Debited Successfully");
          }
          setVisible(false);
          new Transactions(pin).setVisible(true);

          }catch(Exception e){
             System.out.println(e);
          }
        }
    }
    
    public static void main(String[] args){
        new FastCash("").setVisible(true);
    }
}