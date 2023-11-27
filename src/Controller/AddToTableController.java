/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.Home;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Adithya
 */
public class AddToTableController {
    
    static ArrayList<String> idList = new ArrayList<>();
    
    @SuppressWarnings("UseOfObsoleteCollectionType")
    public static Vector addToTable(String itemId,String brand,String itemName,String category, String noOfItems,String price,String availNoOfItems ){
        @SuppressWarnings("UseOfObsoleteCollectionType")
        
        
    
                Vector v = new Vector();
       if(Integer.parseInt(noOfItems)<=Integer.parseInt(availNoOfItems)&& Integer.parseInt(noOfItems)>0) {
       
           boolean b =false;
       for( String id:idList){
    
           if(id.equals(itemId)){
               
               b=true;
           
           }
           
    }
       if(b==false){
       

        v.add(itemId);
        v.add(brand);
        v.add(itemName);
        v.add(category);
        v.add(noOfItems);
        v.add(price);
        
        idList.add(itemId);
       }
       else{
            v.clear();
           JOptionPane.showMessageDialog(null,"The item already added");
       
       }
       
       
       }
       else{
           
           JOptionPane.showMessageDialog(null,"Please enter lesser or equal amount of items than available items");
       
       }
      return v;  
    }
    public static void removefromTable(int index){
    
        
        idList.remove(index);
    
    }
}
