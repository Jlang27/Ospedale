package core.controllers;

import core.controllers.utils.Response;

public class PatientController {

    public static Response register(String idStr, String user, String firstname, String lastname,
            String password, String passwordConfirm, String email, String birthdate,
            String gender, String phone, String address) {
        return new Response("Not implemented", 500);
    }

    public static Response getAll() {
        return new Response("Not implemented", 500);
    }

    public static Response getInfo(String username) {
        return new Response("Not implemented", 500);
    }

    public static Response update(String username, String firstname, String lastname,
            String birthdate, String gender, String email, String phone,
            String address, String newUsername, String password, String passwordConfirm) {
        return new Response("Not implemented", 500);
    }

    public static Response getAppointments(String username) {
        return new Response("Not implemented", 500);
    }

    public static Response requestAppointmentBySpecialty(String username, String specialty,
            String date, String time, String type, String reason) {
        return new Response("Not implemented", 500);
    }

    public static Response requestAppointmentByDoctor(String username, String doctor,
            String date, String time, String reason) {
        return new Response("Not implemented", 500);
    }

    public static Response cancelAppointment(String appointmentId, String observations) {
        return new Response("Not implemented", 500);
    }
}
