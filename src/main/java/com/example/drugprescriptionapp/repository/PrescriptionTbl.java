package com.example.drugprescriptionapp.repository;

import com.example.drugprescriptionapp.entities.Prescription;

import java.util.HashMap;

public class PrescriptionTbl {

    private static volatile PrescriptionTbl instance;
    public HashMap<String, Prescription> prescriptionsTbl = null;

    private PrescriptionTbl() {
        prescriptionsTbl = new HashMap<>();
    }

    public static PrescriptionTbl getInstance() {
        if (instance == null) {
            synchronized (PrescriptionTbl.class) {
                if (instance == null) {
                    instance = new PrescriptionTbl();
                }
            }
        }
        return instance;
    }

    public void addRecord(Prescription p){
        prescriptionsTbl.put(p.getId(), p);
    }

    public Prescription getRecord(String id){
        return prescriptionsTbl.get(id);
    }


}
