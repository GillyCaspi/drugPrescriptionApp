package com.example.drugprescriptionapp.repository;

public class DbFactory {

    public static DbAccess getDescriptionDb(){
        // Add condition if we work local
        return new LocalDescriptionDb();
    }


}
