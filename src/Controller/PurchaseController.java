/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.Home;
import View.Purchase;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Adithya
 */
public class PurchaseController {
    static String amountStat = "";
   public void purchase(){
   
       
       
   } 
   
    public static void openPurchase(boolean b,DefaultTableModel dtm,String amount){
    
        if(b==false){
        amountStat = amount;
        new Purchase().setVisible(true);
        }
        
        else
        JOptionPane.showMessageDialog(null,"Please add items to the table to proceed payment");
    
    }
    
    public static String getAmount(){
    
        
       return amountStat; 
    }
    
    public static boolean auththetiCate(String recived,String amount){
    
        boolean auth = false;
        if(Double.parseDouble(recived)>=Double.parseDouble(amount)){
        
            auth = true;
            
        }
    return auth;
    }
}
