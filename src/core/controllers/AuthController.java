/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.*;
import storage.Database;
import java.util.HashMap;

/**
 *
 * @author josel
 */
public class AuthController {

    public static Response login(String username, String password) {
        try {
            if (username == null || username.trim().isEmpty()
                    || password == null || password.isEmpty()) {
                return new Response("Complete usuario y contraseña", Status.BAD_REQUEST);
            }

            Database db = Database.getInstance();
            User user = db.findUserByUsername(username.trim());
            if (user == null) {
                return new Response("El usuario no existe", Status.NOT_FOUND);
            }
            if (!user.getPassword().equals(password)) {
                return new Response("Contraseña incorrecta", Status.BAD_REQUEST);
            }

            String role = (user instanceof Administrator) ? "admin"
                        : (user instanceof Doctor) ? "doctor" : "patient";

            HashMap<String, Object> data = new HashMap<>();
            data.put("role", role);
            data.put("id", String.valueOf(user.getId()));
            return new Response("Bienvenido", Status.OK, data);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
