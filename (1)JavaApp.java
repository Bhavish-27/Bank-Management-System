import java.sql.*;
public class JavaApp {

    public Connection con;
    public static void main(String[] args) {
     
    try{
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/getconnect","root","root");
    Statement stmt = con.createStatement();
    ResultSet rs = stmt.executeQuery("select * from student ");
    while(rs.next()){
    String name = rs.getString("name");
    System.out.println(name);
    
    }
    if(con!= null){
    System.out.println("successfully connected");
    
    }
    
    
    
    
    
    }catch(Exception e){
    System.out.println("e");
    System.out.println("not connected");
    }
}
}