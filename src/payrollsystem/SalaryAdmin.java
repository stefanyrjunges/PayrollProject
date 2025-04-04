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

        //SQL query to retrieve weekly financial data based on employee ID and week
        String financeQuery = "SELECT weekNumber, monday, tuesday, wednesday, thursday, friday, saturday, sunday, salary "
                + "FROM weekly_finance WHERE employee_id = ? AND weekNumber BETWEEN ? AND ?";

        //Array to store weekly financial data retrieved from the database.
        List<Object[]> financeData = new ArrayList<>();

        int startWeek = 0;
        int endWeek = 0;

        //Mapping months to their corresponding week numbers
        switch (month) {
            case 1:
                //January
                startWeek = 1;
                endWeek = 4;
                break;
            case 2:
                //February
                startWeek = 5;
                endWeek = 8;
                break;
            case 3:
                //March
                startWeek = 9;
                endWeek = 13;
                break;
            case 4:
                //April
                startWeek = 14;
                endWeek = 17;
                break;
            case 5:
                //May
                startWeek = 18;
                endWeek = 21;
                break;
            case 6:
                //June
                startWeek = 22;
                endWeek = 26;
                break;
            case 7:
                //July
                startWeek = 27;
                endWeek = 30;
                break;
            case 8:
                //August
                startWeek = 31;
                endWeek = 35;
                break;
            case 9:
                //September
                startWeek = 36;
                endWeek = 39;
                break;
            case 10:
                //October
                startWeek = 40;
                endWeek = 44;
                break;
            case 11:
                //November
                startWeek = 45;
                endWeek = 48;
                break;
            case 12:
                //December
                startWeek = 49;
                endWeek = 52;
                break;
            default:
                System.out.println("Invalid month: " + month);
                return financeData;
        }

        System.out.println("Fetching data for Employee ID: " + employeeId);
        System.out.println("Month: " + month + " (Weeks: " + startWeek + " to " + endWeek + ")");

        //Establishing connection and preparing the SQL query for execution
        try (Connection con = DatabaseManager.getConnection(); PreparedStatement ps = con.prepareStatement(financeQuery)) {

            //Parameters for the query
            ps.setInt(1, employeeId);
            ps.setInt(2, startWeek);
            ps.setInt(3, endWeek);

            //Executes the SQL query and stores the result set containing the retrieved data
            ResultSet rs = ps.executeQuery();

            //Processing query results
            while (rs.next()) {

                double weekTotal = rs.getDouble("salary");

                //If salary is zero, return N/A (Non-Available) for all days
                if (String.format("%.2f", weekTotal).equals("0,00")) {
                    Object[] rowData = {rs.getInt("weekNumber"), "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A", "N/A"};
                    financeData.add(rowData);
                } else {
                    //Storing weekly financial data
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

        //Income tax rate
        if (weekTotal <= 740) {
            weekTotal = weekTotal - (weekTotal * 0.2); //20% tax for income <= 740        } else {
            weekTotal = weekTotal - (weekTotal * 0.4); //40% tax for income > 740
        }

        //Universal Social Charge (USC) deductions
        if (weekTotal <= 231) {
            weekTotal = weekTotal - (weekTotal * 0.05); //5% for income <= 231
        } else if (weekTotal > 231 && weekTotal <= 447) {
            weekTotal = weekTotal - (weekTotal * 0.02); //2% for income between 231 and 447
        } else if (weekTotal > 447 && weekTotal <= 1038) {
            weekTotal = weekTotal - (weekTotal * 0.04); //4% for income between 447 1038
        } else if (weekTotal > 1038) {
            weekTotal = weekTotal - (weekTotal * 0.08); //8% for income > 1038
        }

        //Pay-Related Social Insurance (PRSI) deduction
        if (weekTotal > 441) {
            weekTotal = weekTotal - (weekTotal * 0.04); //4% PRSI for income > 441
        }

        return weekTotal;

    }

}
