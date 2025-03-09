/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package payrollsystem;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTextField;
/**
 *
 * @author paneq
 */
public class HolidayApprovalGUI extends javax.swing.JFrame { 
    
    public HolidayApprovalGUI() {
        initComponents();  
        generateRequestForms(jPanel1);
    }
    

    public List<HolidayRequest> getHolidayRequests() {
        List<HolidayRequest> requests = new ArrayList<>();

        try (Connection conn = DatabaseManager.getConnection()) {
            String sql = "SELECT holiday_id, employee_id, start_week, end_week, employee_reason FROM holiday_requests WHERE approval_status = 'Pending'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                HolidayRequest request = new HolidayRequest(
                    rs.getInt("holiday_id"),
                    rs.getInt("employee_id"),
                    rs.getString("start_week"),
                    rs.getString("end_week"),
                    rs.getString("employee_reason")  // Getting the employee's reason
                );
                requests.add(request);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

        return requests;
    } 


    public void generateRequestForms(JPanel panel) {
        panel.removeAll(); // Clear previous content

        // Get the list of holiday requests from the database
        List<HolidayRequest> requests = getHolidayRequests();

        // Set layout manager
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Set common font properties
        Font titleFont = new Font("Dialog", Font.BOLD, 24);
        Font labelFont = new Font("Dialog", Font.PLAIN, 14);
        Font buttonFont = new Font("Dialog", Font.BOLD, 16);
        Font reasonFont = new Font("Dialog", Font.PLAIN, 12);

        // Set background color
        panel.setBackground(Color.decode("#e3eaf5"));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (HolidayRequest req : requests) {
            EmployeeInfo employee = EmployeeInfo.getInstance();
            
            req.getEmployeeId();

            // Create request panel
            JPanel requestPanel = new JPanel();
            requestPanel.setLayout(new BoxLayout(requestPanel, BoxLayout.Y_AXIS));
            requestPanel.setBackground(Color.decode("#e3eaf5"));
            requestPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ecf0f1"), 2, true));

            // Employee details label
            JLabel label = new JLabel("<html><b>" + employee.getfName() + " " + employee.getlName() +
                                     " | Position: " + employee.getRole() + 
                                     " | Week: " + req.getStartWeek() + 
                                     " - " + req.getEndWeek() + "</b></html>");
            label.setFont(titleFont);
            label.setForeground(Color.decode("#1c2a4d")); // Dark navy for contrast

            // Employee reason label
            JLabel reasonLabel = new JLabel("Employee Reason: " + req.getEmployeeReason());
            reasonLabel.setFont(labelFont);
            reasonLabel.setForeground(Color.decode("#333845")); // Dark gray for better visibility

             // Radio buttons for approval
            JRadioButton approveBtn = new JRadioButton("Approve");
            JRadioButton rejectBtn = new JRadioButton("Reject");
            ButtonGroup group = new ButtonGroup();
            group.add(approveBtn);
            group.add(rejectBtn);

            // Set the same preferred size for both buttons
            Dimension buttonSize = new Dimension(120, 30); // Adjust width and height as needed
            approveBtn.setPreferredSize(buttonSize);
            rejectBtn.setPreferredSize(buttonSize);

            // Force maximum size to prevent resizing
            approveBtn.setMaximumSize(buttonSize);
            rejectBtn.setMaximumSize(buttonSize);

            // Style radio buttons
            approveBtn.setFont(buttonFont);
            rejectBtn.setFont(buttonFont);
            approveBtn.setForeground(Color.decode("#1c2a4d"));
            rejectBtn.setForeground(Color.decode("#1c2a4d"));
            approveBtn.setBackground(Color.decode("#16a085"));
            rejectBtn.setBackground(Color.decode("#e74c3c"));

            // Manager reason text field
            JTextField managerReasonField = new JTextField(15);
            managerReasonField.setFont(reasonFont);
            managerReasonField.setBackground(Color.decode("#ffffff")); // White background for clarity
            managerReasonField.setForeground(Color.decode("#333845")); // Dark gray text
            managerReasonField.setBorder(BorderFactory.createLineBorder(Color.decode("#7f8c8d")));

            // Manager reason label
            JLabel managerReasonLabel = new JLabel("Manager's Reason:");
            managerReasonLabel.setFont(labelFont);
            managerReasonLabel.setForeground(Color.decode("#1c2a4d")); // Dark navy for visibility

            // Add components to request panel
            requestPanel.add(label);
            requestPanel.add(reasonLabel);
            requestPanel.add(approveBtn);
            requestPanel.add(rejectBtn);
            requestPanel.add(managerReasonLabel);
            requestPanel.add(managerReasonField);

            // Separator
            JSeparator separator = new JSeparator();
            separator.setPreferredSize(new Dimension(panel.getWidth(), 5));
            separator.setBackground(Color.decode("#95a5a6"));
            separator.setForeground(Color.decode("#95a5a6"));
            requestPanel.add(separator);

            panel.add(requestPanel);
        }

        // Confirm button
        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.setFont(buttonFont);
        confirmBtn.setBackground(Color.decode("#16a085"));
        confirmBtn.setForeground(Color.decode("#ffffff")); // White text for readability
        confirmBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        confirmBtn.addActionListener(e -> updateDatabase(panel, requests));

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.decode("#34495e"));
        buttonPanel.add(confirmBtn);
        panel.add(buttonPanel);

        // Refresh panel
        panel.revalidate();
        panel.repaint();
    }
    
    public void updateHolidayStatus(int holidayId, String status, String employeeReason, String managerReason) {
        try (Connection conn = DatabaseManager.getConnection()) { // Use DbManager
            String sql = "UPDATE holiday_requests SET approval_status = ?, employee_reason = ?, manager_reason = ? WHERE holiday_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set the parameters
            stmt.setString(1, status);  // Status (Approved or Rejected)
            stmt.setString(2, employeeReason);  // Employee's reason
            stmt.setString(3, managerReason);  // Manager's reason
            stmt.setInt(4, holidayId);  // Holiday ID

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating holiday status and reasons: " + e.getMessage());
        }
    }
    
    public void updateDatabase(JPanel panel, List<HolidayRequest> requests) {
        Component[] components = panel.getComponents();
        int index = 0;

        for (Component comp : components) {
            if (comp instanceof JPanel) {
                JPanel reqPanel = (JPanel) comp;
                JRadioButton approveBtn = (JRadioButton) reqPanel.getComponent(1);
                JRadioButton rejectBtn = (JRadioButton) reqPanel.getComponent(2);
                JTextField employeeReasonField = (JTextField) reqPanel.getComponent(4);
                JTextField managerReasonField = (JTextField) reqPanel.getComponent(6);

                String status = approveBtn.isSelected() ? "Approved" : "Rejected";
                String employeeReason = employeeReasonField.getText();
                String managerReason = managerReasonField.getText();
                int holidayId = requests.get(index).getHolidayId();

                updateHolidayStatus(holidayId, status, employeeReason, managerReason);
                index++;
            }
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

        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        titleLBL = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        backToMainBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setExtendedState(MAXIMIZED_BOTH);
        setMinimumSize(new java.awt.Dimension(1920, 1080));
        setSize(new java.awt.Dimension(1520, 715));

        jPanel2.setBackground(new java.awt.Color(12, 48, 128));

        titleLBL.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        titleLBL.setForeground(new java.awt.Color(255, 255, 255));
        titleLBL.setText("REVIEW AND APPROVE HOLIDAYS");

        icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payrollsystem/PAYROLL_LOGO_m.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(titleLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
                .addComponent(icon, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(154, 154, 154))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLBL)
                    .addComponent(icon))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );

        backToMainBTN.setBackground(new java.awt.Color(12, 48, 128));
        backToMainBTN.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        backToMainBTN.setForeground(new java.awt.Color(255, 255, 255));
        backToMainBTN.setText("RETURN TO DASHBOARD");
        backToMainBTN.setBorderPainted(false);
        backToMainBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMainBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(backToMainBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(backToMainBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backToMainBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToMainBTNActionPerformed
        ManagerDashboard mngDashboard = new ManagerDashboard();
        mngDashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backToMainBTNActionPerformed
    
    

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
            java.util.logging.Logger.getLogger(HolidayApprovalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HolidayApprovalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HolidayApprovalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HolidayApprovalGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HolidayApprovalGUI().setVisible(true);
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backToMainBTN;
    private javax.swing.JLabel icon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel titleLBL;
    // End of variables declaration//GEN-END:variables
}
