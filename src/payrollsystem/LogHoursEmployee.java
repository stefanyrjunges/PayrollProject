/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package payrollsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.Timer;

/**
 *
 * @author tenhe
 */
public class LogHoursEmployee extends javax.swing.JFrame {
    private Timer timer;
    private final EmployeeDataFetcher dataFetcher = new EmployeeDataFetcher();
    private final EmployeeInfo employeeInfo = EmployeeInfo.getInstance();
    private int employeeId;

    /**
     * Creates new form LogHoursEmployee2
     */
    public LogHoursEmployee() {
        initComponents();
        dataFetcher.loadUserInformation("employee_id", "employee_logins", "employees");
        configureTimeSpinners();
        
        totalHoursMondayTF.setText("0.00 hours");
        totalHoursTuesdayTF.setText("0.00 hours");
        totalHoursWednesdayTF.setText("0.00 hours");
        totalHoursThursdayTF.setText("0.00 hours");
        totalHoursFridayTF.setText("0.00 hours");
        totalHoursSaturdayTF.setText("0.00 hours");
        totalHoursSundayTF.setText("0.00 hours");
        startClock();
         if (employeeId == 0) {
            employeeId = 1; // Default to ID 1 which exists in your database
            System.out.println("Defaulting to employee ID 1 for testing");
        }
        
        
    
        mondayCalculateBtn.addActionListener(e -> calculateDailyHours("Monday", 
            mondayTimeInSpinner, mondayTimeOutSpinner, mondayBreakTF, totalHoursMondayTF));
        tuesdayCalculateBtn.addActionListener(e -> calculateDailyHours("Tuesday", 
            tuesdayTimeInSpinner, tuesdayTimeOutSpinner, tuesdayBreakTF, totalHoursTuesdayTF));
        wednesdayCalculateBtn.addActionListener(e -> calculateDailyHours("Wednesday", 
            wednesdayTimeInSpinner, wednesdayTimeOutSpinner, wednesdayBreakTF, totalHoursWednesdayTF));
        thursdayCalculateBtn.addActionListener(e -> calculateDailyHours("Thursday", 
            thursdayTimeInSpinner, thursdayTimeOutSpinner, thursdayBreakTF, totalHoursThursdayTF));
        fridayCalculateBtn.addActionListener(e -> calculateDailyHours("Friday", 
            fridayTimeInSpinner, fridayTimeOutSpinner, fridayBreakTF, totalHoursFridayTF));
        saturdayCalculateBtn.addActionListener(e -> calculateDailyHours("Saturday", 
            saturdayTimeInSpinner, saturdayTimeOutSpinner, saturdayBreakTF, totalHoursSaturdayTF));
        sundayCalculateBtn.addActionListener(e -> calculateDailyHours("Sunday", 
            sundayTimeInSpinner, sundayTimeOutSpinner, sundayBreakTF, totalHoursSundayTF));
        
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
         weekStartDateTF.setText(sdf.format(getCurrentWeekMonday()));
    }
    private void startClock() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
                String timeString = sdf.format(new Date());
                clockLabel.setText(timeString); 
            }
        });
        timer.start();
    }
    
    
    
    private void configureTimeSpinners() {
    // Configure all time-in spinners
    configureSingleSpinner(mondayTimeInSpinner);
    configureSingleSpinner(tuesdayTimeInSpinner);
    configureSingleSpinner(wednesdayTimeInSpinner);
    configureSingleSpinner(thursdayTimeInSpinner);
    configureSingleSpinner(fridayTimeInSpinner);
    configureSingleSpinner(saturdayTimeInSpinner);
    configureSingleSpinner(sundayTimeInSpinner);
    
    // Configure all time-out spinners
    configureSingleSpinner(mondayTimeOutSpinner);
    configureSingleSpinner(tuesdayTimeOutSpinner);
    configureSingleSpinner(wednesdayTimeOutSpinner);
    configureSingleSpinner(thursdayTimeOutSpinner);
    configureSingleSpinner(fridayTimeOutSpinner);
    configureSingleSpinner(saturdayTimeOutSpinner);
    configureSingleSpinner(sundayTimeOutSpinner);
}
   
 private void configureSingleSpinner(JSpinner spinner) {
    // Create a calendar instance set to today at 00:00
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    
    // Create a spinner model that only handles time (not date)
    SpinnerDateModel model = new SpinnerDateModel(
        calendar.getTime(), // initial value
        null, // no minimum
        null, // no maximum
        Calendar.MINUTE // increment by minutes
    );
    spinner.setModel(model);
    
    // Set the editor to show only hours and minutes (24-hour format)
    JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
    spinner.setEditor(editor);
}
    
  
private void calculateDailyHours(String day, javax.swing.JSpinner timeIn, 
    javax.swing.JSpinner timeOut, javax.swing.JTextField breakField, javax.swing.JLabel totalLabel) {
    try {
        // Get the time values from spinners
        Date inTime = (Date) timeIn.getValue();
        Date outTime = (Date) timeOut.getValue();
        
         // Validate break time input
        String breakText = breakField.getText().trim();
        if (breakText.isEmpty()) {
            breakText = "0"; // Default to 0 if empty
        }
        
        double breakMinutes = Double.parseDouble(breakText);
        if (breakMinutes < 0) {
            throw new IllegalArgumentException("Break time cannot be negative");
        }
        double breakHours = breakMinutes / 60.0;
        
        // Extract just the time portions (ignoring date components)
        Calendar inCal = Calendar.getInstance();
        inCal.setTime(inTime);
        int inHour = inCal.get(Calendar.HOUR_OF_DAY);
        int inMinute = inCal.get(Calendar.MINUTE);
        
        Calendar outCal = Calendar.getInstance();
        outCal.setTime(outTime);
        int outHour = outCal.get(Calendar.HOUR_OF_DAY);
        int outMinute = outCal.get(Calendar.MINUTE);
        
        // Calculate total minutes worked
        int totalMinutes = (outHour * 60 + outMinute) - (inHour * 60 + inMinute);
        
        // Handle overnight shifts (if out time is earlier than in time)
        if (totalMinutes < 0) {
            totalMinutes += 24 * 60; // add 24 hours worth of minutes
        }
        
        // Convert to hours and subtract break time (which is now in hours)
        double totalHours = (totalMinutes / 60.0) - breakHours;
        
        // Display the result with 2 decimal places
        totalLabel.setText(String.format("%.2f hours", totalHours));
        
        // Update weekly total
        updateWeeklyTotal();
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error calculating hours: " + e.getMessage(),
                "Calculation Error", JOptionPane.ERROR_MESSAGE);
    }
}
 
    
    private void updateWeeklyTotal() {
        try {
            double total = 0;
            
            // Sum all daily totals
            total += parseLabelToHours(totalHoursMondayTF.getText());
            total += parseLabelToHours(totalHoursTuesdayTF.getText());
            total += parseLabelToHours(totalHoursWednesdayTF.getText());
            total += parseLabelToHours(totalHoursThursdayTF.getText());
            total += parseLabelToHours(totalHoursFridayTF.getText());
            total += parseLabelToHours(totalHoursSaturdayTF.getText());
            total += parseLabelToHours(totalHoursSundayTF.getText());
            
            totalWeeklyHoursTF.setText(String.format("%.2f", total));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error calculating weekly total: " + e.getMessage(),
                    "Calculation Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private double parseLabelToHours(String labelText) {
        if (labelText == null || labelText.isEmpty() || labelText.equals("jLabel1")) {
            return 0;
        }
        return Double.parseDouble(labelText.replace(" hours", "").trim());
    }
    
  
   
   private Date getCurrentWeekMonday() {
    // Get current date
    java.util.Calendar cal = java.util.Calendar.getInstance();
    
    // Set calendar to Monday of current week
    cal.set(java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.MONDAY);
    
    // Reset hours, minutes, seconds and milliseconds
    cal.set(java.util.Calendar.HOUR_OF_DAY, 0);
    cal.set(java.util.Calendar.MINUTE, 0);
    cal.set(java.util.Calendar.SECOND, 0);
    cal.set(java.util.Calendar.MILLISECOND, 0);
    
    return cal.getTime();
}
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        saturdayLabel = new javax.swing.JLabel();
        saturdayTimeInSpinner = new javax.swing.JSpinner();
        totalHoursWednesdayTF = new javax.swing.JLabel();
        totalHoursSundayTF = new javax.swing.JLabel();
        wednesdayCalculateBtn = new javax.swing.JButton();
        sundayCalculateBtn = new javax.swing.JButton();
        wednesdayTimeOutSpinner = new javax.swing.JSpinner();
        sundayTimeOutSpinner = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        wednesdayBreakTF = new javax.swing.JTextField();
        sundayBreakTF = new javax.swing.JTextField();
        totalHoursThursdayTF = new javax.swing.JLabel();
        tuesdayLabel5 = new javax.swing.JLabel();
        thursdayCalculateBtn = new javax.swing.JButton();
        sundayTimeInSpinner = new javax.swing.JSpinner();
        thursdayTimeOutSpinner = new javax.swing.JSpinner();
        saveWeeklyBtn = new javax.swing.JButton();
        thursdayBreakTF = new javax.swing.JTextField();
        weeklyTotalLabel = new javax.swing.JLabel();
        mondayBreakTF = new javax.swing.JTextField();
        tuesdayLabel2 = new javax.swing.JLabel();
        tuesdayBreakTF = new javax.swing.JTextField();
        thursdayTimeInSpinner = new javax.swing.JSpinner();
        mondayLabel = new javax.swing.JLabel();
        tuesdayLabel = new javax.swing.JLabel();
        totalWeeklyHoursTF = new javax.swing.JTextField();
        totalHoursFridayTF = new javax.swing.JLabel();
        fridayCalculateBtn = new javax.swing.JButton();
        mondayTimeInSpinner = new javax.swing.JSpinner();
        fridayTimeOutSpinner = new javax.swing.JSpinner();
        mondayTimeOutSpinner = new javax.swing.JSpinner();
        fridayBreakTF = new javax.swing.JTextField();
        tuesdayTimeInSpinner = new javax.swing.JSpinner();
        fridayLabel = new javax.swing.JLabel();
        tuesdayTimeOutSpinner = new javax.swing.JSpinner();
        fridayTimeInSpinner = new javax.swing.JSpinner();
        mondayCalculateBtn = new javax.swing.JButton();
        totalHoursSaturdayTF = new javax.swing.JLabel();
        totalHoursMondayTF = new javax.swing.JLabel();
        saturdayCalculateBtn = new javax.swing.JButton();
        totalHoursTuesdayTF = new javax.swing.JLabel();
        saturdayTimeOutSpinner = new javax.swing.JSpinner();
        tuesdayCalculateBtn = new javax.swing.JButton();
        saturdayBreakTF = new javax.swing.JTextField();
        wednesdayLabel = new javax.swing.JLabel();
        wednesdayTimeInSpinner = new javax.swing.JSpinner();
        weekStartDateLabel = new javax.swing.JLabel();
        weekStartDateTF = new javax.swing.JTextField();
        clockLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        saturdayLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        saturdayLabel.setText("Saturday");

        saturdayTimeInSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        saturdayTimeInSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));

        totalHoursWednesdayTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalHoursWednesdayTF.setText("jLabel1");

        totalHoursSundayTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalHoursSundayTF.setText("jLabel1");

        wednesdayCalculateBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        wednesdayCalculateBtn.setText("Calculate");

        sundayCalculateBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sundayCalculateBtn.setText("Calculate");

        wednesdayTimeOutSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        wednesdayTimeOutSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));

        sundayTimeOutSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sundayTimeOutSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));

        jPanel1.setBackground(new java.awt.Color(235, 142, 39));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("LOG HOURS EMPLOYEE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(660, 660, 660)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1570, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel9)
                .addGap(58, 58, 58))
        );

        wednesdayBreakTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        sundayBreakTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        totalHoursThursdayTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalHoursThursdayTF.setText("jLabel1");

        tuesdayLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tuesdayLabel5.setText("Sunday");

        thursdayCalculateBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        thursdayCalculateBtn.setText("Calculate");

        sundayTimeInSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sundayTimeInSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));

        thursdayTimeOutSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        thursdayTimeOutSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));

        saveWeeklyBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        saveWeeklyBtn.setText("Save");
        saveWeeklyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveWeeklyBtnActionPerformed(evt);
            }
        });

        thursdayBreakTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        weeklyTotalLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        weeklyTotalLabel.setText("Weekly total ");

        mondayBreakTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        tuesdayLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tuesdayLabel2.setText("Thursday");

        tuesdayBreakTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        thursdayTimeInSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        thursdayTimeInSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));

        mondayLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mondayLabel.setText("Monday");

        tuesdayLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tuesdayLabel.setText("Tuesday");

        totalWeeklyHoursTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        totalHoursFridayTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalHoursFridayTF.setText("jLabel1");

        fridayCalculateBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        fridayCalculateBtn.setText("Calculate");

        mondayTimeInSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mondayTimeInSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));

        fridayTimeOutSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        fridayTimeOutSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));

        mondayTimeOutSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mondayTimeOutSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        mondayTimeOutSpinner.setToolTipText("");

        fridayBreakTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        tuesdayTimeInSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tuesdayTimeInSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));

        fridayLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        fridayLabel.setText("Friday");

        tuesdayTimeOutSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tuesdayTimeOutSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));

        fridayTimeInSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        fridayTimeInSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));

        mondayCalculateBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mondayCalculateBtn.setText("Calculate");

        totalHoursSaturdayTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalHoursSaturdayTF.setText("jLabel1");

        totalHoursMondayTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalHoursMondayTF.setText("jlabekl");

        saturdayCalculateBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        saturdayCalculateBtn.setText("Calculate");

        totalHoursTuesdayTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalHoursTuesdayTF.setText("jLabel1");

        saturdayTimeOutSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        saturdayTimeOutSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));

        tuesdayCalculateBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tuesdayCalculateBtn.setText("Calculate");

        saturdayBreakTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        wednesdayLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        wednesdayLabel.setText("Wednesday");

        wednesdayTimeInSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        wednesdayTimeInSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));

        weekStartDateLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        weekStartDateLabel.setText("Week Number ");

        weekStartDateTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        clockLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        clockLabel.setText("00.00.00");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mondayLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mondayCalculateBtn)
                                .addGap(18, 18, 18)
                                .addComponent(totalHoursMondayTF))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mondayTimeInSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(mondayTimeOutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(mondayBreakTF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tuesdayLabel)
                            .addComponent(wednesdayLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(wednesdayTimeInSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(wednesdayTimeOutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(wednesdayBreakTF))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tuesdayTimeInSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tuesdayTimeOutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(tuesdayBreakTF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tuesdayCalculateBtn)
                                .addGap(18, 18, 18)
                                .addComponent(totalHoursTuesdayTF))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(wednesdayCalculateBtn)
                                .addGap(18, 18, 18)
                                .addComponent(totalHoursWednesdayTF)))
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(saturdayLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tuesdayLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(thursdayTimeInSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(thursdayTimeOutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(thursdayBreakTF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(thursdayCalculateBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(totalHoursThursdayTF))
                                    .addComponent(fridayLabel)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fridayTimeInSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(fridayTimeOutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(fridayBreakTF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(fridayCalculateBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(totalHoursFridayTF))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(saturdayTimeInSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(saturdayTimeOutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(saturdayBreakTF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(saturdayCalculateBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(totalHoursSaturdayTF)))
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(weekStartDateLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(weekStartDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(weeklyTotalLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(totalWeeklyHoursTF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(saveWeeklyBtn))
                                    .addComponent(tuesdayLabel5)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(sundayCalculateBtn)
                                        .addGap(18, 18, 18)
                                        .addComponent(totalHoursSundayTF))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(sundayTimeInSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(sundayTimeOutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(sundayBreakTF, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(72, 72, 72)
                                        .addComponent(clockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(mondayCalculateBtn)
                                    .addComponent(totalHoursMondayTF))
                                .addGap(44, 44, 44)
                                .addComponent(tuesdayLabel)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tuesdayTimeInSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tuesdayTimeOutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tuesdayBreakTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tuesdayCalculateBtn)
                                    .addComponent(totalHoursTuesdayTF)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(mondayLabel)
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(mondayTimeInSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mondayTimeOutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mondayBreakTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tuesdayLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(thursdayTimeInSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(thursdayTimeOutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(thursdayBreakTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(thursdayCalculateBtn)
                                    .addComponent(totalHoursThursdayTF))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(wednesdayLabel)
                                .addGap(11, 11, 11)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(wednesdayTimeInSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(wednesdayTimeOutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(wednesdayBreakTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(wednesdayCalculateBtn)
                                    .addComponent(totalHoursWednesdayTF)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(saturdayLabel)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(saturdayTimeInSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(saturdayTimeOutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(saturdayBreakTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(saturdayCalculateBtn)
                                    .addComponent(totalHoursSaturdayTF)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(clockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(166, 166, 166))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(137, 137, 137)
                                .addComponent(fridayLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tuesdayLabel5)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(sundayTimeInSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sundayTimeOutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(sundayBreakTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(sundayCalculateBtn)
                                    .addComponent(totalHoursSundayTF))))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fridayTimeInSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fridayTimeOutSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fridayBreakTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(fridayCalculateBtn)
                                    .addComponent(totalHoursFridayTF)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(weekStartDateLabel)
                                    .addComponent(weekStartDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(saveWeeklyBtn)
                                    .addComponent(totalWeeklyHoursTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(weeklyTotalLabel)))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveWeeklyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveWeeklyBtnActionPerformed
         java.sql.Connection conn = null;
        PreparedStatement pstmt = null;
        
        try {
            // Validate week start date
            String weekStartDateStr = weekStartDateTF.getText().trim();
            if (weekStartDateStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a week start date", 
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Parse the date
            java.sql.Date weekStartDate;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedDate = sdf.parse(weekStartDateStr);
                weekStartDate = new java.sql.Date(parsedDate.getTime());
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD", 
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Calculate week number from date
            Calendar cal = Calendar.getInstance();
            cal.setTime(weekStartDate);
            int weekNumber = cal.get(Calendar.WEEK_OF_YEAR);
            
            conn = DatabaseManager.getConnection();
            
            // SQL to insert or update weekly hours
            String sql = "INSERT INTO weekly_finance (employee_id, weekNumber, monday, tuesday, " +
                 "wednesday, thursday, friday, saturday, sunday, salary) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                 "ON DUPLICATE KEY UPDATE " +
                 "monday = VALUES(monday), " +
                 "tuesday = VALUES(tuesday), " +
                 "wednesday = VALUES(wednesday), " +
                 "thursday = VALUES(thursday), " +
                 "friday = VALUES(friday), " +
                 "saturday = VALUES(saturday), " +
                 "sunday = VALUES(sunday)";
            
            double defaultSalary = 0.0; // You can calculate this based on hours if needed

            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, employeeId);
            pstmt.setInt(2, weekNumber);
            pstmt.setDouble(3, parseLabelToHours(totalHoursMondayTF.getText()));
            pstmt.setDouble(4, parseLabelToHours(totalHoursTuesdayTF.getText()));
            pstmt.setDouble(5, parseLabelToHours(totalHoursWednesdayTF.getText()));
            pstmt.setDouble(6, parseLabelToHours(totalHoursThursdayTF.getText()));
            pstmt.setDouble(7, parseLabelToHours(totalHoursFridayTF.getText()));
            pstmt.setDouble(8, parseLabelToHours(totalHoursSaturdayTF.getText()));
            pstmt.setDouble(9, parseLabelToHours(totalHoursSundayTF.getText()));
            pstmt.setDouble(10, defaultSalary);
            
            int rowsAffected = pstmt.executeUpdate();
            
            if (rowsAffected > 0) {
                String message = String.format(
                    "Weekly hours saved successfully!\n" +
                    "Employee ID: %d\n" +
                    "Total hours: %s\n" +
                    "Week number: %d", 
                    employeeId,
                    totalWeeklyHoursTF.getText(),
                    weekNumber
                );
                
                JOptionPane.showMessageDialog(
                    this, 
                    message,
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE
                );
                
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to save hours", 
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number format in hours",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error closing database resources: " + e.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


// Add this helper method to clear the form after saving
private void clearForm() {
    // Reset all time spinners
    configureTimeSpinners();
    
    // Clear break fields
    mondayBreakTF.setText("");
    tuesdayBreakTF.setText("");
    wednesdayBreakTF.setText("");
    thursdayBreakTF.setText("");
    fridayBreakTF.setText("");
    saturdayBreakTF.setText("");
    sundayBreakTF.setText("");
    
    // Reset total labels
    totalHoursMondayTF.setText("0.00 hours");
    totalHoursTuesdayTF.setText("0.00 hours");
    totalHoursWednesdayTF.setText("0.00 hours");
    totalHoursThursdayTF.setText("0.00 hours");
    totalHoursFridayTF.setText("0.00 hours");
    totalHoursSaturdayTF.setText("0.00 hours");
    totalHoursSundayTF.setText("0.00 hours");
    
    // Reset weekly total
    totalWeeklyHoursTF.setText("0.00");

    }//GEN-LAST:event_saveWeeklyBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LogHoursEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogHoursEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogHoursEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogHoursEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogHoursEmployee().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clockLabel;
    private javax.swing.JTextField fridayBreakTF;
    private javax.swing.JButton fridayCalculateBtn;
    private javax.swing.JLabel fridayLabel;
    private javax.swing.JSpinner fridayTimeInSpinner;
    private javax.swing.JSpinner fridayTimeOutSpinner;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField mondayBreakTF;
    private javax.swing.JButton mondayCalculateBtn;
    private javax.swing.JLabel mondayLabel;
    private javax.swing.JSpinner mondayTimeInSpinner;
    private javax.swing.JSpinner mondayTimeOutSpinner;
    private javax.swing.JTextField saturdayBreakTF;
    private javax.swing.JButton saturdayCalculateBtn;
    private javax.swing.JLabel saturdayLabel;
    private javax.swing.JSpinner saturdayTimeInSpinner;
    private javax.swing.JSpinner saturdayTimeOutSpinner;
    private javax.swing.JButton saveWeeklyBtn;
    private javax.swing.JTextField sundayBreakTF;
    private javax.swing.JButton sundayCalculateBtn;
    private javax.swing.JSpinner sundayTimeInSpinner;
    private javax.swing.JSpinner sundayTimeOutSpinner;
    private javax.swing.JTextField thursdayBreakTF;
    private javax.swing.JButton thursdayCalculateBtn;
    private javax.swing.JSpinner thursdayTimeInSpinner;
    private javax.swing.JSpinner thursdayTimeOutSpinner;
    private javax.swing.JLabel totalHoursFridayTF;
    private javax.swing.JLabel totalHoursMondayTF;
    private javax.swing.JLabel totalHoursSaturdayTF;
    private javax.swing.JLabel totalHoursSundayTF;
    private javax.swing.JLabel totalHoursThursdayTF;
    private javax.swing.JLabel totalHoursTuesdayTF;
    private javax.swing.JLabel totalHoursWednesdayTF;
    private javax.swing.JTextField totalWeeklyHoursTF;
    private javax.swing.JTextField tuesdayBreakTF;
    private javax.swing.JButton tuesdayCalculateBtn;
    private javax.swing.JLabel tuesdayLabel;
    private javax.swing.JLabel tuesdayLabel2;
    private javax.swing.JLabel tuesdayLabel5;
    private javax.swing.JSpinner tuesdayTimeInSpinner;
    private javax.swing.JSpinner tuesdayTimeOutSpinner;
    private javax.swing.JTextField wednesdayBreakTF;
    private javax.swing.JButton wednesdayCalculateBtn;
    private javax.swing.JLabel wednesdayLabel;
    private javax.swing.JSpinner wednesdayTimeInSpinner;
    private javax.swing.JSpinner wednesdayTimeOutSpinner;
    private javax.swing.JLabel weekStartDateLabel;
    private javax.swing.JTextField weekStartDateTF;
    private javax.swing.JLabel weeklyTotalLabel;
    // End of variables declaration//GEN-END:variables
}
