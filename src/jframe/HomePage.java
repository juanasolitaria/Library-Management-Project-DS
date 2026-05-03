
package jframe;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import java.awt.Color;
import java.awt.BorderLayout;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 *
 * @author juans
 */
public class HomePage extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(HomePage.class.getName());

    /**
     * Creates new form HomePage
     */
    public HomePage() {
        initComponents();
        showPieChart.setLayout(new BorderLayout());
        showPieChart();                                                                         //calling the piechart method
        updateLegend();                                                                       
        jScrollPane2.getVerticalScrollBar().setPreferredSize(new java.awt.Dimension(0, 0));     //to scroll on homepage tables
        jScrollPane3.getVerticalScrollBar().setPreferredSize(new java.awt.Dimension(0, 0));
        
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
    
public void showPieChart(){                                                                    //piechart method (STATIC FOR NOW)
    
    DefaultPieDataset dataset = new DefaultPieDataset();
    dataset.setValue("Available Books", 45.0);
    dataset.setValue("Issued Books", 30.0);
    dataset.setValue("Overdue Books", 15.0);
    dataset.setValue("Lost Books", 10.0);
    
    JFreeChart pieChart = ChartFactory.createRingChart(
        "",           // sin titulo
        dataset,
        false,        // sin leyenda arriba
        true,
        false
    );
    
    RingPlot plot = (RingPlot) pieChart.getPlot();
    
    // fondo limpio
    pieChart.setBackgroundPaint(new Color(255,255,255));
    plot.setBackgroundPaint(new Color(255,255,255));                                
    plot.setOutlinePaint(new Color(255,255,255));
    plot.setShadowPaint(null);
    plot.setSectionDepth(0.50); // grosor del anillo
    
    // colores bonitos como en el ejemplo
    plot.setSectionPaint("Available Books", new Color(72, 199, 173));   // teal
    plot.setSectionPaint("Issued Books", new Color(255, 107, 139));     // rosa
    plot.setSectionPaint("Overdue Books", new Color(149, 117, 205));    // morado
    plot.setSectionPaint("Lost Books", new Color(100, 181, 246));       // azul
    
    // quitar bordes entre secciones
    plot.setBaseSectionOutlinePaint(new Color(204, 204, 204));
    plot.setBaseSectionOutlineStroke(new java.awt.BasicStroke(0f));
    
    // quitar labels del chart
    plot.setLabelGenerator(null);
    
    ChartPanel chartPanel = new ChartPanel(pieChart);
    chartPanel.setBackground(new Color(204, 204, 204));
    showPieChart.removeAll();
    showPieChart.add(chartPanel, BorderLayout.CENTER);
    showPieChart.validate();
}


public void updateLegend(){                                                                 //method for tags (STATIC FOR NOW)
    panelLegend.removeAll();
    panelLegend.setLayout(null);
    
    String[] names = {"Available Books", "Issued Books", "Overdue Books", "Lost Books"};
    Color[] colors = {
        new Color(72, 199, 173),
        new Color(255, 107, 139),
        new Color(149, 117, 205),
        new Color(100, 181, 246)
    };
    double total = 45.0 + 30.0 + 15.0 + 10.0;
    double[] values = {45.0, 30.0, 15.0, 10.0};
    
    for(int i = 0; i < names.length; i++){
        // cuadrito de color
        JLabel colorBox = new JLabel();
        colorBox.setBackground(colors[i]);
        colorBox.setOpaque(true);
        colorBox.setBounds(10, i * 40 + 10, 14, 14);
        
        // texto con porcentaje
        int percent = (int)((values[i] / total) * 100);
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
        jPanel14 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        rSTableMetro2 = new rojerusan.RSTableMetro();
        jLabel27 = new javax.swing.JLabel();
        showPieChart = new javax.swing.JPanel();
        panelLegend = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        rSTableMetro3 = new rojerusan.RSTableMetro();
        jLabel36 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
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

        jLabel11.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 19)); // NOI18N
        jLabel11.setText("Issue Books");
        jPanel8.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 16, -1, -1));

        jLabel28.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-box-15.png"))); // NOI18N
        jPanel8.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(304, 22, -1, -1));

        jPanel3.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 340, 60));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
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

        jLabel25.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("10");
        jPanel16.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

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

        jLabel22.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("10");
        jPanel15.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(54, 156, -1, -1));

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

        jLabel19.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("10");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 148, -1, -1));

        jPanel14.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 80, 440, 280));

        jLabel26.setBackground(new java.awt.Color(51, 51, 51));
        jLabel26.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Issued Books Details");
        jPanel14.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(1075, 385, -1, -1));

        rSTableMetro2.setForeground(new java.awt.Color(255, 255, 255));
        rSTableMetro2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "ABC", "BCS", "CSS"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "User ID", "Name", "Location", "Book Issued"
            }
        ));
        rSTableMetro2.setColorBackgoundHead(new java.awt.Color(181, 181, 181));
        rSTableMetro2.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        rSTableMetro2.setColorBordeHead(new java.awt.Color(181, 181, 181));
        rSTableMetro2.setColorFilasBackgound2(new java.awt.Color(225, 225, 225));
        rSTableMetro2.setColorFilasForeground1(new java.awt.Color(51, 51, 51));
        rSTableMetro2.setColorFilasForeground2(new java.awt.Color(255, 255, 255));
        rSTableMetro2.setColorSelBackgound(new java.awt.Color(102, 102, 102));
        rSTableMetro2.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        rSTableMetro2.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        rSTableMetro2.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 15)); // NOI18N
        rSTableMetro2.setGridColor(new java.awt.Color(204, 204, 204));
        rSTableMetro2.setGrosorBordeFilas(0);
        rSTableMetro2.setGrosorBordeHead(0);
        rSTableMetro2.setRowHeight(35);
        rSTableMetro2.setShowGrid(false);
        jScrollPane2.setViewportView(rSTableMetro2);

        jPanel14.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 424, 944, 194));

        jLabel27.setBackground(new java.awt.Color(51, 51, 51));
        jLabel27.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("Book Details");
        jPanel14.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 670, -1, -1));

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
        jPanel14.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 385, -1, -1));

        rSTableMetro3.setForeground(new java.awt.Color(255, 255, 255));
        rSTableMetro3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", "ABC", "BCS", "CSS"},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Book ID", "Name", "Author", "Quantity"
            }
        ));
        rSTableMetro3.setColorBackgoundHead(new java.awt.Color(181, 181, 181));
        rSTableMetro3.setColorBordeFilas(new java.awt.Color(255, 255, 255));
        rSTableMetro3.setColorBordeHead(new java.awt.Color(181, 181, 181));
        rSTableMetro3.setColorFilasBackgound2(new java.awt.Color(225, 225, 225));
        rSTableMetro3.setColorFilasForeground1(new java.awt.Color(51, 51, 51));
        rSTableMetro3.setColorFilasForeground2(new java.awt.Color(255, 255, 255));
        rSTableMetro3.setColorSelBackgound(new java.awt.Color(102, 102, 102));
        rSTableMetro3.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 12)); // NOI18N
        rSTableMetro3.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 1, 12)); // NOI18N
        rSTableMetro3.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 15)); // NOI18N
        rSTableMetro3.setGridColor(new java.awt.Color(204, 204, 204));
        rSTableMetro3.setGrosorBordeFilas(0);
        rSTableMetro3.setGrosorBordeHead(0);
        rSTableMetro3.setRowHeight(35);
        rSTableMetro3.setShowGrid(false);
        jScrollPane3.setViewportView(rSTableMetro3);

        jPanel14.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 710, 944, 194));

        jLabel36.setBackground(new java.awt.Color(102, 102, 102));
        jLabel36.setFont(new java.awt.Font("Yu Gothic UI Semibold", 0, 19)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(51, 51, 51));
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/aaaaaaaa/icons8-home-24.png"))); // NOI18N
        jLabel36.setText("Dashboard");
        jPanel14.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 32, 176, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
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
    private javax.swing.JPanel panelLegend;
    private rojerusan.RSTableMetro rSTableMetro2;
    private rojerusan.RSTableMetro rSTableMetro3;
    private javax.swing.JPanel showPieChart;
    // End of variables declaration//GEN-END:variables
}
