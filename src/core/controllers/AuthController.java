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
            if (username.trim().isEmpty() || password.trim().isEmpty())
                return new Response("Complete usuario y contraseña", Status.BAD_REQUEST);
 
            Database db = Database.getInstance();
            User u = db.findUserByUsername(username.trim());
            if (u == null)
                return new Response("El usuario no existe", Status.NOT_FOUND);
            if (!u.getPassword().equals(password))
                return new Response("Contraseña incorrecta", Status.BAD_REQUEST);
 
            // tipo de usuario para que la vista navegue:
            String role = (u instanceof Administrator) ? "admin"
                        : (u instanceof Doctor) ? "doctor" : "patient";
            HashMap<String, Object> data = new HashMap<>();
            data.put("role", role);
            data.put("id", u.getId());
            return new Response("Bienvenido", Status.OK, data);
        } catch (Exception ex) {
            return new Response("Error inesperado", Status.INTERNAL_SERVER_ERROR);
        }
    }
}

