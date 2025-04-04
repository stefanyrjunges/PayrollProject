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
        System.out.println("Selected month: " + month);

        String financeQuery = "SELECT weekNumber, monday, tuesday, wednesday, thursday, friday, saturday, sunday, salary, salary_after_taxes "
                + "FROM weekly_finance WHERE employee_id = ? AND weekNumber BETWEEN ? AND ?";

        List<Object[]> financeData = new ArrayList<>();

        int startWeek = 0, endWeek = 0;

        switch (month) {
            case 1:
                startWeek = 1;
                endWeek = 4;
                break;
            case 2:
                startWeek = 5;
                endWeek = 8;
                break;
            case 3:
                startWeek = 9;
                endWeek = 13;
                break;
            case 4:
                startWeek = 14;
                endWeek = 17;
                break;
            case 5:
                startWeek = 18;
                endWeek = 21;
                break;
            case 6:
                startWeek = 22;
                endWeek = 26;
                break;
            case 7:
                startWeek = 27;
                endWeek = 30;
                break;
            case 8:
                startWeek = 31;
                endWeek = 35;
                break;
            case 9:
                startWeek = 36;
                endWeek = 39;
                break;
            case 10:
                startWeek = 40;
                endWeek = 44;
                break;
            case 11:
                startWeek = 45;
                endWeek = 48;
                break;
            case 12:
                startWeek = 49;
                endWeek = 52;
                break;
            default:
                System.out.println("Invalid month: " + month);
                return financeData;
        }

        System.out.println("Fetching data for Employee ID: " + employeeId);
        System.out.println("Month: " + month + " (Weeks: " + startWeek + " to " + endWeek + ")");

        try (Connection con = DatabaseManager.getConnection(); PreparedStatement ps = con.prepareStatement(financeQuery)) {

            ps.setInt(1, employeeId);
            ps.setInt(2, startWeek);
            ps.setInt(3, endWeek);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int weekNumber = rs.getInt("weekNumber");
                double salary = rs.getDouble("salary");

                if (salary == 0.00) {
                    Object[] rowData = {weekNumber, "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A"};
                    financeData.add(rowData);
                } else {
                    Object[] rowData = {
                        weekNumber,
                        rs.getDouble("monday"),
                        rs.getDouble("tuesday"),
                        rs.getDouble("wednesday"),
                        rs.getDouble("thursday"),
                        rs.getDouble("friday"),
                        rs.getDouble("saturday"),
                        rs.getDouble("sunday"),
                        rs.getDouble("salary"),
                        rs.getDouble("salary_after_taxes")
                    };

                    financeData.add(rowData);

                }
            }

            System.out.println("Data fetched successfully: " + financeData.size() + " rows.");

        } catch (SQLException e) {
            System.out.println("Error fetching data: " + e.getMessage());
            e.printStackTrace();
        }

        return financeData;
    }
}
