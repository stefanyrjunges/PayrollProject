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

    private double newSalary = 0.0;
    private final EmployeeInfo empInfo = EmployeeInfo.getInstance();

    public List<Object[]> loadEmployeeFinance(int employeeId, int month) {
        System.out.println("Selected month: " + month);

        String financeQuery = "SELECT weekNumber, monday, tuesday, wednesday, thursday, friday, saturday, sunday, salary "
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
                int totalHours = rs.getInt("monday") + rs.getInt("tuesday") + rs.getInt("wednesday")
                        + rs.getInt("thursday") + rs.getInt("friday") + rs.getInt("saturday") + rs.getInt("sunday");

                double weekTotal = totalHours * empInfo.getRate();

                if (String.format("%.2f", weekTotal).equals("0,00")) {
                    financeData.add(new Object[]{weekNumber, "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A"});
                } else {

                    newSalary = afterTaxes(weekTotal);

                    financeData.add(new Object[]{
                        weekNumber,
                        rs.getInt("monday"),
                        rs.getInt("tuesday"),
                        rs.getInt("wednesday"),
                        rs.getInt("thursday"),
                        rs.getInt("friday"),
                        rs.getInt("saturday"),
                        rs.getInt("sunday"),
                        String.format("%.2f", weekTotal),
                        String.format("%.2f", newSalary)
                    });

                    updateEmployeeSalary(employeeId, weekNumber, newSalary);
                }
            }

            System.out.println("Data fetched successfully: " + financeData.size() + " rows.");

        } catch (SQLException e) {
            System.out.println("Error fetching data: " + e.getMessage());
            e.printStackTrace();
        }

        return financeData;
    }

    public void updateEmployeeSalary(int employeeId, int weekNumber, double newSalary) {
        String updateQuery = "UPDATE weekly_finance SET salary = ? WHERE employee_id = ? AND weekNumber = ?";

        try (Connection con = DatabaseManager.getConnection(); PreparedStatement ps = con.prepareStatement(updateQuery)) {

            ps.setDouble(1, newSalary);
            ps.setInt(2, employeeId);
            ps.setInt(3, weekNumber);
            ps.executeUpdate();

            System.out.println("Updated salary for Employee ID: " + employeeId + ", Week: " + weekNumber);

        } catch (SQLException e) {
            System.out.println("Error updating salary: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public double afterTaxes(double weekTotal) {
        if (weekTotal <= 740) {
            weekTotal -= weekTotal * 0.2;
        } else {
            weekTotal -= weekTotal * 0.4;
        }

        if (weekTotal <= 231) {
            weekTotal -= weekTotal * 0.05;
        } else if (weekTotal <= 447) {
            weekTotal -= weekTotal * 0.02;
        } else if (weekTotal <= 1038) {
            weekTotal -= weekTotal * 0.04;
        } else {
            weekTotal -= weekTotal * 0.08;
        }

        if (weekTotal > 441) {
            weekTotal -= weekTotal * 0.04;
        }

        return weekTotal;
    }

    public double getNewSalary() {
        return newSalary;
    }
}
