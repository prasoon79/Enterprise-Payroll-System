package payroll_project;
import java.sql.*;

public class ConnectionClass 
{
    Connection conn;
    Statement stmt;
    ConnectionClass()
    {
        try 
        { 
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/PayrollSystem", "root", "prasoon123");
            stmt = conn.createStatement();
            if(conn.isClosed()){
                System.out.println("connection closed");
            }
            else{
                System.out.println("connection create");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        new ConnectionClass();
    }
}
