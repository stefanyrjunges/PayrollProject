/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package payrollsystem;

import java.awt.Dimension;
import java.awt.Font;
import java.security.SecureRandom;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;

/**
 *
 * @author tenhe
 */
public class ManageEmployee extends javax.swing.JFrame {
    
   private DefaultTableModel tableModel;
   private Connection connection;
   private EmployeeInfo employeeInfo = EmployeeInfo.getInstance();
   private final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
   private final SecureRandom random = new SecureRandom();

    /**
     * Creates new form EmployeeSection
     */
     public ManageEmployee() {
        initComponents();
        connectToDatabase();
        addTableClickListener();
        
        // Set table model with the appropriate column names
         tableModel = new DefaultTableModel(
        new Object[][] {},
         new String[] { "ID", "Name", "Surname", "Position", "Rate", "Address", "Phone", "Email", "Hire Date" }
            );
        employeeTable.setModel(tableModel);

        
        // Fetch employees after the table model is set
        fetchEmployees();

        // Set window dimensions and properties
        setPreferredSize(new Dimension(1520, 715));
        setMinimumSize(new Dimension(1520, 715)); // Prevent window from shrinking too small
        setTitle("Employee Management");
        setResizable(true);
    }

    // Database connection method
    public void connectToDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load the MySQL driver
            connection = DriverManager.getConnection(
                "jdbc:mysql://database-1.cvu4aceii2zn.eu-west-1.rds.amazonaws.com:3306/Payroll", 
                "root", 
                "payrollpassword"
            );
            System.out.println("Database connected successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database connection failed!");
        }
    }       

    // Add a listener to the employee table rows for clicking
    private void addTableClickListener() {
    employeeTable.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int selectedRow = employeeTable.getSelectedRow(); // Get the selected row index

            if (selectedRow != -1) {
                // Populate text fields with the selected row's data
                empIdTF.setText(tableModel.getValueAt(selectedRow, 0).toString()); // ID
                fnameTF.setText(tableModel.getValueAt(selectedRow, 1).toString()); // First Name
                lnameTF.setText(tableModel.getValueAt(selectedRow, 2).toString()); // Last Name
                positionTF.setText(tableModel.getValueAt(selectedRow, 3).toString()); // Position
                rateTF.setText(tableModel.getValueAt(selectedRow, 4).toString()); // Rate
                addressTF.setText(tableModel.getValueAt(selectedRow, 5).toString()); // Address
                phoneNumberTF.setText(tableModel.getValueAt(selectedRow, 6).toString()); // Phone Number
                emailTF.setText(tableModel.getValueAt(selectedRow, 7).toString()); // Email
                hireDateTF.setText(tableModel.getValueAt(selectedRow, 8).toString()); // Hire Date
            }
        }
    });
}

    // Method to fetch employees from the database and populate the table
    public void fetchEmployees() {
    try {
        String sql = "SELECT employee_id, fname, lname, position, rate, address, phoneN, email, hire_date FROM employees";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // Clear the table first
        tableModel.setRowCount(0);

        // Populate the table with fetched data
        while (resultSet.next()) {
            String employeeId = resultSet.getString("employee_id");
            String fname = resultSet.getString("fname");
            String lname = resultSet.getString("lname");
            String position = resultSet.getString("position");
            String rate = resultSet.getString("rate");
            String address = resultSet.getString("address");
            String phoneN = resultSet.getString("phoneN");  // Correct phone number column
            String email = resultSet.getString("email");    // Correct email column
            String hireDate = resultSet.getString("hire_date");  // Correct hire date column

            // Add the data to the table model
            tableModel.addRow(new Object[]{
                employeeId, fname, lname, position, rate, address, phoneN, email, hireDate
            });
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching employees: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        iconLBL = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        employeeTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        searchTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fnameTF = new javax.swing.JTextField();
        clearBTN = new javax.swing.JButton();
        addressTF = new javax.swing.JTextField();
        empIdTF = new javax.swing.JTextField();
        positionTF = new javax.swing.JTextField();
        salaryLBL = new javax.swing.JLabel();
        hireDateTF = new javax.swing.JTextField();
        emailTF = new javax.swing.JTextField();
        editBTN = new javax.swing.JButton();
        deleteBTN = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lnameTF = new javax.swing.JTextField();
        idLBL = new javax.swing.JLabel();
        nameLBL = new javax.swing.JLabel();
        positionLBL = new javax.swing.JLabel();
        searchLBL = new javax.swing.JLabel();
        searchBTN = new javax.swing.JButton();
        addBTN = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        rateTF = new javax.swing.JTextField();
        phoneNumberTF = new javax.swing.JTextField();
        backToMainBTN = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1920, 1080));

        jPanel1.setBackground(new java.awt.Color(12, 48, 128));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MANAGE EMPLOYEE");

        iconLBL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payrollsystem/PAYROLL_LOGO_m.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel1)
                .addGap(988, 988, 988)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(iconLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1990, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(iconLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Surname", "Position", "Rate", "Address", "Email", "Phone", "Hire-Date"
            }
        ));
        employeeTable.setRowHeight(60);
        jScrollPane1.setViewportView(employeeTable);

        searchTF.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTFActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(81, 81, 81));
        jLabel4.setText("SURNAME:");

        fnameTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        fnameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnameTFActionPerformed(evt);
            }
        });

        clearBTN.setBackground(new java.awt.Color(0, 153, 102));
        clearBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        clearBTN.setForeground(new java.awt.Color(255, 255, 255));
        clearBTN.setText("clear fields");
        clearBTN.setBorder(null);
        clearBTN.setBorderPainted(false);
        clearBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBTNActionPerformed(evt);
            }
        });

        addressTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        empIdTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        empIdTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empIdTFActionPerformed(evt);
            }
        });

        positionTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        positionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                positionTFActionPerformed(evt);
            }
        });

        salaryLBL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        salaryLBL.setForeground(new java.awt.Color(81, 81, 81));
        salaryLBL.setText("RATE:");

        hireDateTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        emailTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        editBTN.setBackground(new java.awt.Color(33, 118, 206));
        editBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        editBTN.setForeground(new java.awt.Color(255, 255, 255));
        editBTN.setText("edit employee");
        editBTN.setBorder(null);
        editBTN.setBorderPainted(false);
        editBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBTNActionPerformed(evt);
            }
        });

        deleteBTN.setBackground(new java.awt.Color(33, 118, 206));
        deleteBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        deleteBTN.setForeground(new java.awt.Color(255, 255, 255));
        deleteBTN.setText("delete employee");
        deleteBTN.setBorder(null);
        deleteBTN.setBorderPainted(false);
        deleteBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBTNActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(81, 81, 81));
        jLabel3.setText("PHONE NUMBER:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(81, 81, 81));
        jLabel6.setText("ADDRESS:");

        lnameTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        idLBL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        idLBL.setForeground(new java.awt.Color(81, 81, 81));
        idLBL.setText("ID:");

        nameLBL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nameLBL.setForeground(new java.awt.Color(81, 81, 81));
        nameLBL.setText("NAME:");

        positionLBL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        positionLBL.setForeground(new java.awt.Color(81, 81, 81));
        positionLBL.setText("POSITION:");

        searchLBL.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        searchLBL.setForeground(new java.awt.Color(81, 81, 81));
        searchLBL.setText("SEARCH EMPLOYEE BY ID:");

        searchBTN.setBackground(new java.awt.Color(33, 118, 206));
        searchBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        searchBTN.setForeground(new java.awt.Color(255, 255, 255));
        searchBTN.setText("search");
        searchBTN.setBorder(null);
        searchBTN.setBorderPainted(false);
        searchBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBTNActionPerformed(evt);
            }
        });

        addBTN.setBackground(new java.awt.Color(33, 118, 206));
        addBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        addBTN.setForeground(new java.awt.Color(255, 255, 255));
        addBTN.setText("add employee");
        addBTN.setBorder(null);
        addBTN.setBorderPainted(false);
        addBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBTNActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(81, 81, 81));
        jLabel7.setText("HIRE DATE:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(81, 81, 81));
        jLabel5.setText("EMAIL:");

        rateTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rateTFActionPerformed(evt);
            }
        });

        phoneNumberTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        backToMainBTN.setBackground(new java.awt.Color(33, 118, 206));
        backToMainBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        backToMainBTN.setForeground(new java.awt.Color(255, 255, 255));
        backToMainBTN.setText("RETURN TO DASHBOARD");
        backToMainBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMainBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(positionLBL)
                                .addGap(39, 39, 39)
                                .addComponent(positionTF, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(fnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(nameLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(idLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(empIdTF, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(salaryLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hireDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(phoneNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(searchBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(searchLBL)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(clearBTN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(editBTN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deleteBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(206, 206, 206)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 709, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(backToMainBTN)
                    .addContainerGap(3222, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(empIdTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salaryLBL)
                            .addComponent(idLBL))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nameLBL)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(hireDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(positionTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(positionLBL)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phoneNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(49, 49, 49)
                        .addComponent(addBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clearBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(184, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap(726, Short.MAX_VALUE)
                    .addComponent(backToMainBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String generatePassword(int length) {
            StringBuilder password = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int index = random.nextInt(CHARACTERS.length());
                password.append(CHARACTERS.charAt(index));
            }
            return password.toString();
    }
    
    public void createLogIn(int empId, String fname, String lname) {
        String sql = "INSERT INTO employee_logins (employee_id, username, password_hash) VALUES (?, ?, ?)";
        try (PreparedStatement st = connection.prepareStatement(sql)) {
            st.setInt(1, empId);
            st.setString(2, (fname + "." + lname).toLowerCase());
            st.setString(3, generatePassword(8)); // Generates an 8-character password

            int rowsInserted = st.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Employee login added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error inserting employee login!");
        }
    }
    
    private void addBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBTNActionPerformed
    String employee_id = empIdTF.getText();
    String fname = fnameTF.getText();
    String lname = lnameTF.getText();
    String position = positionTF.getText();
    String rate = rateTF.getText();
    String address = addressTF.getText();
    String phoneN = phoneNumberTF.getText(); // Get phone number
    String email = emailTF.getText(); // Get email
    String hireDate = hireDateTF.getText(); // Get hire date

    // Check if any field is empty
    if (employee_id.isEmpty() || fname.isEmpty() || lname.isEmpty() || position.isEmpty() || rate.isEmpty() || address.isEmpty() || phoneN.isEmpty() || email.isEmpty() || hireDate.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        Integer.valueOf(employee_id);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Employee ID must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        Double.valueOf(rate);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Rate must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        String sql = "INSERT INTO employees (employee_id, fname, lname, position, rate, address, phoneN, email, hire_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, Integer.parseInt(employee_id));
        statement.setString(2, fname);
        statement.setString(3, lname);
        statement.setString(4, position);
        statement.setDouble(5, Double.parseDouble(rate));
        statement.setString(6, address);
        statement.setString(7, phoneN); // Set phone number
        statement.setString(8, email); // Set email
        statement.setString(9, hireDate); // Set hire date

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            tableModel.addRow(new Object[]{
                employee_id, fname, lname, position, rate, address, phoneN, email, hireDate
            });
            JOptionPane.showMessageDialog(this, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields(); // Reset the input fields after adding
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error adding employee to database! Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    
    createLogIn(Integer.parseInt(employee_id), fname, lname);

    }//GEN-LAST:event_addBTNActionPerformed

    private void editBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBTNActionPerformed
        int selectedRow = employeeTable.getSelectedRow();

        if (selectedRow != -1) {
            // Get the updated data from the fields
            String id = empIdTF.getText().trim();
            String fname = fnameTF.getText().trim();
            String lname = lnameTF.getText().trim();
            String position = positionTF.getText().trim();
            String rateStr = rateTF.getText().trim();
            String address = addressTF.getText().trim();
            String email = emailTF.getText().trim();
            String phoneN = phoneNumberTF.getText().trim();
            String hireDate = hireDateTF.getText().trim();

            // Check if any field is empty
            if (id.isEmpty() || fname.isEmpty() || lname.isEmpty() || position.isEmpty() || rateStr.isEmpty() || address.isEmpty() || email.isEmpty() || phoneN.isEmpty() || hireDate.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
                return;
            }

            // Validate if rate is numeric
            double rate;
            try {
                rate = Double.parseDouble(rateStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid rate.");
                return;
            }

            // Confirm the update
            int confirmation = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to update this employee's details?", 
                "Confirm Update", 
                JOptionPane.YES_NO_OPTION);

            if (confirmation == JOptionPane.YES_OPTION) {
                // Update the selected row in the table model
                tableModel.setValueAt(fname, selectedRow, 1);  // Update First Name
                tableModel.setValueAt(lname, selectedRow, 2);  // Update Last Name (Fixed index)
                tableModel.setValueAt(position, selectedRow, 3);  // Update Position
                tableModel.setValueAt(rate, selectedRow, 4);  // Update Rate
                tableModel.setValueAt(address, selectedRow, 5);  // Update Address
                tableModel.setValueAt(email, selectedRow, 6);  // Update Email
                tableModel.setValueAt(phoneN, selectedRow, 7);  // Update Phone Number
                tableModel.setValueAt(hireDate, selectedRow, 8);  // Update Hire Date

                try {
                    // Execute the SQL UPDATE query to save changes to the database
                    String sql = "UPDATE employees SET fname = ?, lname = ?, position = ?, rate = ?, address = ?, email = ?, phoneN = ?, hire_date = ? WHERE employee_id = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);

                    statement.setString(1, fname);
                    statement.setString(2, lname);
                    statement.setString(3, position);
                    statement.setDouble(4, rate);
                    statement.setString(5, address);
                    statement.setString(6, email);
                    statement.setString(7, phoneN);
                    statement.setString(8, hireDate);
                    statement.setInt(9, Integer.parseInt(id));

                    int rowsUpdated = statement.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(this, "Employee updated successfully!");
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this, "Error updating employee! " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_editBTNActionPerformed

    private void deleteBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBTNActionPerformed
    int selectedRow = employeeTable.getSelectedRow();

    if (selectedRow != -1) {
    // Get the employee ID of the selected row
    String employee_id = tableModel.getValueAt(selectedRow, 0).toString();

    // Ask for confirmation before deleting
    int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this employee?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
    if (confirmation == JOptionPane.YES_OPTION) {
        // Remove the selected row from the table model
        tableModel.removeRow(selectedRow);

        // Delete the employee and their login from the database
        try {
            // Start a transaction
            connection.setAutoCommit(false);

            // Delete the login information from employee_logins table
            String deleteLoginSql = "DELETE FROM employee_logins WHERE employee_id = ?";
            PreparedStatement deleteLoginStatement = connection.prepareStatement(deleteLoginSql);
            deleteLoginStatement.setInt(1, Integer.parseInt(employee_id));

            int loginRowsDeleted = deleteLoginStatement.executeUpdate();

            // Delete the employee from the employees table
            String deleteEmployeeSql = "DELETE FROM employees WHERE employee_id = ?";
            PreparedStatement deleteEmployeeStatement = connection.prepareStatement(deleteEmployeeSql);
            deleteEmployeeStatement.setInt(1, Integer.parseInt(employee_id));

            int employeeRowsDeleted = deleteEmployeeStatement.executeUpdate();

            // If both deletions are successful, commit the transaction
            if (loginRowsDeleted > 0 && employeeRowsDeleted > 0) {
                connection.commit();
                JOptionPane.showMessageDialog(this, "Employee and login deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                connection.rollback();
                JOptionPane.showMessageDialog(this, "Error: Employee or login ID not found in the database.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            try {
                // Rollback the transaction in case of an error
                connection.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            JOptionPane.showMessageDialog(this, "Error deleting employee and login from database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } finally {
            // Restore the auto-commit mode
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
} else {
    JOptionPane.showMessageDialog(this, "Please select a row to delete.");
}

    }//GEN-LAST:event_deleteBTNActionPerformed

    private void clearBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBTNActionPerformed
        if (empIdTF.getText().isEmpty() && fnameTF.getText().isEmpty() && positionTF.getText().isEmpty() && rateTF.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Fields are already empty. Nothing to clear.");
    } else {
        // Clear the fields if they are not empty
        clearFields();
    }
    }//GEN-LAST:event_clearBTNActionPerformed
     private void clearFields() {
         empIdTF.setText("");
        fnameTF.setText("");
        lnameTF.setText("");
        positionTF.setText("");
        rateTF.setText("");
        addressTF.setText("");
        emailTF.setText("");
        phoneNumberTF.setText("");
        hireDateTF.setText("");
     }
    private void rateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rateTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rateTFActionPerformed

    private void positionTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_positionTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_positionTFActionPerformed

    private void fnameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnameTFActionPerformed

    private void empIdTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empIdTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empIdTFActionPerformed

    private void searchTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTFActionPerformed

    private void searchBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBTNActionPerformed
       String searchTerm = searchTF.getText().trim();

    if (searchTerm.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter an ID to search.");
        return;
    }

    boolean found = false;
    for (int i = 0; i < tableModel.getRowCount(); i++) {
        String id = (String) tableModel.getValueAt(i, 0);
        
        // Making the search case-insensitive
        if (id.equalsIgnoreCase(searchTerm)) {
            // Highlight the row
            employeeTable.setRowSelectionInterval(i, i);
            
            // Scroll to the row
            employeeTable.scrollRectToVisible(employeeTable.getCellRect(i, 0, true));
            
            found = true;
            break;
        }
    }

    if (!found) {
        JOptionPane.showMessageDialog(this, "Employee with ID " + searchTerm + " not found.");
        // Optionally clear the selection if not found
        employeeTable.clearSelection();
    }

    }//GEN-LAST:event_searchBTNActionPerformed

    private void backToMainBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToMainBTNActionPerformed
        ManagerDashboard mngDashboard = new ManagerDashboard();
        mngDashboard.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backToMainBTNActionPerformed

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
            java.util.logging.Logger.getLogger(ManageEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageEmployee.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageEmployee().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBTN;
    private javax.swing.JTextField addressTF;
    private javax.swing.JButton backToMainBTN;
    private javax.swing.JButton clearBTN;
    private javax.swing.JButton deleteBTN;
    private javax.swing.JButton editBTN;
    private javax.swing.JTextField emailTF;
    private javax.swing.JTextField empIdTF;
    private javax.swing.JTable employeeTable;
    private javax.swing.JTextField fnameTF;
    private javax.swing.JTextField hireDateTF;
    private javax.swing.JLabel iconLBL;
    private javax.swing.JLabel idLBL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField lnameTF;
    private javax.swing.JLabel nameLBL;
    private javax.swing.JTextField phoneNumberTF;
    private javax.swing.JLabel positionLBL;
    private javax.swing.JTextField positionTF;
    private javax.swing.JTextField rateTF;
    private javax.swing.JLabel salaryLBL;
    private javax.swing.JButton searchBTN;
    private javax.swing.JLabel searchLBL;
    private javax.swing.JTextField searchTF;
    // End of variables declaration//GEN-END:variables
}
