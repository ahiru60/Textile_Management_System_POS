/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;


/**
 *
 * @author Adithya
 */
public class DatabaseConnection {
    
    
    static Connection connection;
    static Statement state;
    
    
    public Statement getStatementConnection(String uRL){
    
    try{

    String url = uRL;
    
    connection = DriverManager.getConnection(url,"root","");
    state = connection.createStatement();
    }
    catch(Exception ex){
        
    ex.printStackTrace();
}
    return state;
    }
    public static void closeCon() throws SQLException {
 connection.close();
 }
    
}
