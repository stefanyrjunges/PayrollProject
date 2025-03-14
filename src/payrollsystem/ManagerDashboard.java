/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package payrollsystem;

/**
 *
 * @author tenhe
 */
public class ManagerDashboard extends javax.swing.JFrame {

    private EmployeeDataFetcher dataFetcher = new EmployeeDataFetcher();
    private EmployeeInfo employeeInfo = EmployeeInfo.getInstance();
    
    public ManagerDashboard() {
        initComponents();
        dataFetcher.loadUserInformation("manager_id", "manager_logins", "managers");
        subtitleLBL.setText("Hello, " + employeeInfo.getName());
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
        subtitleLBL = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        reviewBTN = new javax.swing.JButton();
        reportBTN = new javax.swing.JButton();
        manageBTN = new javax.swing.JButton();
        holidaysManagmentBTN = new javax.swing.JButton();
        logOutBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setBackground(new java.awt.Color(12, 48, 128));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        titleLBL.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        titleLBL.setForeground(new java.awt.Color(255, 255, 255));
        titleLBL.setText("MANAGER DASHBOARD");

        iconLBL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payrollsystem/PAYROLL_LOGO_m.png"))); // NOI18N

        subtitleLBL.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        subtitleLBL.setForeground(new java.awt.Color(255, 255, 255));
        subtitleLBL.setText("Hello,");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(subtitleLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iconLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconLBL, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(titleLBL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subtitleLBL)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(1920, 1080));

        reviewBTN.setBackground(new java.awt.Color(33, 118, 206));
        reviewBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        reviewBTN.setForeground(new java.awt.Color(255, 255, 255));
        reviewBTN.setText("manage clock in/clock out");
        reviewBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reviewBTNActionPerformed(evt);
            }
        });

        reportBTN.setBackground(new java.awt.Color(33, 118, 206));
        reportBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        reportBTN.setForeground(new java.awt.Color(255, 255, 255));
        reportBTN.setText("financial report");
        reportBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportBTNActionPerformed(evt);
            }
        });

        manageBTN.setBackground(new java.awt.Color(33, 118, 206));
        manageBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        manageBTN.setForeground(new java.awt.Color(255, 255, 255));
        manageBTN.setText("manage employee");
        manageBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageBTNActionPerformed(evt);
            }
        });

        holidaysManagmentBTN.setBackground(new java.awt.Color(33, 118, 206));
        holidaysManagmentBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        holidaysManagmentBTN.setForeground(new java.awt.Color(255, 255, 255));
        holidaysManagmentBTN.setText("holidays management");
        holidaysManagmentBTN.setPreferredSize(new java.awt.Dimension(127, 26));
        holidaysManagmentBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                holidaysManagmentBTNActionPerformed(evt);
            }
        });

        logOutBTN.setBackground(new java.awt.Color(33, 118, 206));
        logOutBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        logOutBTN.setForeground(new java.awt.Color(255, 255, 255));
        logOutBTN.setText("LOG OUT");
        logOutBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(269, 269, 269)
                        .addComponent(manageBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(reportBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(holidaysManagmentBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(149, 149, 149)
                                .addComponent(reviewBTN))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(652, 652, 652)
                        .addComponent(logOutBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(holidaysManagmentBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(manageBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reviewBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(114, 114, 114)
                .addComponent(reportBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(230, 230, 230)
                .addComponent(logOutBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1430, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reviewBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reviewBTNActionPerformed
        SubmissionApprovalGUI submissionApproval = new SubmissionApprovalGUI();
        submissionApproval.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_reviewBTNActionPerformed

    private void holidaysManagmentBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_holidaysManagmentBTNActionPerformed
        HolidayApprovalGUI holidayApproval = new HolidayApprovalGUI();
        holidayApproval.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_holidaysManagmentBTNActionPerformed

    private void manageBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageBTNActionPerformed
        // TODO add your handling code here:
          ManageEmployee manageFrame = new ManageEmployee();
          manageFrame.setVisible(true);
            this.dispose();
    }//GEN-LAST:event_manageBTNActionPerformed

    private void reportBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportBTNActionPerformed
        FinancialReportGUI financialReport = new FinancialReportGUI();
        financialReport.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_reportBTNActionPerformed

    private void logOutBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutBTNActionPerformed
        SessionManager.getInstance().clearSession();
        LogInGUI login = new LogInGUI();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logOutBTNActionPerformed

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
            java.util.logging.Logger.getLogger(ManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagerDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagerDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton holidaysManagmentBTN;
    private javax.swing.JLabel iconLBL;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton logOutBTN;
    private javax.swing.JButton manageBTN;
    private javax.swing.JButton reportBTN;
    private javax.swing.JButton reviewBTN;
    private javax.swing.JLabel subtitleLBL;
    private javax.swing.JLabel titleLBL;
    // End of variables declaration//GEN-END:variables
}
