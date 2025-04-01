 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package payrollsystem;

public class HoursSubmissionData {
    
    private int employeeId, weekNumber;
    private double totalHours, salary;
    private String workedDays, employeeName;
    private boolean isPending;
    
    public HoursSubmissionData(){
    }

    public HoursSubmissionData(int employeeId, String employeeName, int weekNumber, double totalHours, double salary, String workedDays, boolean isPending) {
        this.employeeId = employeeId;
        this.weekNumber = weekNumber;
        this.totalHours = totalHours;
        this.salary = salary;
        this.workedDays = workedDays;
        this.employeeName = employeeName;
        this.isPending = isPending;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setWorkedDays(String workedDays) {
        this.workedDays = workedDays;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setIsPending(boolean isPending) {
        this.isPending = isPending;
    }
    
    public int getEmployeeId() { 
        return employeeId;
    }
    
    public int getWeekNumber() {
        return weekNumber;
    }
    
    public double getTotalHours() {
        return totalHours;
    }
    
    public double getSalary() {
        return salary;
    }
    
    public String getWorkedDays() {
        return workedDays;
    }

    public String getEmployeeName(){
        return employeeName;
    }
    
    public boolean isPending() {
        return isPending;
    }
}