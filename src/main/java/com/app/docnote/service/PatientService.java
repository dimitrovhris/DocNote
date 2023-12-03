package com.app.docnote.service;

import com.app.docnote.model.DTO.PatientAddDTO;
import com.app.docnote.model.entity.Patient;

public interface PatientService {
    void addPatient(PatientAddDTO patientAddDTO, String username);
    Patient findById(Long id);

}
