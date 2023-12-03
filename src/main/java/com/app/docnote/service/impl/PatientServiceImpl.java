package com.app.docnote.service.impl;

import com.app.docnote.service.PatientService;
import com.app.docnote.model.DTO.PatientAddDTO;
import com.app.docnote.model.entity.Patient;
import com.app.docnote.repository.PatientRepository;
import com.app.docnote.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final UserRepository doctorRepository;
    private final ModelMapper modelMapper;


    public PatientServiceImpl(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.doctorRepository = userRepository;

        this.modelMapper = new ModelMapper();
    }

    @Override
    public void addPatient(PatientAddDTO patientAddDTO, String username) {
        Patient patient = modelMapper.map(patientAddDTO, Patient.class);
        patient.setDoctor(doctorRepository.findFirstByUsername(username).get());
        patientRepository.save(patient);
        doctorRepository.findFirstByUsername(username).get().getPatients().add(patient);
    }

    @Override
    public Patient findById(Long id) {
        return patientRepository.findById(id).get();
    }
}
