/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import core.controllers.DoctorController;
import core.controllers.HospitalizationController;
import core.controllers.utils.Response;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DoctorView extends javax.swing.JFrame {

    private String viewedUsername;
    private String adminUsername;

    // Header
    private JLabel lblDoctorName;
    private JLabel lblSpecialty;
    private JButton btnBack;
    private JButton btnLogout;

    // Tab 1 - Appointments
    private JComboBox<String> cbFilter;
    private JButton btnRefreshAppointments;
    private JTable tblAppointments;
    private JButton btnAccept;
    private JButton btnComplete;
    private JButton btnHospitalize;
    private JTextField txtNewTime;
    private JTextField txtRescheduleReason;
    private JButton btnReschedule;
    private JTextField txtMedication;
    private JTextField txtDose;
    private JTextField txtInstructions;
    private JButton btnPrescribe;

    // Tab 2 - Hospitalizations
    private JTable tblHospitalizations;
    private JButton btnApprove;
    private JButton btnDeny;
    private JButton btnRefreshHospitalizations;

    // Tab 3 - My Info
    private JTextField txtId;
    private JTextField txtUsername;
    private JTextField txtFirstname;
    private JTextField txtLastname;
    private JPasswordField txtPassword;
    private JPasswordField txtPasswordConfirm;
    private JTextField txtSpecialty;
    private JTextField txtLicenseNumber;
    private JTextField txtAssignedOffice;
    private JButton btnSave;

    public DoctorView(String viewedUsername, String adminUsername) {
        this.viewedUsername = viewedUsername;
        this.adminUsername = adminUsername;
        initComponents();
        btnBack.setVisible(adminUsername != null);
        loadDoctorInfo();
        loadAppointments();
        loadHospitalizations();
    }

    private void loadDoctorInfo() {
        Response r = DoctorController.getInfo(viewedUsername);
        if (r.getStatus() == 200) {
            HashMap<String, Object> data = r.getData();
            lblDoctorName.setText(data.get("firstname") + " " + data.get("lastname"));
            lblSpecialty.setText("" + data.get("specialty"));
            txtId.setText("" + data.get("id"));
            txtUsername.setText("" + data.get("username"));
            txtFirstname.setText("" + data.get("firstname"));
            txtLastname.setText("" + data.get("lastname"));
            txtSpecialty.setText("" + data.get("specialty"));
            txtLicenseNumber.setText("" + data.get("licenseNumber"));
            txtAssignedOffice.setText("" + data.get("assignedOffice"));
        }
    }

    private void loadAppointments() {
        boolean pendingOnly = "Pending".equals(cbFilter.getSelectedItem());
        Response r = DoctorController.getAppointments(viewedUsername, pendingOnly);
        DefaultTableModel model = (DefaultTableModel) tblAppointments.getModel();
        model.setRowCount(0);
        if (r.getStatus() == 200) {
            Object[][] rows = (Object[][]) r.getData().get("appointments");
            if (rows != null) {
                for (Object[] row : rows) model.addRow(row);
            }
        }
    }

    private void loadHospitalizations() {
        Response r = HospitalizationController.getByDoctor(viewedUsername);
        DefaultTableModel model = (DefaultTableModel) tblHospitalizations.getModel();
        model.setRowCount(0);
        if (r.getStatus() == 200) {
            Object[][] rows = (Object[][]) r.getData().get("hospitalizations");
            if (rows != null) {
                for (Object[] row : rows) model.addRow(row);
            }
        }
    }

    private String getSelectedAppointmentId() {
        int row = tblAppointments.getSelectedRow();
        if (row == -1) return null;
        return (String) tblAppointments.getValueAt(row, 0);
    }

    private String getSelectedHospitalizationId() {
        int row = tblHospitalizations.getSelectedRow();
        if (row == -1) return null;
        return (String) tblHospitalizations.getValueAt(row, 0);
    }

    private void initComponents() {
        setTitle("Doctor View");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950, 680);
        setLocationRelativeTo(null);

        // --- Header ---
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lblDoctorName = new JLabel("Doctor: ");
        lblSpecialty  = new JLabel("Specialty: ");
        btnBack   = new JButton("Back");
        btnLogout = new JButton("Logout");
        headerPanel.add(lblDoctorName);
        headerPanel.add(lblSpecialty);
        headerPanel.add(btnBack);
        headerPanel.add(btnLogout);

        // --- Tab 1: Appointments ---
        JPanel appointmentsTab = new JPanel(new BorderLayout());

        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cbFilter = new JComboBox<>(new String[]{"All", "Pending"});
        btnRefreshAppointments = new JButton("Refresh");
        filterPanel.add(new JLabel("Show:"));
        filterPanel.add(cbFilter);
        filterPanel.add(btnRefreshAppointments);

        String[] apptCols = {"ID", "Patient", "Date", "Time", "Status", "Specialty"};
        tblAppointments = new JTable(new DefaultTableModel(apptCols, 0));
        tblAppointments.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));

        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAccept     = new JButton("Accept");
        btnComplete   = new JButton("Complete");
        btnHospitalize = new JButton("Hospitalize");
        row1.add(btnAccept);
        row1.add(btnComplete);
        row1.add(btnHospitalize);

        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtNewTime         = new JTextField(8);
        txtRescheduleReason = new JTextField(15);
        btnReschedule      = new JButton("Reschedule");
        row2.add(new JLabel("New time (hh:mm):"));
        row2.add(txtNewTime);
        row2.add(new JLabel("Reason:"));
        row2.add(txtRescheduleReason);
        row2.add(btnReschedule);

        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtMedication  = new JTextField(10);
        txtDose        = new JTextField(8);
        txtInstructions = new JTextField(15);
        btnPrescribe   = new JButton("Prescribe");
        row3.add(new JLabel("Medication:"));
        row3.add(txtMedication);
        row3.add(new JLabel("Dose:"));
        row3.add(txtDose);
        row3.add(new JLabel("Instructions:"));
        row3.add(txtInstructions);
        row3.add(btnPrescribe);

        actionsPanel.add(row1);
        actionsPanel.add(row2);
        actionsPanel.add(row3);

        appointmentsTab.add(filterPanel, BorderLayout.NORTH);
        appointmentsTab.add(new JScrollPane(tblAppointments), BorderLayout.CENTER);
        appointmentsTab.add(actionsPanel, BorderLayout.SOUTH);

        // --- Tab 2: Hospitalizations ---
        JPanel hospitalizationsTab = new JPanel(new BorderLayout());

        String[] hospCols = {"ID", "Patient", "Date", "Status", "Room Type"};
        tblHospitalizations = new JTable(new DefaultTableModel(hospCols, 0));
        tblHospitalizations.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JPanel hospActions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnApprove = new JButton("Approve");
        btnDeny    = new JButton("Deny");
        btnRefreshHospitalizations = new JButton("Refresh");
        hospActions.add(btnApprove);
        hospActions.add(btnDeny);
        hospActions.add(btnRefreshHospitalizations);

        hospitalizationsTab.add(new JScrollPane(tblHospitalizations), BorderLayout.CENTER);
        hospitalizationsTab.add(hospActions, BorderLayout.SOUTH);

        // --- Tab 3: My Info ---
        JPanel infoTab = new JPanel(new FlowLayout(FlowLayout.LEFT));
        txtId              = new JTextField(15); txtId.setEnabled(false);
        txtUsername        = new JTextField(15);
        txtFirstname       = new JTextField(15);
        txtLastname        = new JTextField(15);
        txtPassword        = new JPasswordField(15);
        txtPasswordConfirm = new JPasswordField(15);
        txtSpecialty       = new JTextField(15);
        txtLicenseNumber   = new JTextField(15);
        txtAssignedOffice  = new JTextField(10);
        btnSave = new JButton("Save");

        infoTab.add(new JLabel("ID:")); infoTab.add(txtId);
        infoTab.add(new JLabel("Username:")); infoTab.add(txtUsername);
        infoTab.add(new JLabel("First name:")); infoTab.add(txtFirstname);
        infoTab.add(new JLabel("Last name:")); infoTab.add(txtLastname);
        infoTab.add(new JLabel("Password:")); infoTab.add(txtPassword);
        infoTab.add(new JLabel("Confirm password:")); infoTab.add(txtPasswordConfirm);
        infoTab.add(new JLabel("Specialty:")); infoTab.add(txtSpecialty);
        infoTab.add(new JLabel("License number:")); infoTab.add(txtLicenseNumber);
        infoTab.add(new JLabel("Office:")); infoTab.add(txtAssignedOffice);
        infoTab.add(btnSave);

        // --- Tabbed pane ---
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Appointments", appointmentsTab);
        tabbedPane.addTab("Hospitalizations", hospitalizationsTab);
        tabbedPane.addTab("My Info", infoTab);

        // --- Main layout ---
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(headerPanel, BorderLayout.NORTH);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        // --- Wire up events ---
        btnBack.addActionListener(evt -> btnBackActionPerformed());
        btnLogout.addActionListener(evt -> btnLogoutActionPerformed());
        cbFilter.addActionListener(evt -> loadAppointments());
        btnRefreshAppointments.addActionListener(evt -> loadAppointments());
        btnAccept.addActionListener(evt -> btnAcceptActionPerformed());
        btnComplete.addActionListener(evt -> btnCompleteActionPerformed());
        btnHospitalize.addActionListener(evt -> btnHospitalizeActionPerformed());
        btnReschedule.addActionListener(evt -> btnRescheduleActionPerformed());
        btnPrescribe.addActionListener(evt -> btnPrescribeActionPerformed());
        btnApprove.addActionListener(evt -> btnApproveActionPerformed());
        btnDeny.addActionListener(evt -> btnDenyActionPerformed());
        btnRefreshHospitalizations.addActionListener(evt -> loadHospitalizations());
        btnSave.addActionListener(evt -> btnSaveActionPerformed());

        pack();
    }

    private void btnAcceptActionPerformed() {
        String id = getSelectedAppointmentId();
        if (id == null) { JOptionPane.showMessageDialog(this, "Select an appointment first."); return; }
        Response r = DoctorController.acceptAppointment(id);
        JOptionPane.showMessageDialog(this, r.getMessage());
        if (r.getStatus() == 200) loadAppointments();
    }

    private void btnCompleteActionPerformed() {
        String id = getSelectedAppointmentId();
        if (id == null) { JOptionPane.showMessageDialog(this, "Select an appointment first."); return; }
        Response r = DoctorController.completeAppointment(id);
        JOptionPane.showMessageDialog(this, r.getMessage());
        if (r.getStatus() == 200) loadAppointments();
    }

    private void btnRescheduleActionPerformed() {
        String id = getSelectedAppointmentId();
        if (id == null) { JOptionPane.showMessageDialog(this, "Select an appointment first."); return; }
        String newTime = txtNewTime.getText().trim();
        String reason  = txtRescheduleReason.getText().trim();
        Response r = DoctorController.rescheduleAppointment(id, newTime, reason);
        JOptionPane.showMessageDialog(this, r.getMessage());
        if (r.getStatus() == 200) {
            txtNewTime.setText("");
            txtRescheduleReason.setText("");
            loadAppointments();
        }
    }

    private void btnPrescribeActionPerformed() {
        String id = getSelectedAppointmentId();
        if (id == null) { JOptionPane.showMessageDialog(this, "Select an appointment first."); return; }
        String medication   = txtMedication.getText().trim();
        String dose         = txtDose.getText().trim();
        String instructions = txtInstructions.getText().trim();
        Response r = DoctorController.prescribe(id, medication, dose, instructions);
        JOptionPane.showMessageDialog(this, r.getMessage());
        if (r.getStatus() == 200) {
            txtMedication.setText("");
            txtDose.setText("");
            txtInstructions.setText("");
        }
    }

    private void btnHospitalizeActionPerformed() {
        String id = getSelectedAppointmentId();
        if (id == null) { JOptionPane.showMessageDialog(this, "Select an appointment first."); return; }
        Response r = DoctorController.hospitalizeFromAppointment(id);
        JOptionPane.showMessageDialog(this, r.getMessage());
        if (r.getStatus() == 200) {
            loadAppointments();
            loadHospitalizations();
        }
    }

    private void btnApproveActionPerformed() {
        String id = getSelectedHospitalizationId();
        if (id == null) { JOptionPane.showMessageDialog(this, "Select a hospitalization first."); return; }
        Response r = HospitalizationController.approve(id);
        JOptionPane.showMessageDialog(this, r.getMessage());
        if (r.getStatus() == 200) loadHospitalizations();
    }

    private void btnDenyActionPerformed() {
        String id = getSelectedHospitalizationId();
        if (id == null) { JOptionPane.showMessageDialog(this, "Select a hospitalization first."); return; }
        Response r = HospitalizationController.deny(id);
        JOptionPane.showMessageDialog(this, r.getMessage());
        if (r.getStatus() == 200) loadHospitalizations();
    }

    private void btnSaveActionPerformed() {
        String id              = txtId.getText().trim();
        String username        = txtUsername.getText().trim();
        String firstname       = txtFirstname.getText().trim();
        String lastname        = txtLastname.getText().trim();
        String password        = new String(txtPassword.getPassword()).trim();
        String passwordConfirm = new String(txtPasswordConfirm.getPassword()).trim();
        String specialty       = txtSpecialty.getText().trim();
        String licenseNumber   = txtLicenseNumber.getText().trim();
        String office          = txtAssignedOffice.getText().trim();
        Response r = DoctorController.update(id, username, firstname, lastname, password, passwordConfirm, specialty, licenseNumber, office);
        JOptionPane.showMessageDialog(this, r.getMessage());
        if (r.getStatus() == 200) {
            txtPassword.setText("");
            txtPasswordConfirm.setText("");
            loadDoctorInfo();
        }
    }

    private void btnLogoutActionPerformed() {
        new LoginView().setVisible(true);
        this.dispose();
    }

    private void btnBackActionPerformed() {
        new AdminView(adminUsername).setVisible(true);
        this.dispose();
    }
}