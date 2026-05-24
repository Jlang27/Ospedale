/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import java.util.HashMap;

/**
 *
 * @author edangulo
 */
public class Administrator extends User {

    public Administrator(long id, String username, String firstname, String lastname, String password) {
        super(id, username, firstname, lastname, password);
    }

    public HashMap<String, String> serialize() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        map.put("username", username);
        map.put("firstname", firstname);
        map.put("lastname", lastname);
        map.put("password", password);
        return map;
    }

}
