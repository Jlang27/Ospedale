package core.controllers;

import core.controllers.utils.Response;

public class DoctorController {

    public static Response register(String idStr, String user, String firstname, String lastname,
            String password, String passwordConfirm, String specialty,
            String licenseNumber, String assignedOffice) {
        return new Response("Not implemented", 500);
    }

    public static Response getAll() {
        return new Response("Not implemented", 500);
    }

    public static Response getInfo(String username) {
        return new Response("Not implemented", 500);
    }

    public static Response update(String id, String username, String firstname, String lastname,
            String password, String passwordConfirm, String specialty,
            String licenseNumber, String office) {
        return new Response("Not implemented", 500);
    }

    public static Response getSpecialties() {
        return new Response("Not implemented", 500);
    }

    public static Response getAppointments(String username, boolean pendingOnly) {
        return new Response("Not implemented", 500);
    }

    public static Response acceptAppointment(String id) {
        return new Response("Not implemented", 500);
    }

    public static Response completeAppointment(String id) {
        return new Response("Not implemented", 500);
    }

    public static Response rescheduleAppointment(String id, String newTime, String reason) {
        return new Response("Not implemented", 500);
    }

    public static Response prescribe(String id, String medication, String dose, String instructions) {
        return new Response("Not implemented", 500);
    }

    public static Response hospitalizeFromAppointment(String id) {
        return new Response("Not implemented", 500);
    }
}
