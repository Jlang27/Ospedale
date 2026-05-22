/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;
import core.models.*;
import storage.Database;


/**
 *
 * @author josel
 */
public class AuthController {
    private final Database db = Database.getInstance();
    
    public Response login(String username, String password){
        if(username.isEmpty() || password.isEmpty()){
            return new Response(400, "Complete los campos de usuario y contraseña", null);
        }
        User u = db.findUserByUsername(username);
        if (u == null){
            return new Response(404, "El usuario No existe", null);
        }
        if(!u.getPassword().equals(password)){
            return new Response(400, "Contraseña incorrecta", null);
        }
        
        String role = (u instanceof Administrator) ? "admin" : (u instanceof Doctor) ? "doctor" : "patient";
        java.util.Map<String, String> data
    }
}
