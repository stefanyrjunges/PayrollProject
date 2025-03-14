/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package payrollsystem;

import java.awt.Dimension;
import java.awt.Font;
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
        empIdTF = new javax.swing.JTextField();
        fnameTF = new javax.swing.JTextField();
        positionTF = new javax.swing.JTextField();
        idLBL = new javax.swing.JLabel();
        nameLBL = new javax.swing.JLabel();
        positionLBL = new javax.swing.JLabel();
        salaryLBL = new javax.swing.JLabel();
        addBTN = new javax.swing.JButton();
        editBTN = new javax.swing.JButton();
        clearBTN = new javax.swing.JButton();
        deleteBTN = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        employeeTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        searchTF = new javax.swing.JTextField();
        searchBTN = new javax.swing.JButton();
        searchLBL = new javax.swing.JLabel();
        lnameTF = new javax.swing.JTextField();
        addressTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        emailTF = new javax.swing.JTextField();
        hireDateTF = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
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
                .addGap(58, 58, 58)
                .addComponent(jLabel1)
                .addGap(1012, 1012, 1012)
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
                        .addGap(69, 69, 69)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(iconLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        empIdTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        empIdTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empIdTFActionPerformed(evt);
            }
        });

        fnameTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        fnameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnameTFActionPerformed(evt);
            }
        });

        positionTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        positionTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                positionTFActionPerformed(evt);
            }
        });

        idLBL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        idLBL.setText("id :");

        nameLBL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nameLBL.setText("name :");

        positionLBL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        positionLBL.setText("position :");

        salaryLBL.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        salaryLBL.setText("rate :");

        addBTN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        addBTN.setText("add employee");
        addBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBTNActionPerformed(evt);
            }
        });

        editBTN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editBTN.setText("edit employee");
        editBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBTNActionPerformed(evt);
            }
        });

        clearBTN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        clearBTN.setText("clear fields");
        clearBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBTNActionPerformed(evt);
            }
        });

        deleteBTN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        deleteBTN.setText("delete employee");
        deleteBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBTNActionPerformed(evt);
            }
        });

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

        searchBTN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchBTN.setText("search");
        searchBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBTNActionPerformed(evt);
            }
        });

        searchLBL.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchLBL.setText("search for employee:");

        lnameTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        addressTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("surname :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("address :");

        emailTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        hireDateTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Phone number :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("email :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("hire date :");

        rateTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        rateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rateTFActionPerformed(evt);
            }
        });

        phoneNumberTF.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        backToMainBTN.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        backToMainBTN.setText("RETURN TO DASHBOARD");
        backToMainBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMainBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(empIdTF, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(50, 50, 50)
                                .addComponent(fnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(lnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(positionLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(positionTF, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(addBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchLBL)
                            .addComponent(clearBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(rateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(salaryLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(addressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(phoneNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(hireDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(editBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(deleteBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(backToMainBTN))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(idLBL))
                            .addComponent(empIdTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(nameLBL))
                            .addComponent(fnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lnameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4)))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(positionLBL))
                            .addComponent(positionTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(80, 80, 80)
                        .addComponent(addBTN)
                        .addGap(33, 33, 33)
                        .addComponent(clearBTN))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(salaryLBL)))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6))
                            .addComponent(addressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3))
                            .addComponent(phoneNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5))
                            .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel7))
                            .addComponent(hireDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editBTN)
                            .addComponent(deleteBTN)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchLBL, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchTF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(backToMainBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

            // Delete the employee from the database
            try {
                String sql = "DELETE FROM employees WHERE employee_id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);

                // Set the employee ID in the query
                statement.setInt(1, Integer.parseInt(employee_id));

                // Execute the delete query
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "Employee deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Employee ID not found in the database.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error deleting employee from database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
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
