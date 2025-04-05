package payrollsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Stefany Junges
 */

public class EmployeeDataFetcher {

    String idQuery, infoQuery;
    EmployeeInfo employeeInfo = EmployeeInfo.getInstance();
    SessionManager session = SessionManager.getInstance();

    public void loadUserInformation(String idType, String fTable, String sTable) {

        idQuery = "SELECT " + idType + " FROM " + fTable + " WHERE username = ?";
        infoQuery = "SELECT * FROM " + sTable + " WHERE " + idType + " = ?";

        try (Connection con = DatabaseManager.getConnection();
             PreparedStatement ps = con.prepareStatement(idQuery)) {

            ps.setString(1, session.getUser());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int userID = rs.getInt(idType);

                try (PreparedStatement ps2 = con.prepareStatement(infoQuery)) {

                    ps2.setInt(1, userID);
                    ResultSet rs2 = ps2.executeQuery();

                    if (rs2.next()) {
                        String fName = rs2.getString("fname");
                        String lName = rs2.getString("lname");
                        String role = rs2.getString("position");
                        String email = rs2.getString("email");
                        String phoneNo = rs2.getString("phoneN");
                        String address = rs2.getString("address");
                        String hireDate = rs2.getString("hire_date");

                        employeeInfo.setName(fName, lName);
                        employeeInfo.setId(userID);
                        employeeInfo.setRole(role);
                        employeeInfo.setEmail(email);
                        employeeInfo.setPhoneNo(phoneNo);
                        employeeInfo.setAddress(address);
                        employeeInfo.setHireDate(hireDate);

                        if (sTable.equals("employees")) {
                            Double rate = rs2.getDouble("rate");
                            employeeInfo.setRate(rate);
                        } else {
                            Double salary = rs2.getDouble("salary");
                            employeeInfo.setRate(salary);
                        }

                        System.out.println("Logged in: " + fName + lName);
                    }

                }

            } else {
                System.out.println("No user found");
            }

        } catch (SQLException e) {
            System.out.println("Error accessing database: " + e);
        }

    }

}
