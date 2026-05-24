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

    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
