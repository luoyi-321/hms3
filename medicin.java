
import javax.swing.JOptionPane;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class medicin {
     private String medicineId;
     private String medicineName;
     private String madedate;
     private String madeAddress;
     private String Expdate;
     private String comeInDate;
     private int quanlity;
     private int price;
     int countCorrect=0;
     
     
     
    //--------------- pattern----------------\\
     
     
     private boolean medicineIDPattern(String s)
     {
         boolean a=true;
         a=Pattern.matches("m-\\d{5}", s);
         return a;
         
     }
     
     private boolean Datepattern(String s)
     {
         boolean a=true;
         a=Pattern.matches("[0-2][0-1]\\d{2}-[0-1]\\d-[0-3]\\d", s);
         return a;
     }
     
     //-------------------- set method --------------\\
     public void setMedicineId(String id)
     {
        if(!(id.isEmpty())&&medicineIDPattern(id))
        {
            this.medicineId=id;
            countCorrect++;
        }else{
            JOptionPane.showMessageDialog(null," medicine ID is valid !!");
        }
        
     }
     public void setMedicineName(String name)
     {
        if(!name.isEmpty())
        {
            this.medicineName=name;
            countCorrect++;
        }else
        {JOptionPane.showMessageDialog(null,"Name is Empty  !! ");
        }
     }
     public void setmadeDate(String date)
     {
        if(!(date.isEmpty())&&Datepattern(date))
        {
            this.madedate=date;
            countCorrect++;
        }else{
            JOptionPane.showMessageDialog(null," medicine madeDate pattern is valid !!");
        }
     }
     public void setmadeAddress(String address)
     {
        if(!address.isEmpty())
        {
            this.madeAddress=address;
            countCorrect++;
        }else
        {JOptionPane.showMessageDialog(null,"Made address is Empty  !! ");
        }
     }
     public void setExpDate(String exp)
     {
        if(!(exp.isEmpty())&&Datepattern(exp))
        {
            this.Expdate=exp;
            countCorrect++;
        }else{
            JOptionPane.showMessageDialog(null," medicine Exp date pattern is valid !!");
        }
     }
     public void setComeInDate(String comeIn)
     {
        if(!(comeIn.isEmpty())&&Datepattern(comeIn))
        {
            this.comeInDate=comeIn;
            countCorrect++;
            
        }else{
            JOptionPane.showMessageDialog(null," medicine come in date pattern is valid !!");
        }
     }
     public void setQuanlity(int quanlity)
     {
        if(!(quanlity<0))
        {
            this.quanlity=quanlity;
            countCorrect++;
        }else
        {JOptionPane.showMessageDialog(null,"Made quanlity is valid !! ");
        }
     }
     public void setPrice(int price)
     {
        if(!(price<0))
        {
            this.price=price;
            countCorrect++;
        }else
        {JOptionPane.showMessageDialog(null,"Made priec number is valid !! ");
        }
     }
     
     
     //--------------------------* get ---------
     public String getMedicineId()
     {
         return medicineId;
     }
     public String getmedicineName()
     {
         return medicineName;
     }
     public String getmadeDate()
     {
         return madedate;
     }
     public String getmadeAddress()
     {
         return madeAddress;
     }
     public String getexpDate()
     {
         return Expdate;
     }
     public String getcomeInDate()
     {
         return comeInDate;
     }
     public int getQuanlity()
     {
         return quanlity;
     }
     public int getPrice()
     {
         return price;
     }
     
     
}
