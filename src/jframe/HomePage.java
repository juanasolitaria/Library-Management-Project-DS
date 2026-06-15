 
package jframe;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import java.awt.Color;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juans
 */
public class HomePage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HomePage.class.getName());

    /**
     * Creates new form HomePage
     */
    
    //to change color when selecting object from homepage
    Color mouseEnterColor = new Color (221,221,221);
    Color mouseExitColor = new Color (255,255,255);
    DefaultTableModel model;
    double valAvailable, valIssued, valOverdue;
    
    
    public HomePage() {
        initComponents();
        showPieChart.setLayout(new BorderLayout());
        showPieChart();                                                                         //calling the piechart method
        updateLegend();                                                                       
        jScrollPane2.getVerticalScrollBar().setPreferredSize(new java.awt.Dimension(7, 7));     //to edit scroll bar on tables
        jScrollPane3.getVerticalScrollBar().setPreferredSize(new java.awt.Dimension(7, 7));
        setUserDetails();
        setBookDetails();
        setDataToCards();
        jLabel6.setText("Welcome, " + Session.loggedInUser);
        
        //date & time
        
// mostrar la hora inmediatamente al abrir
jLabel3.setText(new java.text.SimpleDateFormat("EEEE, MMMM dd yyyy  |  HH:mm:ss")
    .format(new java.util.Date()));

// luego el timer para seguir actualizando
Timer timer = new javax.swing.Timer(1000, e -> {
    jLabel3.setText(new java.text.SimpleDateFormat("EEEE, MMMM dd yyyy  |  HH:mm:ss")
        .format(new java.util.Date()));
});
timer.start();
        
    }
    
public void showPieChart(){
    
    double returnedBooks = 0;
    double pendingBooks = 0;
    double overdueBooks = 0;
    
    try {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs;
        
        // Returned
        rs = st.executeQuery("SELECT COUNT(*) AS total FROM issue_book_details WHERE status = 'returned'");
        if (rs.next()) {
            returnedBooks = rs.getDouble("total");
        }
        
        // Pending (not overdue)
        rs = st.executeQuery("SELECT COUNT(*) AS total FROM issue_book_details WHERE status = 'pending' AND due_date >= CURDATE()");
        if (rs.next()) {
            pendingBooks = rs.getDouble("total");
        }
        
        // Overdue
        rs = st.executeQuery("SELECT COUNT(*) AS total FROM issue_book_details WHERE status = 'pending' AND due_date < CURDATE()");
        if (rs.next()) {
            overdueBooks = rs.getDouble("total");
        }
        
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    this.valAvailable = returnedBooks;
    this.valIssued = pendingBooks;
    this.valOverdue = overdueBooks;
    
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue("Returned Books", returnedBooks);
    dataset.setValue("Pending Books", pendingBooks);
    dataset.setValue("Overdue Books", overdueBooks);
    
    JFreeChart pieChart = ChartFactory.createRingChart(
        "",
        dataset,
        false,
        true,
        false
    );
    
    RingPlot plot = (RingPlot) pieChart.getPlot();
    
    pieChart.setBackgroundPaint(new Color(255,255,255));
    plot.setBackgroundPaint(new Color(255,255,255));                                
    plot.setOutlinePaint(new Color(255,255,255));
    plot.setShadowPaint(null);
    plot.setSectionDepth(0.50);
    
    plot.setSectionPaint("Returned Books", new Color(72, 199, 173));
    plot.setSectionPaint("Pending Books", new Color(255, 107, 139));
    plot.setSectionPaint("Overdue Books", new Color(149, 117, 205));
    
    plot.setBaseSectionOutlinePaint(new Color(204, 204, 204));
    plot.setBaseSectionOutlineStroke(new java.awt.BasicStroke(0f));
    
    plot.setLabelGenerator(null);
    
    ChartPanel chartPanel = new ChartPanel(pieChart);
    chartPanel.setBackground(new Color(204, 204, 204));
    showPieChart.removeAll();
    showPieChart.add(chartPanel, BorderLayout.CENTER);
    showPieChart.validate();
}


public void updateLegend(){
    panelLegend.removeAll();
    panelLegend.setLayout(null);
    
    String[] names = {"Returned Books", "Pending Books", "Overdue Books"};
    Color[] colors = {
        new Color(72, 199, 173),
        new Color(255, 107, 139),
        new Color(149, 117, 205)
    };
    double total = valAvailable + valIssued + valOverdue;
    double[] values = {valAvailable, valIssued, valOverdue};
    
    for(int i = 0; i < names.length; i++){
        JLabel colorBox = new JLabel();
        colorBox.setBackground(colors[i]);
        colorBox.setOpaque(true);
        colorBox.setBounds(10, i * 40 + 10, 14, 14);
        
        int percent = total == 0 ? 0 : (int)((values[i] / total) * 100);
        JLabel text = new JLabel(names[i] + "   " + percent + "%");
        text.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12));
        text.setBounds(30, i * 40 + 5, 200, 24);
        
        panelLegend.add(colorBox);
        panelLegend.add(text);
    }
    
    panelLegend.revalidate();
    panelLegend.repaint();
}
    
    
    //barchart design:
    
    /** 
public void showBarChart(){
    
    DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
    barDataset.addValue(20, "Books", "January");
    barDataset.addValue(35, "Books", "February");
    barDataset.addValue(15, "Books", "March");
    barDataset.addValue(40, "Books", "April");
    
    JFreeChart barChart = ChartFactory.createBarChart(
        "Books Issued Per Month",
        "",
        "",
        barDataset,
        PlotOrientation.VERTICAL,
        false,
        true,
        false
    );
    
    CategoryPlot plot = barChart.getCategoryPlot();
    
    // fondo
    barChart.setBackgroundPaint(new Color(204, 204, 204));
    plot.setBackgroundPaint(new Color(204, 204, 204));
    plot.setOutlineVisible(false);
    
    //sets the month names with the font
    plot.getDomainAxis().setTickLabelFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 11));
    
    // grid sutil
    plot.setRangeGridlinesVisible(true);
    plot.setRangeGridlinePaint(new Color(102, 102, 102));
    plot.setDomainGridlinesVisible(false);
    
    
    //title font
    barChart.getTitle().setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 13));
    barChart.getTitle().setPaint(new Color(51, 51, 51));    
    
    // ejes
    plot.getDomainAxis().setLabelFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 13));
    plot.getDomainAxis().setAxisLineVisible(false);
    plot.getDomainAxis().setTickMarksVisible(false);
    plot.getRangeAxis().setLabelFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 13));
    plot.getRangeAxis().setAxisLineVisible(false);
    plot.getRangeAxis().setTickMarksVisible(false);
    
    //sets the y axis to 10 
    org.jfree.chart.axis.NumberAxis rangeAxis = (org.jfree.chart.axis.NumberAxis) plot.getRangeAxis();
    rangeAxis.setTickUnit(new org.jfree.chart.axis.NumberTickUnit(10));

    // barras
    BarRenderer renderer = (BarRenderer) plot.getRenderer();
    renderer.setMaximumBarWidth(0.10);
    renderer.setBarPainter(new StandardBarPainter());
    renderer.setSeriesPaint(0, new Color(149, 117, 205));
    renderer.setShadowVisible(false);
    renderer.setItemMargin(0.1);
    
    // quitar leyenda y titulo
    barChart.removeLegend();
    
    ChartPanel chartPanel = new ChartPanel(barChart);
    chartPanel.setBackground(new Color(204, 204, 204));
    panelBarChart.removeAll();
    panelBarChart.add(chartPanel, BorderLayout.CENTER);
    panelBarChart.validate();
}
*/
 
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
                model = (DefaultTableModel) table_users.getModel();       //controls the rows
                
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
    
    //This method displays the book details from database in the table (Reads from database)    
        public void setBookDetails(){
        try {
            Connection con = DBConnection.getConnection();                      //MySQL connection
            Statement st = con.createStatement();                               //SQL executor
            ResultSet rs = st.executeQuery("select * from book_details");       //MySQL instruction (executes the SELECT and stores ALL results in rs)
            
            // loops row by row — rs.next() moves to the next row from MySQL book_details table
            // when there are no more rows it returns false and the while stops
            while(rs.next()){                                                   
                String book_id = rs.getString("book_id");                       //from the current row, grabs each column by its name and stores it in temp variables
                String book_name = rs.getString("book_name");
                String author = rs.getString("author");
                int quantity = rs.getInt("quantity");
                
                Object[] obj = {book_id, book_name, author, quantity};          //packages the 4 values into an array in the same order
                model = (DefaultTableModel) table_bookdetails.getModel();       //controls the rows
                
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
        
public void setDataToCards(){
    try {
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs;
        
        // Number of Users
        rs = st.executeQuery("SELECT COUNT(*) AS total FROM user_details");
        if (rs.next()) {
            lbl_numberofusers.setText(rs.getString("total"));
        }
        
        // Number of Books
        rs = st.executeQuery("SELECT COUNT(*) AS total FROM book_details");
        if (rs.next()) {
            lbl_numberofbooks.setText(rs.getString("total"));
        }
        
        // Issued Books (only pending = currently loaned out)
        rs = st.executeQuery("SELECT COUNT(*) AS total FROM issue_book_details WHERE status = 'pending'");
        if (rs.next()) {
            lbl_issuedbooks.setText(rs.getString("total"));
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        button_exit = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jCTextField1 = new app.bolivia.swing.JCTextField();
        jLabel37 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
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
        jPanel14 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lbl_issuedbooks = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lbl_numberofbooks = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbl_numberofusers = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        showPieChart = new javax.swing.JPanel();
        panelLegend = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_bookdetails = new rojerusan.RSTableMetro();
        jLabel36 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_users = new rojerusan.RSTableMetro();
        jPanel18 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1564, 16, 286, 32));

        jLabel4.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel4.setText("Library Management System");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, -1, -1));

        button_exit.setBackground(new java.awt.Color(51, 51, 51));
        button_exit.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 18)); // NOI18N
        button_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-power-21.png"))); // NOI18N
        button_exit.setBorderPainted(false);
        button_exit.setContentAreaFilled(false);
        button_exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_exit.addActionListener(this::button_exitActionPerformed);
        jPanel1.add(button_exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1845, 12, 50, 38));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-menu-24 (1).png"))); // NOI18N
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 12, 26, 38));

        jCTextField1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jCTextField1.setForeground(new java.awt.Color(150, 150, 150));
        jCTextField1.setPlaceholder("Search...");
        jCTextField1.addActionListener(this::jCTextField1ActionPerformed);
        jPanel1.add(jCTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 12, 618, -1));

        jLabel37.setBackground(new java.awt.Color(102, 102, 102));
        jLabel37.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 19)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-search-21.png"))); // NOI18N
        jPanel1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 20, 28, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1905, 60));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel13MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel13MouseExited(evt);
            }
        });
        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-log-out-24.png"))); // NOI18N
        jLabel16.setText(" Log out");
        jPanel13.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 12, -1, -1));

        jPanel3.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 903, 340, 60));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel10MouseExited(evt);
            }
        });
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

        jPanel14.setBackground(new java.awt.Color(245, 245, 245));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(130, 217, 199));
        jPanel16.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 10, 0, new java.awt.Color(255, 255, 255)));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel23.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-graph-24 (1).png"))); // NOI18N
        jPanel16.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 52, 42));

        jLabel24.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Issued Books");
        jPanel16.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 96, -1, -1));

        lbl_issuedbooks.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        lbl_issuedbooks.setForeground(new java.awt.Color(255, 255, 255));
        lbl_issuedbooks.setText("10");
        jPanel16.add(lbl_issuedbooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jPanel14.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 80, 440, 280));

        jPanel15.setBackground(new java.awt.Color(146, 202, 246));
        jPanel15.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 10, 0, new java.awt.Color(255, 255, 255)));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-books-24 (3).png"))); // NOI18N
        jPanel15.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 26, 52, 42));

        jLabel21.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Number of Books ");
        jPanel15.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 92, -1, -1));

        lbl_numberofbooks.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        lbl_numberofbooks.setForeground(new java.awt.Color(255, 255, 255));
        lbl_numberofbooks.setText("10");
        jPanel15.add(lbl_numberofbooks, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 156, -1, -1));

        jPanel14.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(552, 80, 440, 280));

        jPanel2.setBackground(new java.awt.Color(255, 183, 160));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 10, 0, new java.awt.Color(255, 255, 255)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/24.png"))); // NOI18N
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(362, 26, 42, 40));

        jLabel18.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Number of Users ");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(46, 84, -1, -1));

        lbl_numberofusers.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        lbl_numberofusers.setForeground(new java.awt.Color(255, 255, 255));
        lbl_numberofusers.setText("10");
        jPanel2.add(lbl_numberofusers, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 148, -1, -1));

        jPanel14.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 80, 440, 280));

        jLabel26.setBackground(new java.awt.Color(51, 51, 51));
        jLabel26.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Issued Books Details");
        jPanel14.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(1075, 385, -1, -1));

        jLabel27.setBackground(new java.awt.Color(51, 51, 51));
        jLabel27.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("Book Details");
        jPanel14.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 655, -1, -1));

        showPieChart.setBackground(new java.awt.Color(255, 255, 255));
        showPieChart.setLayout(new java.awt.BorderLayout());
        jPanel14.add(showPieChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 424, 440, 330));

        panelLegend.setBackground(new java.awt.Color(255, 255, 255));
        panelLegend.setName(""); // NOI18N
        jPanel14.add(panelLegend, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 746, 440, 176));

        jLabel35.setBackground(new java.awt.Color(51, 51, 51));
        jLabel35.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("User Details");
        jPanel14.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 390, -1, -1));

        table_bookdetails.setForeground(new java.awt.Color(255, 255, 255));
        table_bookdetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Book ID", "Name", "Author", "Quantity"
            }
        ));
        table_bookdetails.setColorBackgoundHead(new java.awt.Color(181, 181, 181));
        table_bookdetails.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        table_bookdetails.setColorBordeHead(new java.awt.Color(181, 181, 181));
        table_bookdetails.setColorFilasBackgound2(new java.awt.Color(225, 225, 225));
        table_bookdetails.setColorFilasForeground1(new java.awt.Color(51, 51, 51));
        table_bookdetails.setColorFilasForeground2(new java.awt.Color(51, 51, 51));
        table_bookdetails.setColorSelBackgound(new java.awt.Color(154, 202, 241));
        table_bookdetails.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        table_bookdetails.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        table_bookdetails.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 15)); // NOI18N
        table_bookdetails.setGridColor(new java.awt.Color(204, 204, 204));
        table_bookdetails.setGrosorBordeFilas(0);
        table_bookdetails.setGrosorBordeHead(0);
        table_bookdetails.setRowHeight(35);
        table_bookdetails.setShowGrid(false);
        jScrollPane3.setViewportView(table_bookdetails);

        jPanel14.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 698, 944, 206));

        jLabel36.setBackground(new java.awt.Color(102, 102, 102));
        jLabel36.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 19)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-home-24.png"))); // NOI18N
        jLabel36.setText("Dashboard");
        jPanel14.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 32, 176, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table_users.setForeground(new java.awt.Color(255, 255, 255));
        table_users.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Name", "Location"
            }
        ));
        table_users.setColorBackgoundHead(new java.awt.Color(181, 181, 181));
        table_users.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        table_users.setColorBordeHead(new java.awt.Color(181, 181, 181));
        table_users.setColorFilasBackgound2(new java.awt.Color(225, 225, 225));
        table_users.setColorFilasForeground1(new java.awt.Color(51, 51, 51));
        table_users.setColorFilasForeground2(new java.awt.Color(51, 51, 51));
        table_users.setColorSelBackgound(new java.awt.Color(154, 202, 241));
        table_users.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        table_users.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        table_users.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 15)); // NOI18N
        table_users.setGridColor(new java.awt.Color(204, 204, 204));
        table_users.setGrosorBordeFilas(0);
        table_users.setGrosorBordeHead(0);
        table_users.setRowHeight(35);
        table_users.setShowGrid(false);
        jScrollPane2.setViewportView(table_users);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 54, 944, 200));

        jPanel14.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(42, 380, 984, 544));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 380, 440, 46));

        getContentPane().add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 1566, 964));

        setSize(new java.awt.Dimension(1905, 1023));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_exitActionPerformed
        System.exit(0); 
    }//GEN-LAST:event_button_exitActionPerformed

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        BooksManagement books = new BooksManagement();                          //redirects to book management page
        books.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel6MouseClicked

    private void jCTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCTextField1ActionPerformed
        // TODO add your handling code here:                                    //ignore
    }//GEN-LAST:event_jCTextField1ActionPerformed

    private void jPanel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseClicked
        LoginPage logout = new LoginPage();
        logout.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel13MouseClicked

    private void jPanel17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseEntered
        jPanel17.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jPanel17MouseEntered

    private void jPanel17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseExited
        jPanel17.setBackground(mouseExitColor);
    }//GEN-LAST:event_jPanel17MouseExited

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        jPanel6.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jPanel6MouseEntered

    private void jPanel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseExited
        jPanel6.setBackground(mouseExitColor);
    }//GEN-LAST:event_jPanel6MouseExited

    private void jPanel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseEntered
        jPanel7.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jPanel7MouseEntered

    private void jPanel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseExited
        jPanel7.setBackground(mouseExitColor);
    }//GEN-LAST:event_jPanel7MouseExited

    private void jPanel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel7MouseClicked
        UserManagement usermanagement = new UserManagement();
        usermanagement.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel7MouseClicked

    private void jPanel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel17MouseClicked
        HomePage homepage = new HomePage();
        homepage.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel17MouseClicked

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
        jPanel8.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jPanel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseExited
        jPanel8.setBackground(mouseExitColor);
    }//GEN-LAST:event_jPanel8MouseExited

    private void jPanel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseClicked
        IssueBook IssueBook = new IssueBook();
        IssueBook.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel8MouseClicked

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

    private void jPanel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseClicked
        Records records = new Records();
        records.setVisible(true);
        dispose();
    }//GEN-LAST:event_jPanel10MouseClicked

    private void jPanel10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseEntered
        jPanel10.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jPanel10MouseEntered

    private void jPanel10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel10MouseExited
        jPanel10.setBackground(mouseExitColor);
    }//GEN-LAST:event_jPanel10MouseExited

    private void jPanel13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseEntered
        jPanel13.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jPanel13MouseEntered

    private void jPanel13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel13MouseExited
        jPanel13.setBackground(mouseExitColor);
    }//GEN-LAST:event_jPanel13MouseExited
  
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new HomePage().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_exit;
    private app.bolivia.swing.JCTextField jCTextField1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_issuedbooks;
    private javax.swing.JLabel lbl_numberofbooks;
    private javax.swing.JLabel lbl_numberofusers;
    private javax.swing.JPanel panelLegend;
    private javax.swing.JPanel showPieChart;
    private rojerusan.RSTableMetro table_bookdetails;
    private rojerusan.RSTableMetro table_users;
    // End of variables declaration//GEN-END:variables
}
