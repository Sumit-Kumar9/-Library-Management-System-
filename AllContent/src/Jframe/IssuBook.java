/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Jframe;

import static Jframe.DBConnection.con;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import javax.swing.table.TableModel;
import java.util.Date;
//import java.sql.Date;


/**
 *
 * @author sumit
 */
public class IssuBook extends javax.swing.JFrame {

    /**
     * Creates new form IssuBook
     */
    public IssuBook() {
        initComponents();
    }
    
    
    //to fatch the book details from the databaase and display it to book details panel
    public void getBookDetails(){
        int bookId = Integer.parseInt(txt_bookId.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from book_details where book_id = ?");
            pst.setInt(1, bookId);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                lbl_bookId.setText(rs.getString("book_id"));
                lbl_bookName.setText(rs.getString("book_name"));
                lbl_author.setText(rs.getString("author"));
                lbl_quantity1.setText(rs.getString("quantity"));
            }
            else
            {
                lbl_bookError.setText("Invalid book Id");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //to fatch the Students details from the databaase and display it to Students details panel
    public void getStudentDetails(){
        int studentId = Integer.parseInt(txt_studentId1.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            PreparedStatement pst = con.prepareStatement("select * from student_details where student_id = ?");
            pst.setInt(1, studentId);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                lbl_studentId.setText(rs.getString("student_id"));
                lbl_studentName.setText(rs.getString("name"));
                lbl_course.setText(rs.getString("course"));
                lbl_branch1.setText(rs.getString("branch"));
            }
            else
            {
                lbl_studentError.setText("Invalid Student Id");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //insert issue book details to database
    public boolean issueBook(){
        boolean isIssued = false;
        int bookId = Integer.parseInt(txt_bookId.getText());
        int studentId = Integer.parseInt(txt_studentId1.getText());
        String bookName = lbl_bookName.getText();
        String studentName = lbl_studentName.getText();
        
        Date uIssueDate = date_issueDate.getDatoFecha();
        Date uDueDate = date_dueDate.getDatoFecha();
        
        Long l1 = uIssueDate.getTime();
        long l2 = uDueDate.getTime();
        
        java.sql.Date sIssueDate = new java.sql.Date(l1);
        java.sql.Date sDueDate = new java.sql.Date(l2);
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "insert into issue_book_details(book_id,book_name,student_id,student_name,issu_date,due_date,status)values(?,?,?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setString(2, bookName);
            pst.setInt(3, studentId);
            pst.setString(4, studentName);
            pst.setDate(5, sIssueDate);
            pst.setDate(6, sDueDate);
            pst.setString(7,"pending");
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                isIssued = true;
            }
            else
            {
                isIssued = false;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return isIssued;
    }
    
    //updating book count
    public void updateBookCount(){
        int bookId = Integer.parseInt(txt_bookId.getText());
        try{
            Connection con = DBConnection.getConnection();
            String sql = "update book_details set quantity = quantity - 1 where book_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,bookId);
            
            int rowCount = pst.executeUpdate();
             if(rowCount > 0){
                JOptionPane.showMessageDialog(this,"book count updated");
                int initialCount = Integer.parseInt(lbl_quantity1.getText());
                lbl_quantity1.setText(Integer.toString(initialCount - 1));
            }
            else
            {
                JOptionPane.showMessageDialog(this,"can't update book count");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //checking wather book already allocated or not
    public boolean isAlreadyIssued(){
        boolean isAlreadyIssued = false;
        int bookId = Integer.parseInt(txt_bookId.getText());
        int studentId = Integer.parseInt(txt_studentId1.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "select * from issue_book_details where book_id = ? and student_id = ? and status = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, bookId);
            pst.setInt(2, studentId);
            pst.setString(3, "pending");
            
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                isAlreadyIssued = true;
            }else{
                isAlreadyIssued = false;
            }
        }   
        catch(Exception e){
            e.printStackTrace();
        }
        return isAlreadyIssued;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panal_main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_studentId = new javax.swing.JLabel();
        lbl_studentName = new javax.swing.JLabel();
        lbl_course = new javax.swing.JLabel();
        lbl_studentError = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        lbl_branch1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbl_bookError = new javax.swing.JLabel();
        lbl_bookId = new javax.swing.JLabel();
        lbl_bookName = new javax.swing.JLabel();
        lbl_author = new javax.swing.JLabel();
        lbl_quantity1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txt_bookId = new app.bolivia.swing.JCTextField();
        jLabel16 = new javax.swing.JLabel();
        txt_studentId1 = new app.bolivia.swing.JCTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        date_dueDate = new rojeru_san.componentes.RSDateChooser();
        date_issueDate = new rojeru_san.componentes.RSDateChooser();
        rSMaterialButtonCircle3 = new necesario.RSMaterialButtonCircle();
        jPanel9 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panal_main.setBackground(new java.awt.Color(204, 255, 255));
        panal_main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 203, 185));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 250, 4));

        jLabel4.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("Student Id :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 80, -1));

        jLabel5.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("Student name :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 100, -1));

        jLabel6.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setText("Course :");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 60, -1));

        jLabel7.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(204, 0, 0));
        jLabel7.setText("Branch :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 60, -1));

        lbl_studentId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 200, 40));

        lbl_studentName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_studentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 200, 40));

        lbl_course.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 200, 40));

        lbl_studentError.setForeground(new java.awt.Color(204, 0, 0));
        jPanel1.add(lbl_studentError, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 270, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Student_Registration_100px_2.png"))); // NOI18N
        jLabel3.setText("     Students Details");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 260, 110));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 250, 4));

        lbl_branch1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lbl_branch1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 200, 40));

        panal_main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 0, 350, 700));

        jPanel4.setBackground(new java.awt.Color(255, 204, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(255, 203, 185));
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel8.setText("Back");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel8);

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 60));

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Literature_100px_1.png"))); // NOI18N
        jLabel9.setText("     Book Details");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 260, 110));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 250, 4));

        jLabel10.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 0, 0));
        jLabel10.setText("Book Id :");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 60, -1));

        jLabel11.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(204, 0, 0));
        jLabel11.setText("Book name :");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 90, -1));

        jLabel12.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(204, 0, 0));
        jLabel12.setText("Author :");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 60, -1));

        jLabel13.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 0, 0));
        jLabel13.setText("Quantity :");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 440, 70, -1));

        lbl_bookError.setForeground(new java.awt.Color(204, 0, 0));
        jPanel4.add(lbl_bookError, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 530, 250, 40));

        lbl_bookId.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 200, 40));

        lbl_bookName.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_bookName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 330, 200, 40));

        lbl_author.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_author, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 380, 200, 40));

        lbl_quantity1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(lbl_quantity1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 430, 200, 40));

        panal_main.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 700));

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(251, 51, 51));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Books_52px_1.png"))); // NOI18N
        jLabel2.setText("    Issue Book");
        panal_main.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 100, 260, 110));

        jPanel2.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 4, Short.MAX_VALUE)
        );

        panal_main.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 190, 200, 4));

        jLabel15.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 0, 0));
        jLabel15.setText("Book Id :");
        panal_main.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 260, -1, 20));

        txt_bookId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 0, 0)));
        txt_bookId.setPlaceholder("Enter Book Id....");
        txt_bookId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_bookIdFocusLost(evt);
            }
        });
        panal_main.add(txt_bookId, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 240, 280, 40));

        jLabel16.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(204, 0, 0));
        jLabel16.setText("Due Date :");
        panal_main.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 470, -1, 20));

        txt_studentId1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 0, 0)));
        txt_studentId1.setPlaceholder("Enter Student Id....");
        txt_studentId1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_studentId1FocusLost(evt);
            }
        });
        panal_main.add(txt_studentId1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 310, 280, 40));

        jLabel17.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(204, 0, 0));
        jLabel17.setText("Student Id :");
        panal_main.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 330, -1, 20));

        jLabel18.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(204, 0, 0));
        jLabel18.setText("Issue Date :");
        panal_main.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 400, -1, 20));

        date_dueDate.setColorBackground(new java.awt.Color(204, 0, 0));
        date_dueDate.setColorForeground(new java.awt.Color(0, 0, 0));
        date_dueDate.setFuente(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        date_dueDate.setPlaceholder("Select Due Date");
        panal_main.add(date_dueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 460, 280, -1));

        date_issueDate.setColorBackground(new java.awt.Color(204, 0, 0));
        date_issueDate.setColorForeground(new java.awt.Color(0, 0, 0));
        date_issueDate.setFuente(new java.awt.Font("Segoe UI Emoji", 0, 12)); // NOI18N
        date_issueDate.setPlaceholder("Select Issu Date");
        panal_main.add(date_issueDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 390, 280, -1));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle3.setText("Issue Book");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        panal_main.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 560, 330, 50));

        jPanel9.setBackground(new java.awt.Color(0, 204, 204));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("X");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel19);

        panal_main.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 0, 60, -1));

        getContentPane().add(panal_main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 700));

        setSize(new java.awt.Dimension(1200, 700));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
       if(lbl_quantity1.getText().equals("0")){
           JOptionPane.showMessageDialog(this,"book is not avilable");
       }
       else{
           if(isAlreadyIssued() == false){
           if(issueBook() == true){
            JOptionPane.showMessageDialog(this,"book issued successfully");
            updateBookCount();
            }
              else{
             JOptionPane.showMessageDialog(this,"can't issue the book");
            }
       }
       else{
            JOptionPane.showMessageDialog(this,"this student already has this book");
       }
       }     
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void txt_bookIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_bookIdFocusLost
       if(!txt_bookId.getText().equals("")){
           getBookDetails();
       }   
    }//GEN-LAST:event_txt_bookIdFocusLost

    private void txt_studentId1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_studentId1FocusLost
      if(!txt_studentId1.getText().equals("")){
           getStudentDetails();
       }
    }//GEN-LAST:event_txt_studentId1FocusLost

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
       
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel19MouseClicked

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
            java.util.logging.Logger.getLogger(IssuBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssuBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssuBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssuBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssuBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser date_dueDate;
    private rojeru_san.componentes.RSDateChooser date_issueDate;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbl_author;
    private javax.swing.JLabel lbl_bookError;
    private javax.swing.JLabel lbl_bookId;
    private javax.swing.JLabel lbl_bookName;
    private javax.swing.JLabel lbl_branch1;
    private javax.swing.JLabel lbl_course;
    private javax.swing.JLabel lbl_quantity1;
    private javax.swing.JLabel lbl_studentError;
    private javax.swing.JLabel lbl_studentId;
    private javax.swing.JLabel lbl_studentName;
    private javax.swing.JPanel panal_main;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private app.bolivia.swing.JCTextField txt_bookId;
    private app.bolivia.swing.JCTextField txt_studentId1;
    // End of variables declaration//GEN-END:variables
}
