package com.example.drugprescriptionapp.controller;

import com.example.drugprescriptionapp.entities.Medication;
import com.example.drugprescriptionapp.entities.Prescription;
import com.example.drugprescriptionapp.repository.DbFactory;
import com.example.drugprescriptionapp.validations.MedicationValidate;

public class AddMedicationController {

    public static String addMedication(String id, String name, int dosage, int frequency) {
        if (id == null || id.isEmpty() || name == null || name.isEmpty()) {
            return null;
        }
        Prescription p = DbFactory.getDescriptionDb().getPrescription(id);
        if(p == null){
            return "PRESCRIPTION DOES NOT EXISTS";
        }
        if(!p.isOpen()){
            return "PRESCRIPTION CLOSED. NO ADDING MEDICATION ALLOWED";
        }
        String error = MedicationValidate.checkIfMedicationExists(name);
        if(error != null){
            return error;
        }
        p.addMedication(new Medication(name,frequency, dosage));
        return "ADD SUCCESSFULLY";

    }
}
