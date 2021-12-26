
import javax.swing.JOptionPane;


public class patient {
    private String ID;
    private String pname;
    private int Age;
    private String address;
    private int temp;
    private String bloodpress;
    private String contact;
    private String gender;
    private int weigth;
    public int countcorrectField=0;
    
    // set method
    public void setID(String ID){
        if(ID.isEmpty())
            JOptionPane.showMessageDialog(null, "Patient ID is empty");
        else{
            this.ID=ID;
            countcorrectField++;//1
        }
        
    }
    public void setWeigth(int w)
    {
        if(w<0&&w>400)
          JOptionPane.showMessageDialog(null," weigth num bwe is valid!!");
        else
            this.weigth=w;
        countcorrectField++;
    }
    public void setPname(String pname){
        if(pname.isEmpty())
            JOptionPane.showMessageDialog(null,"patient name is empty ");
        else{
            this.pname=pname;
            this.pname.toUpperCase();
            countcorrectField++;//2
        }
            
    }
    public void setAddress(String p,String d,String r){
        if(p.isEmpty()||d.isEmpty()||r.isEmpty())
            JOptionPane.showMessageDialog(null,"address is empty!! ");
        else{
            this.address=p+" Province - "+d+" Distric - "+r+" Road";
            countcorrectField++;//3
        }        
    }
    public void setAge(int Age){
        if(Age>200||Age<0)
            JOptionPane.showMessageDialog(null,"patient Age is Invalid! ");
        else{
            this.Age=Age;
            countcorrectField++;//4
        }
        
    }
    public void setTemp(int temp){
        if(temp<15||temp>60)
            JOptionPane.showMessageDialog(null,"patient temperature is Invalid ");
        else{
            this.temp=temp;
            countcorrectField++;//5
        }
    }
    public void setBloodpress(String bloodpress){
        if(bloodpress.isEmpty())
            JOptionPane.showMessageDialog(null,"patient bloodpressure is empty ");
        else{
            this.bloodpress=bloodpress;
            countcorrectField++;//6
        }
    }
    public void setContact(String contact){
        if(contact.isEmpty())
            JOptionPane.showMessageDialog(null,"patient contact is empty ");
        else{
            this.contact=contact;
            countcorrectField++;//7
        }
    }
    public void setGender(String gender){
        if(gender.isEmpty())
            JOptionPane.showMessageDialog(null,"patient gender is unselect ");
        else{
            this.gender=gender;
            countcorrectField++;//8
        }
    }
    // get method
    
    
    public String getID()
    {
        return ID;
    }
    public String getPname()
    {
        return pname;
    }
    public String getAddress()
    {
        return address;
    }
    public int getAge()
    {
        return Age;
    }
    public int getTemp()
    {
        return temp;
    }
    public String getBloodpress()
    {
        return bloodpress;
    }
    public String getContact()
    {
        return contact;
    }
    public String getGender()
    {
        return gender;
    }
    public int getWeigth()
    {
        return weigth;
    }
    
    
}
