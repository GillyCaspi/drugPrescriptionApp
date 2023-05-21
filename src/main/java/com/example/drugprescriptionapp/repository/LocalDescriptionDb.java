package com.example.drugprescriptionapp.repository;

import com.example.drugprescriptionapp.entities.Medication;
import com.example.drugprescriptionapp.entities.Prescription;

import java.util.UUID;

public class LocalDescriptionDb implements DbAccess{

    @Override
    public String createPrescription(String patientId) {
        String id = UUID.randomUUID().toString();
        PrescriptionTbl.getInstance().addRecord(new Prescription(id, patientId));
        return id;
    }

    @Override
    public Prescription getPrescription(String prescriptionId) {
        return PrescriptionTbl.getInstance().getRecord(prescriptionId);
    }
}
