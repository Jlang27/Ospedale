package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.*;
import storage.Database;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PatientController {

    public static Response register(String idStr, String username, String firstname, String lastname,
                                    String password, String passwordConfirm, String email,
                                    String birthdate, String gender, String phone, String address) {
        try {
            if (isBlank(idStr) || isBlank(username) || isBlank(firstname) || isBlank(lastname)
                    || isBlank(password) || isBlank(passwordConfirm) || isBlank(email)
                    || isBlank(birthdate) || isBlank(gender) || isBlank(phone) || isBlank(address)) {
                return new Response("Complete todos los campos obligatorios", Status.BAD_REQUEST);
            }
            if (!idStr.trim().matches("\\d{12}")) {
                return new Response("El id debe tener exactamente 12 dígitos", Status.BAD_REQUEST);
            }
            long id = Long.parseLong(idStr.trim());
            if (id <= 0) {
                return new Response("El id debe ser mayor que 0", Status.BAD_REQUEST);
            }
            Database db = Database.getInstance();
            if (db.findUserById(id) != null) {
                return new Response("Ya existe un usuario con ese id", Status.BAD_REQUEST);
            }
            if (db.findUserByUsername(username.trim()) != null) {
                return new Response("El username ya está en uso", Status.BAD_REQUEST);
            }
            if (!password.equals(passwordConfirm)) {
                return new Response("Las contraseñas no coinciden", Status.BAD_REQUEST);
            }
            if (!email.trim().matches("^[^@\\s]+@[^@\\s]+\\.com$")) {
                return new Response("El email debe tener formato XXXXX@XXXXX.com", Status.BAD_REQUEST);
            }
            if (!phone.trim().matches("\\d{10}")) {
                return new Response("El teléfono debe tener exactamente 10 dígitos", Status.BAD_REQUEST);
            }
            Boolean genderValue = parseGender(gender);
            if (genderValue == null) {
                return new Response("Seleccione un género válido", Status.BAD_REQUEST);
            }
            LocalDate birth;
            try {
                birth = LocalDate.parse(birthdate.trim());
            } catch (Exception e) {
                return new Response("La fecha de nacimiento debe tener formato AAAA-MM-DD", Status.BAD_REQUEST);
            }
            Patient patient = new Patient(id, username.trim(), firstname.trim(), lastname.trim(),
                    password, email.trim(), birth, genderValue, Long.parseLong(phone.trim()), address.trim());
            db.addUser(patient);
            return new Response("Paciente registrado correctamente", Status.OK);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response update(String viewedUsername, String firstname, String lastname,
                                  String birthdate, String gender, String email, String phone,
                                  String address, String username, String password, String passwordConfirm) {
        try {
            Database db = Database.getInstance();
            Patient patient = resolvePatient(db, viewedUsername);
            if (patient == null) {
                return new Response("Paciente no encontrado", Status.NOT_FOUND);
            }
            if (isBlank(firstname) || isBlank(lastname) || isBlank(birthdate) || isBlank(gender)
                    || isBlank(email) || isBlank(phone) || isBlank(address) || isBlank(username)) {
                return new Response("Complete todos los campos obligatorios", Status.BAD_REQUEST);
            }
            User other = db.findUserByUsername(username.trim());
            if (other != null && other != patient) {
                return new Response("El username ya está en uso", Status.BAD_REQUEST);
            }
            if (!email.trim().matches("^[^@\\s]+@[^@\\s]+\\.com$")) {
                return new Response("El email debe tener formato XXXXX@XXXXX.com", Status.BAD_REQUEST);
            }
            if (!phone.trim().matches("\\d{10}")) {
                return new Response("El teléfono debe tener exactamente 10 dígitos", Status.BAD_REQUEST);
            }
            Boolean genderValue = parseGender(gender);
            if (genderValue == null) {
                return new Response("Seleccione un género válido", Status.BAD_REQUEST);
            }
            LocalDate birth;
            try {
                birth = LocalDate.parse(birthdate.trim());
            } catch (Exception e) {
                return new Response("La fecha de nacimiento debe tener formato AAAA-MM-DD", Status.BAD_REQUEST);
            }
            if (!isBlank(password) || !isBlank(passwordConfirm)) {
                if (!password.equals(passwordConfirm)) {
                    return new Response("Las contraseñas no coinciden", Status.BAD_REQUEST);
                }
                patient.setPassword(password);
            }
            patient.setFirstname(firstname.trim());
            patient.setLastname(lastname.trim());
            patient.setBirthdate(birth);
            patient.setGender(genderValue);
            patient.setEmail(email.trim());
            patient.setPhone(Long.parseLong(phone.trim()));
            patient.setAddress(address.trim());
            patient.setUsername(username.trim());
            return new Response("Información actualizada correctamente", Status.OK);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getInfo(String username) {
        try {
            Database db = Database.getInstance();
            Patient patient = resolvePatient(db, username);
            if (patient == null) {
                return new Response("Paciente no encontrado", Status.NOT_FOUND);
            }
            HashMap<String, String> info = patient.serialize();
            HashMap<String, Object> data = new HashMap<>();
            data.put("firstname", info.get("firstname"));
            data.put("lastname", info.get("lastname"));
            data.put("birthdate", info.get("birthdate"));
            data.put("email", info.get("email"));
            data.put("phone", info.get("phone"));
            data.put("address", info.get("address"));
            data.put("username", info.get("username"));
            return new Response("Información del paciente", Status.OK, data);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getAll() {
        try {
            Database db = Database.getInstance();
            List<String> patients = new ArrayList<>();
            for (User u : db.getUsers()) {
                if (u instanceof Patient) {
                    patients.add(u.getId() + " - " + u.getFirstname() + " " + u.getLastname());
                }
            }
            HashMap<String, Object> data = new HashMap<>();
            data.put("patients", patients);
            return new Response("Lista de pacientes", Status.OK, data);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getAppointments(String username) {
        try {
            Database db = Database.getInstance();
            Patient patient = resolvePatient(db, username);
            if (patient == null) {
                return new Response("Paciente no encontrado", Status.NOT_FOUND);
            }
            List<Appointment> appointments = new ArrayList<>();
            for (Appointment a : db.getAppointments()) {
                if (a.getPatient() != null && a.getPatient().getId() == patient.getId()) {
                    appointments.add(a);
                }
            }
            appointments.sort((a, b) -> b.getDatetime().compareTo(a.getDatetime()));
            List<String[]> rows = new ArrayList<>();
            for (Appointment a : appointments) {
                rows.add(new String[]{
                    a.getId(),
                    a.getDatetime().toString(),
                    String.valueOf(a.getDoctor().getId()),
                    a.getSpecialty().name(),
                    String.valueOf(a.isType()),
                    a.getStatus().name()
                });
            }
            HashMap<String, Object> data = new HashMap<>();
            data.put("appointments", rows);
            return new Response("Citas del paciente", Status.OK, data);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response requestAppointmentByDoctor(String patientUsername, String doctorSelected,
                                                      String date, String time, String reason) {
        try {
            Database db = Database.getInstance();
            Patient patient = resolvePatient(db, patientUsername);
            if (patient == null) {
                return new Response("Paciente no encontrado", Status.NOT_FOUND);
            }
            if (isBlank(doctorSelected) || doctorSelected.trim().equals("Select one")) {
                return new Response("Doctor inválido", Status.BAD_REQUEST);
            }
            Doctor doctor = resolveDoctor(db, doctorSelected);
            if (doctor == null) {
                return new Response("Doctor inválido", Status.BAD_REQUEST);
            }
            if (!isValidDate(date)) {
                return new Response("La fecha debe tener formato AAAA-MM-DD", Status.BAD_REQUEST);
            }
            if (!isValidTime(time)) {
                return new Response("La hora debe tener formato hh:mm con minutos 00, 15, 30 o 45", Status.BAD_REQUEST);
            }
            LocalDateTime datetime = LocalDateTime.parse(date.trim() + "T" + time.trim());
            if (!isDoctorAvailable(db, doctor, datetime)) {
                return new Response("El doctor no está disponible en ese horario", Status.BAD_REQUEST);
            }
            String id = db.nextAppointmentId(patient.getId());
            Appointment appointment = new Appointment(id, patient, doctor, doctor.getSpecialty(),
                    datetime, reason, false);
            db.addAppointment(appointment);
            return new Response("Cita solicitada correctamente", Status.OK);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response requestAppointmentBySpecialty(String patientUsername, String specialtySelected,
                                                         String date, String time, String type, String reason) {
        try {
            Database db = Database.getInstance();
            Patient patient = resolvePatient(db, patientUsername);
            if (patient == null) {
                return new Response("Paciente no encontrado", Status.NOT_FOUND);
            }
            if (isBlank(specialtySelected) || specialtySelected.trim().equals("Select one")) {
                return new Response("Especialidad inválida", Status.BAD_REQUEST);
            }
            Specialty specialty;
            try {
                specialty = Specialty.valueOf(specialtySelected.trim());
            } catch (Exception e) {
                return new Response("Especialidad inválida", Status.BAD_REQUEST);
            }
            if (!isValidDate(date)) {
                return new Response("La fecha debe tener formato AAAA-MM-DD", Status.BAD_REQUEST);
            }
            if (!isValidTime(time)) {
                return new Response("La hora debe tener formato hh:mm con minutos 00, 15, 30 o 45", Status.BAD_REQUEST);
            }
            LocalDateTime datetime = LocalDateTime.parse(date.trim() + "T" + time.trim());
            Doctor available = null;
            for (User u : db.getUsers()) {
                if (u instanceof Doctor) {
                    Doctor d = (Doctor) u;
                    if (d.getSpecialty() == specialty && isDoctorAvailable(db, d, datetime)) {
                        available = d;
                        break;
                    }
                }
            }
            if (available == null) {
                return new Response("No hay doctores disponibles para esa especialidad y horario", Status.BAD_REQUEST);
            }
            String id = db.nextAppointmentId(patient.getId());
            Appointment appointment = new Appointment(id, patient, available, specialty,
                    datetime, reason, Boolean.parseBoolean(type));
            db.addAppointment(appointment);
            return new Response("Cita solicitada correctamente", Status.OK);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response cancelAppointment(String appointmentId, String observations) {
        try {
            if (isBlank(appointmentId) || appointmentId.trim().equals("Select one")) {
                return new Response("Seleccione una cita válida", Status.BAD_REQUEST);
            }
            Database db = Database.getInstance();
            Appointment target = null;
            for (Appointment a : db.getAppointments()) {
                if (a.getId().equals(appointmentId.trim())) {
                    target = a;
                    break;
                }
            }
            if (target == null) {
                return new Response("Cita no encontrada", Status.NOT_FOUND);
            }
            if (target.getStatus() == AppointmentStatus.COMPLETED) {
                return new Response("No se puede cancelar una cita completada", Status.BAD_REQUEST);
            }
            target.setStatus(AppointmentStatus.CANCELED);
            target.setObservations(observations);
            return new Response("Cita cancelada correctamente", Status.OK);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }

    private static Boolean parseGender(String gender) {
        if (gender == null) {
            return null;
        }
        String t = gender.trim();
        if (t.equalsIgnoreCase("Male")) {
            return Boolean.TRUE;
        }
        if (t.equalsIgnoreCase("Female")) {
            return Boolean.FALSE;
        }
        return null;
    }

    private static boolean isValidDate(String date) {
        if (date == null) {
            return false;
        }
        try {
            LocalDate.parse(date.trim());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isValidTime(String time) {
        if (time == null) {
            return false;
        }
        String[] parts = time.trim().split(":");
        if (parts.length != 2 || parts[0].length() != 2 || parts[1].length() != 2) {
            return false;
        }
        try {
            int hour = Integer.parseInt(parts[0]);
            int minute = Integer.parseInt(parts[1]);
            if (hour < 0 || hour > 23) {
                return false;
            }
            return minute == 0 || minute == 15 || minute == 30 || minute == 45;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isDoctorAvailable(Database db, Doctor doctor, LocalDateTime datetime) {
        for (Appointment a : db.getAppointments()) {
            if (a.getDoctor() != null && a.getDoctor().getId() == doctor.getId()
                    && a.getStatus() != AppointmentStatus.CANCELED
                    && a.getDatetime() != null && a.getDatetime().equals(datetime)) {
                return false;
            }
        }
        return true;
    }

    private static Patient resolvePatient(Database db, String selection) {
        if (selection == null) {
            return null;
        }
        String t = selection.trim();
        if (t.isEmpty()) {
            return null;
        }
        User byUsername = db.findUserByUsername(t);
        if (byUsername instanceof Patient) {
            return (Patient) byUsername;
        }
        Long id = leadingId(t);
        if (id != null) {
            User byId = db.findUserById(id);
            if (byId instanceof Patient) {
                return (Patient) byId;
            }
        }
        return null;
    }

    private static Doctor resolveDoctor(Database db, String selection) {
        if (selection == null) {
            return null;
        }
        String t = selection.trim();
        if (t.isEmpty()) {
            return null;
        }
        User byUsername = db.findUserByUsername(t);
        if (byUsername instanceof Doctor) {
            return (Doctor) byUsername;
        }
        Long id = leadingId(t);
        if (id != null) {
            User byId = db.findUserById(id);
            if (byId instanceof Doctor) {
                return (Doctor) byId;
            }
        }
        return null;
    }

    private static Long leadingId(String s) {
        int i = 0;
        while (i < s.length() && Character.isDigit(s.charAt(i))) {
            i++;
        }
        if (i == 0) {
            return null;
        }
        try {
            return Long.parseLong(s.substring(0, i));
        } catch (Exception e) {
            return null;
        }
    }
}
