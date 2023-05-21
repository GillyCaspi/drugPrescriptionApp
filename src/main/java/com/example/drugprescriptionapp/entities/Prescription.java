package com.example.drugprescriptionapp.entities;

import java.util.ArrayList;
import java.util.List;

public class Prescription {

    private String id;
    private String patientId;
    private Status status;
    private List<Medication> medications = new ArrayList<>();


    public enum Status {
        open,
        close
    }

    public Prescription(String id, String patientId) {
        this.id = id;
        this.patientId = patientId;
        this.status = Status.open;
    }

    public String getId() {
        return id;
    }

    public void addMedication(Medication medication){
        medications.add(medication);
    }

    public List<Medication> getMedications(){
        return medications;
    }

    public void closePrescription(){
        this.status = Status.close;
    }

    public boolean isOpen(){
        return (this.status == Status.open);
    }

}
