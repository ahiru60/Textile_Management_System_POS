/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DatabaseOperations;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adithya
 */
public class UpdateController {

    
    ResultSet rs;
    public ResultSet updateBrandsCmb(){
    
        try{
           
            rs = DatabaseOperations.getBrandTableNames();
            
            }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        return rs;
    }  
    
    public ResultSet updateItemCmb(String brandTbl){
    
        ResultSet rs = DatabaseOperations.getItems(brandTbl);
    
        return rs;                                                                                                                                                                                                                                                                                                                                                                                                                                                          
    }
    //@override
    public ResultSet updateItemCmb(String brandTbl,String category){
    
        ResultSet rs = DatabaseOperations.getItems(brandTbl,category);
    
        return rs;                                                                                                                                                                                                                                                                                                                                                                                                                                                          
    }
    
    public static void updatePurchaseReport(String brand_name,String item_id,String no_of_items,String price,String item_Name) throws SQLException {
    
        DatabaseOperations.insertPurchase(brand_name,item_id, no_of_items, price,item_Name);    
    }
    
    public static DefaultTableModel getPurchaseTable(DefaultTableModel dtm) throws SQLException{
        
        Vector v = new Vector();
        ResultSet rs = DatabaseOperations.getPurchaseReport();
        
        while(rs.next()){
        
            v.add(rs.getString("purchase_id"));
            v.add(rs.getString("item_id"));
            v.add(rs.getString("brand_name"));
            v.add(rs.getString("item_name"));
            v.add(rs.getString("date_time"));
            v.add(rs.getString("no_of_items"));
            v.add(rs.getString("price"));
            
            System.out.println(v.get(0));
            dtm.addRow(v);
            v.clear();
        }
        
        
        
        return dtm;
    }
}
