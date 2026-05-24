/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

/**
 *
 * @author josel
 */

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.*;
import storage.Database;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DoctorController {

    public static Response register(String idStr, String username, String firstname, String lastname,
                                    String password, String passwordConfirm, String specialty,
                                    String licenseNumber, String assignedOffice) {
        try {
            if (isBlank(idStr) || isBlank(username) || isBlank(firstname) || isBlank(lastname)
                    || isBlank(password) || isBlank(passwordConfirm) || isBlank(specialty)
                    || isBlank(licenseNumber) || isBlank(assignedOffice)) {
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
            if (specialty.trim().equals("Select one")) {
                return new Response("Especialidad inválida", Status.BAD_REQUEST);
            }
            Specialty specialtyValue;
            try {
                specialtyValue = Specialty.valueOf(specialty.trim());
            } catch (Exception e) {
                return new Response("Especialidad inválida", Status.BAD_REQUEST);
            }
            if (!licenseNumber.trim().matches("L-\\d{10} MTL")) {
                return new Response("La licencia debe tener formato L-XXXXXXXXXX MTL (10 dígitos)", Status.BAD_REQUEST);
            }
            if (!assignedOffice.trim().matches("O-\\d{3}")) {
                return new Response("La oficina debe tener formato O-XXX (3 dígitos)", Status.BAD_REQUEST);
            }
            Doctor doctor = new Doctor(id, username.trim(), firstname.trim(), lastname.trim(),
                    password, specialtyValue, licenseNumber.trim(), assignedOffice.trim());
            db.addUser(doctor);
            return new Response("Doctor registrado correctamente", Status.OK);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response update(String doctorIdentifier, String firstname, String lastname,
                                  String specialty, String licenseNumber, String assignedOffice,
                                  String username, String password, String passwordConfirm) {
        try {
            Database db = Database.getInstance();
            Doctor doctor = resolveDoctor(db, doctorIdentifier);
            if (doctor == null) {
                return new Response("Doctor no encontrado", Status.NOT_FOUND);
            }
            if (isBlank(firstname) || isBlank(lastname) || isBlank(specialty)
                    || isBlank(licenseNumber) || isBlank(assignedOffice) || isBlank(username)) {
                return new Response("Complete todos los campos obligatorios", Status.BAD_REQUEST);
            }
            User other = db.findUserByUsername(username.trim());
            if (other != null && other != doctor) {
                return new Response("El username ya está en uso", Status.BAD_REQUEST);
            }
            if (specialty.trim().equals("Select one")) {
                return new Response("Especialidad inválida", Status.BAD_REQUEST);
            }
            Specialty specialtyValue = parseSpecialty(specialty);
            if (specialtyValue == null) {
                return new Response("Especialidad inválida", Status.BAD_REQUEST);
            }
            if (!licenseNumber.trim().matches("L-\\d{10} MTL")) {
                return new Response("La licencia debe tener formato L-XXXXXXXXXX MTL (10 dígitos)", Status.BAD_REQUEST);
            }
            if (!assignedOffice.trim().matches("O-\\d{3}")) {
                return new Response("La oficina debe tener formato O-XXX (3 dígitos)", Status.BAD_REQUEST);
            }
            if (!isBlank(password) || !isBlank(passwordConfirm)) {
                if (!password.equals(passwordConfirm)) {
                    return new Response("Las contraseñas no coinciden", Status.BAD_REQUEST);
                }
                doctor.setPassword(password);
            }
            doctor.setFirstname(firstname.trim());
            doctor.setLastname(lastname.trim());
            doctor.setSpecialty(specialtyValue);
            doctor.setLicenceNumber(licenseNumber.trim());
            doctor.setAssignedOffice(assignedOffice.trim());
            doctor.setUsername(username.trim());
            return new Response("Información actualizada correctamente", Status.OK);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getInfo(String doctorIdentifier) {
        try {
            Database db = Database.getInstance();
            Doctor doctor = resolveDoctor(db, doctorIdentifier);
            if (doctor == null) {
                return new Response("Doctor no encontrado", Status.NOT_FOUND);
            }
            HashMap<String, String> info = doctor.serialize();
            HashMap<String, Object> data = new HashMap<>();
            data.put("firstname", info.get("firstname"));
            data.put("lastname", info.get("lastname"));
            data.put("specialty", info.get("specialty"));
            data.put("licenceNumber", info.get("licenceNumber"));
            data.put("assignedOffice", info.get("assignedOffice"));
            data.put("username", info.get("username"));
            return new Response("Información del doctor", Status.OK, data);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getAll() {
        try {
            Database db = Database.getInstance();
            List<String> doctors = new ArrayList<>();
            for (User u : db.getUsers()) {
                if (u instanceof Doctor) {
                    Doctor d = (Doctor) u;
                    doctors.add(d.getId() + " - " + d.getFirstname() + " " + d.getLastname()
                            + " (" + d.getSpecialty().name() + ")");
                }
            }
            HashMap<String, Object> data = new HashMap<>();
            data.put("doctors", doctors);
            return new Response("Lista de doctores", Status.OK, data);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getSpecialties() {
        try {
            List<String> specialties = new ArrayList<>();
            for (Specialty s : Specialty.values()) {
                specialties.add(s.name());
            }
            HashMap<String, Object> data = new HashMap<>();
            data.put("specialties", specialties);
            return new Response("Lista de especialidades", Status.OK, data);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getDoctorAppointments(String doctorIdentifier, boolean onlyPending) {
        try {
            Database db = Database.getInstance();
            Doctor doctor = resolveDoctor(db, doctorIdentifier);
            if (doctor == null) {
                return new Response("Doctor no encontrado", Status.NOT_FOUND);
            }
            List<Appointment> filtered = new ArrayList<>();
            for (Appointment a : db.getAppointments()) {
                if (a.getDoctor() != null && a.getDoctor().getId() == doctor.getId()) {
                    if (!onlyPending || a.getStatus() == AppointmentStatus.PENDING) {
                        filtered.add(a);
                    }
                }
            }
            filtered.sort((a, b) -> b.getDatetime().compareTo(a.getDatetime()));
            List<String[]> rows = new ArrayList<>();
            for (Appointment a : filtered) {
                rows.add(new String[]{
                    a.getId(),
                    a.getDatetime().toString(),
                    a.getPatient().getFirstname() + " " + a.getPatient().getLastname(),
                    a.getSpecialty().name(),
                    String.valueOf(a.isType()),
                    a.getStatus().name()
                });
            }
            HashMap<String, Object> data = new HashMap<>();
            data.put("appointments", rows);
            return new Response("Citas del doctor", Status.OK, data);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response acceptAppointment(String appointmentId) {
        try {
            if (isBlank(appointmentId) || appointmentId.trim().equals("Select one")) {
                return new Response("Seleccione una cita válida", Status.BAD_REQUEST);
            }
            Database db = Database.getInstance();
            Appointment appointment = findAppointment(db, appointmentId);
            if (appointment == null) {
                return new Response("Cita no encontrada", Status.NOT_FOUND);
            }
            if (appointment.getStatus() != AppointmentStatus.REQUESTED) {
                return new Response("Solo se puede aceptar una cita en estado solicitado", Status.BAD_REQUEST);
            }
            appointment.setStatus(AppointmentStatus.PENDING);
            return new Response("Cita aceptada correctamente", Status.OK);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response completeAppointment(String appointmentId, String diagnosis, String observations,
                                               String recommendedTreatment, String followUp) {
        try {
            if (isBlank(appointmentId) || appointmentId.trim().equals("Select one")) {
                return new Response("Seleccione una cita válida", Status.BAD_REQUEST);
            }
            Database db = Database.getInstance();
            Appointment appointment = findAppointment(db, appointmentId);
            if (appointment == null) {
                return new Response("Cita no encontrada", Status.NOT_FOUND);
            }
            if (appointment.getStatus() != AppointmentStatus.PENDING) {
                return new Response("Solo se puede completar una cita pendiente", Status.BAD_REQUEST);
            }
            appointment.setStatus(AppointmentStatus.COMPLETED);
            appointment.setDiagnosis(diagnosis);
            appointment.setObservations(observations);
            appointment.setRecommendedTreatment(recommendedTreatment);
            appointment.setFollowUp(followUp);
            return new Response("Cita completada correctamente", Status.OK);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response rescheduleAppointment(String appointmentId, String newTime, String newReason) {
        try {
            if (isBlank(appointmentId) || appointmentId.trim().equals("Select one")) {
                return new Response("Seleccione una cita válida", Status.BAD_REQUEST);
            }
            Database db = Database.getInstance();
            Appointment appointment = findAppointment(db, appointmentId);
            if (appointment == null) {
                return new Response("Cita no encontrada", Status.NOT_FOUND);
            }
            if (appointment.getStatus() == AppointmentStatus.COMPLETED
                    || appointment.getStatus() == AppointmentStatus.CANCELED) {
                return new Response("No se puede reprogramar una cita completada o cancelada", Status.BAD_REQUEST);
            }
            if (!isValidTime(newTime)) {
                return new Response("La hora debe tener formato hh:mm con minutos 00, 15, 30 o 45", Status.BAD_REQUEST);
            }
            String[] parts = newTime.trim().split(":");
            LocalTime time = LocalTime.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
            LocalDateTime newDatetime = appointment.getDatetime().toLocalDate().atTime(time);
            appointment.setDatetime(newDatetime);
            if (!isBlank(newReason)) {
                String original = appointment.getReason();
                if (original == null || original.trim().isEmpty()) {
                    appointment.setReason(newReason.trim());
                } else {
                    appointment.setReason(original + " | " + newReason.trim());
                }
            }
            return new Response("Cita reprogramada correctamente", Status.OK);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response prescribe(String appointmentId, List<String[]> medications) {
        try {
            if (isBlank(appointmentId) || appointmentId.trim().equals("Select one")) {
                return new Response("Seleccione una cita válida", Status.BAD_REQUEST);
            }
            if (medications == null || medications.isEmpty()) {
                return new Response("Agregue al menos una medicación", Status.BAD_REQUEST);
            }
            Database db = Database.getInstance();
            Appointment appointment = findAppointment(db, appointmentId);
            if (appointment == null) {
                return new Response("Cita no encontrada", Status.NOT_FOUND);
            }
            if (appointment.getStatus() != AppointmentStatus.PENDING) {
                return new Response("Solo se puede prescribir en una cita pendiente", Status.BAD_REQUEST);
            }
            List<Object[]> parsed = new ArrayList<>();
            for (String[] m : medications) {
                if (m == null || m.length < 6) {
                    return new Response("Datos de medicación incompletos", Status.BAD_REQUEST);
                }
                if (isBlank(m[0])) {
                    return new Response("El nombre de la medicación es obligatorio", Status.BAD_REQUEST);
                }
                double dose;
                try {
                    dose = Double.parseDouble(m[1].trim());
                } catch (Exception e) {
                    return new Response("La dosis debe ser un número", Status.BAD_REQUEST);
                }
                int duration;
                try {
                    duration = Integer.parseInt(m[3].trim());
                } catch (Exception e) {
                    return new Response("La duración del tratamiento debe ser un número entero", Status.BAD_REQUEST);
                }
                int frequency;
                try {
                    frequency = Integer.parseInt(m[5].trim());
                } catch (Exception e) {
                    return new Response("La frecuencia debe ser un número entero", Status.BAD_REQUEST);
                }
                parsed.add(new Object[]{m[0].trim(), dose, m[2] == null ? "" : m[2].trim(),
                    duration, m[4] == null ? "" : m[4].trim(), frequency});
            }
            for (Object[] r : parsed) {
                new Prescription(appointment, (String) r[0], (double) r[1], (String) r[2],
                        (int) r[3], (String) r[4], (int) r[5]);
            }
            return new Response("Prescripción registrada correctamente", Status.OK);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
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

    private static Specialty parseSpecialty(String specialty) {
        if (specialty == null) {
            return null;
        }
        String t = specialty.trim();
        try {
            return Specialty.valueOf(t);
        } catch (Exception e) {
            String norm = t.toUpperCase().replace("&", " ").replaceAll("[^A-Z0-9]+", "_").replaceAll("^_|_$", "");
            try {
                return Specialty.valueOf(norm);
            } catch (Exception ex) {
                return null;
            }
        }
    }

    private static Appointment findAppointment(Database db, String id) {
        for (Appointment a : db.getAppointments()) {
            if (a.getId().equals(id.trim())) {
                return a;
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
