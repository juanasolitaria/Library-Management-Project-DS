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
public class Records extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Records.class.getName());

    /**
     * Creates new form BooksManagement
     */
    
    //to change color when selecting object from homepage
    Color mouseEnterColor = new Color (221,221,221);
    Color mouseExitColor = new Color (255,255,255);
    
    DefaultTableModel model;
    
    public Records() {
        initComponents();
        setIssueBooksToTable();
    
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
            

    button_reset.setVisible(false);
    
    }

    
  // --------------- methods ---------------  
    
    //This method displays all the *issue details* from database in the table (Reads from database)
    public void setIssueBooksToTable(){
        button_reset.setVisible(false);
        try {
            Connection con = DBConnection.getConnection();                      //MySQL connection
            Statement st = con.createStatement();                               //SQL executor
            ResultSet rs = st.executeQuery("select * from issue_book_details"); //MySQL instruction (executes the SELECT and stores ALL results in rs)
            
            // loops row by row — rs.next() moves to the next row from MySQL book_details table
            // when there are no more rows it returns false and the while stops
            while(rs.next()){  
                String IssueId = rs.getString("issue_id");
                String BookName = rs.getString("book_name");                    //from the current row, grabs each column by its name and stores it in temp variables
                String user = rs.getString("username");
                String IssueDate = rs.getString("issue_date");
                String DueDate = rs.getString("due_date");
                String Status = rs.getString("status");
                
                Object[] obj = {IssueId, BookName, user, IssueDate, DueDate, Status};                        //packages the 4 values into an array in the same order
                model = (DefaultTableModel) table_records.getModel();           //controls the rows
                
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
    
    //this method clears the table so the data inserted (issue details) wont duplicate each time we add a new book.
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) table_records.getModel();
        model.setRowCount(0);
    }
    
    // Search method
public void search() {
    java.util.Date issuedate = date_issuedate.getDatoFecha();
    java.util.Date duedate = date_duedate.getDatoFecha();
    
    long l1 = issuedate.getTime();
    long l2 = duedate.getTime();
    
    Date Oissuedate = new Date(l1);
    Date Oduedate = new Date(l2);
            
    try {
        Connection con = DBConnection.getConnection();
        String sql = "select * from issue_book_details where issue_date BETWEEN ? and ?";
        PreparedStatement pst = con.prepareStatement(sql);
        
        pst.setDate(1, Oissuedate);
        pst.setDate(2, Oduedate);

        ResultSet rs = pst.executeQuery();

        boolean found = false;
        while (rs.next()) {
            found = true;
            String IssueId = rs.getString("issue_id");
            String BookName = rs.getString("book_name");
            String user = rs.getString("username");
            String IssueDate = rs.getString("issue_date");
            String DueDate = rs.getString("due_date");
            String Status = rs.getString("status");

            Object[] obj = {IssueId, BookName, user, IssueDate, DueDate, Status};
            model = (DefaultTableModel) table_records.getModel();
            model.addRow(obj); 
        }
        
        if (!found) {
            JOptionPane.showMessageDialog(this, "No records Found!", "", JOptionPane.WARNING_MESSAGE);
        }

    } catch (Exception e) {
        e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        date_duedate = new rojeru_san.componentes.RSDateChooser();
        jLabel38 = new javax.swing.JLabel();
        date_issuedate = new rojeru_san.componentes.RSDateChooser();
        button_search = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_records = new rojerusan.RSTableMetro();
        button_reset = new javax.swing.JButton();
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
        jPanel14 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Dates");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 12, 194, 40));

        jPanel15.setBackground(new java.awt.Color(102, 102, 102));
        jPanel15.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 52, 74, 4));

        jLabel36.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Due Date:");
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(536, 72, 96, 40));

        date_duedate.setColorBackground(new java.awt.Color(141, 141, 141));
        date_duedate.setColorButtonHover(new java.awt.Color(146, 202, 246));
        date_duedate.setColorForeground(new java.awt.Color(141, 141, 141));
        jPanel1.add(date_duedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(644, 74, 308, -1));

        jLabel38.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Issue Date:");
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 72, 130, 40));

        date_issuedate.setColorBackground(new java.awt.Color(141, 141, 141));
        date_issuedate.setColorButtonHover(new java.awt.Color(146, 202, 246));
        date_issuedate.setColorForeground(new java.awt.Color(141, 141, 141));
        jPanel1.add(date_issuedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 74, 308, -1));

        button_search.setBackground(new java.awt.Color(146, 202, 246));
        button_search.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 12)); // NOI18N
        button_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/search-21.png"))); // NOI18N
        button_search.setText("SEARCH");
        button_search.setToolTipText("");
        button_search.addActionListener(this::button_searchActionPerformed);
        jPanel1.add(button_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(1054, 70, 342, 44));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 156, 1460, 132));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 102, 102));
        jLabel22.setText("History");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 10, 194, 40));

        jPanel16.setBackground(new java.awt.Color(102, 102, 102));
        jPanel16.setForeground(new java.awt.Color(102, 102, 102));
        jPanel2.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 50, 74, 4));

        table_records.setForeground(new java.awt.Color(255, 255, 255));
        table_records.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Issue Id", "Book Name", "User", "Issue Date", "Due Date", "Status"
            }
        ));
        table_records.setColorBackgoundHead(new java.awt.Color(181, 181, 181));
        table_records.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        table_records.setColorBordeHead(new java.awt.Color(181, 181, 181));
        table_records.setColorFilasBackgound2(new java.awt.Color(238, 238, 238));
        table_records.setColorFilasForeground1(new java.awt.Color(64, 64, 64));
        table_records.setColorFilasForeground2(new java.awt.Color(64, 64, 64));
        table_records.setColorSelBackgound(new java.awt.Color(255, 186, 118));
        table_records.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        table_records.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        table_records.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 15)); // NOI18N
        table_records.setGridColor(new java.awt.Color(204, 204, 204));
        table_records.setGrosorBordeFilas(0);
        table_records.setGrosorBordeHead(0);
        table_records.setRowHeight(35);
        table_records.setShowGrid(false);
        jScrollPane3.setViewportView(table_records);

        jPanel2.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 74, 1384, 592));

        button_reset.setBackground(new java.awt.Color(51, 51, 51));
        button_reset.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        button_reset.setForeground(new java.awt.Color(255, 255, 255));
        button_reset.setText("<html><font color='#a8cdff'> <u>Reset</u></html>\n");
        button_reset.setActionCommand("<html><font color='#a8c6ff'> <u>Reset</u></html>\n");
        button_reset.setBorderPainted(false);
        button_reset.setContentAreaFilled(false);
        button_reset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_reset.addActionListener(this::button_resetActionPerformed);
        jPanel2.add(button_reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 32, 90, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 308, 1460, 694));

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

        jPanel14.setBackground(new java.awt.Color(226, 226, 226));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(79, 79, 79));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-records-50.png"))); // NOI18N
        jLabel1.setText(" Records");
        jPanel14.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 24, -1, -1));

        getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 1566, 964));

        setSize(new java.awt.Dimension(1905, 1023));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        LoginPage logout = new LoginPage();
        logout.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel13MouseClicked

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        UserManagement usermanagement = new UserManagement();                   //to redirect to usermanagement page
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
        Records books = new Records();                          //redirects to book management page
        books.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        jPanel6.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jPanel6MouseEntered

    private void jPanel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseExited
        jPanel6.setBackground(mouseExitColor);
    }//GEN-LAST:event_jPanel6MouseExited

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

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
        HomePage homepage = new HomePage();
        homepage.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel17MouseClicked

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        IssueBook IssueBook = new IssueBook();                                  //to redirect to issuebook page
        IssueBook.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel8MouseClicked

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
        jPanel8.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jPanel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseExited
        jPanel8.setBackground(mouseExitColor);
    }//GEN-LAST:event_jPanel8MouseExited

    private void jPanel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseClicked
        ReturnBook returnbook = new ReturnBook();
        returnbook.setVisible(true);
        dispose();    }//GEN-LAST:event_jPanel9MouseClicked

    private void jPanel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseEntered
        jPanel9.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jPanel9MouseEntered

    private void jPanel9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel9MouseExited
        jPanel9.setBackground(mouseExitColor);
    }//GEN-LAST:event_jPanel9MouseExited

    private void button_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_searchActionPerformed
        if (date_issuedate.getDatoFecha() == null || date_duedate.getDatoFecha() == null) {
            JOptionPane.showMessageDialog(this, "Please insert dates.", "Empty Fields.", JOptionPane.WARNING_MESSAGE);
        } else {
            clearTable();
            search();
            button_reset.setVisible(true);
        }
    }//GEN-LAST:event_button_searchActionPerformed

    private void button_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_resetActionPerformed
        clearTable();
        setIssueBooksToTable();
        date_issuedate.setDatoFecha(null);
        date_duedate.setDatoFecha(null);
    }//GEN-LAST:event_button_resetActionPerformed
   
    
    
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
        java.awt.EventQueue.invokeLater(() -> new Records().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_exit1;
    private javax.swing.JButton button_reset;
    private javax.swing.JButton button_search;
    private rojeru_san.componentes.RSDateChooser date_duedate;
    private rojeru_san.componentes.RSDateChooser date_issuedate;
    private app.bolivia.swing.JCTextField jCTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
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
    private rojerusan.RSTableMetro table_records;
    // End of variables declaration//GEN-END:variables
}
