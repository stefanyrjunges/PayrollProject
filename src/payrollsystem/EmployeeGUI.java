/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package payrollsystem;

public class EmployeeGUI extends javax.swing.JFrame {

    private final EmployeeDataFetcher dataFetcher = new EmployeeDataFetcher();
    private final EmployeeInfo employeeInfo = EmployeeInfo.getInstance();
    private final EmployeeNotification employeeNotif = new EmployeeNotification();
    
    public EmployeeGUI() {
        initComponents();
        dataFetcher.loadUserInformation("employee_id", "employee_logins", "employees");
        empNameLBL.setText(employeeInfo.getName() + "!");
        employeeNotif.notifyEmployee(employeeInfo.getId());
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
        logoutBTN = new javax.swing.JButton();
        menuPNL = new javax.swing.JPanel();
        subtitleLBL = new javax.swing.JLabel();
        empNameLBL = new javax.swing.JLabel();
        questionLBL = new javax.swing.JLabel();
        hoursBTN = new javax.swing.JButton();
        holidayBTN1 = new javax.swing.JButton();
        statementBTN = new javax.swing.JButton();
        salaryBTN = new javax.swing.JButton();
        empProfileBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1920, 1080));
        setPreferredSize(new java.awt.Dimension(1520, 715));
        setResizable(false);
        setSize(new java.awt.Dimension(1520, 715));

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));
        jPanel1.setForeground(new java.awt.Color(255, 153, 51));
        jPanel1.setMinimumSize(new java.awt.Dimension(1920, 1080));

        titleLBL.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        titleLBL.setForeground(new java.awt.Color(255, 255, 255));
        titleLBL.setText("DASHBOARD");

        iconLBL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payrollsystem/PAYROLL_LOGO_E.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(653, Short.MAX_VALUE)
                .addComponent(titleLBL)
                .addGap(491, 491, 491)
                .addComponent(iconLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(473, 473, 473))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(titleLBL))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(iconLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        logoutBTN.setBackground(new java.awt.Color(237, 170, 12));
        logoutBTN.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        logoutBTN.setForeground(new java.awt.Color(255, 255, 255));
        logoutBTN.setText("LOG OUT");
        logoutBTN.setBorderPainted(false);
        logoutBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBTNActionPerformed(evt);
            }
        });

        menuPNL.setBackground(new java.awt.Color(248, 248, 248));

        subtitleLBL.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        subtitleLBL.setForeground(new java.awt.Color(80, 80, 80));
        subtitleLBL.setText("Welcome back,");

        empNameLBL.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        empNameLBL.setForeground(new java.awt.Color(80, 80, 80));
        empNameLBL.setText("Employee");

        questionLBL.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        questionLBL.setText("How can we help you today?");

        hoursBTN.setBackground(new java.awt.Color(237, 170, 12));
        hoursBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        hoursBTN.setForeground(new java.awt.Color(255, 255, 255));
        hoursBTN.setText("clock in/clock out submission");
        hoursBTN.setBorder(null);
        hoursBTN.setBorderPainted(false);
        hoursBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hoursBTNActionPerformed(evt);
            }
        });

        holidayBTN1.setBackground(new java.awt.Color(237, 170, 12));
        holidayBTN1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        holidayBTN1.setForeground(new java.awt.Color(255, 255, 255));
        holidayBTN1.setText("holiday request");
        holidayBTN1.setBorderPainted(false);
        holidayBTN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                holidayBTN1ActionPerformed(evt);
            }
        });

        statementBTN.setBackground(new java.awt.Color(237, 170, 12));
        statementBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        statementBTN.setForeground(new java.awt.Color(255, 255, 255));
        statementBTN.setText("generate statement");
        statementBTN.setBorder(null);
        statementBTN.setBorderPainted(false);
        statementBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statementBTNActionPerformed(evt);
            }
        });

        salaryBTN.setBackground(new java.awt.Color(237, 170, 12));
        salaryBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        salaryBTN.setForeground(new java.awt.Color(255, 255, 255));
        salaryBTN.setText("estimated salary");
        salaryBTN.setBorder(null);
        salaryBTN.setBorderPainted(false);
        salaryBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salaryBTNActionPerformed(evt);
            }
        });

        empProfileBTN.setBackground(new java.awt.Color(237, 170, 12));
        empProfileBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        empProfileBTN.setForeground(new java.awt.Color(255, 255, 255));
        empProfileBTN.setText("see profile");
        empProfileBTN.setBorderPainted(false);
        empProfileBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empProfileBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPNLLayout = new javax.swing.GroupLayout(menuPNL);
        menuPNL.setLayout(menuPNLLayout);
        menuPNLLayout.setHorizontalGroup(
            menuPNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPNLLayout.createSequentialGroup()
                .addGroup(menuPNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuPNLLayout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(questionLBL))
                    .addGroup(menuPNLLayout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addGroup(menuPNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(empProfileBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hoursBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(holidayBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salaryBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statementBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(menuPNLLayout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(subtitleLBL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(empNameLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        menuPNLLayout.setVerticalGroup(
            menuPNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPNLLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(menuPNLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(empNameLBL)
                    .addComponent(subtitleLBL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(questionLBL)
                .addGap(52, 52, 52)
                .addComponent(hoursBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(holidayBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salaryBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statementBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(empProfileBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(449, 449, 449)
                        .addComponent(menuPNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(711, 711, 711)
                        .addComponent(logoutBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(menuPNL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logoutBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(304, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void statementBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statementBTNActionPerformed
        // TODO add your handling code here:
        EmployeeStatementGUI statementGui = new EmployeeStatementGUI();
        statementGui.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_statementBTNActionPerformed

    private void salaryBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salaryBTNActionPerformed
        // TODO add your handling code here:
        EmployeeSalaryGUI salaryGui = new EmployeeSalaryGUI();
        salaryGui.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_salaryBTNActionPerformed

    private void empProfileBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empProfileBTNActionPerformed
        EditEmployeeInfoGUI editInfo = new EditEmployeeInfoGUI();
        editInfo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_empProfileBTNActionPerformed

    private void logoutBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBTNActionPerformed
        SessionManager.getInstance().clearSession();
        LogInGUI login = new LogInGUI();
        login.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutBTNActionPerformed

    private void hoursBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hoursBTNActionPerformed
        LogHoursEmployee logHours = new LogHoursEmployee();
        logHours.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_hoursBTNActionPerformed

    private void holidayBTN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_holidayBTN1ActionPerformed
        // TODO add your handling code here:
        HolidayRequestGUI holidayRequest = new HolidayRequestGUI();
        holidayRequest.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_holidayBTN1ActionPerformed

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
            java.util.logging.Logger.getLogger(EmployeeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel empNameLBL;
    private javax.swing.JButton empProfileBTN;
    private javax.swing.JButton holidayBTN1;
    private javax.swing.JButton hoursBTN;
    private javax.swing.JLabel iconLBL;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton logoutBTN;
    private javax.swing.JPanel menuPNL;
    private javax.swing.JLabel questionLBL;
    private javax.swing.JButton salaryBTN;
    private javax.swing.JButton statementBTN;
    private javax.swing.JLabel subtitleLBL;
    private javax.swing.JLabel titleLBL;
    // End of variables declaration//GEN-END:variables
}
