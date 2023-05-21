package com.example.drugprescriptionapp.validations;

import com.example.drugprescriptionapp.repository.MedicationTbl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class MedicationValidate {

    private static final String url = "https://clinicaltables.nlm.nih.gov/api/rxterms/v3/search?";

    public static String checkIfMedicationExists(String name) {
        if (!MedicationTbl.getInstance().isExists(name)) {
            return searchForMedicationOnLine(name);
        }
        return null;
    }

    private static String searchForMedicationOnLine(String name) {
        try {
            String uri = url + "terms=" + name.replace(" ", "+") + "&ef=RXCUIS";
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI(uri)).build();
            HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

            JsonNode node = new ObjectMapper().readTree(httpResponse.body());
            List<List<String>> list = new ObjectMapper().convertValue(node.findValue("RXCUIS"), new TypeReference<List<List<String>>>(){});

            if (list != null && list.size() > 0) {
                if(list.get(0) != null && list.get(0).size() > 0){
                    MedicationTbl.getInstance().addRecord(name, (ArrayList<String>) list.get(0));
                    return null;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Medication was not found";
    }


}
