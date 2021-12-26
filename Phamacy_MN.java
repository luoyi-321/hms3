
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import java.util.Date;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Phamacy_MN extends javax.swing.JFrame {

    /**
     * Creates new form Phamacy_MN
     */
    Connection conn;
      ResultSet rs;
      PreparedStatement pst;
      Statement st;
      off_login off=new off_login();
      String offID=off.ID;
      LocalDate d=LocalDate.now();
      boolean checkAdd; // check addmidicin is true if ture m_add run if false m_add stop
      
      
      /*  String patientID=jTextField9.getText();*/
    public Phamacy_MN() {
        initComponents();
        offID="HMSO-0000";
        conn=javaconnect.ConnerDB();
        jComboBox1.setModel(new DefaultComboBoxModel(medicineName1));
        
     String s=" ----------------------------HMS----------------------------"
             + "\n\n             Pateint Get Medincine             \n"
             + "patient ID:"+jTextField9.getText()+"\n"
             +"Date : "+d+"\n\n";
        jTextArea3.setText(s);
        
    }
    
    
    //------------------------------------------------medicine edit page--------------------------------*
    
    //serch for medicine
    public void serchMedicine()
    {
        medicin m=new medicin();
        
        //m.setMedicineId(jTextField1.getText());
        String medicineID=jTextField2.getText();
        
        
        String sql="select * from medicine where medicineName='"+medicineID+"'";
        
        if (!medicineID.isEmpty())
        {
            try{
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            DefaultTableModel table=(DefaultTableModel)jTable1.getModel();
            if(rs.next()){
                String medicineId=rs.getString(1);
                String medicineName=rs.getString(2);
                String madeDate=rs.getString(3);
                String madeAddress=rs.getString(4);
                String expDate=rs.getString(5);
                String comeIndate=rs.getString(6);
                String quanlity=rs.getString(7);
                String price=rs.getString(8);
                
                String [] a={medicineId,medicineName,madeDate,madeAddress,expDate,comeIndate,quanlity,price};
                
                table.addRow(a);
                
             rs.close();
             pst.close();
            }
        else{
            JOptionPane.showMessageDialog(null,"Incorrect medicine ID ");
            }
          }catch(Exception e){
           JOptionPane.showMessageDialog(null,e); 
         }
        }
        
    }
    
    //add medicine 
     public void addInfo()
     {
         medicin m= new medicin();
         m.setMedicineId(jTextField1.getText());
         String medicineID=m.getMedicineId();
         m.setMedicineName(jTextField2.getText());
         String medicineName=m.getmedicineName();
         m.setmadeDate(jTextField3.getText());
         String madeDate=m.getmadeDate();
         m.setmadeAddress(jTextField4.getText());
         String madeAddress=m.getmadeAddress();
         m.setExpDate(jTextField5.getText());
         String expDate=m.getexpDate();
         m.setComeInDate(jTextField6.getText());
         String comeInDate=m.getcomeInDate();
         m.setQuanlity(Integer.parseInt(jTextField10.getText()));
         String quanlity=m.getQuanlity()+"";
         m.setPrice(Integer.parseInt(jTextField11.getText()));
         String price=m.getPrice()+"";
         int cCorrect=m.countCorrect;
         
     
         String sql="insert into medicine(medicineId,medicineName,madeDate,madeAddress,expDate,comeInDate,quanlity,price) values(?,?,?,?,?,?,?,?)";
         try{
             pst=conn.prepareStatement(sql);
             pst.setString(1,medicineID);
             pst.setString(2,medicineName);
             pst.setString(3,madeDate);
             pst.setString(4,madeAddress);
             pst.setString(5,expDate);
             pst.setString(6,comeInDate);
             pst.setString(7,quanlity);
             pst.setString(8,price);
             if(cCorrect==8)
             {
                 pst.execute();
                 cCorrect=0;
                 checkAdd=true;
                 JOptionPane.showMessageDialog(null,"new medicine was added!!");
             }else{
                 checkAdd=false;
                 cCorrect=0;
                 JOptionPane.showMessageDialog(null,"new medicine wasn't added!!");
             }
             
            
         }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
         }
     }
     
     
     private int getMaddId(int m_addId)
     {
         try{
             String sql="select count(mAddId) as rowcout from MHS.dbo.m_add;";
             st=conn.createStatement();
             rs=st.executeQuery(sql);
             if(rs.next()){
              m_addId=rs.getInt("rowcout");
              m_addId++;   
              rs.close();
              st.close();
             }
             
         }catch(Exception e)
         {
         JOptionPane.showMessageDialog(null,e);
         }
         return m_addId;
     }
     
     // insert to m-add
     public void M_add()
     {
        int m_addId=0;
        m_addId=getMaddId(m_addId);
        System.out.print(getMaddId(m_addId));
        
        int quanlity=Integer.parseInt(jTextField10.getText());
        int price=Integer.parseInt(jTextField11.getText());
        int totalPrice=price*quanlity;
        LocalDate billDate=LocalDate.now();
       String billdate=billDate.toString();
       String medicineId=jTextField1.getText();
     
         String sql="insert into m_add(mAddId,officerID,Quanlity,totalPrice,billDate,medicineId) values(?,?,?,?,?,?)";
         try{
             pst=conn.prepareStatement(sql);
             pst.setInt(1,m_addId);
             pst.setString(2,offID);
             pst.setInt(3,quanlity);
             pst.setInt(4,totalPrice);
             pst.setString(5,billdate);
             pst.setString(6,medicineId);
             if(checkAdd)
             {
                pst.execute(); 
             }
             
            
         }catch(Exception e){
             JOptionPane.showMessageDialog(null,e);
         }
     }
    
    // update medicine
     public void updateInfo()
     {
         String a1=jTextField1.getText();
         String a2=jTextField2.getText();
         String a3=jTextField3.getText();
         String a4=jTextField4.getText();
         String a5=jTextField5.getText();
         String a6=jTextField6.getText();
         String a7=jTextField10.getText();
         String a8=jTextField11.getText();
         try{
             String sql="update medicine\n"+
                     "set medicineId='"+a1+"',medicineName='"+a2+"',madeDate='"+a3+"',madeAddress='"+a4
                     +"',expDate='"+a5+"',comeInDate='"+a6+"',quanlity='"+a7+"',price='"+a8+"'";
             pst=conn.prepareStatement(sql);
             pst.execute();
            JOptionPane.showMessageDialog(null, "patient medicine info was updated !!");
        }catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
        }
         
     }
    
     // delete medicine Info
     
      public void delete()
    {
      String a1=jTextField1.getText();
      String sql="delete medicine where medicineId ='"+a1+"'";
        try{
            
            pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "medicine was Deleted");
        }catch(Exception e){
          JOptionPane.showMessageDialog(null, e);
        }
    }
    
      
    //clear
 public void clear2()
 {
     jTextField1.setText("");
     jTextField2.setText("");
     jTextField3.setText("");
     jTextField4.setText("");
     jTextField5.setText("");
     jTextField6.setText("");
     jTextField10.setText("");
     jTextField11.setText("");
     jTextField1.setEditable(true);
    DefaultTableModel table=(DefaultTableModel)jTable1.getModel();
     table.setRowCount(0);
     
 }
 
 //----------------------------------------------------------  patiant getmedicine page ------------------------------------------

ArrayList <String> medicineId=new ArrayList(); // keeeb medicine id whichh one patient use
 ArrayList <String> sodage =new ArrayList();  // keeb medicine sodage patient use
 ArrayList <String> sd=new ArrayList(); // keep expdate frome name
 ArrayList <String> dm=new ArrayList(); //keep exp Id fome name 
 
 String[] medicineName1= {
        "Amoxcilin 500mg",
        "Ibuprofen 400mg",
        "VitaminC 500mg",
        "bromhexin 4 mg",
        "Salbutamol",
        "Prednisolon 5mg ",
        "Erythromycin 500 mg",
        "Amytas 10mg",
        "Tiffy", 
        "Thiamin 100mg",
        "Mifenimic acid 500 mg",
        "Methricobal500mg ",
        "Neurobion",
        "voltaren cream",
        "Omeprazole 20 mg",
        "Offloxaxin 200 mg",
        "Buscopan 10 mg",
        "Sp amacon",
        "Dompiladon 10 mg",
        "Methronidazole",
        "Sorbitol"
        
    };
    String[] medicineName2= {
        "Amoxcilin 250 mg",
        "Ibuprofen 200 mg",
        "VitaminC 250 mg",
        "bromhexin 2mg",
        "Salbutamol 1 mg",
        "Prednisolon 2.5 mg ",
        "Erythromycin 250 mg",
        "Amytas 5 mg",
        "Tiffy",
        "Thiamin 50 mg",
        "Mifenimic acid 250 mg",
        "Methricobal 250 mg ",
        "Neurobion",
        "voltaren cream",
        "Omeprazole 10 mg",
        "Offloxaxin 100 mg",
        "Buscopan 5 mg",
        "Sp amacon",
        "Dompiladon 5 mg",
        "Methronidazole",
        "Sorbitol"
    };
//clear
 public void clear1()
    {
        jTextField9.setText("");
        jTextArea2.setText("");
        
        jTextArea3.setText("");
        jTextField8.setText("");
        
        
        
    }
 // keep ---------------medicine Id patient use
 public void getMedicineUsed()
 {
     medicineId.add(selectBeforeMedicineId(sd));
     sodage.add(jTextField8.getText());
     System.out.println("medicinId use:"+medicineId.toString());
     System.out.println(sodage.toString());
 }
 
//-----------------select exdate before of medicine Id
 private String selectBeforeMedicineId(ArrayList <String> exp)  
 {
     getMedicineId() ;
     String id="";
     
     SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
     try{
         int j=0;
         String bfDate=exp.get(0);
         String cpDate;  //
         for(int i=1;i<=exp.size()-1;++i)
        {
            cpDate=exp.get(i); // keep value from expDate
            Date compairDate=format.parse(cpDate); // set exp before Date type
            Date beforeDate=format.parse(bfDate);
            /*System.out.println(bfDate);
            System.out.println(cpDate);*/
            if(!(beforeDate.before(compairDate)))
            {
                bfDate=cpDate;
                j=i;
            }
            //System.out.println(i);
        }
         //System.out.println(j); // set medicine Id for
         id=dm.get(j);
     }catch(Exception e){}
     
     return id;
 }
 
 
 

// ------------------------get medicineId and expDate from database
 //compair expDate compair(expDate) min  expDate[]{comparivalue1,compairvalue2....n} check min get medicin[i]
 // to string medicinId 
 public void getMedicineId() 
 {
     String mName=jComboBox1.getSelectedItem().toString();
     String sql="select * from medicine where medicineName='"+mName+"'";
     try{
     pst=conn.prepareStatement(sql);
     rs=pst.executeQuery();
     for(int i=0;i<=rs.getRow();i++)
     {
         if(rs.next())
     {
        String medicineId=rs.getString(1);
        String expDate=rs.getString(5);
        dm.add(medicineId);
        sd.add(expDate);
     }
     else{
     JOptionPane.showMessageDialog(null, "incorrect medicine name!!");
     }
     }
     /*System.out.println(dm.toString());
     System.out.println(sd.toString());*/
     }catch(Exception e){
     }
 }
 
 //get diagnosisID 
String diagnosisID;

 // add textarear keeb in to medicin Id [] sodage[]   
 private void getTextArea()
 {
     jTextArea3.setText(jTextArea3.getText()+jComboBox1.getSelectedItem().toString()+"   "+selectBeforeMedicineId(sd)
               +"\t\t"+jTextField8.getText()+"\n");
 }
 
 // medicine[i]=sodage[i] for{int i=0;i<=}
 
 
 // STRING  GETID=  selectmedicine();
 
 
    
// serch 
    public void serchPatientDiagnosisInfo()
    {
        String patientID=jTextField9.getText();
        String sql="select * from diagnosis where patientID='"+patientID+"'";
        
        try{
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                diagnosisID=rs.getString(1);
                jTextArea2.setText(jTextArea2.getText()+" diagnosis ID :\t\t"+diagnosisID+"\n");
                jTextArea2.setText(jTextArea2.getText()+" Pateint ID :\t\t"+rs.getString(2)+"\n");
                jTextArea2.setText(jTextArea2.getText()+" Pateint medicine&sodage:\t"+rs.getString(5)+"\n");
                jTextArea2.setText(jTextArea2.getText()+" status :\t\t"+rs.getString(6)+"\n");
                jTextArea2.setText(jTextArea2.getText()+" diagnosis Date :\t\t"+rs.getString(7)+"\n");
                
             rs.close();
             pst.close();
            }
            else{
            JOptionPane.showMessageDialog(null,"Incorrect Pattient ID!!");
            }
        }catch(Exception e){
           JOptionPane.showMessageDialog(null,e); 
        }
    }
    public int gettotalPrice()
    {
        int total=0;
        for (int i=0;i<=sodage.size();i++)
        {
            total=+Integer.parseInt(sodage.get(i));
        }
        return total;
    }
   
    //add p_bill
    public void addP_bill()
    {
        String sql="insert into P_bill(P_billID,patintID,diagnosisId,officerID,totalPrice,billDate) values(?,?,?,?,?,?)";
        try{
            pst=conn.prepareStatement(sql);
            String billdate=d+"";
            String patientID=jTextField8.getText();
            String total=gettotalPrice()+"";
            String P_billID=patientID.substring(patientID.length()-4+d.getDayOfMonth());
            for(int i=0;i<=medicineId.size()-1;i++)
            {
              pst.setString(1,P_billID);
              pst.setString(2,patientID);
              pst.setString(3,diagnosisID);
              pst.setString(4,offID);
              pst.setString(5,total);
              pst.setString(6,billdate);
              if(!jTextField8.getText().isEmpty())
              {
                  pst.execute();
              }else{
                  JOptionPane.showMessageDialog(null, " please enter Info frist!!");
              }
            }
            
        }catch(Exception e){
        }
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 278, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Medicine ID");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Medicine Name");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Made Date");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Made Address");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Exp Date");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("ComeIn Date");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Print");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("Serch");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Medicine detail");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Medicine ID", "Medicine Name", "made Date", "made Address", "exp date", "comein Date", "quanlity", "price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable1);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Quanlity");

        jTextField10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Price");

        jButton9.setText("reset");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField3)
                    .addComponent(jTextField6)
                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(67, 67, 67)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTextField10)
                    .addComponent(jTextField11))
                .addGap(47, 47, 47)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(197, 197, 197))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(jButton2)
                        .addGap(64, 64, 64)
                        .addComponent(jButton3)
                        .addGap(60, 60, 60)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(608, 608, 608))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel7)
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton9)))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton3)
                            .addComponent(jButton4)))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Medicine process", jPanel1);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jLabel8.setText("Medicine Name");

        jLabel9.setText("Sorage");

        jButton6.setText("Add");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel10.setText("patien ID");

        jButton7.setText("Serch");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Save");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jCheckBox1.setText("chiildern Mode");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField8)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(0, 246, Short.MAX_VALUE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jCheckBox1)
                                        .addGap(36, 36, 36))
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 408, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(123, 123, 123)
                .addComponent(jLabel10)
                .addGap(58, 58, 58)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8)
                                .addGap(0, 43, Short.MAX_VALUE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(jButton7))
                        .addGap(150, 150, 150)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jCheckBox1))
                        .addGap(23, 23, 23)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jButton6)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pateint get medicine", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // serch for patient get medicine
        serchPatientDiagnosisInfo();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       //addd to textarea
       /*getMedicineId();*/
       getMedicineUsed();
       dm.clear();
       sd.clear();
       getTextArea();
       
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //serch medicine
        serchMedicine();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jTextField2KeyPressed

    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //add method 
        addInfo();
        M_add();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //delete medicine method
        int warnn =JOptionPane.showConfirmDialog(null," Are you sure to delete this medicine info!! ","Warning !!",JOptionPane.YES_NO_OPTION);
        if(warnn==0){
            delete();
            clear2();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel RecordTable =(DefaultTableModel)jTable1.getModel();
        int SelectedRow = jTable1.getSelectedRow();
        jTextField1.setText(RecordTable.getValueAt(SelectedRow, 0).toString());
        jTextField2.setText(RecordTable.getValueAt(SelectedRow, 1).toString());
        jTextField3.setText(RecordTable.getValueAt(SelectedRow, 2).toString());
        jTextField4.setText(RecordTable.getValueAt(SelectedRow, 3).toString());
        jTextField5.setText(RecordTable.getValueAt(SelectedRow, 4).toString());
        jTextField6.setText(RecordTable.getValueAt(SelectedRow, 5).toString());
        jTextField10.setText(RecordTable.getValueAt(SelectedRow, 6).toString());
        jTextField11.setText(RecordTable.getValueAt(SelectedRow, 7).toString());
        
        jTextField1.setEditable(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        // update medicine
        int warnn =JOptionPane.showConfirmDialog(null," Are you sure update this medicine info!! ","Warning !!",JOptionPane.YES_NO_OPTION);
        if(warnn==0){
            updateInfo();
            clear2();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        // add p-bill
        addP_bill();
        clear1();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // reset
        clear2();
        jTextField1.setEditable(true);
        
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox1.isSelected())
        jComboBox1.setModel(new DefaultComboBoxModel(medicineName2));
        else
            jComboBox1.setModel(new DefaultComboBoxModel(medicineName1));
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Phamacy_MN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Phamacy_MN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Phamacy_MN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Phamacy_MN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Phamacy_MN().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
