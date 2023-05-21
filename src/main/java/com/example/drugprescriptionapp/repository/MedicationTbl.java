package com.example.drugprescriptionapp.repository;

import java.util.ArrayList;
import java.util.HashMap;

public class MedicationTbl {

    private static volatile MedicationTbl instance;

    private HashMap<String, ArrayList<String>> medicationsTbl = null;

    private MedicationTbl() {
        medicationsTbl = new HashMap<>();
    }

    public static MedicationTbl getInstance() {
        if (instance == null) {
            synchronized (MedicationTbl.class) {
                if (instance == null) {
                    instance = new MedicationTbl();
                }
            }
        }
        return instance;
    }

    public void addRecord(String name, ArrayList<String> codes) {
        medicationsTbl.put(name, codes);
    }

    public ArrayList<String> getRecord(String name) {
        return medicationsTbl.get(name);
    }

    public boolean isExists(String name) {
        return medicationsTbl.containsKey(name);
    }

}
