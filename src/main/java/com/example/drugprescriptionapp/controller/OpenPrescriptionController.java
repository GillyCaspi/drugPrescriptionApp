package com.example.drugprescriptionapp.controller;

import com.example.drugprescriptionapp.repository.DbFactory;

public class OpenPrescriptionController {

    public static String openPrescription(String id){
        if(id == null || id.isEmpty()){
            return null;
        }
        return DbFactory.getDescriptionDb().createPrescription(id);
    }
}
