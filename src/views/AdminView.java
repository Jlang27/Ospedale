package views;

import core.controllers.DoctorController;
import core.controllers.PatientController;
import core.controllers.utils.Response;
import java.awt.Color;
import java.util.List;
import javax.swing.JOptionPane;

public class AdminView extends javax.swing.JFrame {

    private int x, y;
    private String adminUsername;

    public AdminView(String adminUsername) {
        initComponents();
        this.adminUsername = adminUsername;
        this.setBackground(new Color(0, 0, 0, 0));
        this.setLocationRelativeTo(null);
        loadDoctors();
        loadPatients();
    }

    private void loadDoctors() {
        Response response = DoctorController.getAll();
        if (response.getStatus() == 200) {
            cbDoctor.removeAllItems();
            List<String> doctors = (List<String>) response.getData().get("doctors");
            for (String doc : doctors) {
                cbDoctor.addItem(doc);
            }
        }
    }

    private void loadPatients() {
        Response response = PatientController.getAll();
        if (response.getStatus() == 200) {
            cbPatient.removeAllItems();
            List<String> patients = (List<String>) response.getData().get("patients");
            for (String pat : patients) {
                cbPatient.addItem(pat);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panelRound1 = new views.PanelRound();
        panelRound2 = new views.PanelRound();
        btnClose = new javax.swing.JButton();
        lblTitle = new javax.swing.JLabel();
        panelRound3 = new views.PanelRound();
        btnDoctorView = new javax.swing.JButton();
        btnPatientView = new javax.swing.JButton();
        lblFirstname = new javax.swing.JLabel();
        txtFirstname = new javax.swing.JTextField();
        lblLastname = new javax.swing.JLabel();
        txtLastname = new javax.swing.JTextField();
        lblId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblSpecialty = new javax.swing.JLabel();
        lblLicenseNumber = new javax.swing.JLabel();
        txtLicenseNumber = new javax.swing.JTextField();
        lblAssignedOffice = new javax.swing.JLabel();
        txtAssignedOffice = new javax.swing.JTextField();
        lblUser = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPasswordDoc = new javax.swing.JTextField();
        lblPasswordConfirm = new javax.swing.JLabel();
        txtPasswordConfirm = new javax.swing.JTextField();
        cbSpecialty = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        cbDoctor = new javax.swing.JComboBox<>();
        lblDoctor = new javax.swing.JLabel();
        lblPatient = new javax.swing.JLabel();
        cbPatient = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        btnLogout = new javax.swing.JButton();

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
        lblTitle.setText("ADMIN VIEW");

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addGap(19, 19, 19))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(btnClose, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTitle))
        );

        btnDoctorView.setFont(new java.awt.Font("Yu Gothic UI", 1, 18));
        btnDoctorView.setText("DOCTOR VIEW");
        btnDoctorView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoctorViewActionPerformed(evt);
            }
        });

        btnPatientView.setFont(new java.awt.Font("Yu Gothic UI", 1, 18));
        btnPatientView.setText("PATIENT VIEW");
        btnPatientView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPatientViewActionPerformed(evt);
            }
        });

        lblFirstname.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblFirstname.setText("Firstname");
        txtFirstname.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblLastname.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblLastname.setText("Lastname");
        txtLastname.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblId.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblId.setText("ID");
        txtId.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblSpecialty.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblSpecialty.setText("Specialty");

        lblLicenseNumber.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblLicenseNumber.setText("License Number");
        txtLicenseNumber.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblAssignedOffice.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblAssignedOffice.setText("Assigned office");
        txtAssignedOffice.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblUser.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblUser.setText("User");
        txtUser.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblPassword.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblPassword.setText("Password");
        txtPasswordDoc.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblPasswordConfirm.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblPasswordConfirm.setText("Password confirmation");
        txtPasswordConfirm.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        cbSpecialty.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        cbSpecialty.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{
            "Select one", "GENERAL_MEDICINE", "CARDIOLOGY", "PEDIATRICS",
            "NEUROLOGY", "ORTHOPEDICS", "GYNECOLOGY", "DERMATOLOGY",
            "PSYCHIATRY", "ONCOLOGY", "OPHTHALMOLOGY", "INTERNAL_MEDICINE"
        }));

        btnSave.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cbDoctor.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        cbDoctor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select one"}));

        lblDoctor.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblDoctor.setText("Doctor");

        lblPatient.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblPatient.setText("Patient");

        cbPatient.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        cbPatient.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select one"}));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnLogout.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(326, 326, 326)
                        .addComponent(btnSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFirstname)
                                    .addComponent(lblSpecialty))
                                .addGap(18, 18, 18)
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelRound3Layout.createSequentialGroup()
                                        .addComponent(cbSpecialty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblLicenseNumber)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtLicenseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelRound3Layout.createSequentialGroup()
                                        .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(lblLastname)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblId)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addComponent(lblAssignedOffice)
                                .addGap(18, 18, 18)
                                .addComponent(txtAssignedOffice, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(panelRound3Layout.createSequentialGroup()
                                        .addComponent(lblUser)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelRound3Layout.createSequentialGroup()
                                        .addComponent(lblPassword)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtPasswordDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelRound3Layout.createSequentialGroup()
                                        .addComponent(lblPasswordConfirm)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(333, 333, 333)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnDoctorView)
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelRound3Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(cbDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelRound3Layout.createSequentialGroup()
                                        .addGap(47, 47, 47)
                                        .addComponent(lblDoctor)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogout)
                        .addGap(318, 318, 318)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnPatientView)
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(cbPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(lblPatient)))
                .addGap(88, 88, 88))
            .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                    .addContainerGap(707, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(523, 523, 523)))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFirstname)
                    .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLastname)
                    .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblId)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSpecialty)
                    .addComponent(cbSpecialty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLicenseNumber)
                    .addComponent(txtLicenseNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAssignedOffice)
                    .addComponent(txtAssignedOffice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUser)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPassword)
                            .addComponent(txtPasswordDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPasswordConfirm)
                            .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(lblDoctor)
                        .addGap(18, 18, 18)
                        .addComponent(cbDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(btnDoctorView)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(btnSave)
                .addGap(123, 123, 123)
                .addComponent(btnLogout)
                .addGap(38, 38, 38))
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(203, 203, 203)
                .addComponent(lblPatient)
                .addGap(18, 18, 18)
                .addComponent(cbPatient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btnPatientView)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelRound3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator2)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    }

    private void panelRound2MousePressed(java.awt.event.MouseEvent evt) {
        x = evt.getX();
        y = evt.getY();
    }

    private void panelRound2MouseDragged(java.awt.event.MouseEvent evt) {
        this.setLocation(this.getLocation().x + evt.getX() - x, this.getLocation().y + evt.getY() - y);
    }

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        String firstname = txtFirstname.getText();
        String lastname = txtLastname.getText();
        String idStr = txtId.getText();
        String specialty = (String) cbSpecialty.getSelectedItem();
        String licenseNumber = txtLicenseNumber.getText();
        String assignedOffice = txtAssignedOffice.getText();
        String user = txtUser.getText();
        String password = txtPasswordDoc.getText();
        String passwordConfirm = txtPasswordConfirm.getText();

        Response response = DoctorController.register(
            idStr, user, firstname, lastname,
            password, passwordConfirm,
            specialty, licenseNumber, assignedOffice
        );

        JOptionPane.showMessageDialog(this, response.getMessage());

        if (response.getStatus() == 200) {
            txtFirstname.setText("");
            txtLastname.setText("");
            txtId.setText("");
            cbSpecialty.setSelectedIndex(0);
            txtLicenseNumber.setText("");
            txtAssignedOffice.setText("");
            txtUser.setText("");
            txtPasswordDoc.setText("");
            txtPasswordConfirm.setText("");
            loadDoctors();
        }
    }

    private void btnDoctorViewActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedDoctor = (String) cbDoctor.getSelectedItem();
        if (selectedDoctor == null || selectedDoctor.equals("Select one")) {
            JOptionPane.showMessageDialog(this, "Selecciona un doctor de la lista");
            return;
        }
        DoctorView doctor = new DoctorView(selectedDoctor, adminUsername);
        this.setVisible(false);
        doctor.setVisible(true);
    }

    private void btnPatientViewActionPerformed(java.awt.event.ActionEvent evt) {
        String selectedPatient = (String) cbPatient.getSelectedItem();
        if (selectedPatient == null || selectedPatient.equals("Select one")) {
            JOptionPane.showMessageDialog(this, "Selecciona un paciente de la lista");
            return;
        }
        PatientView patient = new PatientView(selectedPatient, adminUsername);
        this.setVisible(false);
        patient.setVisible(true);
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        LoginView login = new LoginView();
        this.setVisible(false);
        login.setVisible(true);
    }

    // Variables declaration
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDoctorView;
    private javax.swing.JButton btnPatientView;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnLogout;
    private javax.swing.JComboBox<String> cbSpecialty;
    private javax.swing.JComboBox<String> cbDoctor;
    private javax.swing.JComboBox<String> cbPatient;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblFirstname;
    private javax.swing.JLabel lblLastname;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblSpecialty;
    private javax.swing.JLabel lblLicenseNumber;
    private javax.swing.JLabel lblAssignedOffice;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblPasswordConfirm;
    private javax.swing.JLabel lblDoctor;
    private javax.swing.JLabel lblPatient;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLicenseNumber;
    private javax.swing.JTextField txtAssignedOffice;
    private javax.swing.JTextField txtUser;
    private javax.swing.JTextField txtPasswordDoc;
    private javax.swing.JTextField txtPasswordConfirm;
    private views.PanelRound panelRound1;
    private views.PanelRound panelRound2;
    private views.PanelRound panelRound3;
}