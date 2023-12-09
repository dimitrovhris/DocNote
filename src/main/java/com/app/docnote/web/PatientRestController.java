package com.app.docnote.web;

import com.app.docnote.model.entity.Patient;
import com.app.docnote.service.PatientService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/patient")
public class PatientRestController {
    private final PatientService patientService;

    public PatientRestController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/showInfo/{id}")
    public HttpEntity<Patient> showInfo(@PathVariable Long id){
        Patient patient = patientService.findById(id);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("firstName", patient.getFirstName());
        responseHeaders.set("surname", patient.getSurname());
        responseHeaders.set("lastName", patient.getLastName());
        responseHeaders.set("birthday", patient.getBirthday().toString());
        responseHeaders.set("egn", patient.getEgn());
        responseHeaders.set("address", patient.getAddress());
        responseHeaders.set("height", String.valueOf(patient.getHeight()));
        responseHeaders.set("weight", String.valueOf(patient.getWeight()));
        return new HttpEntity<>(patient, responseHeaders);
    }
}
