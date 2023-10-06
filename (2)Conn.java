package JavaApp;

import java.sql.*;  

public class Conn{
    Connection c;
    Statement s;
    public Conn(){  
        try{  
            Class.forName("com.mysql.jdbc.Driver");  
            c =DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem","root","root");    
            s =c.createStatement(); 
           System.out.println("Success");
          
            
        }catch(Exception e){ 
            System.out.println(e);
        }  
    }  
    public static void main(String[] args){
     new Conn();
    
    }
}  