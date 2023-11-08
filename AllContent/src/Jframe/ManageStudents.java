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

/**
 *
 * @author sumit
 */
public class ManageStudents extends javax.swing.JFrame {

    /**
     * Creates new form ManageBooks
     */
    
    String studentName,course,branch;
    int studentId;
    DefaultTableModel model;
    
    
    public ManageStudents() {
        initComponents();
        setStudentDetailsToTable();
    }
    
    //to set the book details into the table
    public void setStudentDetailsToTable()
    {
        try
        {
         /* Class.forName("com.mysql.cj.jdbc.Driver");
          con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/library_ms","root","Sumit@8221");*/
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from student_details");
            
            while(rs.next())
            {
                String StudentId = rs.getString("student_id");
                String studentName = rs.getString("name");
                String course = rs.getString("course");
                String branch = rs.getString("branch");
                
                Object[] obj = {StudentId,studentName,course,branch};
                model = (DefaultTableModel)tbl_StudentDetails.getModel();
                model.addRow(obj);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //To add student_details to  table
    public boolean addStudent(){
        boolean isAdded = false;
        studentId = Integer.parseInt( txt_studentId.getText());
        studentName = txt_StudentName.getText();
        course = combo_CourseName.getSelectedItem().toString();
        branch = combo_branch.getSelectedItem().toString();
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "insert into student_details values(?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1,studentId);
            pst.setString(2,studentName);
            pst.setString(3,course);
            pst.setString(4,branch);
            
            int rowCount = pst.executeUpdate();
            if (rowCount > 0){
                isAdded = true;
            }else{
                isAdded = false;
            }
        }catch(Exception e){
           e.printStackTrace();
        }
        return isAdded;
    }
    
    //method to clear table
    public void clearTable()
    {
        DefaultTableModel model = (DefaultTableModel) tbl_StudentDetails.getModel();
        model.setRowCount(0);
    }
    
    
   //To update students deatails.
    public boolean updateStudent()
    {
        boolean isUpdated = false;
        studentId = Integer.parseInt( txt_studentId.getText());
        studentName = txt_StudentName.getText();
        course = combo_CourseName.getSelectedItem().toString();
        branch = combo_branch.getSelectedItem().toString();
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "update student_details set name = ?,course = ?,branch = ? where student_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,studentName);
            pst.setString(2,course);
            pst.setString(3,branch);
            pst.setInt(4,studentId);
            
            int rowCount = pst.executeUpdate();
            if (rowCount > 0){
                isUpdated = true;
            }else{
                isUpdated = false;
            }
        }catch(Exception e){
           e.printStackTrace();
        }
        return isUpdated;
    }
    
    //method to delete book detail
    public boolean deleteStudent()
    {
        boolean isDeleted = false;
        studentId = Integer.parseInt(txt_studentId.getText());
        
        try{
            Connection con = DBConnection.getConnection();
            String sql = "delete from student_details where student_id = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, studentId);
            
            int rowCount = pst.executeUpdate();
            if(rowCount > 0){
                isDeleted = true;
            }else{
                isDeleted = false;
            }
        }
        catch(Exception e){
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_studentId = new app.bolivia.swing.JCTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_StudentName = new app.bolivia.swing.JCTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rSMaterialButtonCircle2 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new necesario.RSMaterialButtonCircle();
        rSMaterialButtonCircle4 = new necesario.RSMaterialButtonCircle();
        combo_branch = new javax.swing.JComboBox<>();
        combo_CourseName = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_StudentDetails = new rojeru_san.complementos.RSTableMetro();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 203, 185));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Rewind_48px.png"))); // NOI18N
        jLabel1.setText("Back");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel1);

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, -1));

        txt_studentId.setBackground(new java.awt.Color(255, 204, 204));
        txt_studentId.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 0, 0)));
        txt_studentId.setPlaceholder("Enter Student Id....");
        jPanel1.add(txt_studentId, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 280, 40));

        jLabel9.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Enter Student Id :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, 20));

        jLabel6.setBackground(new java.awt.Color(204, 0, 0));
        jLabel6.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 0, 0));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Contact_26px.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 50, 50));

        jLabel7.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Moleskine_26px.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 250, 50, 50));

        jLabel10.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Enter Student Name :");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, 20));

        txt_StudentName.setBackground(new java.awt.Color(255, 204, 204));
        txt_StudentName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(204, 0, 0)));
        txt_StudentName.setPlaceholder("Enter Student Name ......");
        jPanel1.add(txt_StudentName, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 280, 40));

        jLabel8.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Collaborator_Male_26px.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 50, 50));

        jLabel11.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Select Course :");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, 20));

        jLabel12.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Unit_26px.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 50, 50));

        jLabel13.setFont(new java.awt.Font("Sitka Text", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Select Branch :");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, -1, 20));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle2.setText("delete");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 580, 120, 50));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle3.setText("ADD");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 580, 120, 50));

        rSMaterialButtonCircle4.setBackground(new java.awt.Color(255, 51, 51));
        rSMaterialButtonCircle4.setText("update");
        rSMaterialButtonCircle4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle4ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 580, 120, 50));

        combo_branch.setBackground(new java.awt.Color(255, 204, 204));
        combo_branch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IT", "CS", "ELECTRONIC", "PLAIN", " " }));
        combo_branch.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 0, 0)));
        jPanel1.add(combo_branch, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 460, 280, 40));

        combo_CourseName.setBackground(new java.awt.Color(255, 204, 204));
        combo_CourseName.setForeground(new java.awt.Color(102, 102, 102));
        combo_CourseName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bsc", "Msc", "BCA", "Bsc-IT", "PHD", "BBA", "BBM", "BCom", " " }));
        combo_CourseName.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 0, 0)));
        jPanel1.add(combo_CourseName, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 362, 280, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 700));

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_StudentDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student Id", "Name", "Course", "Branch"
            }
        ));
        tbl_StudentDetails.setColorBackgoundHead(new java.awt.Color(0, 204, 204));
        tbl_StudentDetails.setColorBordeFilas(new java.awt.Color(0, 102, 102));
        tbl_StudentDetails.setColorBordeHead(new java.awt.Color(255, 0, 51));
        tbl_StudentDetails.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tbl_StudentDetails.setColorSelBackgound(new java.awt.Color(255, 204, 153));
        tbl_StudentDetails.setRowHeight(25);
        tbl_StudentDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_StudentDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_StudentDetails);
        if (tbl_StudentDetails.getColumnModel().getColumnCount() > 0) {
            tbl_StudentDetails.getColumnModel().getColumn(0).setResizable(false);
            tbl_StudentDetails.getColumnModel().getColumn(3).setResizable(false);
        }

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 580, 180));

        jPanel5.setBackground(new java.awt.Color(255, 51, 51));
        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, 300, 3));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 51, 51));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/AddNewBookIcons/AddNewBookIcons/icons8_Student_Male_100px.png"))); // NOI18N
        jLabel3.setText("  Manage Students");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 50, 310, -1));

        jPanel4.setBackground(new java.awt.Color(0, 204, 204));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("X");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel2);

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 0, 60, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 0, 800, 700));

        setSize(new java.awt.Dimension(1200, 700));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        HomePage home = new HomePage();
        home.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed

       if(deleteStudent() == true){
            JOptionPane.showMessageDialog(this, "Student Deleted");
            clearTable();
            setStudentDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Student Deletion Failed");
        }

//        if(validateLogin())
//        {
//            login();
//        }
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        if(addStudent() == true){
            JOptionPane.showMessageDialog(this, "Student Added");
            clearTable();
            setStudentDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Student Addition Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void rSMaterialButtonCircle4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle4ActionPerformed
         if(updateStudent() == true){
            JOptionPane.showMessageDialog(this, "Student Updated");
            clearTable();
            setStudentDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Student Updation Failed");
        }
    }//GEN-LAST:event_rSMaterialButtonCircle4ActionPerformed

    private void tbl_StudentDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_StudentDetailsMouseClicked
        int rowNo = tbl_StudentDetails.getSelectedRow();
        TableModel model = tbl_StudentDetails.getModel();
        
        txt_studentId.setText(model.getValueAt(rowNo,0).toString());
        txt_StudentName.setText(model.getValueAt(rowNo,1).toString());
        combo_CourseName.setSelectedItem(model.getValueAt(rowNo, 2).toString());
        combo_branch.setSelectedItem(model.getValueAt(rowNo, 3).toString());
      
    }//GEN-LAST:event_tbl_StudentDetailsMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

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
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageStudents.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageStudents().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combo_CourseName;
    private javax.swing.JComboBox<String> combo_branch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private necesario.RSMaterialButtonCircle rSMaterialButtonCircle4;
    private rojeru_san.complementos.RSTableMetro tbl_StudentDetails;
    private app.bolivia.swing.JCTextField txt_StudentName;
    private app.bolivia.swing.JCTextField txt_studentId;
    // End of variables declaration//GEN-END:variables
}
