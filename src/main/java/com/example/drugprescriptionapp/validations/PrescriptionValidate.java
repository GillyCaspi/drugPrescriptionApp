package com.example.drugprescriptionapp.validations;

import com.example.drugprescriptionapp.entities.Medication;
import com.example.drugprescriptionapp.entities.Prescription;
import com.example.drugprescriptionapp.repository.MedicationTbl;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PrescriptionValidate {

    private static final String url = "https://rxnav.nlm.nih.gov/REST/interaction/list.json?rxcuis=";

    public static String getWarningsForPrescription(Prescription p) {
        {
            if (p == null || p.getMedications().size() <= 1) {
                return "No Warnings";
            }
            String allCodes = getAllMedicationCodes(p.getMedications());
            return getWarningsOnLine(allCodes);
        }
    }

    private static String getAllMedicationCodes(List<Medication> medications) {
        if (medications == null) {
            return "";
        }
        StringBuffer codes = new StringBuffer();
        medications.forEach(m -> {
            ArrayList<String> list = MedicationTbl.getInstance().getRecord(m.getName());
            if (list != null) {
                list.forEach(x -> {
                    codes.append(x + "+");
                });
            }
        });
        return codes.toString();
    }

    public static String getWarningsOnLine(String allCodes) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI(url + allCodes)).build();
            HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
            JsonNode jsonNode = new ObjectMapper().readTree(httpResponse.body());
            List<JsonNode> warningsList = jsonNode.findValues("description");
            if(warningsList == null){
                return "No warnings found";
            }
            StringBuffer warning = new StringBuffer();
            Set<String> setForPreventDuplicates = new HashSet<>();
            warningsList.forEach(node -> {
                setForPreventDuplicates.add(node.asText());
            });
            setForPreventDuplicates.forEach(x -> {
                warning.append(x);
                warning.append("\n");
            });
            return warning.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Error searching for warnings on line";
    }


}
