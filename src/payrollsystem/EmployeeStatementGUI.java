/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package payrollsystem;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class EmployeeStatementGUI extends javax.swing.JFrame {
    
    private final EmployeeDataFetcher dataFetcher = new EmployeeDataFetcher();
    private final StatementGenerator statementGenerator = new StatementGenerator();
    
    public EmployeeStatementGUI() {
        initComponents();
        dataFetcher.loadUserInformation("employee_id", "employee_logins", "employees");
        scrollPane.setVisible(false);
        openBTN.setVisible(false);
        statementGenerator.loadPastPDFs(pdfList);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loadingDialog = new javax.swing.JDialog();
        dialogPanel = new javax.swing.JPanel();
        loadingLBL = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        titleLBL = new javax.swing.JLabel();
        iconLBL = new javax.swing.JLabel();
        generateBTN = new javax.swing.JButton();
        pdfICON = new javax.swing.JLabel();
        generatePreviousBTN = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        pdfList = new javax.swing.JList<>();
        openBTN = new javax.swing.JButton();
        backBTN1 = new javax.swing.JButton();

        loadingDialog.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        loadingDialog.setTitle("Loading...");

        dialogPanel.setName(""); // NOI18N

        loadingLBL.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        loadingLBL.setForeground(new java.awt.Color(81, 81, 81));
        loadingLBL.setText("Loading...");

        progressBar.setIndeterminate(true);

        javax.swing.GroupLayout dialogPanelLayout = new javax.swing.GroupLayout(dialogPanel);
        dialogPanel.setLayout(dialogPanelLayout);
        dialogPanelLayout.setHorizontalGroup(
            dialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogPanelLayout.createSequentialGroup()
                .addGroup(dialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dialogPanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(dialogPanelLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(loadingLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        dialogPanelLayout.setVerticalGroup(
            dialogPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialogPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(loadingLBL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout loadingDialogLayout = new javax.swing.GroupLayout(loadingDialog.getContentPane());
        loadingDialog.getContentPane().setLayout(loadingDialogLayout);
        loadingDialogLayout.setHorizontalGroup(
            loadingDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dialogPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        loadingDialogLayout.setVerticalGroup(
            loadingDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(dialogPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1920, 1080));

        jPanel2.setBackground(new java.awt.Color(235, 142, 39));

        titleLBL.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        titleLBL.setForeground(new java.awt.Color(255, 255, 255));
        titleLBL.setText("EMPLOYEE STATEMENT");

        iconLBL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payrollsystem/PAYROLL_LOGO_E.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(titleLBL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iconLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(iconLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(titleLBL)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        generateBTN.setBackground(new java.awt.Color(235, 142, 39));
        generateBTN.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        generateBTN.setForeground(new java.awt.Color(255, 255, 255));
        generateBTN.setText("generate statement for current week");
        generateBTN.setBorder(null);
        generateBTN.setBorderPainted(false);
        generateBTN.setMaximumSize(new java.awt.Dimension(32767, 32767));
        generateBTN.setPreferredSize(new java.awt.Dimension(1520, 715));
        generateBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateBTNActionPerformed(evt);
            }
        });

        pdfICON.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payrollsystem/pd_ficon.png"))); // NOI18N

        generatePreviousBTN.setBackground(new java.awt.Color(235, 142, 39));
        generatePreviousBTN.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        generatePreviousBTN.setForeground(new java.awt.Color(255, 255, 255));
        generatePreviousBTN.setText("see previous weeks' statements");
        generatePreviousBTN.setBorder(null);
        generatePreviousBTN.setBorderPainted(false);
        generatePreviousBTN.setMaximumSize(new java.awt.Dimension(32767, 32767));
        generatePreviousBTN.setMinimumSize(new java.awt.Dimension(237, 23));
        generatePreviousBTN.setPreferredSize(new java.awt.Dimension(1520, 715));
        generatePreviousBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generatePreviousBTNActionPerformed(evt);
            }
        });

        pdfList.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        pdfList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        pdfList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(pdfList);

        openBTN.setBackground(new java.awt.Color(235, 142, 39));
        openBTN.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        openBTN.setForeground(new java.awt.Color(255, 255, 255));
        openBTN.setText("open");
        openBTN.setBorder(null);
        openBTN.setBorderPainted(false);
        openBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openBTNActionPerformed(evt);
            }
        });

        backBTN1.setBackground(new java.awt.Color(235, 142, 39));
        backBTN1.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        backBTN1.setForeground(new java.awt.Color(255, 255, 255));
        backBTN1.setText("RETURN TO MAIN PAGE");
        backBTN1.setBorder(null);
        backBTN1.setBorderPainted(false);
        backBTN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBTN1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(497, 497, 497)
                        .addComponent(generateBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(generatePreviousBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
                            .addComponent(scrollPane)
                            .addComponent(openBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(652, 652, 652)
                        .addComponent(backBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(698, 698, 698)
                        .addComponent(pdfICON)))
                .addContainerGap(523, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(pdfICON)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(generateBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generatePreviousBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(openBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(backBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1519, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void generatePreviousBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generatePreviousBTNActionPerformed
        
        openBTN.setVisible(true);
        scrollPane.setVisible(true);

//        sm.generateFinancialReport();
//        JOptionPane.showMessageDialog(null, "Statement saved.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_generatePreviousBTNActionPerformed

    private void generateBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateBTNActionPerformed

        loadingDialog.setSize(210, 100);
        loadingDialog.setLocationRelativeTo(null);
        SwingUtilities.invokeLater(() -> loadingDialog.setVisible(true));

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try {
                    statementGenerator.generateEmployeeStatement();
                } catch (IOException e) {
                    Logger.getLogger(EmployeeStatementGUI.class.getName()).log(Level.SEVERE, null, e);
                    JOptionPane.showMessageDialog(null, "Error generating statement.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }

            @Override
            protected void done() {
                
                SwingUtilities.invokeLater(() -> loadingDialog.dispose());

                JOptionPane.showMessageDialog(null, "Statement saved.");
                
                statementGenerator.loadPastPDFs(pdfList);
            }
        };

        worker.execute();

    }//GEN-LAST:event_generateBTNActionPerformed

    private void openBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openBTNActionPerformed
        statementGenerator.openPastPDFs(pdfList);
    }//GEN-LAST:event_openBTNActionPerformed

    private void backBTN1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBTN1ActionPerformed
        EmployeeGUI empDashboard = new EmployeeGUI();
        empDashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backBTN1ActionPerformed

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
            java.util.logging.Logger.getLogger(EmployeeStatementGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeStatementGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeStatementGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeStatementGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeStatementGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backBTN1;
    private javax.swing.JPanel dialogPanel;
    private javax.swing.JButton generateBTN;
    private javax.swing.JButton generatePreviousBTN;
    private javax.swing.JLabel iconLBL;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JDialog loadingDialog;
    private javax.swing.JLabel loadingLBL;
    private javax.swing.JButton openBTN;
    private javax.swing.JLabel pdfICON;
    private javax.swing.JList<String> pdfList;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel titleLBL;
    // End of variables declaration//GEN-END:variables
}
