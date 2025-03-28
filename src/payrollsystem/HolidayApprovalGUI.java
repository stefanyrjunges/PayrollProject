/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package payrollsystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
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
            String sql = "SELECT holiday_id, employee_id, start_week, end_week, employee_reason " +
                         "FROM holiday_requests WHERE approval_status = 'Pending'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                HolidayRequest request = new HolidayRequest(
                    rs.getInt("holiday_id"),
                    rs.getInt("employee_id"),
                    rs.getString("start_week"),
                    rs.getString("end_week"),
                    rs.getString("employee_reason")
                );
                requests.add(request);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving holiday requests: " + e.getMessage());
        }
        return requests;
    }

    private EmployeeInfo getEmployeeInfo(int employeeId) {
        EmployeeInfo employee = null;
        try (Connection conn = DatabaseManager.getConnection()) {
            String sql = "SELECT fname, lname, position FROM employees WHERE employee_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Use the singleton instance and set the data from the database
                employee = EmployeeInfo.getInstance();
                employee.setfName(rs.getString("fname"));
                employee.setlName(rs.getString("lname"));
                employee.setRole(rs.getString("position"));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving employee info: " + e.getMessage());
        }
        return employee;
    }

    public void generateRequestForms(JPanel panel) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            // Create a panel for the content.
            JPanel contentPanel = new JPanel();
            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
            contentPanel.setBackground(Color.decode("#e3eaf5"));
            contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            // Get the list of pending holiday requests.
            List<HolidayRequest> requests = getHolidayRequests();

            Font titleFont = new Font("Dialog", Font.BOLD, 24);
            Font labelFont = new Font("Dialog", Font.PLAIN, 14);
            Font buttonFont = new Font("Dialog", Font.BOLD, 16);
            Font reasonFont = new Font("Dialog", Font.PLAIN, 12);

            if (requests.isEmpty()) {
                // If there are no requests, show a message.
                JLabel noRequests = new JLabel("No requests to be approved");
                noRequests.setFont(titleFont);
                noRequests.setForeground(Color.decode("#1c2a4d"));
                noRequests.setAlignmentX(Component.CENTER_ALIGNMENT);
                contentPanel.add(noRequests);
            } else {
                // For each request, create a panel with the details.
                for (HolidayRequest req : requests) {
                    // Retrieve the proper employee info from the employees table.
                    EmployeeInfo employee = getEmployeeInfo(req.getEmployeeId());
                    if (employee == null) {
                        employee = new EmployeeInfo("Unknown", "Employee", "N/A");
                    }

                    JPanel requestPanel = new JPanel();
                    requestPanel.setLayout(new BoxLayout(requestPanel, BoxLayout.Y_AXIS));
                    requestPanel.setBackground(Color.decode("#e3eaf5"));
                    requestPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#ecf0f1"), 2, true));

                    // Label with employee details and week range.
                    JLabel label = new JLabel("<html><b>" + employee.getfName() + " " + employee.getlName() +
                            " | Position: " + employee.getRole() +
                            " | Week: " + req.getStartWeek() +
                            " - " + req.getEndWeek() + "</b></html>");
                    label.setFont(titleFont);
                    label.setForeground(Color.decode("#1c2a4d"));
                    
                    // Restrict panel width and height
                    requestPanel.setMaximumSize(new Dimension(500, 180));
                    requestPanel.setPreferredSize(new Dimension(500, 180));
                    requestPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

                    // Label for the employee's reason.
                    JLabel reasonLabel = new JLabel("Employee Reason: " + req.getEmployeeReason());
                    reasonLabel.setFont(labelFont);
                    reasonLabel.setForeground(Color.decode("#333845"));

                    // Radio buttons to approve or reject the request.
                    JRadioButton approveBtn = new JRadioButton("Approve");
                    JRadioButton rejectBtn = new JRadioButton("Reject");
                    ButtonGroup group = new ButtonGroup();
                    group.add(approveBtn);
                    group.add(rejectBtn);

                    Dimension buttonSize = new Dimension(120, 30);
                    approveBtn.setPreferredSize(buttonSize);
                    rejectBtn.setPreferredSize(buttonSize);
                    approveBtn.setMaximumSize(buttonSize);
                    rejectBtn.setMaximumSize(buttonSize);
                    approveBtn.setFont(buttonFont);
                    rejectBtn.setFont(buttonFont);
                    approveBtn.setForeground(Color.decode("#1c2a4d"));
                    rejectBtn.setForeground(Color.decode("#1c2a4d"));
                    approveBtn.setBackground(Color.decode("#16a085"));
                    rejectBtn.setBackground(Color.decode("#e74c3c"));

                    // Text field for the manager to enter a reason.
                    JTextField managerReasonField = new JTextField(15);
                    managerReasonField.setFont(reasonFont);
                    managerReasonField.setBackground(Color.decode("#ffffff"));
                    managerReasonField.setForeground(Color.decode("#333845"));
                    managerReasonField.setBorder(BorderFactory.createLineBorder(Color.decode("#7f8c8d")));

                    JLabel managerReasonLabel = new JLabel("Manager's Reason:");
                    managerReasonLabel.setFont(labelFont);
                    managerReasonLabel.setForeground(Color.decode("#1c2a4d"));

                    // Add components to the request panel.
                    requestPanel.add(label);               // Component index 0
                    requestPanel.add(reasonLabel);         // Component index 1
                    requestPanel.add(approveBtn);          // Component index 2
                    requestPanel.add(rejectBtn);           // Component index 3
                    requestPanel.add(managerReasonLabel);  // Component index 4
                    requestPanel.add(managerReasonField);  // Component index 5

                    // Separator to visually separate requests.
                    JSeparator separator = new JSeparator();
                    separator.setPreferredSize(new Dimension(panel.getWidth(), 5));
                    separator.setBackground(Color.decode("#95a5a6"));
                    separator.setForeground(Color.decode("#95a5a6"));
                    requestPanel.add(separator);           // Component index 6

                    contentPanel.add(requestPanel);
                    contentPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Add spacing between panels
                }

                // Create the Confirm button only if there is at least one request.
                JButton confirmBtn = new JButton("Confirm");
                confirmBtn.setFont(buttonFont);
                confirmBtn.setBackground(Color.decode("#16a085"));
                confirmBtn.setForeground(Color.decode("#ffffff"));
                confirmBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                confirmBtn.addActionListener(e -> {
                    // Update only those requests where a decision has been made.
                    updateDatabase(panel, requests);
                    // Show confirmation message.
                    JOptionPane.showMessageDialog(this, "Requests updated successfully.");
                    // Refresh the request forms so that approved/rejected requests disappear.
                    generateRequestForms(panel);
                });
                JPanel buttonPanel = new JPanel();
                buttonPanel.setBackground(Color.decode("#34495e"));
                buttonPanel.setMaximumSize(new Dimension(500, 50)); // Restrict button panel height
                buttonPanel.setPreferredSize(new Dimension(500, 50));
                buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                buttonPanel.add(confirmBtn);
                contentPanel.add(buttonPanel);
            }

            // Wrap the content panel in a scroll pane.
            JScrollPane scrollPane = new JScrollPane(contentPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setPreferredSize(new Dimension(550, 400));

            panel.add(scrollPane, BorderLayout.CENTER);
            panel.revalidate();
            panel.repaint();
        }


         // Updates the holiday request in the database.
        public void updateHolidayStatus(int holidayId, String status, String employeeReason, String managerReason) {
            try (Connection conn = DatabaseManager.getConnection()) {
                String sql = "UPDATE holiday_requests SET approval_status = ?, employee_reason = ?, manager_reason = ? WHERE holiday_id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, status);
                stmt.setString(2, employeeReason);
                stmt.setString(3, managerReason);
                stmt.setInt(4, holidayId);
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error updating holiday status and reasons: " + e.getMessage());
            }
        }
    
    public void updateDatabase(JPanel panel, List<HolidayRequest> requests) {
        // Get the components of the JScrollPane
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JScrollPane) {
                JScrollPane scrollPane = (JScrollPane) comp;
                // Get the panel inside the scroll pane (the viewport)
                JPanel contentPanel = (JPanel) scrollPane.getViewport().getView();

                // Now, loop through the request panels inside contentPanel
                Component[] requestPanels = contentPanel.getComponents();
                int index = 0;

                for (Component reqComp : requestPanels) {
                    if (reqComp instanceof JPanel) {
                        JPanel reqPanel = (JPanel) reqComp;

                        // Debug: print number of components in requestPanel
                        System.out.println("Number of components in requestPanel: " + reqPanel.getComponentCount());

                        // Ensure the panel has the expected components.
                        if (reqPanel.getComponentCount() >= 7) {
                            // Retrieve components using their indices:
                            JRadioButton approveBtn = (JRadioButton) reqPanel.getComponent(2);  // Approve button
                            JRadioButton rejectBtn = (JRadioButton) reqPanel.getComponent(3);   // Reject button
                            JTextField managerReasonField = (JTextField) reqPanel.getComponent(5);  // Manager reason field
                            JLabel employeeReasonLabel = (JLabel) reqPanel.getComponent(1);     // Employee reason label

                            // Get the actual employee reason (remove the label prefix)
                            String employeeReason = employeeReasonLabel.getText().replace("Employee Reason: ", "").trim();
                            String managerReason = managerReasonField.getText().trim();
                            int holidayId = requests.get(index).getHolidayId();

                            // Determine the decision state. Default is "Pending".
                            String decision = "Pending";
                            if (approveBtn.isSelected()) {
                                decision = "Approved";
                            } else if (rejectBtn.isSelected()) {
                                decision = "Rejected";
                            }

                            // Only update if a decision was made.
                            if (!decision.equals("Pending")) {
                                updateHolidayStatus(holidayId, decision, employeeReason, managerReason);
                            }
                        } else {
                            System.out.println("Skipping request panel with insufficient components.");
                        }
                        index++;
                    }
                }
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
        setAlwaysOnTop(true);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1920, 800));
        setPreferredSize(new java.awt.Dimension(1410, 741));
        setSize(new java.awt.Dimension(0, 0));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 630, Short.MAX_VALUE)
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
            .addGap(0, 527, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backToMainBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(backToMainBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        pack();
        setLocationRelativeTo(null);
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
