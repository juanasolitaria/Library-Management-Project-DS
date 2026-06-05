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

public class ReturnBook extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ReturnBook.class.getName());

    /**
     * Creates new form BooksManagement
     */
    
    // to change color when selecting object from the homepage menu (left side bar)
    Color mouseEnterColor = new Color (221,221,221);
    Color mouseExitColor = new Color (255,255,255);
    
    public ReturnBook() {
        initComponents();   
        
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


        // Initialize error labels as invisible             
        lbl_infoError.setVisible(false);
       
    }
    
// method to load Issued book details
    public void getIssueBookDetails() {
        int bookId = Integer.parseInt(txt_bookId.getText());
        int UserId = Integer.parseInt(txt_userId.getText());

        try {
            Connection con = DBConnection.getConnection();
            String sql = ("select * from issue_book_details where book_id=? and user_id=? and status=?");
            
            
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setInt(2, UserId);
            pst.setString(3, "pending");
            
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                txt_issueID.setText(rs.getString("id"));
                txt_bookName.setText(rs.getString("book_name"));
                txt_user.setText(rs.getString("username"));
                txt_issuedate.setText(rs.getString("issue_date"));
                txt_duedate.setText(rs.getString("due_date"));
                lbl_infoError.setVisible(false);

            } else {
                lbl_infoError.setVisible(true);
                txt_issueID.setText("");
                txt_bookName.setText("");
                txt_user.setText("");
                txt_issuedate.setText("");
                txt_duedate.setText("");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


    
        //mehtod to update the count of books
        public void updateBookCount() {
        try {
            Connection con = DBConnection.getConnection();
            int bookId = Integer.parseInt(txt_bookId.getText());
            
            String sql = "update book_details set quantity = quantity +1 where book_id=?";  //query
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                JOptionPane.showMessageDialog(this, "Book Count updated successfully!");
                
            } else {
                JOptionPane.showMessageDialog(this, "Error in updating Book count");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    //method to Return the book
    public boolean returnBook() {
        boolean isReturned = false;
        
        int bookId = Integer.parseInt(txt_bookId.getText());
        int userId = Integer.parseInt(txt_userId.getText());

        try {
            
            Connection con = DBConnection.getConnection();
            String sql = ("update issue_book_details set status=? where book_id=? and user_id=? and status=?");
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, "returned");
            pst.setInt(2, bookId);
            pst.setInt(3, userId);
            pst.setString(4, "pending");
            
            int rowcount = pst.executeUpdate();
            if (rowcount > 0) {
                isReturned = true;
            } else {
                isReturned = false;
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        return isReturned;
    }
        
        


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        button_exit1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jCTextField1 = new app.bolivia.swing.JCTextField();
        jLabel37 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txt_issuedate = new app.bolivia.swing.JCTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_user = new app.bolivia.swing.JCTextField();
        jLabel18 = new javax.swing.JLabel();
        txt_bookName = new app.bolivia.swing.JCTextField();
        jLabel19 = new javax.swing.JLabel();
        txt_issueID = new app.bolivia.swing.JCTextField();
        jLabel36 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txt_duedate = new app.bolivia.swing.JCTextField();
        jLabel21 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        txt_userId = new app.bolivia.swing.JCTextField();
        jLabel44 = new javax.swing.JLabel();
        button_issuebook = new javax.swing.JButton();
        txt_bookId = new app.bolivia.swing.JCTextField();
        button_issuebook1 = new javax.swing.JButton();
        lbl_infoError = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel2.setBackground(new java.awt.Color(226, 226, 226));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_issuedate.setBackground(new java.awt.Color(255, 255, 255));
        txt_issuedate.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        txt_issuedate.setForeground(new java.awt.Color(102, 102, 102));
        txt_issuedate.setFocusable(false);
        txt_issuedate.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 18)); // NOI18N
        txt_issuedate.setPhColor(new java.awt.Color(113, 113, 113));
        txt_issuedate.setSelectedTextColor(new java.awt.Color(255, 102, 102));
        jPanel1.add(txt_issuedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 588, 200, -1));

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Issue Date");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 130, 40));

        txt_user.setBackground(new java.awt.Color(255, 255, 255));
        txt_user.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        txt_user.setForeground(new java.awt.Color(102, 102, 102));
        txt_user.setFocusable(false);
        txt_user.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 18)); // NOI18N
        txt_user.setPhColor(new java.awt.Color(113, 113, 113));
        txt_user.setSelectedTextColor(new java.awt.Color(255, 102, 102));
        jPanel1.add(txt_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 472, 200, -1));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("User");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 424, 130, 40));

        txt_bookName.setBackground(new java.awt.Color(255, 255, 255));
        txt_bookName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        txt_bookName.setForeground(new java.awt.Color(102, 102, 102));
        txt_bookName.setFocusable(false);
        txt_bookName.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 18)); // NOI18N
        txt_bookName.setPhColor(new java.awt.Color(113, 113, 113));
        txt_bookName.setSelectedTextColor(new java.awt.Color(255, 102, 102));
        jPanel1.add(txt_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 358, 200, -1));

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Book Name");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 130, 40));

        txt_issueID.setBackground(new java.awt.Color(255, 255, 255));
        txt_issueID.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        txt_issueID.setForeground(new java.awt.Color(102, 102, 102));
        txt_issueID.setFocusable(false);
        txt_issueID.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 18)); // NOI18N
        txt_issueID.setPhColor(new java.awt.Color(113, 113, 113));
        txt_issueID.setSelectedTextColor(new java.awt.Color(204, 204, 204));
        jPanel1.add(txt_issueID, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 242, 200, -1));

        jLabel36.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Issue ID");
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 194, 130, 40));

        jPanel20.setBackground(new java.awt.Color(245, 245, 245));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Details");
        jPanel20.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 66, 192, 40));

        jPanel15.setBackground(new java.awt.Color(102, 102, 102));
        jPanel15.setForeground(new java.awt.Color(102, 102, 102));
        jPanel20.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(144, 102, 100, 4));

        jLabel5.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(79, 79, 79));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-details-100 (2).png"))); // NOI18N
        jPanel20.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 32, -1, -1));

        jPanel1.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 442, 170));

        txt_duedate.setBackground(new java.awt.Color(255, 255, 255));
        txt_duedate.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        txt_duedate.setForeground(new java.awt.Color(102, 102, 102));
        txt_duedate.setFocusable(false);
        txt_duedate.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 18)); // NOI18N
        txt_duedate.setPhColor(new java.awt.Color(113, 113, 113));
        txt_duedate.setSelectedTextColor(new java.awt.Color(255, 102, 102));
        jPanel1.add(txt_duedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 704, 200, -1));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Due Date");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 656, 130, 40));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 442, 788));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel22.setBackground(new java.awt.Color(245, 245, 245));
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel39.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(102, 102, 102));
        jLabel39.setText("Return Book");
        jPanel22.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 72, 194, 40));

        jPanel24.setBackground(new java.awt.Color(102, 102, 102));
        jPanel24.setForeground(new java.awt.Color(102, 102, 102));
        jPanel22.add(jPanel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(174, 108, 150, 4));

        jLabel35.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(79, 79, 79));
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-return-book-100.png"))); // NOI18N
        jPanel22.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 38, -1, -1));

        jPanel14.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 638, 170));

        jLabel43.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Book ID");
        jLabel43.setName(""); // NOI18N
        jPanel14.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 276, 130, 40));

        txt_userId.setBackground(new java.awt.Color(255, 255, 255));
        txt_userId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        txt_userId.setForeground(new java.awt.Color(102, 102, 102));
        txt_userId.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 18)); // NOI18N
        txt_userId.setName(""); // NOI18N
        txt_userId.setPhColor(new java.awt.Color(113, 113, 113));
        txt_userId.setPlaceholder("Enter user ID...");
        jPanel14.add(txt_userId, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 448, 340, -1));

        jLabel44.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("User ID");
        jLabel44.setName(""); // NOI18N
        jPanel14.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 130, 40));

        button_issuebook.setBackground(new java.awt.Color(146, 202, 246));
        button_issuebook.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        button_issuebook.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/search-21.png"))); // NOI18N
        button_issuebook.setText("FIND DETAILS");
        button_issuebook.setToolTipText("");
        button_issuebook.addActionListener(this::button_issuebookActionPerformed);
        jPanel14.add(button_issuebook, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 519, 342, 44));

        txt_bookId.setBackground(new java.awt.Color(255, 255, 255));
        txt_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(219, 219, 219)));
        txt_bookId.setForeground(new java.awt.Color(102, 102, 102));
        txt_bookId.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 18)); // NOI18N
        txt_bookId.setName(""); // NOI18N
        txt_bookId.setPhColor(new java.awt.Color(113, 113, 113));
        txt_bookId.setPlaceholder("Enter Book ID...");
        txt_bookId.setSelectedTextColor(new java.awt.Color(204, 204, 204));
        jPanel14.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 324, 346, -1));

        button_issuebook1.setBackground(new java.awt.Color(255, 183, 160));
        button_issuebook1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        button_issuebook1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-return-purchase-21.png"))); // NOI18N
        button_issuebook1.setText("RETURN BOOK");
        button_issuebook1.setToolTipText("");
        button_issuebook1.addActionListener(this::button_issuebook1ActionPerformed);
        jPanel14.add(button_issuebook1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 580, 342, 44));

        lbl_infoError.setBackground(new java.awt.Color(255, 204, 102));
        lbl_infoError.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        lbl_infoError.setForeground(new java.awt.Color(255, 153, 51));
        lbl_infoError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-warning-20.png"))); // NOI18N
        lbl_infoError.setText("Information not found");
        jPanel14.add(lbl_infoError, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 643, 226, 40));

        jPanel2.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, 638, 788));

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(79, 79, 79));
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-return-purchase-50.png"))); // NOI18N
        jLabel22.setText(" Return Book");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(56, 37, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 1566, 964));

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

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-log-out-24.png"))); // NOI18N
        jLabel16.setText(" Log out");
        jPanel13.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 12, -1, -1));

        jPanel3.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 903, 340, 60));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 19)); // NOI18N
        jLabel15.setText("Defaulter List");
        jPanel12.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 16, -1, -1));

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

        jLabel13.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 19)); // NOI18N
        jLabel13.setText("Records");
        jPanel10.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, -1, -1));

        jLabel30.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-records-15.png"))); // NOI18N
        jPanel10.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(302, 22, -1, -1));

        jPanel3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 340, 60));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel9MouseExited(evt);
            }
        });
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
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel8MouseExited(evt);
            }
        });
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 19)); // NOI18N
        jLabel11.setText("Issue Books");
        jPanel8.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 16, -1, -1));

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

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 340, 964));

        setSize(new java.awt.Dimension(1905, 1023));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_exit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_exit1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_button_exit1ActionPerformed

    private void jCTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCTextField1ActionPerformed
        // TODO add your handling code here:                                    //ignore
    }//GEN-LAST:event_jCTextField1ActionPerformed

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        LoginPage logout = new LoginPage();
        logout.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel13MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        IssueBook IssueBook = new IssueBook();
        IssueBook.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
        jPanel8.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jPanel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseExited
        jPanel8.setBackground(mouseExitColor);
    }//GEN-LAST:event_jPanel8MouseExited

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

    private void button_issuebookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_issuebookActionPerformed
        
        if (txt_bookId.getText().trim().isEmpty() || txt_userId.getText().trim().isEmpty()){
        JOptionPane.showMessageDialog(this, "Please fill in all required fields.", "Empty Fields.", JOptionPane.WARNING_MESSAGE);
    }else{
            getIssueBookDetails();
        }   
    }//GEN-LAST:event_button_issuebookActionPerformed

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        ReturnBook returnbook = new ReturnBook();
        returnbook.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel9MouseClicked

    private void jPanel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseEntered
        jPanel9.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jPanel9MouseEntered

    private void jPanel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseExited
        jPanel9.setBackground(mouseExitColor);
    }//GEN-LAST:event_jPanel9MouseExited

    private void button_issuebook1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_issuebook1ActionPerformed
        if (returnBook() == true){
            JOptionPane.showMessageDialog(this, "Book returned succesfully! ✓", "Success", JOptionPane.INFORMATION_MESSAGE);
            updateBookCount();
        }else{
            JOptionPane.showMessageDialog(this, "Error.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_button_issuebook1ActionPerformed


    
    
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
        java.awt.EventQueue.invokeLater(() -> new ReturnBook().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_exit1;
    private javax.swing.JButton button_issuebook;
    private javax.swing.JButton button_issuebook1;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
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
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbl_infoError;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_bookName;
    private app.bolivia.swing.JCTextField txt_duedate;
    private app.bolivia.swing.JCTextField txt_issueID;
    private app.bolivia.swing.JCTextField txt_issuedate;
    private app.bolivia.swing.JCTextField txt_user;
    private app.bolivia.swing.JCTextField txt_userId;
    // End of variables declaration//GEN-END:variables
}
