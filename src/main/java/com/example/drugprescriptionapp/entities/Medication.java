package com.example.drugprescriptionapp.entities;

public class Medication {
    private String name;
    private int frequency;
    private int dosage;

    public Medication(String name, int frequency, int dosage) {
        this.name = name;
        this.frequency = frequency;
        this.dosage = dosage;
    }

    public String getName(){
        return name;
    }

    public int getFrequency(){
        return frequency;
    }

    public int getDosage(){
        return dosage;
    }

}
