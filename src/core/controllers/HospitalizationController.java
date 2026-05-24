package core.controllers;

import core.controllers.utils.Response;

public class HospitalizationController {

    public static Response request(String username, String doctor, String reason,
            String admissionDate, String roomType, String observations) {
        return new Response("Not implemented", 500);
    }

    public static Response getByDoctor(String username) {
        return new Response("Not implemented", 500);
    }

    public static Response approve(String id) {
        return new Response("Not implemented", 500);
    }

    public static Response deny(String id) {
        return new Response("Not implemented", 500);
    }
}
