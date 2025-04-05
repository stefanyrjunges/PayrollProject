package payrollsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stefany Junges
 */

public class ValidateLogin {

    String query;

    public boolean checkCredentials(String table, String username, String password) {

        query = "SELECT * FROM " + table + " WHERE username = ? AND password_hash = ?";

        try (Connection con = DatabaseManager.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.out.println("Error accessing database: " + e);
            return false;
        }

    }

}
