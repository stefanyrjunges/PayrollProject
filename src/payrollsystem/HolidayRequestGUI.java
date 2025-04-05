package payrollsystem;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Felipe Paneque
 */
public class HolidayRequestGUI extends javax.swing.JFrame {

    /**
     * Creates new form HolidayRequestGUI
     */
    public HolidayRequestGUI() {
        initComponents();
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
        titleLBL = new javax.swing.JLabel();
        iconLBL = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        returnBTN = new javax.swing.JButton();
        requestBTN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        reasonTF = new javax.swing.JTextArea();
        reasonLBL = new javax.swing.JLabel();
        toWeekTF = new javax.swing.JTextField();
        fromWeekTF = new javax.swing.JTextField();
        toLBL = new javax.swing.JLabel();
        fromLBL = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1920, 1080));
        setPreferredSize(new java.awt.Dimension(1520, 715));
        setSize(new java.awt.Dimension(1520, 715));

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));
        jPanel1.setForeground(new java.awt.Color(255, 153, 51));
        jPanel1.setMinimumSize(new java.awt.Dimension(1920, 1080));

        titleLBL.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        titleLBL.setForeground(new java.awt.Color(255, 255, 255));
        titleLBL.setText("REQUEST HOLIDAYS");

        iconLBL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payrollsystem/PAYROLL_LOGO_E.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(titleLBL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1354, Short.MAX_VALUE)
                .addComponent(iconLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(101, 101, 101))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(iconLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(titleLBL)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(1920, 800));

        returnBTN.setBackground(new java.awt.Color(255, 153, 0));
        returnBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        returnBTN.setForeground(new java.awt.Color(255, 255, 255));
        returnBTN.setText("RETURN TO DASHBOARD");
        returnBTN.setBorder(null);
        returnBTN.setBorderPainted(false);
        returnBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBTNActionPerformed(evt);
            }
        });

        requestBTN.setBackground(new java.awt.Color(0, 255, 102));
        requestBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        requestBTN.setForeground(new java.awt.Color(255, 255, 255));
        requestBTN.setText("REQUEST");
        requestBTN.setBorder(null);
        requestBTN.setBorderPainted(false);
        requestBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestBTNActionPerformed(evt);
            }
        });

        reasonTF.setColumns(20);
        reasonTF.setRows(5);
        jScrollPane1.setViewportView(reasonTF);

        reasonLBL.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        reasonLBL.setForeground(new java.awt.Color(81, 81, 81));
        reasonLBL.setText("REASON FOR HOLIDAY:");

        fromWeekTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fromWeekTFActionPerformed(evt);
            }
        });

        toLBL.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        toLBL.setForeground(new java.awt.Color(81, 81, 81));
        toLBL.setText("TO WEEK");

        fromLBL.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        fromLBL.setForeground(new java.awt.Color(81, 81, 81));
        fromLBL.setText("FROM WEEK");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(returnBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reasonLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(fromLBL)
                        .addGap(28, 28, 28)
                        .addComponent(fromWeekTF, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(toLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(toWeekTF, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addComponent(requestBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fromLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromWeekTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(toLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toWeekTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(reasonLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(requestBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(returnBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(416, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fromWeekTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fromWeekTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fromWeekTFActionPerformed
    private int getEmployeeID(String username) {
        int employeeId = -1; // Default invalid ID
        String sql = "SELECT employee_id FROM employee_logins WHERE username = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                employeeId = rs.getInt("employee_id");
            }
        } catch (SQLException e) {
            System.out.println("error: " + e);
        }
        return employeeId;
    }
    private void requestBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestBTNActionPerformed

        String fromWeekText = fromWeekTF.getText().trim();
        String toWeekText = toWeekTF.getText().trim();
        String reason = reasonTF.getText().trim();  
        
        String username = SessionManager.getInstance().getUser();

        int employeeId = getEmployeeID(username);

        if (employeeId == -1) {
            JOptionPane.showMessageDialog(null, "Error: No valid user session.");
            return;
        }

        if (fromWeekText.isEmpty() || toWeekText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Week fields cannot be empty");
        }

        try {
            int fromWeek = Integer.parseInt(fromWeekText);
            int toWeek = Integer.parseInt(toWeekText);

            if (fromWeek <= 0 || toWeek <= 0 || toWeek < fromWeek) {
                JOptionPane.showMessageDialog(null, "Invalid week range.");
                return;
            }

            if (insertHolidayRequest(employeeId, fromWeek, toWeek, reason)) {
                JOptionPane.showMessageDialog(null, "Your holiday request was submitted successfully!");
                fromWeekTF.setText("");
                toWeekTF.setText("");
                reasonTF.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to submit holiday request.");
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numeric values for weeks.");
        }
        
        
    }//GEN-LAST:event_requestBTNActionPerformed

    private void returnBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBTNActionPerformed
        // TODO add your handling code here:
        EmployeeGUI empDashboard = new EmployeeGUI();
        empDashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_returnBTNActionPerformed
    
    private boolean insertHolidayRequest(int employeeId, int fromWeek, int toWeek, String reason) {
        String sql = "INSERT INTO holiday_requests (employee_id, start_week, end_week, approval_status, employee_reason) VALUES (?, ?, ?, 'Pending', ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, employeeId);
            stmt.setInt(2, fromWeek);
            stmt.setInt(3, toWeek);
            stmt.setString(4, reason);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("error: " + e);
            return false;
        }
    }
    
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
            java.util.logging.Logger.getLogger(HolidayRequestGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HolidayRequestGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HolidayRequestGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HolidayRequestGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HolidayRequestGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fromLBL;
    private javax.swing.JTextField fromWeekTF;
    private javax.swing.JLabel iconLBL;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel reasonLBL;
    private javax.swing.JTextArea reasonTF;
    private javax.swing.JButton requestBTN;
    private javax.swing.JButton returnBTN;
    private javax.swing.JLabel titleLBL;
    private javax.swing.JLabel toLBL;
    private javax.swing.JTextField toWeekTF;
    // End of variables declaration//GEN-END:variables
}
