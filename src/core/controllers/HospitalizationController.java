/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.*;
import storage.Database;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author josel
 */

public class HospitalizationController {

    public static Response request(String patientUsername, String doctorSelected, String reason,
                                   String admissionDate, String roomType, String observations) {
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
            if (isBlank(reason)) {
                return new Response("Indique el motivo de la hospitalización", Status.BAD_REQUEST);
            }
            if (isBlank(admissionDate)) {
                return new Response("La fecha de ingreso debe tener formato AAAA-MM-DD", Status.BAD_REQUEST);
            }
            LocalDate date;
            try {
                date = LocalDate.parse(admissionDate.trim());
            } catch (Exception e) {
                return new Response("La fecha de ingreso debe tener formato AAAA-MM-DD", Status.BAD_REQUEST);
            }
            if (isBlank(roomType) || roomType.trim().equals("Select one")) {
                return new Response("Tipo de habitación inválido", Status.BAD_REQUEST);
            }
            RoomType roomTypeValue;
            try {
                roomTypeValue = RoomType.valueOf(roomType.trim());
            } catch (Exception e) {
                return new Response("Tipo de habitación inválido", Status.BAD_REQUEST);
            }
            String id = db.nextHospitalizationId(patient.getId());
            Hospitalization hospitalization = new Hospitalization(id, patient, doctor, date,
                    reason, roomTypeValue, observations);
            db.addHospitalization(hospitalization);
            return new Response("Hospitalización solicitada correctamente", Status.OK);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response approveHospitalization(String hospitalizationId) {
        try {
            if (isBlank(hospitalizationId) || hospitalizationId.trim().equals("Select one")) {
                return new Response("Seleccione una hospitalización válida", Status.BAD_REQUEST);
            }
            Database db = Database.getInstance();
            Hospitalization hospitalization = findHospitalization(db, hospitalizationId);
            if (hospitalization == null) {
                return new Response("Hospitalización no encontrada", Status.NOT_FOUND);
            }
            if (hospitalization.getStatus() != HospitalizationStatus.REQUESTED) {
                return new Response("Solo se puede aprobar una hospitalización en estado solicitado", Status.BAD_REQUEST);
            }
            hospitalization.setStatus(HospitalizationStatus.ONGOING);
            return new Response("Hospitalización aprobada correctamente", Status.OK);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response denyHospitalization(String hospitalizationId) {
        try {
            if (isBlank(hospitalizationId) || hospitalizationId.trim().equals("Select one")) {
                return new Response("Seleccione una hospitalización válida", Status.BAD_REQUEST);
            }
            Database db = Database.getInstance();
            Hospitalization hospitalization = findHospitalization(db, hospitalizationId);
            if (hospitalization == null) {
                return new Response("Hospitalización no encontrada", Status.NOT_FOUND);
            }
            if (hospitalization.getStatus() != HospitalizationStatus.REQUESTED) {
                return new Response("Solo se puede denegar una hospitalización en estado solicitado", Status.BAD_REQUEST);
            }
            hospitalization.setStatus(HospitalizationStatus.CANCELED);
            return new Response("Hospitalización denegada correctamente", Status.OK);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response hospitalizeFromAppointment(String appointmentId, String reason,
                                                      String admissionDate, String observations) {
        try {
            if (isBlank(appointmentId) || appointmentId.trim().equals("Select one")) {
                return new Response("Seleccione una cita válida", Status.BAD_REQUEST);
            }
            Database db = Database.getInstance();
            Appointment appointment = null;
            for (Appointment a : db.getAppointments()) {
                if (a.getId().equals(appointmentId.trim())) {
                    appointment = a;
                    break;
                }
            }
            if (appointment == null) {
                return new Response("Cita no encontrada", Status.NOT_FOUND);
            }
            if (appointment.getStatus() == AppointmentStatus.CANCELED) {
                return new Response("No se puede hospitalizar desde una cita cancelada", Status.BAD_REQUEST);
            }
            if (isBlank(reason)) {
                return new Response("Indique el motivo de la hospitalización", Status.BAD_REQUEST);
            }
            LocalDate date;
            if (isBlank(admissionDate)) {
                date = appointment.getDatetime().toLocalDate();
            } else {
                try {
                    date = LocalDate.parse(admissionDate.trim());
                } catch (Exception e) {
                    return new Response("La fecha de ingreso debe tener formato AAAA-MM-DD", Status.BAD_REQUEST);
                }
            }
            Patient patient = appointment.getPatient();
            Doctor doctor = appointment.getDoctor();
            appointment.setStatus(AppointmentStatus.COMPLETED);
            String id = db.nextHospitalizationId(patient.getId());
            Hospitalization hospitalization = new Hospitalization(id, patient, doctor, date,
                    reason.trim(), RoomType.STANDARD, observations, HospitalizationStatus.ONGOING);
            db.addHospitalization(hospitalization);
            return new Response("Hospitalización generada correctamente", Status.OK);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response getDoctorHospitalizations(String doctorIdentifier, boolean onlyRequested) {
        try {
            Database db = Database.getInstance();
            Doctor doctor = resolveDoctor(db, doctorIdentifier);
            if (doctor == null) {
                return new Response("Doctor no encontrado", Status.NOT_FOUND);
            }
            List<String[]> rows = new ArrayList<>();
            for (Hospitalization h : db.getHospitalizations()) {
                if (h.getDoctor() != null && h.getDoctor().getId() == doctor.getId()) {
                    if (!onlyRequested || h.getStatus() == HospitalizationStatus.REQUESTED) {
                        rows.add(new String[]{
                            h.getId(),
                            h.getDate().toString(),
                            h.getPatient().getFirstname() + " " + h.getPatient().getLastname(),
                            h.getRoomType().name(),
                            h.getStatus().name()
                        });
                    }
                }
            }
            HashMap<String, Object> data = new HashMap<>();
            data.put("hospitalizations", rows);
            return new Response("Hospitalizaciones del doctor", Status.OK, data);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }

    private static Hospitalization findHospitalization(Database db, String id) {
        for (Hospitalization h : db.getHospitalizations()) {
            if (h.getId().equals(id.trim())) {
                return h;
            }
        }
        return null;
    }

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
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
