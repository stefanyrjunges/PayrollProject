/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payrollsystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SalaryAdmin.java 
 * February 2025
 * @author Murilo Batiuk
 */
public class SalaryAdmin {

    public List<Object[]> loadEmployeeFinance(int employeeId, int month) {
        System.out.println("Selected month: " + month);

        String financeQuery = "SELECT weekNumber, monday, tuesday, wednesday, thursday, friday, saturday, sunday, salary "
                + "FROM weekly_finance WHERE employee_id = ? AND weekNumber BETWEEN ? AND ?";

        List<Object[]> financeData = new ArrayList<>();

        int startWeek = 0;
        int endWeek = 0;

        switch (month) {
            case 1:
                // January
                startWeek = 1;
                endWeek = 4;
                break;
            case 2:
                // February
                startWeek = 5;
                endWeek = 8;
                break;
            case 3:
                // March
                startWeek = 9;
                endWeek = 13;
                break;
            case 4:
                // April
                startWeek = 14;
                endWeek = 17;
                break;
            case 5:
                // May
                startWeek = 18;
                endWeek = 21;
                break;
            case 6:
                // June
                startWeek = 22;
                endWeek = 26;
                break;
            case 7:
                // July
                startWeek = 27;
                endWeek = 30;
                break;
            case 8:
                // August
                startWeek = 31;
                endWeek = 35;
                break;
            case 9:
                // September
                startWeek = 36;
                endWeek = 39;
                break;
            case 10:
                // October
                startWeek = 40;
                endWeek = 44;
                break;
            case 11:
                // November
                startWeek = 45;
                endWeek = 48;
                break;
            case 12:
                // December
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

                double weekTotal = rs.getDouble("salary");

                if (String.format("%.2f", weekTotal).equals("0,00")) {
                    Object[] rowData = {rs.getInt("weekNumber"), "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A"};
                    financeData.add(rowData);
                } else {
                    Object[] rowData = {
                        rs.getInt("weekNumber"),
                        rs.getInt("monday"),
                        rs.getInt("tuesday"),
                        rs.getInt("wednesday"),
                        rs.getInt("thursday"),
                        rs.getInt("friday"),
                        rs.getInt("saturday"),
                        rs.getInt("sunday"),
                        String.format("%.2f", weekTotal),
                        String.format("%.2f", afterTaxes(weekTotal))
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

    public double afterTaxes(double weekTotal) {
        if (weekTotal <= 740) {
            weekTotal = weekTotal - (weekTotal * 0.2);
        } else {
            weekTotal = weekTotal - (weekTotal * 0.4);
        }

        if (weekTotal <= 231) {
            weekTotal = weekTotal - (weekTotal * 0.05);
        } else if (weekTotal > 231 && weekTotal <= 447) {
            weekTotal = weekTotal - (weekTotal * 0.02);
        } else if (weekTotal > 447 && weekTotal <= 1038) {
            weekTotal = weekTotal - (weekTotal * 0.04);
        } else if (weekTotal > 1038) {
            weekTotal = weekTotal - (weekTotal * 0.08);
        }

        if (weekTotal > 441) {
            weekTotal = weekTotal - (weekTotal * 0.04);
        }

        return weekTotal;

    }

}
