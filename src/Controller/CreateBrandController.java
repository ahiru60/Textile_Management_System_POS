/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import java.sql.*;
import Model.*;
import View.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Adithya
 */
public class CreateBrandController {
    
    public static void create(String brandName, ResultSet rs){
    brandName = brandName.replace(" ", "_");
        boolean b =false;
        
        if(!brandName.equals("")){
            
            try{
                
                while(rs.next()){
                    
                    if(brandName.equals(rs.getString("table_name"))){
                    
                        b=true;
                    
                    }
                    
                }
            if(!b){
            
                new DatabaseOperations().createBrandTable(brandName);
                
            }
            else{
            
                JOptionPane.showMessageDialog(null,"Brand name already exsists!");
            }
            
            
        }
        catch(Exception ex){
            
            ex.printStackTrace();}
    }
        else{
        
            JOptionPane.showMessageDialog(null,"Please enter valid brand name!");
        
        }
            
        }
    
    public static void remove(String brandName){
    
        ResultSet rs;
        
        try{
        
            new DatabaseOperations().dropBrandTable(brandName);
           
        }
        catch(Exception ex){ex.printStackTrace();}
    }
    
}

