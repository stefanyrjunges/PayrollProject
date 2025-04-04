package payrollsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PasswordManager {

    String query;

    public PasswordManager() {
    }

    public boolean checkIdentity(String table, String idType, String id, String username) {

        query = "SELECT * FROM " + table + " WHERE " + idType + " = ? AND username = ?";

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            
            ps.setString(1, id);
            ps.setString(2, username);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error accessing database: " + e);
            return false;
        }

    }

    public boolean updatePassword(String table, String idType, String id, String username, String password) {

        query = "UPDATE " + table + " SET password_hash = ? WHERE " + idType + " = ? AND username = ?";

        try (Connection con = DatabaseManager.getConnection(); //Using prepared statement to execute the query
                 PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, password);
            ps.setString(2, id);
            ps.setString(3, username);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error accessing database: " + e);
            return false;
        }
    }
}
