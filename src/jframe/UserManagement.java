/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

import java.awt.Color;
import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author juans
 */
public class UserManagement extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(UserManagement.class.getName());

    /**
     * Creates new form BooksManagement
     */
    
    //to change color when selecting object from homepage
    Color mouseEnterColor = new Color (221,221,221);
    Color mouseExitColor = new Color (255,255,255);
    
    public UserManagement() {
        initComponents();
        setUserDetails();                                                       //calling method to display details from database in table
        
//date & time     
// mostrar la hora inmediatamente al abrir
jLabel3.setText(new java.text.SimpleDateFormat("EEEE, MMMM dd yyyy  |  HH:mm:ss")
    .format(new java.util.Date()));

// luego el timer para seguir actualizando
javax.swing.Timer timer = new javax.swing.Timer(1000, e -> {
    jLabel3.setText(new java.text.SimpleDateFormat("EEEE, MMMM dd yyyy  |  HH:mm:ss")
        .format(new java.util.Date()));
});
timer.start();
       
    }
    

    

    String name, location;
    int userID;
    DefaultTableModel model;
    
    //This method displays the user details from database in the table (Reads from database)
    public void setUserDetails(){
        try {
            Connection con = DBConnection.getConnection();                      //MySQL connection
            Statement st = con.createStatement();                               //SQL executor
            ResultSet rs = st.executeQuery("select * from user_details");       //MySQL instruction (executes the SELECT and stores ALL results in rs)
            
            // loops row by row — rs.next() moves to the next row from MySQL book_details table
            // when there are no more rows it returns false and the while stops
            while(rs.next()){                                                   
                String userID = rs.getString("user_id");                       //from the current row, grabs each column by its name and stores it in temp variables
                String name = rs.getString("name");
                String location = rs.getString("location");
                
                Object[] obj = {userID, name, location};                        //packages the 4 values into an array in the same order
                model = (DefaultTableModel) table_userdetails.getModel();       //controls the rows
                
                // adds the array as a new row in the visual table
                // each loop iteration adds a new row with the current book
                model.addRow(obj);
            }
            
        } catch (Exception e) {
            // if something fails (DB off, table doesn't exist, etc)
            // prints the error in console instead of crashing the program
            e.printStackTrace();
        }
}
    
    
    //add user method
    public boolean addUser(){
        Boolean isAdded = false;
        userID = Integer.parseInt(txt_userId.getText());                        //bc get.Text returns a String we have to convert it to Integer bc variable bookId is integer type
        name = txt_Name.getText();                                              //returns a normal string name
        location = txt_location.getText();
        
        
        try {                                                                   //query to insert in database
            Connection con = DBConnection.getConnection();
            String sql = "insert into user_details values(?,?,?)";              //mysql instruction
            PreparedStatement pst = con.prepareStatement(sql);                  //prepared instruction for security
            
            pst.setInt(1, userID);                                              //setting values for placeholders
            pst.setString(2, name);
            pst.setString(3, location);
            
            int rowCount = pst.executeUpdate();                                 //pst will return how many rows were affected (if 1 row was affected, then rowCount = 1)
            if (rowCount > 0) {
                isAdded = true;
            }
            else{
                isAdded = false;
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        
        return isAdded;
    }
    
    //this method clears the table so the data inserted (new book) wont duplicate each time we add a new book.
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) table_userdetails.getModel();
        model.setRowCount(0);
    }
    
    
    //Update method (to update user details)
    public boolean updateUser(){
        Boolean isUpdated = false;
        userID = Integer.parseInt(txt_userId.getText());                        //bc get.Text returns a String we have to convert it to Integer bc variable bookId is integer type
        name = txt_Name.getText();                                              //returns a normal string name
        location = txt_location.getText();

        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "update user_details set name = ?, location = ? where user_id = ?";  //MySQL query instruction
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, name);
            pst.setString(2, location);
            pst.setInt(3, userID);

           
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                isUpdated = true;          
            } else{
                isUpdated = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isUpdated;
    }
    
    

        //method to delete user
    public boolean delete(){
        boolean isDeleted = false;
        userID = Integer.parseInt(txt_userId.getText());
        
        try {
        Connection con = DBConnection.getConnection();
        String sql = "delete from user_details where user_id = ?";              //mysq instruction
        PreparedStatement pst = con.prepareStatement(sql);
        
        pst.setInt(1, userID);                                                  //places the userID value in the placeholder (?), so the SQL instruction is executed at that ID value (and deletes it)
        
        int rowCount = pst.executeUpdate();                                     
        if (rowCount > 0) {                                                     //if user_id was found, pst.executeUpdate will execute in 1 row, then rowCount = 1 (true)
            isDeleted = true;
        } else{
            isDeleted = false;
        }
        

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isDeleted;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txt_userId = new app.bolivia.swing.JCTextField();
        txt_Name = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        button_update = new javax.swing.JButton();
        button_add = new javax.swing.JButton();
        button_delete = new javax.swing.JButton();
        txt_location = new app.bolivia.swing.JCTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        button_exit1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jCTextField1 = new app.bolivia.swing.JCTextField();
        jLabel37 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_userdetails = new rojerusan.RSTableMetro();
        jPanel16 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_userId.setBackground(new java.awt.Color(255, 255, 255));
        txt_userId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        txt_userId.setForeground(new java.awt.Color(102, 102, 102));
        txt_userId.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 18)); // NOI18N
        txt_userId.setPhColor(new java.awt.Color(113, 113, 113));
        txt_userId.setPlaceholder("Enter ID...");
        jPanel1.add(txt_userId, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 156, 340, -1));

        txt_Name.setBackground(new java.awt.Color(255, 255, 255));
        txt_Name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        txt_Name.setForeground(new java.awt.Color(102, 102, 102));
        txt_Name.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 18)); // NOI18N
        txt_Name.setPhColor(new java.awt.Color(113, 113, 113));
        txt_Name.setPlaceholder("Enter Name...");
        jPanel1.add(txt_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 272, 340, -1));

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("User ID");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 108, 130, 40));

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Location");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 338, 130, 40));

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Name");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 224, 130, 40));

        button_update.setBackground(new java.awt.Color(130, 217, 199));
        button_update.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        button_update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-edit-pencil-15 (1).png"))); // NOI18N
        button_update.setText("UPDATE");
        button_update.addActionListener(this::button_updateActionPerformed);
        jPanel1.add(button_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 646, 342, 44));

        button_add.setBackground(new java.awt.Color(146, 202, 246));
        button_add.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        button_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-add-15 (1).png"))); // NOI18N
        button_add.setText("ADD");
        button_add.setToolTipText("");
        button_add.addActionListener(this::button_addActionPerformed);
        jPanel1.add(button_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 580, 342, 44));

        button_delete.setBackground(new java.awt.Color(255, 183, 160));
        button_delete.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        button_delete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-trash-can-15 (1).png"))); // NOI18N
        button_delete.setText("DELETE");
        button_delete.addActionListener(this::button_deleteActionPerformed);
        jPanel1.add(button_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 710, 342, 44));

        txt_location.setBackground(new java.awt.Color(255, 255, 255));
        txt_location.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        txt_location.setForeground(new java.awt.Color(102, 102, 102));
        txt_location.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 18)); // NOI18N
        txt_location.setPhColor(new java.awt.Color(113, 113, 113));
        txt_location.setPlaceholder("Enter Location...");
        jPanel1.add(txt_location, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 386, 340, -1));

        jPanel15.setBackground(new java.awt.Color(102, 102, 102));
        jPanel15.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 50, 74, 4));

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("User Information");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 10, 194, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 156, 444, 788));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
        });
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-log-out-24.png"))); // NOI18N
        jLabel17.setText(" Log out");
        jPanel13.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 12, -1, -1));

        jPanel3.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 903, 340, 60));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 19)); // NOI18N
        jLabel18.setText("Defaulter List");
        jPanel12.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 16, -1, -1));

        jLabel32.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-list-view-15.png"))); // NOI18N
        jPanel12.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 22, -1, -1));

        jPanel3.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 340, 60));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 19)); // NOI18N
        jLabel14.setText("View Issued Books ");
        jPanel11.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, -1, -1));

        jLabel31.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-love-book-15.png"))); // NOI18N
        jPanel11.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(302, 24, -1, -1));

        jPanel3.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 420, 340, 60));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 19)); // NOI18N
        jLabel19.setText("Records");
        jPanel10.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, -1, -1));

        jLabel30.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-records-15.png"))); // NOI18N
        jPanel10.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(302, 22, -1, -1));

        jPanel3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 340, 60));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 19)); // NOI18N
        jLabel12.setText("Books returned");
        jPanel9.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, -1, -1));

        jLabel29.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-return-purchase-15.png"))); // NOI18N
        jPanel9.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 22, -1, -1));

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 340, 60));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 19)); // NOI18N
        jLabel20.setText("Issue Books");
        jPanel8.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 16, -1, -1));

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-box-15.png"))); // NOI18N
        jPanel8.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 22, -1, -1));

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 340, 60));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel7MouseExited(evt);
            }
        });
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 19)); // NOI18N
        jLabel10.setText("User management");
        jPanel7.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 16, -1, -1));

        jLabel8.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-user-15.png"))); // NOI18N
        jPanel7.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 22, -1, -1));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 340, 60));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel6MouseExited(evt);
            }
        });
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 19)); // NOI18N
        jLabel9.setText("Books Management");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, -1, -1));

        jLabel7.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-books-15.png"))); // NOI18N
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(302, 24, -1, -1));

        jPanel3.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 340, 60));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 19)); // NOI18N
        jLabel6.setText("Welcome, Admin");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 16, -1, -1));

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 60));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel17MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel17MouseExited(evt);
            }
        });
        jPanel17.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel33.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 19)); // NOI18N
        jLabel33.setText("Dashboard");
        jPanel17.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 12, -1, -1));

        jLabel34.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-homepage-15.png"))); // NOI18N
        jPanel17.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(302, 18, -1, -1));

        jPanel3.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 340, 60));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, 964));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1564, 16, 286, 32));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel4.setText("Library Management System");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, -1, -1));

        button_exit1.setBackground(new java.awt.Color(51, 51, 51));
        button_exit1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        button_exit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-power-21.png"))); // NOI18N
        button_exit1.setBorderPainted(false);
        button_exit1.setContentAreaFilled(false);
        button_exit1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_exit1.addActionListener(this::button_exit1ActionPerformed);
        jPanel4.add(button_exit1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1845, 12, 50, 38));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-menu-24 (1).png"))); // NOI18N
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 12, 26, 38));

        jCTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jCTextField1.setForeground(new java.awt.Color(150, 150, 150));
        jCTextField1.setUI(null);
        jCTextField1.setPlaceholder("Search...");
        jCTextField1.addActionListener(this::jCTextField1ActionPerformed);
        jPanel4.add(jCTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 12, 618, -1));

        jLabel37.setBackground(new java.awt.Color(102, 102, 102));
        jLabel37.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 19)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-search-21.png"))); // NOI18N
        jPanel4.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 20, 28, -1));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1905, 60));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_userdetails.setForeground(new java.awt.Color(255, 255, 255));
        table_userdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Name", "Location"
            }
        ));
        table_userdetails.setColorBackgoundHead(new java.awt.Color(181, 181, 181));
        table_userdetails.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        table_userdetails.setColorBordeHead(new java.awt.Color(181, 181, 181));
        table_userdetails.setColorFilasBackgound2(new java.awt.Color(238, 238, 238));
        table_userdetails.setColorFilasForeground1(new java.awt.Color(64, 64, 64));
        table_userdetails.setColorFilasForeground2(new java.awt.Color(64, 64, 64));
        table_userdetails.setColorSelBackgound(new java.awt.Color(255, 186, 118));
        table_userdetails.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        table_userdetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        table_userdetails.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 15)); // NOI18N
        table_userdetails.setGridColor(new java.awt.Color(204, 204, 204));
        table_userdetails.setGrosorBordeFilas(0);
        table_userdetails.setGrosorBordeHead(0);
        table_userdetails.setRowHeight(35);
        table_userdetails.setShowGrid(false);
        table_userdetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_userdetailsMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(table_userdetails);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 81, 928, 684));

        jPanel16.setBackground(new java.awt.Color(102, 102, 102));
        jPanel16.setForeground(new java.awt.Color(102, 102, 102));
        jPanel2.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 50, 74, 4));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("Users List");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 10, 194, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 156, 964, 786));

        jPanel14.setBackground(new java.awt.Color(226, 226, 226));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(79, 79, 79));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-user-50.png"))); // NOI18N
        jLabel5.setText(" User Management");
        jPanel14.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 24, -1, -1));

        getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 1566, 962));

        setSize(new java.awt.Dimension(1905, 1023));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void table_userdetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_userdetailsMouseClicked
        int row = table_userdetails.getSelectedRow();                           //method to show details in the placeholders
        TableModel model = table_userdetails.getModel();
        
        txt_userId.setText(model.getValueAt(row, 0).toString());                //placeholders rewritten (row, column)
        txt_Name.setText(model.getValueAt(row, 1).toString());
        txt_location.setText(model.getValueAt(row, 2).toString());
    }//GEN-LAST:event_table_userdetailsMouseClicked

    private void button_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_addActionPerformed
        if (addUser()== true){
            clearTable();
            setUserDetails();                                                 //this method will take the new information SENT to the database and display it on table
            JOptionPane.showMessageDialog(this, "User Added successfully! ✓", "Success",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "There was an error.", "Error",JOptionPane.ERROR_MESSAGE); 
        }

    }//GEN-LAST:event_button_addActionPerformed

    private void button_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_updateActionPerformed
            if (updateUser()== true){
            clearTable();
            setUserDetails();                                                   //this method will take the new information SENT to the database and display it on table
            JOptionPane.showMessageDialog(this, "User updated successfully! ✓", "Success",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "There was an error.", "Error",JOptionPane.ERROR_MESSAGE); 
        }

    }//GEN-LAST:event_button_updateActionPerformed

    private void button_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_deleteActionPerformed
            if (delete()== true){
            clearTable();                                                       //clears the table and then calls the new information from database
            setUserDetails();                                                   //this method will take the new information SENT to the database and display it on table
            JOptionPane.showMessageDialog(this, "User deleted successfully! ✓", "Success",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "There was an error.", "Error",JOptionPane.ERROR_MESSAGE); 
        }

    }//GEN-LAST:event_button_deleteActionPerformed

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        LoginPage logout = new LoginPage();
        logout.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel13MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        UserManagement usermanagement = new UserManagement();
        usermanagement.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseEntered
        jPanel7.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jPanel7MouseEntered

    private void jPanel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseExited
        jPanel7.setBackground(mouseExitColor);
    }//GEN-LAST:event_jPanel7MouseExited

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        BooksManagement books = new BooksManagement();                          //redirects to book management page
        books.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        jPanel6.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jPanel6MouseEntered

    private void jPanel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseExited
        jPanel6.setBackground(mouseExitColor);
    }//GEN-LAST:event_jPanel6MouseExited

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
        HomePage homepage = new HomePage();
        homepage.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel17MouseClicked

    private void jPanel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseEntered
        jPanel17.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jPanel17MouseEntered

    private void jPanel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseExited
        jPanel17.setBackground(mouseExitColor);
    }//GEN-LAST:event_jPanel17MouseExited

    private void button_exit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_exit1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_button_exit1ActionPerformed

    private void jCTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCTextField1ActionPerformed
        // TODO add your handling code here:                                    //ignore
    }//GEN-LAST:event_jCTextField1ActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new UserManagement().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_add;
    private javax.swing.JButton button_delete;
    private javax.swing.JButton button_exit1;
    private javax.swing.JButton button_update;
    private app.bolivia.swing.JCTextField jCTextField1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane3;
    private rojerusan.RSTableMetro table_userdetails;
    private app.bolivia.swing.JCTextField txt_Name;
    private app.bolivia.swing.JCTextField txt_location;
    private app.bolivia.swing.JCTextField txt_userId;
    // End of variables declaration//GEN-END:variables
}
