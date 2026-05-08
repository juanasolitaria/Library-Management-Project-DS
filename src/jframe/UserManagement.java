/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jframe;

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
    public UserManagement() {
        initComponents();
        setUserDetails();                                                       //calling method to display details from database in table
    }
    

    

    String name, location;
    int userID;
    DefaultTableModel model;
    
    //This method displays the book details from database in the table (Reads from database)
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
    
    
    //add book method
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
            
            int rowCount = pst.executeUpdate();
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
    
    

        //method to delete books
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
        button_back = new javax.swing.JButton();
        txt_userId = new app.bolivia.swing.JCTextField();
        txt_Name = new app.bolivia.swing.JCTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        button_update = new javax.swing.JButton();
        button_add = new javax.swing.JButton();
        button_delete = new javax.swing.JButton();
        txt_location = new app.bolivia.swing.JCTextField();
        jPanel2 = new javax.swing.JPanel();
        button_exit = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_userdetails = new rojerusan.RSTableMetro();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        button_back.setBackground(new java.awt.Color(229, 229, 229));
        button_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-back-32.png"))); // NOI18N
        button_back.setBorderPainted(false);
        button_back.setContentAreaFilled(false);
        button_back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_back.setFocusPainted(false);
        button_back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_backMouseClicked(evt);
            }
        });
        jPanel1.add(button_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(4, 10, -1, -1));

        txt_userId.setBackground(new java.awt.Color(51, 51, 51));
        txt_userId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txt_userId.setForeground(new java.awt.Color(255, 255, 255));
        txt_userId.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 18)); // NOI18N
        txt_userId.setPhColor(new java.awt.Color(255, 255, 255));
        txt_userId.setPlaceholder("Enter User ID...");
        jPanel1.add(txt_userId, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 220, 340, -1));

        txt_Name.setBackground(new java.awt.Color(51, 51, 51));
        txt_Name.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txt_Name.setForeground(new java.awt.Color(255, 255, 255));
        txt_Name.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 18)); // NOI18N
        txt_Name.setPhColor(new java.awt.Color(255, 255, 255));
        txt_Name.setPlaceholder("Enter Name...");
        jPanel1.add(txt_Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 350, 340, -1));

        jLabel11.setFont(new java.awt.Font("Yu Gothic Light", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("User ID");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 180, 130, 40));

        jLabel13.setFont(new java.awt.Font("Yu Gothic Light", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Location");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 440, 130, 40));

        jLabel16.setFont(new java.awt.Font("Yu Gothic Light", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Name");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 310, 130, 40));

        button_update.setBackground(new java.awt.Color(204, 204, 204));
        button_update.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        button_update.setText("UPDATE");
        button_update.addActionListener(this::button_updateActionPerformed);
        jPanel1.add(button_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 674, 342, 44));

        button_add.setBackground(new java.awt.Color(204, 204, 204));
        button_add.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        button_add.setText("ADD");
        button_add.setToolTipText("");
        button_add.addActionListener(this::button_addActionPerformed);
        jPanel1.add(button_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 608, 342, 44));

        button_delete.setBackground(new java.awt.Color(204, 204, 204));
        button_delete.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        button_delete.setText("DELETE");
        button_delete.addActionListener(this::button_deleteActionPerformed);
        jPanel1.add(button_delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 738, 342, 44));

        txt_location.setBackground(new java.awt.Color(51, 51, 51));
        txt_location.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(255, 255, 255)));
        txt_location.setForeground(new java.awt.Color(255, 255, 255));
        txt_location.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 18)); // NOI18N
        txt_location.setPhColor(new java.awt.Color(255, 255, 255));
        txt_location.setPlaceholder("Enter Address ...");
        jPanel1.add(txt_location, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 480, 340, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 580, 1024));

        jPanel2.setBackground(new java.awt.Color(245, 245, 245));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        button_exit.setBackground(new java.awt.Color(51, 51, 51));
        button_exit.setFont(new java.awt.Font("Yu Gothic UI Light", 1, 20)); // NOI18N
        button_exit.setForeground(new java.awt.Color(51, 51, 51));
        button_exit.setText("X");
        button_exit.setBorderPainted(false);
        button_exit.setContentAreaFilled(false);
        button_exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_exit.setFocusPainted(false);
        button_exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_exitMouseClicked(evt);
            }
        });
        jPanel2.add(button_exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1264, 12, 64, -1));

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

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 145, 1026, 776));

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-user-50.png"))); // NOI18N
        jLabel1.setText(" User Management");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 70, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, -2, 1328, 1028));

        setSize(new java.awt.Dimension(1905, 1023));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_backMouseClicked
        HomePage home = new HomePage();                                         //to go back to homepage
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_button_backMouseClicked

    private void button_exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_exitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_button_exitMouseClicked

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
    private javax.swing.JButton button_back;
    private javax.swing.JButton button_delete;
    private javax.swing.JButton button_exit;
    private javax.swing.JButton button_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private rojerusan.RSTableMetro table_userdetails;
    private app.bolivia.swing.JCTextField txt_Name;
    private app.bolivia.swing.JCTextField txt_location;
    private app.bolivia.swing.JCTextField txt_userId;
    // End of variables declaration//GEN-END:variables
}
