/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Adithya
 */
import java.awt.*;
import java.awt.print.*;
import javax.swing.table.DefaultTableModel;

public class PrintBill implements Printable
{
    DefaultTableModel dtmCopy;
    float txtcash;
    float total=0;
    float balance;
    
    public void printBill(DefaultTableModel dtm,String txtcash)
    {
        dtmCopy = dtm;
        this.txtcash = Float.valueOf(txtcash);
        PrinterJob printjob = PrinterJob.getPrinterJob();
        printjob.setPrintable(this,getPageFormat(printjob));
        if(printjob.printDialog())
        {
         try
         {
             printjob.print();
            } catch (Exception ex)
            {
                System.out.println("ERROR : "+ex);
            
        }
    }
}
/*CODE TO ALIGN TEXT
       * ----------------------
       * ---------------------------------------
       * --------------------------------------------------------
       * --------------------------------------------------------------------------
       * ------------------------------------------------------------------------------------------------
       * ----------------------------------------------------------------------------------------------------------------------------
       * ----------------------------------------------------------------------------------------------------------------------------------------------
       * ---------------------------------------------------------------------------------------------------------------------------------------------------
       * ---------------------------------------------------------------------------------------------------------------------------------------------------------
       * --------------------------------------------------------------------------------------------------------------------------------------------------------
       ----------------------------------------------------------------------------------------------------------------------------------------------------------*/
   public PageFormat getPageFormat(PrinterJob pj)
{
    
    PageFormat pf = pj.defaultPage();
    Paper paper = pf.getPaper();    

    double middleHeight =8.0;  
    double headerHeight = 2.0;                  
    double footerHeight = 2.0;                  
    double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
    double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
    paper.setSize(width, height);
    paper.setImageableArea(                    
        0,
        10,
        width,            
        height - convert_CM_To_PPI(1)
    );   //define boarder size    after that printBill area width is about 180 points
            
    pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
    pf.setPaper(paper);    

    return pf;
}
    
    protected static double convert_CM_To_PPI(double cm) {            
	        return toPPI(cm * 0.393600787);            
}
 
protected static double toPPI(double inch) {            
	        return inch * 72d;            
}

public int print(Graphics g, PageFormat pageFormat, int page)
{
  if(page==0)
  {
      Graphics2D g2d = (Graphics2D) g;
            g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 
            
              
        try{
            /*Draw Header*/
            int y=20;
            int yShift = 10;
            int headerRectHeight=15;
            int headerRectHeighta=40;
             g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
      //========================================================================================
     //                      ENTER THE TEXT AREA TO PRINT
     //===============================================================================================
              
        
             g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
            //g2d.drawImage(icon.getImage(), 50, 20, 90, 30, rootPane);y+=yShift+30;
            g2d.drawString("-------------------------------------",12,y);y+=yShift;
            g2d.drawString("         DT Moblie        ",12,y);y+=yShift;
            g2d.drawString("   No 00000 Address Line One ",12,y);y+=yShift;
            g2d.drawString("   Address Line 02 SRI LANKA ",12,y);y+=yShift;
            g2d.drawString("   www.facebook.com/CodeGuid ",12,y);y+=yShift;
            g2d.drawString("        +94700000000      ",12,y);y+=yShift;
            g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;

            g2d.drawString(" Item Name                  Price   ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
            
            for(int s=0; s<dtmCopy.getRowCount(); s++)
            {
            g2d.drawString(" "+dtmCopy.getValueAt(s, 2).toString().replace("_", " ")+"                            ",10,y);y+=yShift;
            g2d.drawString("      "+dtmCopy.getValueAt(s, 4).toString()+" * "+Integer.parseInt(dtmCopy.getValueAt(s, 5).toString())/Integer.parseInt(dtmCopy.getValueAt(s, 4).toString()),10,y); g2d.drawString(dtmCopy.getValueAt(s, 5).toString(),160,y);y+=yShift;
            total =+ Integer.parseInt(dtmCopy.getValueAt(s, 5).toString()); 
            }
          
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Total amount:               "+total+"   ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            g2d.drawString(" Cash      :                 "+txtcash+"   ",10,y);y+=yShift;
            g2d.drawString("-------------------------------------",10,y);y+=yShift;
            balance =txtcash-total;
            g2d.drawString(" Balance   :                 "+balance+"   ",10,y);y+=yShift;
  
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("       THANK YOU COME AGAIN            ",10,y);y+=yShift;
            g2d.drawString("*************************************",10,y);y+=yShift;
            g2d.drawString("  SOFTWARE BY: Adithya Karunarathna  ",10,y);y+=yShift;
            g2d.drawString("    Mobile/Whatsapp: +94766227533    ",10,y);y+=yShift; 
            g2d.drawString("     Email: adhirunath@gmail.com     ",10,y);y+=yShift; 
            
            
    return (PAGE_EXISTS);
    } catch (Exception ex)
    {
        System.out.println("ERROR : "+ex);
    }
    return(PAGE_EXISTS);
}
    else
    return(NO_SUCH_PAGE);
    

}
}
