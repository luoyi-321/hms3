
import javax.swing.JOptionPane;
import java.util.Date;


public class officer {
   
    private String ID;
    private String name;
    private String dateOfBirth;
    private String role;
    private String passW;
    private String sec_Q;
    private String anKey;
    public int countcorrectField=0;
    Date d=new Date();
    int Y=d.getYear()+1900;
    
    //get method
    public String getId(){
        return ID;
    }
    public String getName(){
        return name;
    }
    public String getDateOfBirth(){
        return dateOfBirth;
    }
    public String getRole(){
        return role;
    }
    public String getPassW(){
        return passW;
    }
    public String getSec_Q(){
        return sec_Q;
    }
    public String getAnswerKey(){
        return anKey;
    }
    public void setId(String ID){
        this.ID=ID;
        countcorrectField++;
    }
    // set method
    public void setName(String name){
        if(name.isEmpty())
        {
            JOptionPane.showMessageDialog(null, " Name box is Empty! ");
        }else{
            this.name=name;
            this.name.toUpperCase();
            countcorrectField++;
        }
    }
    public void setDateOfBirth(int year,int month,int date){
        
        String dateOfBirth=year+"-"+month+"-"+date;
        if(dateOfBirth.isEmpty())// age validation
        {
            JOptionPane.showMessageDialog(null, "date of birth is Empty! ");
        }else{
            if(date<0||date>31){
            JOptionPane.showMessageDialog(null, "Invalid date Number! ");
            }
            else if(month<0||month>12){
            JOptionPane.showMessageDialog(null, "Invalid month Number! ");
            }
            else if(year<0||year>Y){
            JOptionPane.showMessageDialog(null, "Invalid Year Number! ");
            }else{
            this.dateOfBirth=dateOfBirth;
            countcorrectField++;
            }
        }
       
    }
    public void setRole(String role){
        if(role.isEmpty())
        {
            JOptionPane.showMessageDialog(null, " role box is Empty! ");
        }else{
            this.role=role;
            countcorrectField++;
            
        }
    }
    public void setPassW(String passW)
    {
        if(passW.isEmpty())
        {
            JOptionPane.showMessageDialog(null, " password box is Empty! ");
        }else if(passW.length()<8)
        {
            JOptionPane.showMessageDialog(null, " password were so short! ");
        }
        else{
            this.passW=passW;
            countcorrectField++;
        }
    }
    
    public void setSec_Q(String sec_Q){
        if(sec_Q.isEmpty())
        {
            JOptionPane.showMessageDialog(null, " security Question box is Empty! ");
        }else{
            this.sec_Q=sec_Q;
            countcorrectField++;
        }
    }
    public void setAnKey(String anKey){
        if(anKey.isEmpty())
        {
            JOptionPane.showMessageDialog(null, " ansWer Key box is Empty! ");
        }else{
            this.anKey=anKey;
            countcorrectField++;
        }
    }
    

}
