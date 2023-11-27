/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.Home;
import View.Purchase;
import javax.swing.JOptionPane;

/**
 *
 * @author Adithya
 */
public class CalculateController {
    
    public static boolean calculate(String recived, String amount){
        
        boolean auth = false;
    String balance = "";
        if(Double.parseDouble(recived)>=Double.parseDouble(amount)){
        
            balance = Double.toString(Double.parseDouble(recived)-Double.parseDouble(amount));
            
            JOptionPane.showMessageDialog(null,"Change is Rs:"+balance+"/=");
            auth = true;
            
        }
        else{
        
            JOptionPane.showMessageDialog(null,"Recived amount is not sufficent to purchase");
            
        }
    
        return auth;
        
    }
    
}
