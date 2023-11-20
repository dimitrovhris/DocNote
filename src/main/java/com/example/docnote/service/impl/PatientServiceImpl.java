package com.example.docnote.service.impl;

import com.example.docnote.model.DTO.PatientAddDTO;
import com.example.docnote.model.entity.Patient;
import com.example.docnote.repository.DoctorRepository;
import com.example.docnote.repository.PatientRepository;
import com.example.docnote.service.PatientService;
import com.example.docnote.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public PatientServiceImpl(PatientRepository patientRepository, DoctorRepository doctorRepository, CurrentUser currentUser) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.currentUser = currentUser;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void addPatient(PatientAddDTO patientAddDTO) {
        Patient patient = modelMapper.map(patientAddDTO, Patient.class);

        Long currentDoctorId = currentUser.getDoctor().getId();
        patient.setDoctor(doctorRepository.findById(currentDoctorId).get());
        currentUser.getDoctor().getPatients().add(patient);
        patientRepository.save(patient);
        doctorRepository.findById(currentDoctorId).get().getPatients().add(patient);
    }
}
