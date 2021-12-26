
import java.sql.*;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ACER
 */
public class javaconnect {
    Connection conn;
    
    public static Connection ConnerDB(){
     try{ 
         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
         String ConnectionUrl="jdbc:sqlserver://localhost:1433;databaseName=MHS;"
                 + "user=su;password=56646435;";
         Connection conn=DriverManager.getConnection(ConnectionUrl);
         return conn;
     }catch(Exception e){
        JOptionPane.showMessageDialog(null, e);
        return null;
     }
    }
}   

