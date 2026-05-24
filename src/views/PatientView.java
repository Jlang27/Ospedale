/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import core.controllers.PatientController;
import core.controllers.DoctorController;
import core.controllers.HospitalizationController;
import core.controllers.utils.Response;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PatientView extends javax.swing.JFrame {

    private int x, y;
    private String viewedUsername;
    private String adminUsername;

    public PatientView(String viewedUsername, String adminUsername) {
        initComponents();
        this.viewedUsername = viewedUsername;
        this.adminUsername = adminUsername;
        this.setBackground(new Color(0, 0, 0, 0));
        this.setLocationRelativeTo(null);
        btnBack.setVisible(adminUsername != null);
        loadPatientInfo();
        loadDoctors();
        loadAppointments();
    }

    private void loadPatientInfo() {
        Response response = PatientController.getInfo(viewedUsername);
        if (response.getStatus() == 200) {
            txtFirstname.setText((String) response.getData().get("firstname"));
            txtLastname.setText((String) response.getData().get("lastname"));
            txtBirthdate.setText((String) response.getData().get("birthdate"));
            txtEmail.setText((String) response.getData().get("email"));
            txtPhone.setText((String) response.getData().get("phone"));
            txtAddress.setText((String) response.getData().get("address"));
            txtUser.setText((String) response.getData().get("username"));
        }
    }

    private void loadDoctors() {
        Response response = DoctorController.getAll();
        if (response.getStatus() == 200) {
            List<String> doctors = (List<String>) response.getData().get("doctors");
            cbAttendingDoctor.removeAllItems();
            cbAttendingDoctor.addItem("Select one");
            for (String doc : doctors) {
                cbAttendingDoctor.addItem(doc);
            }
        }
    }

    private void loadAppointments() {
        Response response = PatientController.getAppointments(viewedUsername);
        if (response.getStatus() == 200) {
            List<String[]> appts = (List<String[]>) response.getData().get("appointments");
            DefaultTableModel model = (DefaultTableModel) tblAppointments.getModel();
            model.setRowCount(0);
            for (String[] row : appts) {
                model.addRow(row);
            }
            cbAppointmentToCancel.removeAllItems();
            cbAppointmentToCancel.addItem("Select one");
            for (String[] row : appts) {
                cbAppointmentToCancel.addItem(row[0]); // ID is first column
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound1 = new views.PanelRound();
        panelRound2 = new views.PanelRound();
        btnClose = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblAppointments = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblFirstname = new javax.swing.JLabel();
        txtFirstname = new javax.swing.JTextField();
        lblLastname = new javax.swing.JLabel();
        txtLastname = new javax.swing.JTextField();
        lblBirthdate = new javax.swing.JLabel();
        txtBirthdate = new javax.swing.JTextField();
        lblGender = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblPhone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        lblAddress = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        txtPassword = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        lblPasswordConfirm = new javax.swing.JLabel();
        txtPasswordConfirm = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        lblUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        cbGender = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        lblRequestAppointment = new javax.swing.JLabel();
        rbSpecialty = new javax.swing.JRadioButton();
        rbDoctor = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        lblAppointmentDate = new javax.swing.JLabel();
        txtAppointmentDate = new javax.swing.JTextField();
        txtAppointmentTime = new javax.swing.JTextField();
        lblAppointmentTime = new javax.swing.JLabel();
        lblAppointmentType = new javax.swing.JLabel();
        lblAppointmentReason = new javax.swing.JLabel();
        cbAppointmentType = new javax.swing.JComboBox<>();
        btnCreateAppointment = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        lblRequestHospitalization = new javax.swing.JLabel();
        lblHospitalizationReason = new javax.swing.JLabel();
        lblAttendingDoctor = new javax.swing.JLabel();
        cbAttendingDoctor = new javax.swing.JComboBox<>();
        txtAdmissionDate = new javax.swing.JTextField();
        lblAdmissionDate = new javax.swing.JLabel();
        lblRoomType = new javax.swing.JLabel();
        cbRoomType = new javax.swing.JComboBox<>();
        lblObservations = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taObservations = new javax.swing.JTextArea();
        btnCreateHospitalization = new javax.swing.JButton();
        lblCancelAppointment = new javax.swing.JLabel();
        lblAppointmentId = new javax.swing.JLabel();
        lblObservationsCancel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taObservationsCancel = new javax.swing.JTextArea();
        btnCancelAppointment = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        taHospitalizationReason = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        taAppointmentReason = new javax.swing.JTextArea();
        cbDoctorOrSpecialty = new javax.swing.JComboBox<>();
        cbAppointmentToCancel = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelRound1.setRadius(50);

        panelRound2.setRadius(50);
        panelRound2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panelRound2MouseDragged(evt);
            }
        });
        panelRound2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panelRound2MousePressed(evt);
            }
        });

        btnClose.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        btnClose.setText("X");
        btnClose.setBorderPainted(false);
        btnClose.setContentAreaFilled(false);
        btnClose.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnClose.setFocusable(false);
        btnClose.setRequestFocusEnabled(false);
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        lblTitle.setFont(new java.awt.Font("Yu Gothic UI", 0, 14));
        lblTitle.setText("PATIENT VIEW");

        btnBack.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTitle)
                .addGap(29, 29, 29)
                .addComponent(btnBack)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addGap(19, 19, 19))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnClose))
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lblTitle, javax.swing.GroupLayout.Alignment.TRAILING,
                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tblAppointments.setAutoCreateRowSorter(true);
        tblAppointments.setModel(new javax.swing.table.DefaultTableModel(
            new Object[][] { {null, null, null, null, null, null} },
            new String[]{"ID", "Date", "Doctor", "Specialty", "Type", "Status"}
        ) {
            boolean[] canEdit = new boolean[]{false, false, false, false, false, false};
            public boolean isCellEditable(int r, int c) { return canEdit[c]; }
        });
        jScrollPane3.setViewportView(tblAppointments);

        btnRefresh.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnLogout.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(602, 602, 602)
                .addComponent(btnRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout)
                .addGap(78, 78, 78))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRefresh)
                    .addComponent(btnLogout))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Appointment history", jPanel3);

        lblFirstname.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblFirstname.setText("Firstname");
        txtFirstname.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblLastname.setFont(new java.awt.Font("Yu Gothic UI", 0, 14));
        lblLastname.setText("Lastname");
        txtLastname.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblBirthdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblBirthdate.setText("Birthdate");
        txtBirthdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblGender.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblGender.setText("Gender");

        lblEmail.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblEmail.setText("Email");
        txtEmail.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblPhone.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblPhone.setText("Phone");
        txtPhone.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblAddress.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblAddress.setText("Address");
        txtAddress.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        txtPassword.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblPassword.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblPassword.setText("Password");

        lblPasswordConfirm.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblPasswordConfirm.setText("Password confirmation");

        txtPasswordConfirm.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        btnSave.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblUser.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblUser.setText("User");
        txtUser.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        cbGender.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        cbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select one", "Female", "Male"}));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(lblFirstname)
                .addGap(18, 18, 18)
                .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(lblLastname)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblPhone)
                        .addGap(18, 18, 18)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAddress)
                        .addGap(18, 18, 18)
                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblBirthdate)
                        .addGap(18, 18, 18)
                        .addComponent(txtBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblGender)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(lblEmail)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(141, 141, 141))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(516, 516, 516)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(btnSave))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblPasswordConfirm)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(lblPassword))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(39, 39, 39)
                                    .addComponent(lblUser)))
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstname)
                    .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLastname)
                    .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBirthdate)
                    .addComponent(txtBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblGender)
                    .addComponent(lblEmail)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhone)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAddress)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(lblUser)
                .addGap(18, 18, 18)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPassword)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblPasswordConfirm)
                .addGap(18, 18, 18)
                .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnSave)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Modify info", jPanel1);

        lblRequestAppointment.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblRequestAppointment.setText("Request medical appointment");

        rbSpecialty.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        rbSpecialty.setText("Specialty");
        rbSpecialty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSpecialtyActionPerformed(evt);
            }
        });

        rbDoctor.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        rbDoctor.setText("Doctor");
        rbDoctor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbDoctorActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblAppointmentDate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblAppointmentDate.setText("Appointment date");
        txtAppointmentDate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        txtAppointmentTime.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblAppointmentTime.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblAppointmentTime.setText("Appointment time");

        lblAppointmentType.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblAppointmentType.setText("Appointment type");

        lblAppointmentReason.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblAppointmentReason.setText("Appointment reason");

        cbAppointmentType.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        cbAppointmentType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select one", "Remote", "In-person"}));

        btnCreateAppointment.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        btnCreateAppointment.setText("Create");
        btnCreateAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateAppointmentActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lblRequestHospitalization.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblRequestHospitalization.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRequestHospitalization.setText("Request hospitalization");

        lblHospitalizationReason.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblHospitalizationReason.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHospitalizationReason.setText("Hospitalization reason");

        lblAttendingDoctor.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblAttendingDoctor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAttendingDoctor.setText("Attending doctor");

        cbAttendingDoctor.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        cbAttendingDoctor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select one"}));

        txtAdmissionDate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblAdmissionDate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblAdmissionDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAdmissionDate.setText("Estimated date of admission");

        lblRoomType.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblRoomType.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRoomType.setText("Desired room type");

        cbRoomType.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        cbRoomType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select one", "PRIVATE", "SHARED", "ICU"}));

        lblObservations.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblObservations.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblObservations.setText("Observations");

        taObservations.setColumns(20);
        taObservations.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        taObservations.setRows(5);
        jScrollPane1.setViewportView(taObservations);

        btnCreateHospitalization.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        btnCreateHospitalization.setText("Create");
        btnCreateHospitalization.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateHospitalizationActionPerformed(evt);
            }
        });

        lblCancelAppointment.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblCancelAppointment.setText("Cancel appointment");

        lblAppointmentId.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblAppointmentId.setText("ID appointment");

        lblObservationsCancel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblObservationsCancel.setText("Observations");

        taObservationsCancel.setColumns(20);
        taObservationsCancel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        taObservationsCancel.setRows(5);
        jScrollPane2.setViewportView(taObservationsCancel);

        btnCancelAppointment.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        btnCancelAppointment.setText("Cancel");
        btnCancelAppointment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelAppointmentActionPerformed(evt);
            }
        });

        taHospitalizationReason.setColumns(20);
        taHospitalizationReason.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        taHospitalizationReason.setRows(5);
        jScrollPane4.setViewportView(taHospitalizationReason);

        taAppointmentReason.setColumns(20);
        taAppointmentReason.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        taAppointmentReason.setRows(5);
        jScrollPane5.setViewportView(taAppointmentReason);

        cbDoctorOrSpecialty.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        cbDoctorOrSpecialty.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select one"}));

        cbAppointmentToCancel.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        cbAppointmentToCancel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select one"}));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(rbSpecialty)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(rbDoctor))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(63, 63, 63)
                                    .addComponent(txtAppointmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(47, 47, 47)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(lblAppointmentTime)
                                        .addComponent(lblAppointmentDate)
                                        .addComponent(cbDoctorOrSpecialty, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(63, 63, 63)
                                    .addComponent(txtAppointmentTime, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(38, 38, 38)
                                    .addComponent(lblAppointmentReason))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(46, 46, 46)
                                    .addComponent(lblAppointmentType))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(55, 55, 55)
                                    .addComponent(cbAppointmentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(42, 42, 42)
                            .addComponent(lblRequestAppointment)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(btnCreateAppointment)))
                .addGap(69, 69, 69)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(btnCreateHospitalization))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblHospitalizationReason, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(lblRequestHospitalization, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                            .addComponent(lblAttendingDoctor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblObservations, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAdmissionDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRoomType, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(cbAttendingDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(txtAdmissionDate, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(cbRoomType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 126, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(lblCancelAppointment))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(77, 77, 77)
                                .addComponent(btnCancelAppointment))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(cbAppointmentToCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblAppointmentId)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(lblObservationsCancel)))
                        .addGap(49, 49, 49)))
                .addGap(81, 81, 81))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblRequestHospitalization)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(lblHospitalizationReason)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblAttendingDoctor)
                        .addGap(18, 18, 18)
                        .addComponent(cbAttendingDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAdmissionDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtAdmissionDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(lblRoomType)
                        .addGap(18, 18, 18)
                        .addComponent(cbRoomType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblObservations)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCreateHospitalization)
                        .addGap(15, 15, 15))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblRequestAppointment)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rbSpecialty)
                                    .addComponent(rbDoctor))
                                .addGap(18, 18, 18)
                                .addComponent(cbDoctorOrSpecialty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblAppointmentDate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtAppointmentDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(lblAppointmentTime)
                                .addGap(18, 18, 18)
                                .addComponent(txtAppointmentTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAppointmentReason)
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblCancelAppointment)
                                .addGap(39, 39, 39)
                                .addComponent(lblAppointmentId)
                                .addGap(18, 18, 18)
                                .addComponent(cbAppointmentToCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblObservationsCancel)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(56, 56, 56)
                                .addComponent(btnCancelAppointment)))
                        .addGap(18, 18, 18)
                        .addComponent(lblAppointmentType)
                        .addGap(18, 18, 18)
                        .addComponent(cbAppointmentType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnCreateAppointment)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Request/Cancel", jPanel2);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void panelRound2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MousePressed
        x = evt.getX();
        y = evt.getY();
    }//GEN-LAST:event_panelRound2MousePressed

    private void panelRound2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelRound2MouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }//GEN-LAST:event_panelRound2MouseDragged

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        AdminView admin = new AdminView(adminUsername);
        this.dispose();
        admin.setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        LoginView login = new LoginView();
        this.dispose();
        login.setVisible(true);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadAppointments();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        String firstname = txtFirstname.getText();
        String lastname = txtLastname.getText();
        String birthdate = txtBirthdate.getText();
        String gender = (String) cbGender.getSelectedItem();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String address = txtAddress.getText();
        String username = txtUser.getText();
        String password = txtPassword.getText();
        String passwordConfirm = txtPasswordConfirm.getText();

        Response response = PatientController.update(
            viewedUsername, firstname, lastname,
            birthdate, gender, email, phone,
            address, username, password, passwordConfirm
        );

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            txtPassword.setText("");
            txtPasswordConfirm.setText("");
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void rbSpecialtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSpecialtyActionPerformed
        if (rbDoctor.isSelected()) rbDoctor.setSelected(false);
        Response response = DoctorController.getSpecialties();
        cbDoctorOrSpecialty.removeAllItems();
        cbDoctorOrSpecialty.addItem("Select one");
        if (response.getStatus() == 200) {
            List<String> specialties = (List<String>) response.getData().get("specialties");
            for (String s : specialties) {
                cbDoctorOrSpecialty.addItem(s);
            }
        }
    }//GEN-LAST:event_rbSpecialtyActionPerformed

    private void rbDoctorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbDoctorActionPerformed
        if (rbSpecialty.isSelected()) rbSpecialty.setSelected(false);
        Response response = DoctorController.getAll();
        cbDoctorOrSpecialty.removeAllItems();
        cbDoctorOrSpecialty.addItem("Select one");
        if (response.getStatus() == 200) {
            List<String> doctors = (List<String>) response.getData().get("doctors");
            for (String doc : doctors) {
                cbDoctorOrSpecialty.addItem(doc);
            }
        }
    }//GEN-LAST:event_rbDoctorActionPerformed

    private void btnCreateAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateAppointmentActionPerformed
        String selected = (String) cbDoctorOrSpecialty.getSelectedItem();
        String date = txtAppointmentDate.getText();
        String time = txtAppointmentTime.getText();
        String type = (String) cbAppointmentType.getSelectedItem();
        String reason = taAppointmentReason.getText();

        Response response;
        if (rbSpecialty.isSelected()) {
            response = PatientController.requestAppointmentBySpecialty(
                viewedUsername, selected, date, time, type, reason
            );
        } else {
            response = PatientController.requestAppointmentByDoctor(
                viewedUsername, selected, date, time, reason
            );
        }

        JOptionPane.showMessageDialog(this, response.getMessage());

        if (response.getStatus() == 200) {
            txtAppointmentDate.setText("");
            txtAppointmentTime.setText("");
            cbAppointmentType.setSelectedIndex(0);
            taAppointmentReason.setText("");
            cbDoctorOrSpecialty.setSelectedIndex(0);
            rbSpecialty.setSelected(false);
            rbDoctor.setSelected(false);
            loadAppointments();
        }
    }//GEN-LAST:event_btnCreateAppointmentActionPerformed

    private void btnCreateHospitalizationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateHospitalizationActionPerformed
        String reason = taHospitalizationReason.getText();
        String doctor = (String) cbAttendingDoctor.getSelectedItem();
        String admissionDate = txtAdmissionDate.getText();
        String roomType = (String) cbRoomType.getSelectedItem();
        String observations = taObservations.getText();

        Response response = HospitalizationController.request(
            viewedUsername, doctor, reason, admissionDate, roomType, observations
        );

        JOptionPane.showMessageDialog(this, response.getMessage());

        if (response.getStatus() == 200) {
            taHospitalizationReason.setText("");
            cbAttendingDoctor.setSelectedIndex(0);
            txtAdmissionDate.setText("");
            cbRoomType.setSelectedIndex(0);
            taObservations.setText("");
        }
    }//GEN-LAST:event_btnCreateHospitalizationActionPerformed

    private void btnCancelAppointmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelAppointmentActionPerformed
        String appointmentId = (String) cbAppointmentToCancel.getSelectedItem();
        String observations = taObservationsCancel.getText();

        Response response = PatientController.cancelAppointment(appointmentId, observations);

        JOptionPane.showMessageDialog(this, response.getMessage());

        if (response.getStatus() == 200) {
            taObservationsCancel.setText("");
            loadAppointments();
        }
    }//GEN-LAST:event_btnCancelAppointmentActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCancelAppointment;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnCreateAppointment;
    private javax.swing.JButton btnCreateHospitalization;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbAppointmentToCancel;
    private javax.swing.JComboBox<String> cbAppointmentType;
    private javax.swing.JComboBox<String> cbAttendingDoctor;
    private javax.swing.JComboBox<String> cbDoctorOrSpecialty;
    private javax.swing.JComboBox<String> cbGender;
    private javax.swing.JComboBox<String> cbRoomType;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblFirstname;
    private javax.swing.JLabel lblLastname;
    private javax.swing.JLabel lblBirthdate;
    private javax.swing.JLabel lblGender;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblPhone;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPasswordConfirm;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblRequestAppointment;
    private javax.swing.JLabel lblAppointmentDate;
    private javax.swing.JLabel lblAppointmentTime;
    private javax.swing.JLabel lblAppointmentType;
    private javax.swing.JLabel lblAppointmentReason;
    private javax.swing.JLabel lblRequestHospitalization;
    private javax.swing.JLabel lblHospitalizationReason;
    private javax.swing.JLabel lblAttendingDoctor;
    private javax.swing.JLabel lblAdmissionDate;
    private javax.swing.JLabel lblRoomType;
    private javax.swing.JLabel lblObservations;
    private javax.swing.JLabel lblCancelAppointment;
    private javax.swing.JLabel lblAppointmentId;
    private javax.swing.JLabel lblObservationsCancel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton rbDoctor;
    private javax.swing.JRadioButton rbSpecialty;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblAppointments;
    private javax.swing.JTextArea taAppointmentReason;
    private javax.swing.JTextArea taHospitalizationReason;
    private javax.swing.JTextArea taObservations;
    private javax.swing.JTextArea taObservationsCancel;
    private javax.swing.JTextField txtAdmissionDate;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAppointmentDate;
    private javax.swing.JTextField txtAppointmentTime;
    private javax.swing.JTextField txtBirthdate;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPasswordConfirm;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtUser;
    private views.PanelRound panelRound1;
    private views.PanelRound panelRound2;
}