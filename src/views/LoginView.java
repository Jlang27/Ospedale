package views;

import core.controllers.AuthController;
import core.controllers.PatientController;
import core.controllers.utils.Response;
import java.awt.Color;
import javax.swing.JOptionPane;

public class LoginView extends javax.swing.JFrame {

    private int x, y;

    public LoginView() {
        initComponents();
        this.setBackground(new Color(0, 0, 0, 0));
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        panelRound1 = new views.PanelRound();
        panelRound2 = new views.PanelRound();
        btnClose = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelRound3 = new views.PanelRound();
        lblLoginTitle = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblLoginUsername = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        lblLoginPassword = new javax.swing.JLabel();
        btnEnter = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        lblRegFirstname = new javax.swing.JLabel();
        txtFirstname = new javax.swing.JTextField();
        lblRegLastname = new javax.swing.JLabel();
        txtLastname = new javax.swing.JTextField();
        lblRegId = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        lblRegGender = new javax.swing.JLabel();
        lblRegPhone = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        lblRegEmail = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        lblRegUsername = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtPasswordReg = new javax.swing.JTextField();
        lblRegPassword = new javax.swing.JLabel();
        lblRegPasswordConfirm = new javax.swing.JLabel();
        txtPasswordConfirm = new javax.swing.JTextField();
        cbGender = new javax.swing.JComboBox<>();
        btnSave = new javax.swing.JButton();
        lblRegAddress = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        lblRegBirthdate = new javax.swing.JLabel();
        txtBirthdate = new javax.swing.JTextField();

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

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnClose)
                .addGap(19, 19, 19))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnClose, javax.swing.GroupLayout.Alignment.TRAILING,
                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        lblLoginTitle.setFont(new java.awt.Font("Yu Gothic UI", 1, 24));
        lblLoginTitle.setText("LOGIN");

        txtUsername.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        txtUsername.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblLoginUsername.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblLoginUsername.setText("USERNAME");

        txtPassword.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        txtPassword.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lblLoginPassword.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblLoginPassword.setText("PASSWORD");

        btnEnter.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        btnEnter.setText("ENTER");
        btnEnter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                .addContainerGap(475, Short.MAX_VALUE)
                .addComponent(lblLoginTitle)
                .addGap(481, 481, 481))
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(431, 431, 431)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound3Layout.createSequentialGroup()
                                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblLoginPassword, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblLoginUsername, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(24, 24, 24))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(471, 471, 471)
                        .addComponent(btnEnter)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(lblLoginTitle)
                .addGap(74, 74, 74)
                .addComponent(lblLoginUsername)
                .addGap(18, 18, 18)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblLoginPassword)
                .addGap(18, 18, 18)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(btnEnter)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Login", panelRound3);

        lblRegFirstname.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblRegFirstname.setText("Firstname");
        txtFirstname.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblRegLastname.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblRegLastname.setText("Lastname");
        txtLastname.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblRegId.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblRegId.setText("ID");
        txtId.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblRegGender.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblRegGender.setText("Gender");

        lblRegPhone.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblRegPhone.setText("Phone");
        txtPhone.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblRegEmail.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblRegEmail.setText("Email");
        txtEmail.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblRegUsername.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblRegUsername.setText("User");
        txtUser.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        txtPasswordReg.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblRegPassword.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblRegPassword.setText("Password");

        lblRegPasswordConfirm.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblRegPasswordConfirm.setText("Password confirmation");

        txtPasswordConfirm.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        txtPasswordConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordConfirmActionPerformed(evt);
            }
        });

        cbGender.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        cbGender.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Select one", "Female", "Male"}));

        btnSave.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        lblRegAddress.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblRegAddress.setText("Address");
        txtAddress.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        lblRegBirthdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));
        lblRegBirthdate.setText("Birthdate");
        txtBirthdate.setFont(new java.awt.Font("Yu Gothic UI", 0, 18));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(450, 450, 450)
                        .addComponent(lblRegPassword))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(434, 434, 434)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPasswordReg, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(473, 473, 473)
                        .addComponent(lblRegUsername))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(432, 432, 432)
                        .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(456, 456, 456)
                        .addComponent(btnSave))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(396, 396, 396)
                        .addComponent(lblRegPasswordConfirm))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblRegBirthdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(lblRegFirstname)
                                .addGap(34, 34, 34)
                                .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblRegLastname)
                            .addComponent(lblRegAddress))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblRegId)
                                .addGap(30, 30, 30)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblRegGender)
                                .addGap(26, 26, 26)
                                .addComponent(cbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblRegPhone)
                                .addGap(18, 18, 18)
                                .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblRegEmail)
                                .addGap(18, 18, 18)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRegFirstname)
                    .addComponent(txtFirstname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRegLastname)
                    .addComponent(txtLastname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRegId)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRegGender)
                    .addComponent(cbGender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRegBirthdate)
                        .addComponent(txtBirthdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRegAddress)
                        .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblRegPhone)
                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblRegEmail)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(lblRegUsername)
                .addGap(18, 18, 18)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRegPassword)
                .addGap(18, 18, 18)
                .addComponent(txtPasswordReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblRegPasswordConfirm)
                .addGap(18, 18, 18)
                .addComponent(txtPasswordConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnSave)
                .addGap(42, 42, 42))
        );

        jTabbedPane1.addTab("Patient register", jPanel3);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1028, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
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
    private void btnEnterActionPerformed(java.awt.event.ActionEvent evt) {
    String username = txtUsername.getText();
    String password = txtPassword.getText();

    Response response = AuthController.login(username, password);

    if (response.getStatus() >= 500) {
        JOptionPane.showMessageDialog(null, response.getMessage(),
            "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
    } else if (response.getStatus() >= 400) {
        JOptionPane.showMessageDialog(null, response.getMessage(),
            "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
    } else {
        String role = (String) response.getData().get("role");

        if ("admin".equals(role)) {
            new AdminView(username).setVisible(true);
        } else if ("doctor".equals(role)) {
            new DoctorView(username, null).setVisible(true);
        } else {
            new PatientView(username, null).setVisible(true);
        }
        this.dispose();
    }
}
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        String firstname = txtFirstname.getText();
        String lastname = txtLastname.getText();
        String idStr = txtId.getText();
        String phone = txtPhone.getText();
        String email = txtEmail.getText();
        String user = txtUser.getText();
        String password = txtPasswordReg.getText();
        String passwordConfirm = txtPasswordConfirm.getText();
        String address = txtAddress.getText();
        String birthdate = txtBirthdate.getText();
        String gender = (String) cbGender.getSelectedItem();

        Response response = PatientController.register(
            idStr, user, firstname, lastname,
            password, passwordConfirm,
            email, birthdate, gender, phone, address
        );

        if (response.getStatus() >= 500) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.ERROR_MESSAGE);
        } else if (response.getStatus() >= 400) {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Error " + response.getStatus(), JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, response.getMessage(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            txtFirstname.setText("");
            txtLastname.setText("");
            txtId.setText("");
            txtPhone.setText("");
            txtEmail.setText("");
            txtUser.setText("");
            txtPasswordReg.setText("");
            txtPasswordConfirm.setText("");
            txtAddress.setText("");
            txtBirthdate.setText("");
            cbGender.setSelectedIndex(0);
        }
    }

    private void txtPasswordConfirmActionPerformed(java.awt.event.ActionEvent evt) {
        // no action needed
    }

    // Variables declaration
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnEnter;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cbGender;
    private javax.swing.JLabel lblLoginTitle;
    private javax.swing.JLabel lblLoginUsername;
    private javax.swing.JLabel lblLoginPassword;
    private javax.swing.JLabel lblRegFirstname;
    private javax.swing.JLabel lblRegLastname;
    private javax.swing.JLabel lblRegId;
    private javax.swing.JLabel lblRegGender;
    private javax.swing.JLabel lblRegPhone;
    private javax.swing.JLabel lblRegEmail;
    private javax.swing.JLabel lblRegUsername;
    private javax.swing.JLabel lblRegPassword;
    private javax.swing.JLabel lblRegPasswordConfirm;
    private javax.swing.JLabel lblRegAddress;
    private javax.swing.JLabel lblRegBirthdate;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private views.PanelRound panelRound1;
    private views.PanelRound panelRound2;
    private views.PanelRound panelRound3;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtBirthdate;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFirstname;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLastname;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtPasswordConfirm;
    private javax.swing.JTextField txtPasswordReg;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtUser;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JTextField txtAddress2;
    private javax.swing.JTextField txtBirthdate2;
}