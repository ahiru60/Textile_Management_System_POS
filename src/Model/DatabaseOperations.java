/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Adithya
 */
public class DatabaseOperations {
    
    private static String userLoginDB ="jdbc:mysql://localhost:3306/clothmgt_userLogin";
    private static String BrandsDB = "jdbc:mysql://localhost:3306/clothmgt_brands";
    private static String reportsDB = "jdbc:mysql://localhost:3306/clothmgt_reports";
            
    public static ResultSet getLoginSet(String userName){
        // we get list of every user from DB matching to this userName argument
        Statement state;
        ResultSet rs = null;
            
        try{
            String usName = userName;
        
            
            state = new DatabaseConnection().getStatementConnection(userLoginDB);
            
            
           rs = state.executeQuery("SELECT * FROM passmgt where user_name='"+usName+"'");
           String st = rs.getString("user_name");
        
        }
        catch(Exception ex){
        ex.printStackTrace();
        }
    return rs;
    }
    
    public static boolean checkPassword(String userName,String password){
        
        Statement state;
        boolean b = false;
            
        try{
            
        
            state = new DatabaseConnection().getStatementConnection(userLoginDB);
            
           ResultSet rs =state.executeQuery("SELECT * FROM passmgt");
           
           while(rs.next()){
               
           if(rs.getString("password").equals(password)||rs.getString("user_name").equals(userName)){
           
               b = true;
              break;
              
               
           }
           }
           
           //System.out.println(b);
        
        }
        catch(Exception ex){
        ex.printStackTrace();
        }
    return b;
    }
    
    public static void setLoginSet(String userName, String password){
        
        Statement state;
        
              try{
            
        
            state = new DatabaseConnection().getStatementConnection(userLoginDB);
            
           state.execute("INSERT INTO passmgt (user_name,password) VALUES('"+userName+"','"+password+"')");
           
        
        }
        catch(Exception ex){
        ex.printStackTrace();
        }
        
    }
    
    public static void createBrandTable(String brandName){
        
        Statement state;
        ResultSet rs = null;
        
    state = new DatabaseConnection().getStatementConnection(BrandsDB);
   
           try {    
                    state.execute("CREATE TABLE "+brandName+"(" +  	
                    "	itemId INT NOT NULL AUTO_INCREMENT," +
                    "  	item_name VARCHAR(30)," +
                    "  	category VARCHAR(20)," +
                    "   price VARCHAR (20),"+
                    "  	no_of_items VARCHAR (20)," +
                    "  	PRIMARY KEY(itemId));");
                    
                    
                    
                } 
                catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,"Brand name couldn't add to the database");
                    ex.printStackTrace();
                }
        
        
    }
    
    public static void dropBrandTable(String brandName){
        
        Statement state;
        ResultSet rs = null;
        
    state = new DatabaseConnection().getStatementConnection(BrandsDB);
    
           try {    
                    state.execute("DROP TABLE "+brandName+";");
                    
                    
                    
                } 
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
        
        
    }
    
    public static void insertItem(String brandTbl,String itemId,String item,String category,String price,String noOfItems){
        
        Statement state;
        
        
        state = new DatabaseConnection().getStatementConnection(BrandsDB);
                try {
                    state.execute("INSERT INTO "+brandTbl+"(itemId,item_name,category,price,no_of_items) VALUES('"+itemId+"','"+item+"','"+category+"','"+price+"','"+noOfItems+"')");
                } 
                catch (SQLException ex) {
                    
                    ex.printStackTrace();
                    
                }
    
        
        
    }

    public static ResultSet getItems(String brandTbl){
        
        Statement state;
        ResultSet rs = null;
   
            state = new DatabaseConnection().getStatementConnection(BrandsDB);
                try {
                    rs = state.executeQuery("SELECT * FROM "+brandTbl);
                } 
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
        return rs;
    }
    //@override polymophiysm
    public static ResultSet getItems(String brandTbl,String category){
        
        Statement state;
        ResultSet rs = null;
   
            state = new DatabaseConnection().getStatementConnection(BrandsDB);
                try {
                    rs = state.executeQuery("SELECT * FROM "+brandTbl+" WHERE category='"+category+"';");
                } 
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
        return rs;
    }
    
    public static void removeItem(String brandTbl,String itemName){
        
        Statement state;
        
            state = new DatabaseConnection().getStatementConnection(BrandsDB);
                try {
                    state.execute("DELETE FROM "+brandTbl+" WHERE item_name='"+itemName+"' AND itemid='"+DatabaseOperations.getitemId(brandTbl, itemName)+"';");
                } 
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
        
    }
    
    public static void setNoOfItems(String brandName,String itemName,String newValue){
        Statement state;
        state = new DatabaseConnection().getStatementConnection(BrandsDB);
        try {
            
            state.execute("UPDATE "+brandName+" SET no_of_items = '"+newValue+"' WHERE item_name = '"+itemName+"';");
            
        } 
        catch (Exception ex2) {
                    ex2.printStackTrace();
                }
    
    }
    
    public static void setNoOfItemsId(String brandName,String itemId,String newValue){
        Statement state;
        state = new DatabaseConnection().getStatementConnection(BrandsDB);
        try {
            
            state.execute("UPDATE "+brandName+" SET no_of_items = '"+newValue+"' WHERE itemId = '"+itemId+"';");
            
        } 
        catch (Exception ex2) {
                    ex2.printStackTrace();
                }
    System.out.println("done");
    }
    
    public static String getNoOfItems(String brandName,String itemName){
        ResultSet rs;
        Statement state;
        String rtn = "";
        state = new DatabaseConnection().getStatementConnection(BrandsDB);
        
            
        try {
            rs = state.executeQuery("SELECT * FROM "+brandName+" WHERE item_name='"+itemName+"';");
            rs.next();
            rtn = rs.getString("no_of_items");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return rtn;
    }
    
    public static String getNoOfItemsId(String brandName,String itemId){
        ResultSet rs;
        Statement state;
        String rtn = "";
        state = new DatabaseConnection().getStatementConnection(BrandsDB);
        
            
        try {
            rs = state.executeQuery("SELECT * FROM "+brandName+" WHERE itemId='"+itemId+"';");
            rs.next();
            rtn = rs.getString("no_of_items");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return rtn;
    }
    public static String getPrice(String brandName,String itemName){
        ResultSet rs;
        Statement state;
        String rtn = "";
        state = new DatabaseConnection().getStatementConnection(BrandsDB);
        try {
            rs = state.executeQuery("SELECT * FROM "+brandName+" WHERE item_name='"+itemName+"'");
            rs.next();
            rtn = rs.getString("price");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return rtn;
    }
    
    public static String getitemId(String brandName,String itemName){
        ResultSet rs;
        Statement state;
        String rtn = "";
        state = new DatabaseConnection().getStatementConnection(BrandsDB);
        try {
            rs = state.executeQuery("SELECT * FROM "+brandName+" WHERE item_name='"+itemName+"'");
            rs.next();
            rtn = rs.getString("itemId");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return rtn;
    }
    
    public static ResultSet getBrandTableNames(){
        
        Statement state;
        ResultSet rs = null;
   
            state = new DatabaseConnection().getStatementConnection(BrandsDB);
                try {
                    rs = state.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'clothmgt_brands'");
                    
                } 
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
        return rs;
    }

    public static String[] checkitemId(String itemId) throws SQLException {
        ResultSet rs;
        String [] rtn = new String[6];
        rtn[0] = null;
        Statement state;
        int i = 0;
        ResultSet brandNames = getBrandTableNames();
        
          
            
            while(brandNames.next()){
                
              String tableName = brandNames.getString("table_name");
                state = new DatabaseConnection().getStatementConnection(BrandsDB);
        try {
            rs = state.executeQuery("SELECT * FROM "+tableName+" WHERE itemId='"+itemId+"'");
            if(rs!=null){
            while(rs.next()){
                
                if(rs.getString("itemId").equals(itemId)){
                    
                    
                        if(i==0){
                        System.out.println(rs.getString("itemId"));
                        rtn[0] = rs.getString("itemId");
                        System.out.println(rs.getString("item_name"));
                        rtn[1] = rs.getString("item_name");
                        rtn[2] = rs.getString("category");
                        rtn[3] = rs.getString("price");
                        rtn[4] = rs.getString("no_of_items");
                        rtn[5] = tableName;
                        i++;
                        }
                    }
                    
                }
            }
            /*}else{
                        rtn[0] = null;
                        rtn[1] = null;
                        rtn[2] = null;
                        rtn[3] = null;
                        rtn[4] = null;
                        rtn[5] = null;
                
            }*/
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
            
       return rtn;
                
            }
    
    public static void insertPurchase(String brand_name,String item_id,String no_of_items,String price,String item_Name) throws SQLException{
     
        Statement state = new DatabaseConnection().getStatementConnection(reportsDB);
        
        state.execute("INSERT INTO purchase_report (item_id,no_of_items,price,brand_name,item_name) VALUES('"+item_id+"','"+no_of_items+"','"+price+"','"+brand_name+"','"+item_Name+"')");
    }
    
    public static ResultSet getPurchaseReport() throws SQLException{
    
    Statement state = new DatabaseConnection().getStatementConnection(reportsDB);
    return state.executeQuery("SELECT * from purchase_report");
    }
        
    public static ResultSet getDailyPurchaseReport() throws SQLException{
    
    Statement state = new DatabaseConnection().getStatementConnection(reportsDB);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
    LocalDateTime now = LocalDateTime.now();  
   System.out.println(dtf.format(now));  
    return state.executeQuery("SELECT * from purchase_report WHERE date_time LIKE '%"+dtf.format(now)+"%'");
    } 
    
    public static ResultSet getSearchPurchaseReport(String search, String column) throws SQLException{
    
    Statement state = new DatabaseConnection().getStatementConnection(reportsDB);  
    return state.executeQuery("SELECT * FROM purchase_report WHERE "+column+" LIKE '%"+search+"%' or "+column+" = '%"+search+"%'" );
    } 

public static ResultSet getBetweenDatesPurchaseReport(String from, String to) throws SQLException{
    
    Statement state = new DatabaseConnection().getStatementConnection(reportsDB);  
    return state.executeQuery("SELECT * FROM purchase_report WHERE date_time BETWEEN '"+from+"' and '"+to+"'" );
    }
    
     public static void removePurchase(String purchaseId,String item_id) throws SQLException{
     
        Statement state = new DatabaseConnection().getStatementConnection(reportsDB);
        
        state.execute("DELETE FROM  purchase_report WHERE purchase_id = '"+purchaseId+"' AND item_id = '"+item_id+"'");
        
    }   
        

}
    
   
