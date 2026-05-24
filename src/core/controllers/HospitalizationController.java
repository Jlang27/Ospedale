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
