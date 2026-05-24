package core.controllers;

import core.controllers.utils.Response;
import core.models.Administrator;
import core.models.Doctor;
import java.util.HashMap;
import packagee.User;
import storage.Database;

public class AuthController {

    public static Response login(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return new Response("Complete los campos de usuario y contraseña", 400);
        }
        Database db = Database.getInstance();
        User u = db.findUserByUsername(username);
        if (u == null) {
            return new Response("El usuario no existe", 404);
        }
        if (!u.getPassword().equals(password)) {
            return new Response("Contraseña incorrecta", 400);
        }
        String role = (u instanceof Administrator) ? "admin" : (u instanceof Doctor) ? "doctor" : "patient";
        HashMap<String, Object> data = new HashMap<>();
        data.put("username", u.getUsername());
        data.put("role", role);
        return new Response("Login exitoso", 200, data);
    }
}
