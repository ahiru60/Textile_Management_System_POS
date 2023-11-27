/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DatabaseOperations;
import com.mysql.cj.util.StringUtils;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Adithya
 */
public class AddItemController {

    
    public void insert(String brandTbl,String itemId,String item,String category,String price,String noOfItems){
        ResultSet rs =DatabaseOperations.getItems(brandTbl);
        boolean b =false;
        try {
            while(rs.next()){
                
                if(rs.getString("item_name").equals(item)){
                
                    b=true;
                    
                }
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!brandTbl.equals("")&&!itemId.equals("")&&!item.equals("")&&!category.equals("")&&!price.equals("")&&!noOfItems.equals("")){
            if(b==false)
            {
                if(StringUtils.isStrictlyNumeric(price)){
                    DatabaseOperations.insertItem(brandTbl,itemId,item,category,price,noOfItems);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Enter a valied value for price");
                }
                
            }
            else{
            
                JOptionPane.showMessageDialog(null,"Item name already exsists");
                
            }
            
            
        }
        else{
        
            JOptionPane.showMessageDialog(null,"Please check wether input fields are filled");
            
        }
        
        
    }
    
    public static void setNoOfItemsRmv(String brandTbl,String itemName, String noOfItems){
        
        ResultSet rs = DatabaseOperations.getItems(brandTbl);
        if(!(brandTbl==null)&&!(itemName==null)&&!(noOfItems==null)){
        
            if(!(noOfItems.equals(""))){
        
            try {
            while(rs.next()){
                
            if(rs.getString("item_name").equals(itemName)){
                
                if((Integer.parseInt(noOfItems)>0)&&(Integer.parseInt(noOfItems)<= Integer.parseInt(rs.getString("no_of_items")))){
                
                    int valueDb = Integer.parseInt(rs.getString("no_of_items"));
                    int valueEntd = Integer.parseInt(noOfItems);
                    
                    String newValue = Integer.toString(valueDb-valueEntd);
                    
                    DatabaseOperations.setNoOfItems(brandTbl, itemName, newValue);
                    
                }
                else{
                
                    JOptionPane.showMessageDialog(null,"Please check the number of items again!");
                
                }
            }
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AddItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        else{
        
            JOptionPane.showMessageDialog(null, "Please enter a value in No of Items");
            
        }
            
        }
        else{
        
            JOptionPane.showMessageDialog(null,"Please fill required fields!(Brand Name,Item Name,No. of Items)");
            
        }
        
        
        
    
    }
    
    public static void setNoOfItemsAddId(String brandTbl,String itemId, String noOfItems){
        
        String rs = DatabaseOperations.getNoOfItemsId(brandTbl, itemId);
        if(!(brandTbl==null)&&!(itemId==null)&&!(noOfItems==null)){
        
            if(!(noOfItems.equals(""))){
        
                if((Integer.parseInt(noOfItems)>0)){
                
                    int valueDb = Integer.parseInt(rs);
                    int valueEntd = Integer.parseInt(noOfItems);
                    
                    String newValue = Integer.toString(valueDb+valueEntd);
                    
                    DatabaseOperations.setNoOfItemsId(brandTbl, itemId, newValue);
                    System.out.println(brandTbl);
                }
                else{
                
                    JOptionPane.showMessageDialog(null,"Please check the number of items again!");
                
                }
        
        }
        else{
        
            JOptionPane.showMessageDialog(null, "Please enter a value in No of Items");
            
        }
            
        }
        else{
        
            JOptionPane.showMessageDialog(null,"Please fill required fields!(Brand Name,Item Name,No. of Items)");
            
        }
        
        
        
    
    }
    
    public void setNoOfItemsAdd(String brandTbl,String itemName, String noOfItems){
        
        ResultSet rs = DatabaseOperations.getItems(brandTbl);
        if(!(brandTbl==null)&&!(itemName==null)&&!(noOfItems==null)){
        
            if(!(noOfItems.equals(""))){
        
            try {
            while(rs.next()){
                
            if(rs.getString("item_name").equals(itemName)){
                
                if((Integer.parseInt(noOfItems)>0)){
                
                    int valueDb = Integer.parseInt(rs.getString("no_of_items"));
                    int valueEntd = Integer.parseInt(noOfItems);
                    
                    String newValue = Integer.toString(valueDb+valueEntd);
                    
                    DatabaseOperations.setNoOfItems(brandTbl, itemName, newValue);
                    
                }
                else{
                
                    JOptionPane.showMessageDialog(null,"Please check the number of items again!");
                
                }
            }
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(AddItemController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        }
        else{
        
            JOptionPane.showMessageDialog(null, "Please enter a value in No of Items");
            
        }
            
        }
        else{
        
            JOptionPane.showMessageDialog(null,"Please fill required fields!(Brand Name,Item Name,No. of Items)");
            
        }
        
        
        
    
    }
    
    
    public static String getNoOfItems(String brandName,String itemName){
    
        String value=null;
        value =DatabaseOperations.getNoOfItems(brandName, itemName);
    
        if(value!=null){
        return value;
        }
        else{
        return "--";
        }
    }
    public static String getPrice(String brandName,String itemName){
        String value = null;
        value=DatabaseOperations.getPrice(brandName, itemName);
    
        if(value!=null){
        return value;
        }
        else{
        return "--";
        }
        
    }
    public static String getitemId(String brandName,String itemName){
    
        String value = null;
        value=DatabaseOperations.getitemId(brandName, itemName);
    
        if(value!=null){
        return value;
        }
        else{
        return "--";
        }
    }
    
    public void removeItem(String brandTbl,String itemName){
    
        DatabaseOperations.removeItem(brandTbl, itemName);
    
    }
    
    
    public static String[] checkItemId(String itemId) throws SQLException {
        ResultSet rtn = null;
        String [] rs = new String[5];
            rs = DatabaseOperations.checkitemId(itemId);
            
    return rs;
}
}





