/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models;

import core.models.Appointment;
import java.util.HashMap;

/**
 *
 * @author jjlora
 */
public class Prescription {
    private Appointment appointment;
    private String medicationName;
    private double dose;
    private String administrationRoute;
    private int treatmentDuration;
    private String additionalInstructions;
    private int frecuency;

    public Prescription(Appointment appointment, String medicationName, double dose, String administrationRoute, int treatmentDuration, String additionalInstructions, int frecuency) {
        this.appointment = appointment;
        appointment.addPrescription(this);
        this.medicationName = medicationName;
        this.dose = dose;
        this.administrationRoute = administrationRoute;
        this.treatmentDuration = treatmentDuration;
        this.additionalInstructions = additionalInstructions;
        this.frecuency = frecuency;
    }

    public HashMap<String, String> serialize() {
        HashMap<String, String> map = new HashMap<>();
        map.put("appointmentId", appointment.getId());
        map.put("medicationName", medicationName);
        map.put("dose", String.valueOf(dose));
        map.put("administrationRoute", administrationRoute);
        map.put("treatmentDuration", String.valueOf(treatmentDuration));
        map.put("additionalInstructions", additionalInstructions);
        map.put("frecuency", String.valueOf(frecuency));
        return map;
    }

}
