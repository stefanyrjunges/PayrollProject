/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package payrollsystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * DatabaseManager.java 
 * February 2025
 * @author Murilo Batiuk
 */

public class EmployeeSalaryGUI extends javax.swing.JFrame {

    //Instances
    private final EmployeeInfo employeeInfo = EmployeeInfo.getInstance();
    private final JTable financeTable;
    private final SalaryAdmin salaryAdmin;
    private final EmployeeDataFetcher dataFetcher = new EmployeeDataFetcher();
    private final SalaryAdmin Admin = new SalaryAdmin();

    /**
     * Creates new form PayrollGUI
     */
    public EmployeeSalaryGUI() {
        initComponents();

        //Load employee information based on the employee ID from the database table
        dataFetcher.loadUserInformation("employee_id", "employee_logins", "employees");

        //Displays a personalised greeting message using the employee name
        subtitleLBL.setText("Hello, " + employeeInfo.getName() + "!");

        //Initialises SalaryAdmin for salary
        salaryAdmin = new SalaryAdmin();

        //Defines JTable columns
        String[] columnNames = {"Week", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday", "Total Hours", "Total After Taxes"};
        financeTable = new JTable(new DefaultTableModel(columnNames, 0));

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
        maintitleLBL = new javax.swing.JLabel();
        subtitleLBL = new javax.swing.JLabel();
        orangelogoLBL = new javax.swing.JLabel();
        jPanel = new javax.swing.JPanel();
        returnBTN = new javax.swing.JButton();
        selectmonthLBL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        weektableTBL = new javax.swing.JTable();
        januaryBTN = new javax.swing.JButton();
        februaryBTN = new javax.swing.JButton();
        marchBTN = new javax.swing.JButton();
        aprilBTN = new javax.swing.JButton();
        mayBTN = new javax.swing.JButton();
        juneBTN = new javax.swing.JButton();
        julyBTN = new javax.swing.JButton();
        augustBTN = new javax.swing.JButton();
        septemberBTN = new javax.swing.JButton();
        octoberBTN = new javax.swing.JButton();
        novemberBTN = new javax.swing.JButton();
        decemberBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setBackground(new java.awt.Color(235, 142, 39));

        maintitleLBL.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        maintitleLBL.setText("ESTIMATED SALARY");

        subtitleLBL.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        subtitleLBL.setText("Hello, Employee!");

        orangelogoLBL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payrollsystem/PAYROLL_LOGO_E.png"))); // NOI18N

        jPanel.setBackground(new java.awt.Color(255, 255, 255));
        jPanel.setFocusTraversalPolicyProvider(true);
        jPanel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jPanel.setPreferredSize(new java.awt.Dimension(1513, 1513));

        returnBTN.setBackground(new java.awt.Color(235, 142, 39));
        returnBTN.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        returnBTN.setForeground(new java.awt.Color(255, 255, 255));
        returnBTN.setText("return");
        returnBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBTNActionPerformed(evt);
            }
        });

        selectmonthLBL.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        selectmonthLBL.setText("select month:");

        weektableTBL.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        weektableTBL.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "week", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday", "week total (€)", "Total After Taxes "
            }
        ));
        weektableTBL.setRowHeight(60);
        jScrollPane1.setViewportView(weektableTBL);

        januaryBTN.setBackground(new java.awt.Color(235, 142, 39));
        januaryBTN.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        januaryBTN.setText("january");
        januaryBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                januaryBTNActionPerformed(evt);
            }
        });

        februaryBTN.setBackground(new java.awt.Color(235, 142, 39));
        februaryBTN.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        februaryBTN.setText("february");
        februaryBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                februaryBTNActionPerformed(evt);
            }
        });

        marchBTN.setBackground(new java.awt.Color(235, 142, 39));
        marchBTN.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        marchBTN.setText("march");
        marchBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                marchBTNActionPerformed(evt);
            }
        });

        aprilBTN.setBackground(new java.awt.Color(235, 142, 39));
        aprilBTN.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        aprilBTN.setText("april");
        aprilBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aprilBTNActionPerformed(evt);
            }
        });

        mayBTN.setBackground(new java.awt.Color(235, 142, 39));
        mayBTN.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        mayBTN.setText("may");
        mayBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mayBTNActionPerformed(evt);
            }
        });

        juneBTN.setBackground(new java.awt.Color(235, 142, 39));
        juneBTN.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        juneBTN.setText("june");
        juneBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                juneBTNActionPerformed(evt);
            }
        });

        julyBTN.setBackground(new java.awt.Color(235, 142, 39));
        julyBTN.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        julyBTN.setText("july");
        julyBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                julyBTNActionPerformed(evt);
            }
        });

        augustBTN.setBackground(new java.awt.Color(235, 142, 39));
        augustBTN.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        augustBTN.setText("august");
        augustBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                augustBTNActionPerformed(evt);
            }
        });

        septemberBTN.setBackground(new java.awt.Color(235, 142, 39));
        septemberBTN.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        septemberBTN.setText("september");
        septemberBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                septemberBTNActionPerformed(evt);
            }
        });

        octoberBTN.setBackground(new java.awt.Color(235, 142, 39));
        octoberBTN.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        octoberBTN.setText("october");
        octoberBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                octoberBTNActionPerformed(evt);
            }
        });

        novemberBTN.setBackground(new java.awt.Color(235, 142, 39));
        novemberBTN.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        novemberBTN.setText("november");
        novemberBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novemberBTNActionPerformed(evt);
            }
        });

        decemberBTN.setBackground(new java.awt.Color(235, 142, 39));
        decemberBTN.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        decemberBTN.setText("december");
        decemberBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decemberBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(januaryBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(septemberBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(marchBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(julyBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mayBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(novemberBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(38, 38, 38)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(februaryBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(decemberBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(aprilBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(juneBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(augustBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(octoberBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(93, 93, 93)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1034, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(669, 669, 669)
                        .addComponent(returnBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(selectmonthLBL)))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(selectmonthLBL)
                        .addGap(37, 37, 37)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(januaryBTN)
                            .addComponent(februaryBTN))
                        .addGap(39, 39, 39)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(marchBTN)
                            .addComponent(aprilBTN))
                        .addGap(42, 42, 42)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mayBTN)
                            .addComponent(juneBTN))
                        .addGap(43, 43, 43)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(julyBTN)
                            .addComponent(augustBTN))
                        .addGap(42, 42, 42)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(septemberBTN)
                            .addComponent(octoberBTN))))
                .addGap(28, 28, 28)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(novemberBTN)
                    .addComponent(decemberBTN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(returnBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(maintitleLBL)
                    .addComponent(subtitleLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(orangelogoLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1506, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(maintitleLBL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(subtitleLBL)
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(orangelogoLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Buttons for each month of the year. When the given month is selected, it displays the corresponding weekly information
    private void decemberBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decemberBTNActionPerformed
        // TODO add your handling code here:

        loadFinanceData(12);

    }//GEN-LAST:event_decemberBTNActionPerformed

    private void novemberBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novemberBTNActionPerformed
        // TODO add your handling code here:

        loadFinanceData(11);

    }//GEN-LAST:event_novemberBTNActionPerformed

    private void octoberBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_octoberBTNActionPerformed
        // TODO add your handling code here:

        loadFinanceData(10);

    }//GEN-LAST:event_octoberBTNActionPerformed

    private void septemberBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_septemberBTNActionPerformed
        // TODO add your handling code here:

        loadFinanceData(9);

    }//GEN-LAST:event_septemberBTNActionPerformed

    private void augustBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_augustBTNActionPerformed
        // TODO add your handling code here:

        loadFinanceData(8);

    }//GEN-LAST:event_augustBTNActionPerformed

    private void julyBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_julyBTNActionPerformed
        // TODO add your handling code here:

        loadFinanceData(7);

    }//GEN-LAST:event_julyBTNActionPerformed

    private void juneBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_juneBTNActionPerformed
        // TODO add your handling code here:

        loadFinanceData(6);

    }//GEN-LAST:event_juneBTNActionPerformed

    private void mayBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mayBTNActionPerformed
        // TODO add your handling code here:

        loadFinanceData(5);

    }//GEN-LAST:event_mayBTNActionPerformed

    private void aprilBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aprilBTNActionPerformed
        // TODO add your handling code here:

        loadFinanceData(4);

    }//GEN-LAST:event_aprilBTNActionPerformed

    private void marchBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_marchBTNActionPerformed
        // TODO add your handling code here:

        loadFinanceData(3);

    }//GEN-LAST:event_marchBTNActionPerformed

    private void februaryBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_februaryBTNActionPerformed
        // TODO add your handling code here:

        loadFinanceData(2);

    }//GEN-LAST:event_februaryBTNActionPerformed

    private void januaryBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_januaryBTNActionPerformed
        // TODO add your handling code here:

        loadFinanceData(1);

    }//GEN-LAST:event_januaryBTNActionPerformed

    //Return button
    private void returnBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBTNActionPerformed
        // TODO add your handling code here:
        
        EmployeeGUI empDashboard = new EmployeeGUI();
        empDashboard.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_returnBTNActionPerformed

    private void loadFinanceData(int month) {
        //Gets the employee ID from the employeeInfo instance
        int employeeId = employeeInfo.getId();
        
        //Fetches the finance data for the specific eployee and month using SalaryAdmin
        List<Object[]> financeData = Admin.loadEmployeeFinance(employeeId, month);

        //Gets the default table to modify the data displayed in the table
        DefaultTableModel model = (DefaultTableModel) weektableTBL.getModel();
       
        //Clears existing rows
        model.setRowCount(0);  

        //Iterates over the fetched finance data and each row to the table
        for (Object[] row : financeData) {
            model.addRow(row);
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
        java.util.logging.Logger.getLogger(EmployeeSalaryGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
        java.util.logging.Logger.getLogger(EmployeeSalaryGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
        java.util.logging.Logger.getLogger(EmployeeSalaryGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        java.util.logging.Logger.getLogger(EmployeeSalaryGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
    }
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>
    //</editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new EmployeeSalaryGUI().setVisible(true);
        }
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton aprilBTN;
    private javax.swing.JButton augustBTN;
    private javax.swing.JButton decemberBTN;
    private javax.swing.JButton februaryBTN;
    private javax.swing.JPanel jPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton januaryBTN;
    private javax.swing.JButton julyBTN;
    private javax.swing.JButton juneBTN;
    private javax.swing.JLabel maintitleLBL;
    private javax.swing.JButton marchBTN;
    private javax.swing.JButton mayBTN;
    private javax.swing.JButton novemberBTN;
    private javax.swing.JButton octoberBTN;
    private javax.swing.JLabel orangelogoLBL;
    private javax.swing.JButton returnBTN;
    private javax.swing.JLabel selectmonthLBL;
    private javax.swing.JButton septemberBTN;
    private javax.swing.JLabel subtitleLBL;
    private javax.swing.JTable weektableTBL;
    // End of variables declaration//GEN-END:variables
}
