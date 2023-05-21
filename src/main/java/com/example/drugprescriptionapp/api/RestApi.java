package com.example.drugprescriptionapp.api;

import com.example.drugprescriptionapp.controller.AddMedicationController;
import com.example.drugprescriptionapp.controller.ClosePrescriptionController;
import com.example.drugprescriptionapp.controller.OpenPrescriptionController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApi {

    @PostMapping("/openPrescription")
    public String openPrescription(String patientId) {
        return OpenPrescriptionController.openPrescription(patientId);
    }

    @PostMapping("/addMedication")
    public String addMedication(String prescription, String name, int dosage, int frequency) {
        return AddMedicationController.addMedication(prescription, name, dosage, frequency);
    }

    @PostMapping("/closePrescription")
    public String closePrescription(String prescription) {
        return ClosePrescriptionController.closePrescription(prescription);
    }


}
