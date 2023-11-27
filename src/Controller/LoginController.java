/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.*;
import View.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Adithya
 */
public  class LoginController {
    
    public static void login(String userName,String password){
        ResultSet rs;
        String user=null;
        String pass=null;
        try{
            
            rs = new DatabaseOperations().getLoginSet(userName);
            
            while(rs.next()){
            user = rs.getString("user_name");
            pass = rs.getString("password");
                
            }
            //System.out.println(rs.getString("user_name")+rs.getString("password"));
            if(user!=null&&pass!=null){
            
                if(user.equals(userName)&&pass.equals(password)){
                    
                   Login.getFrames()[0].dispose();
                   new Home().setVisible(true);
                   System.out.println("Login Succuessful!");
                   
                    
                }
                else{
                
               JOptionPane.showMessageDialog(null, "Your login attempt failed please check your username and password again!");}
                
            }
            else{JOptionPane.showMessageDialog(null, "Your login attempt failed please check your username and password again!");}
            
            DatabaseConnection.closeCon();
        }
        catch(Exception ex){
            
            ex.printStackTrace();
        
        }
        
        
    }
    
    public static void register(String userName,String password,String rePassword, String adminUserName,String adminPassword){
    
        
        try {
            if(password.equals(rePassword)){
            
            
                if(!DatabaseOperations.checkPassword(userName, password)){
        
            if(password.length()>=8){
                DatabaseOperations.setLoginSet(userName, password);
            }
            else{
            
                JOptionPane.showMessageDialog(null,"Password must contain 8 or more characters!");
            }
            
        
        }
                
            
            }
            
        else{
        
            JOptionPane.showMessageDialog(null,"Admin username or password incorrect or already exsist!");
        
        }
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }
    public class Class{
    
        Class(){
        
        int val = 89;
        
        }
        
    }
    
    Class obj = new Class();
}
