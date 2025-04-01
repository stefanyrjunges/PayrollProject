package payrollsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class EmployeeNotification {

    public boolean notifyEmployee(int id) {
        String queryStatus = "SELECT status, notified, rejection_reason FROM weekly_finance WHERE employee_id = ?";
        String updateQuery = "UPDATE weekly_finance SET notified = TRUE WHERE employee_id = ?";

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(queryStatus)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { 
                    String status = rs.getString("status");
                    boolean notified = rs.getBoolean("notified");
                    String rejectionReason = rs.getString("rejection_reason");

                    if ("approved".equalsIgnoreCase(status) && !notified) {
                        JOptionPane.showMessageDialog(null, "Your hour submission has been approved.");
                        
                        // Mark as notified
                        try (PreparedStatement updatePs = con.prepareStatement(updateQuery)) {
                            updatePs.setInt(1, id);
                            updatePs.executeUpdate();
                        }
                        return true;
                        
                    } else if ("rejected".equalsIgnoreCase(status) && !notified) {
                        JOptionPane.showMessageDialog(null, 
                            "Your hour submission was not approved due to the following reason:\n**" 
                            + (rejectionReason != null ? rejectionReason : "No reason provided") 
                            + "**\nPlease contact your assigned manager.");
                        
                        try (PreparedStatement updatePs = con.prepareStatement(updateQuery)) {
                            updatePs.setInt(1, id);
                            updatePs.executeUpdate();
                        }
                        
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }    
        
        return false;
    }
}