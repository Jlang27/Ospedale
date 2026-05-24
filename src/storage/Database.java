/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package storage;

import core.models.Administrator;
import core.models.Appointment;
import core.models.Doctor;
import core.models.Hospitalization;
import core.models.Patient;
import core.models.Specialty;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import core.models.User;

/**
 *
 * @author LENOVO
 */
public class Database {
    
    private static Database instance;       
    private final List<User> users = new ArrayList<>();
    private final List<Appointment> appointments = new ArrayList<>();
    private final List<Hospitalization> hospitalizations = new ArrayList<>();
 
    private Database() { loadUsers(); }     
 
    public static Database getInstance() {   
        if (instance == null) instance = new Database();
        return instance;
    }
 
    private void loadUsers() {
        try {
            String txt = new String(Files.readAllBytes(Paths.get("json/users.json")));
            JSONArray arr = new JSONObject(txt).getJSONArray("users");
            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                String type = o.getString("type");
                long id = o.getLong("id");
                String un = o.getString("username");
                String fn = o.getString("firstname");
                String ln = o.getString("lastname");
                String pw = o.getString("password");
                switch (type) {
                    case "admin":
                        users.add(new Administrator(id, un, fn, ln, pw));
                        break;
                    case "patient":
                        users.add(new Patient(id, un, fn, ln, pw,
                            o.getString("email"),
                            LocalDate.parse(o.getString("birthdate")),
                            o.getBoolean("gender"),
                            o.getLong("phone"),
                            o.getString("address")));
                        break;
                    case "doctor":
                        users.add(new Doctor(id, un, fn, ln, pw,
                            Specialty.valueOf(o.getString("specialty")),
                            o.getString("licenceNumber"),
                            o.getString("assignedOffice")));
                        break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error cargando JSON: " + e.getMessage());
        }
    }
 
    public User findUserByUsername(String username) {
        for (User u : users) if (u.getUsername().equals(username)) return u;
        return null;
    }
    public User findUserById(long id) {
        for (User u : users) if (u.getId() == id) return u;
        return null;
    }
    public List<User> getUsers() { return users; }
    public List<Appointment> getAppointments() { return appointments; }
    public List<Hospitalization> getHospitalizations() { return hospitalizations; }
    
    
    public String nextAppointmentId(long patientId) {
    int count = 0;
    String prefix = "A-" + patientId + "-";
    for (Appointment a : appointments)
        if (a.getId().startsWith(prefix)) count++;
    return prefix + String.format("%04d", count);  
    }
    
    public String nextHospitalizationId(long patientId) {
        int count = 0;
        String prefix = "H-" + patientId + "-";
        for (Hospitalization h : hospitalizations)
            if (h.getId().startsWith(prefix)) count++;
        return prefix + String.format("%04d", count);
    }
    
    public void addUser(User user) {
        users.add(user);
    }
 
    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
 
    public void addHospitalization(Hospitalization hospitalization) {
        hospitalizations.add(hospitalization);
    }   
}