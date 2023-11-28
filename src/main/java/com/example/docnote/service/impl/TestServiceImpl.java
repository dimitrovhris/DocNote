package com.example.docnote.service.impl;

import com.example.docnote.model.DTO.TestAddDTO;
import com.example.docnote.model.entity.Patient;
import com.example.docnote.model.entity.Test;
import com.example.docnote.repository.PatientRepository;
import com.example.docnote.repository.TestRepository;
import com.example.docnote.service.TestService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {
    private final TestRepository testRepository;

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public TestServiceImpl(TestRepository testRepository, PatientRepository patientRepository) {
        this.testRepository = testRepository;
        this.patientRepository = patientRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void add(TestAddDTO testAddDTO, Long id) {
        Test test = modelMapper.map(testAddDTO, Test.class);
        Patient patient = patientRepository.findById(id).get();
        patient.getTests().add(test);
        test.setPatient(patient);
        testRepository.save(test);
    }
}
