package com.example.drugprescriptionapp.repository;

import com.example.drugprescriptionapp.entities.Prescription;

public interface DbAccess {

    String createPrescription(String patientId);
    Prescription getPrescription(String prescription);
}
