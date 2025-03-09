/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payrollsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SalaryAdmin.java February 2025
 *
 * @author Murilo Batiuk
 */
public class SalaryAdmin {

    public List<Object[]> loadEmployeeFinance(int employeeId, int month) {
        String financeQuery = "SELECT weekNumber, monday, tuesday, wednesday, thursday, friday, saturday, sunday, salary "
                + "FROM weekly_finance WHERE employee_id = ? AND weekNumber BETWEEN ? AND ?";

        List<Object[]> financeData = new ArrayList<>();

        int startWeek = (month - 1) * 4 + 1;
        int endWeek = startWeek + 3;

        try (Connection con = DatabaseManager.getConnection(); PreparedStatement ps = con.prepareStatement(financeQuery)) {

            ps.setInt(1, employeeId);
            ps.setInt(2, startWeek);
            ps.setInt(3, endWeek);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Object[] rowData = {
                    rs.getInt("weekNumber"),
                    rs.getInt("monday"),
                    rs.getInt("tuesday"),
                    rs.getInt("wednesday"),
                    rs.getInt("thursday"),
                    rs.getInt("friday"),
                    rs.getInt("saturday"),
                    rs.getInt("sunday"),
                    rs.getDouble("salary")
                };
                financeData.add(rowData);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching data: " + e.getMessage());
        }

        return financeData;
    }

}
