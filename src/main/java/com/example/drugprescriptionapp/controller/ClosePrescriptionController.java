package com.example.drugprescriptionapp.controller;

import com.example.drugprescriptionapp.entities.Prescription;
import com.example.drugprescriptionapp.repository.DbFactory;
import com.example.drugprescriptionapp.validations.PrescriptionValidate;

public class ClosePrescriptionController {

    public static String closePrescription(String id) {
        if (id == null || id.isEmpty()) {
            return null;
        }
        Prescription p = DbFactory.getDescriptionDb().getPrescription(id);
        if (p == null) {
            return "PRESCRIPTION DOES NOT EXISTS";
        }
        if (p != null && p.isOpen()) {
            p.closePrescription();
            return PrescriptionValidate.getWarningsForPrescription(p);
        }
        return "PRESCRIPTION CLOSED";
    }

}
