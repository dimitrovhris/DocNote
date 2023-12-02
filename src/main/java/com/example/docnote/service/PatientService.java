package com.example.docnote.service;

import com.example.docnote.model.DTO.PatientAddDTO;
import com.example.docnote.model.entity.Patient;

public interface PatientService {
    void addPatient(PatientAddDTO patientAddDTO, String username);
    Patient findById(Long id);

}
