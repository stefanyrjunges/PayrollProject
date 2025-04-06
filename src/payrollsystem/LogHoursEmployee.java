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
 * @author Filip
 */
public class LogHoursEmployee extends javax.swing.JFrame {

    private Timer timer;
    private final EmployeeDataFetcher dataFetcher = new EmployeeDataFetcher();
    private final EmployeeInfo employeeInfo = EmployeeInfo.getInstance();
    private int employeeId;
    private final SalaryAdmin salaryAdm = new SalaryAdmin();

    public LogHoursEmployee() {
        initComponents();

        employeeId = employeeInfo.getId();
        if (employeeId == 0) {
            JOptionPane.showMessageDialog(this, "No employee ID found. Please log in again.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            this.dispose();
            return;
        }

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

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        weekStartDateTF.setText(sdf.format(getCurrentWeekMonday()));

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
        configureSingleSpinner(mondayTimeInSpinner);
        configureSingleSpinner(tuesdayTimeInSpinner);
        configureSingleSpinner(wednesdayTimeInSpinner);
        configureSingleSpinner(thursdayTimeInSpinner);
        configureSingleSpinner(fridayTimeInSpinner);
        configureSingleSpinner(saturdayTimeInSpinner);
        configureSingleSpinner(sundayTimeInSpinner);

        configureSingleSpinner(mondayTimeOutSpinner);
        configureSingleSpinner(tuesdayTimeOutSpinner);
        configureSingleSpinner(wednesdayTimeOutSpinner);
        configureSingleSpinner(thursdayTimeOutSpinner);
        configureSingleSpinner(fridayTimeOutSpinner);
        configureSingleSpinner(saturdayTimeOutSpinner);
        configureSingleSpinner(sundayTimeOutSpinner);
    }

    private void configureSingleSpinner(JSpinner spinner) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        SpinnerDateModel model = new SpinnerDateModel(
                calendar.getTime(),
                null,
                null,
                Calendar.MINUTE
        );
        spinner.setModel(model);

        JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm");
        spinner.setEditor(editor);
    }

    private void calculateDailyHours(String day, JSpinner timeIn,
            JSpinner timeOut, javax.swing.JTextField breakField, javax.swing.JLabel totalLabel) {
        try {
            Date inTime = (Date) timeIn.getValue();
            Date outTime = (Date) timeOut.getValue();

            String breakText = breakField.getText().trim();
            if (breakText.isEmpty()) {
                breakText = "0";
            }

            double breakMinutes = Double.parseDouble(breakText);
            if (breakMinutes < 0) {
                throw new IllegalArgumentException("Break time cannot be negative");
            }
            double breakHours = breakMinutes / 60.0;

            Calendar inCal = Calendar.getInstance();
            inCal.setTime(inTime);
            int inHour = inCal.get(Calendar.HOUR_OF_DAY);
            int inMinute = inCal.get(Calendar.MINUTE);

            Calendar outCal = Calendar.getInstance();
            outCal.setTime(outTime);
            int outHour = outCal.get(Calendar.HOUR_OF_DAY);
            int outMinute = outCal.get(Calendar.MINUTE);

            int totalMinutes = (outHour * 60 + outMinute) - (inHour * 60 + inMinute);

            if (totalMinutes < 0) {
                totalMinutes += 24 * 60;
            }

            double totalHours = (totalMinutes / 60.0) - breakHours;

            totalLabel.setText(String.format("%.2f hours", totalHours));
            updateWeeklyTotal();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error calculating hours: " + e.getMessage(),
                    "Calculation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateWeeklyTotal() {
        try {
            double total = 0;

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
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        mondayBreakTF = new javax.swing.JTextField();
        fridayLabel = new javax.swing.JLabel();
        clockLabel = new javax.swing.JLabel();
        totalHoursFridayTF = new javax.swing.JLabel();
        totalHoursSaturdayTF = new javax.swing.JLabel();
        weekStartDateTF = new javax.swing.JTextField();
        mondayTimeInSpinner = new javax.swing.JSpinner();
        thursdayTimeOutSpinner = new javax.swing.JSpinner();
        thursdayBreakTF = new javax.swing.JTextField();
        totalHoursTuesdayTF = new javax.swing.JLabel();
        totalHoursThursdayTF = new javax.swing.JLabel();
        sundayBreakTF = new javax.swing.JTextField();
        tuesdayLabel5 = new javax.swing.JLabel();
        fridayTimeInSpinner = new javax.swing.JSpinner();
        mondayCalculateBtn = new javax.swing.JButton();
        sundayTimeOutSpinner = new javax.swing.JSpinner();
        fridayTimeOutSpinner = new javax.swing.JSpinner();
        tuesdayLabel = new javax.swing.JLabel();
        tuesdayTimeOutSpinner = new javax.swing.JSpinner();
        thursdayTimeInSpinner = new javax.swing.JSpinner();
        totalHoursMondayTF = new javax.swing.JLabel();
        wednesdayBreakTF = new javax.swing.JTextField();
        mondayLabel = new javax.swing.JLabel();
        sundayTimeInSpinner = new javax.swing.JSpinner();
        saturdayBreakTF = new javax.swing.JTextField();
        tuesdayLabel2 = new javax.swing.JLabel();
        tuesdayBreakTF = new javax.swing.JTextField();
        tuesdayTimeInSpinner = new javax.swing.JSpinner();
        wednesdayTimeInSpinner = new javax.swing.JSpinner();
        wednesdayLabel = new javax.swing.JLabel();
        weeklyTotalLabel = new javax.swing.JLabel();
        saveWeeklyBtn = new javax.swing.JButton();
        mondayTimeOutSpinner = new javax.swing.JSpinner();
        fridayBreakTF = new javax.swing.JTextField();
        totalWeeklyHoursTF = new javax.swing.JTextField();
        weekStartDateLabel = new javax.swing.JLabel();
        saturdayTimeOutSpinner = new javax.swing.JSpinner();
        wednesdayTimeOutSpinner = new javax.swing.JSpinner();
        totalHoursSundayTF = new javax.swing.JLabel();
        totalHoursWednesdayTF = new javax.swing.JLabel();
        saturdayTimeInSpinner = new javax.swing.JSpinner();
        saturdayLabel = new javax.swing.JLabel();
        instructionsLBL = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tuesdayCalculateBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        returnBTN = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        thursdayCalculateBtn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        fridayCalculateBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        saturdayCalculateBtn = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        sundayCalculateBtn = new javax.swing.JButton();
        wednesdayCalculateBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(235, 142, 39));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("CLOCK IN/CLOCK OUT SUBMISSION");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1372, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(62, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(56, 56, 56))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(1920, 1080));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mondayBreakTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel2.add(mondayBreakTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 96, 230, -1));

        fridayLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        fridayLabel.setForeground(new java.awt.Color(81, 81, 81));
        fridayLabel.setText("FRIDAY");
        jPanel2.add(fridayLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 240, 73, -1));

        clockLabel.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        clockLabel.setText("00.00.00");
        jPanel2.add(clockLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1360, 240, 226, 20));

        totalHoursFridayTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalHoursFridayTF.setText("jLabel1");
        jPanel2.add(totalHoursFridayTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 370, -1, -1));

        totalHoursSaturdayTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalHoursSaturdayTF.setText("jLabel1");
        jPanel2.add(totalHoursSaturdayTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 560, -1, -1));

        weekStartDateTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel2.add(weekStartDateTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 240, 162, -1));

        mondayTimeInSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mondayTimeInSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        jPanel2.add(mondayTimeInSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 62, -1, -1));

        thursdayTimeOutSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        thursdayTimeOutSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        jPanel2.add(thursdayTimeOutSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 60, -1, -1));

        thursdayBreakTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel2.add(thursdayBreakTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 100, 180, -1));

        totalHoursTuesdayTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalHoursTuesdayTF.setText("jLabel1");
        jPanel2.add(totalHoursTuesdayTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        totalHoursThursdayTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalHoursThursdayTF.setText("jLabel1");
        jPanel2.add(totalHoursThursdayTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 180, -1, -1));

        sundayBreakTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel2.add(sundayBreakTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 100, 170, -1));

        tuesdayLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tuesdayLabel5.setForeground(new java.awt.Color(81, 81, 81));
        tuesdayLabel5.setText("SUNDAY");
        jPanel2.add(tuesdayLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 60, -1, -1));

        fridayTimeInSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        fridayTimeInSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        jPanel2.add(fridayTimeInSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 242, -1, 20));

        mondayCalculateBtn.setBackground(new java.awt.Color(237, 170, 12));
        mondayCalculateBtn.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        mondayCalculateBtn.setForeground(new java.awt.Color(255, 255, 255));
        mondayCalculateBtn.setText("CALCULATE");
        mondayCalculateBtn.setBorder(null);
        mondayCalculateBtn.setBorderPainted(false);
        jPanel2.add(mondayCalculateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 136, 330, 22));

        sundayTimeOutSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sundayTimeOutSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        jPanel2.add(sundayTimeOutSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 60, -1, -1));

        fridayTimeOutSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        fridayTimeOutSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        jPanel2.add(fridayTimeOutSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 240, -1, -1));

        tuesdayLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tuesdayLabel.setForeground(new java.awt.Color(81, 81, 81));
        tuesdayLabel.setText("TUESDAY");
        jPanel2.add(tuesdayLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 248, 97, -1));

        tuesdayTimeOutSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tuesdayTimeOutSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        jPanel2.add(tuesdayTimeOutSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 247, -1, -1));

        thursdayTimeInSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        thursdayTimeInSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        jPanel2.add(thursdayTimeInSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 60, -1, -1));

        totalHoursMondayTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalHoursMondayTF.setText("jlabekl");
        jPanel2.add(totalHoursMondayTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 182, 294, -1));

        wednesdayBreakTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel2.add(wednesdayBreakTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 470, 210, -1));

        mondayLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        mondayLabel.setForeground(new java.awt.Color(81, 81, 81));
        mondayLabel.setText("MONDAY");
        jPanel2.add(mondayLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 63, -1, -1));

        sundayTimeInSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        sundayTimeInSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        jPanel2.add(sundayTimeInSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 60, -1, -1));

        saturdayBreakTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel2.add(saturdayBreakTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 470, 190, -1));

        tuesdayLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        tuesdayLabel2.setForeground(new java.awt.Color(81, 81, 81));
        tuesdayLabel2.setText("THURSDAY");
        jPanel2.add(tuesdayLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, -1, -1));

        tuesdayBreakTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel2.add(tuesdayBreakTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 220, -1));

        tuesdayTimeInSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        tuesdayTimeInSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        jPanel2.add(tuesdayTimeInSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 247, -1, -1));

        wednesdayTimeInSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        wednesdayTimeInSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        jPanel2.add(wednesdayTimeInSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, -1, -1));

        wednesdayLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        wednesdayLabel.setForeground(new java.awt.Color(81, 81, 81));
        wednesdayLabel.setText("WEDNESDAY");
        jPanel2.add(wednesdayLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, -1, -1));

        weeklyTotalLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        weeklyTotalLabel.setForeground(new java.awt.Color(81, 81, 81));
        weeklyTotalLabel.setText("TOTAL HOURS:");
        jPanel2.add(weeklyTotalLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 280, -1, -1));

        saveWeeklyBtn.setBackground(new java.awt.Color(237, 170, 12));
        saveWeeklyBtn.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        saveWeeklyBtn.setForeground(new java.awt.Color(255, 255, 255));
        saveWeeklyBtn.setText("SAVE");
        saveWeeklyBtn.setBorder(null);
        saveWeeklyBtn.setBorderPainted(false);
        saveWeeklyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveWeeklyBtnActionPerformed(evt);
            }
        });
        jPanel2.add(saveWeeklyBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 320, 280, 22));

        mondayTimeOutSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        mondayTimeOutSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        mondayTimeOutSpinner.setToolTipText("");
        jPanel2.add(mondayTimeOutSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(306, 62, -1, -1));

        fridayBreakTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel2.add(fridayBreakTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 280, 200, -1));

        totalWeeklyHoursTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jPanel2.add(totalWeeklyHoursTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 280, 162, -1));

        weekStartDateLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        weekStartDateLabel.setForeground(new java.awt.Color(81, 81, 81));
        weekStartDateLabel.setText("CURRENT DATE:");
        jPanel2.add(weekStartDateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 240, -1, -1));

        saturdayTimeOutSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        saturdayTimeOutSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        jPanel2.add(saturdayTimeOutSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 430, -1, -1));

        wednesdayTimeOutSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        wednesdayTimeOutSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        jPanel2.add(wednesdayTimeOutSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 430, -1, -1));

        totalHoursSundayTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalHoursSundayTF.setText("jLabel1");
        jPanel2.add(totalHoursSundayTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 180, -1, -1));

        totalHoursWednesdayTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        totalHoursWednesdayTF.setText("jLabel1");
        jPanel2.add(totalHoursWednesdayTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, -1, -1));

        saturdayTimeInSpinner.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        saturdayTimeInSpinner.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.AM_PM));
        jPanel2.add(saturdayTimeInSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 430, -1, -1));

        saturdayLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        saturdayLabel.setForeground(new java.awt.Color(81, 81, 81));
        saturdayLabel.setText("SATURDAY");
        jPanel2.add(saturdayLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 430, -1, -1));

        instructionsLBL.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        instructionsLBL.setForeground(new java.awt.Color(81, 81, 81));
        instructionsLBL.setText("Please input your start time, end time and break for each day");
        jPanel2.add(instructionsLBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 25, 435, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(81, 81, 81));
        jLabel1.setText("BREAK (min):");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 97, 100, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(81, 81, 81));
        jLabel2.setText("BREAK (min):");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, 100, -1));

        tuesdayCalculateBtn.setBackground(new java.awt.Color(237, 170, 12));
        tuesdayCalculateBtn.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tuesdayCalculateBtn.setForeground(new java.awt.Color(255, 255, 255));
        tuesdayCalculateBtn.setText("CALCULATE");
        tuesdayCalculateBtn.setBorder(null);
        tuesdayCalculateBtn.setBorderPainted(false);
        tuesdayCalculateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tuesdayCalculateBtnActionPerformed(evt);
            }
        });
        jPanel2.add(tuesdayCalculateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 320, 22));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(81, 81, 81));
        jLabel3.setText("BREAK (min):");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 470, -1, -1));

        returnBTN.setBackground(new java.awt.Color(237, 170, 12));
        returnBTN.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        returnBTN.setForeground(new java.awt.Color(255, 255, 255));
        returnBTN.setText("RETURN TO DASHBOARD");
        returnBTN.setBorder(null);
        returnBTN.setBorderPainted(false);
        returnBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnBTNActionPerformed(evt);
            }
        });
        jPanel2.add(returnBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 670, 320, 22));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(81, 81, 81));
        jLabel4.setText("BREAK (min):");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 100, 100, -1));

        thursdayCalculateBtn.setBackground(new java.awt.Color(237, 170, 12));
        thursdayCalculateBtn.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        thursdayCalculateBtn.setForeground(new java.awt.Color(255, 255, 255));
        thursdayCalculateBtn.setText("CALCULATE");
        thursdayCalculateBtn.setBorder(null);
        thursdayCalculateBtn.setBorderPainted(false);
        jPanel2.add(thursdayCalculateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 140, 280, 22));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(81, 81, 81));
        jLabel5.setText("BREAK (min):");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 280, 100, -1));

        fridayCalculateBtn.setBackground(new java.awt.Color(237, 170, 12));
        fridayCalculateBtn.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        fridayCalculateBtn.setForeground(new java.awt.Color(255, 255, 255));
        fridayCalculateBtn.setText("CALCULATE");
        fridayCalculateBtn.setBorder(null);
        fridayCalculateBtn.setBorderPainted(false);
        fridayCalculateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fridayCalculateBtnActionPerformed(evt);
            }
        });
        jPanel2.add(fridayCalculateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 330, 280, 22));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(81, 81, 81));
        jLabel6.setText("BREAK (min):");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 470, -1, -1));

        saturdayCalculateBtn.setBackground(new java.awt.Color(237, 170, 12));
        saturdayCalculateBtn.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        saturdayCalculateBtn.setForeground(new java.awt.Color(255, 255, 255));
        saturdayCalculateBtn.setText("CALCULATE");
        saturdayCalculateBtn.setBorder(null);
        saturdayCalculateBtn.setBorderPainted(false);
        jPanel2.add(saturdayCalculateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 520, 290, 22));

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(81, 81, 81));
        jLabel7.setText("BREAK (min):");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 100, 100, -1));

        sundayCalculateBtn.setBackground(new java.awt.Color(237, 170, 12));
        sundayCalculateBtn.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        sundayCalculateBtn.setForeground(new java.awt.Color(255, 255, 255));
        sundayCalculateBtn.setText("CALCULATE");
        sundayCalculateBtn.setBorder(null);
        sundayCalculateBtn.setBorderPainted(false);
        jPanel2.add(sundayCalculateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 140, 250, 22));

        wednesdayCalculateBtn.setBackground(new java.awt.Color(237, 170, 12));
        wednesdayCalculateBtn.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        wednesdayCalculateBtn.setForeground(new java.awt.Color(255, 255, 255));
        wednesdayCalculateBtn.setText("CALCULATE");
        wednesdayCalculateBtn.setBorder(null);
        wednesdayCalculateBtn.setBorderPainted(false);
        wednesdayCalculateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wednesdayCalculateBtnActionPerformed(evt);
            }
        });
        jPanel2.add(wednesdayCalculateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 520, 320, 22));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 909, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveWeeklyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveWeeklyBtnActionPerformed
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            String weekStartDateStr = weekStartDateTF.getText().trim();
            if (weekStartDateStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a week start date",
                        "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

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

            Calendar cal = Calendar.getInstance();
            cal.setTime(weekStartDate);
            int weekNumber = cal.get(Calendar.WEEK_OF_YEAR);

            conn = DatabaseManager.getConnection();

            String sql = "UPDATE weekly_finance SET "
                    + "monday = ?, "
                    + "tuesday = ?, "
                    + "wednesday = ?, "
                    + "thursday = ?, "
                    + "friday = ?, "
                    + "saturday = ?, "
                    + "sunday = ?, "
                    + "salary = ?, "
                    + "rejection_reason = null, "
                    + "status = ?, "
                    + "notified = ? "
                    + "WHERE employee_id = ? AND weekNumber = ?";

            pstmt = conn.prepareStatement(sql);

            double defaultSalary = 0.0;

            pstmt.setDouble(1, parseLabelToHours(totalHoursMondayTF.getText()));
            pstmt.setDouble(2, parseLabelToHours(totalHoursTuesdayTF.getText()));
            pstmt.setDouble(3, parseLabelToHours(totalHoursWednesdayTF.getText()));
            pstmt.setDouble(4, parseLabelToHours(totalHoursThursdayTF.getText()));
            pstmt.setDouble(5, parseLabelToHours(totalHoursFridayTF.getText()));
            pstmt.setDouble(6, parseLabelToHours(totalHoursSaturdayTF.getText()));
            pstmt.setDouble(7, parseLabelToHours(totalHoursSundayTF.getText()));
            pstmt.setDouble(8, defaultSalary);
            pstmt.setString(9, "pending");
            pstmt.setInt(10, 0);
            pstmt.setInt(11, employeeId);
            pstmt.setInt(12, weekNumber);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                String message = String.format(
                        "Weekly hours updated successfully!\n"
                        + "Employee ID: %d\n"
                        + "Total hours: %s\n"
                        + "Week number: %d",
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
                JOptionPane.showMessageDialog(this, "No existing record found to update for this employee and week",
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
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error closing database resources: " + e.getMessage(),
                        "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void clearForm() {
        configureTimeSpinners();

        mondayBreakTF.setText("");
        tuesdayBreakTF.setText("");
        wednesdayBreakTF.setText("");
        thursdayBreakTF.setText("");
        fridayBreakTF.setText("");
        saturdayBreakTF.setText("");
        sundayBreakTF.setText("");

        totalHoursMondayTF.setText("0.00 hours");
        totalHoursTuesdayTF.setText("0.00 hours");
        totalHoursWednesdayTF.setText("0.00 hours");
        totalHoursThursdayTF.setText("0.00 hours");
        totalHoursFridayTF.setText("0.00 hours");
        totalHoursSaturdayTF.setText("0.00 hours");
        totalHoursSundayTF.setText("0.00 hours");

        totalWeeklyHoursTF.setText("0.00");

    }//GEN-LAST:event_saveWeeklyBtnActionPerformed

    private void returnBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnBTNActionPerformed
        EmployeeGUI empDashboard = new EmployeeGUI();
        empDashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_returnBTNActionPerformed

    private void wednesdayCalculateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wednesdayCalculateBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wednesdayCalculateBtnActionPerformed

    private void tuesdayCalculateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tuesdayCalculateBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tuesdayCalculateBtnActionPerformed

    private void fridayCalculateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fridayCalculateBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fridayCalculateBtnActionPerformed

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
    private javax.swing.JLabel instructionsLBL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField mondayBreakTF;
    private javax.swing.JButton mondayCalculateBtn;
    private javax.swing.JLabel mondayLabel;
    private javax.swing.JSpinner mondayTimeInSpinner;
    private javax.swing.JSpinner mondayTimeOutSpinner;
    private javax.swing.JButton returnBTN;
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
