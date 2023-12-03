package com.app.docnote.service.impl;

import com.app.docnote.model.DTO.TestAddDTO;
import com.app.docnote.model.entity.Test;
import com.app.docnote.service.TestService;
import com.app.docnote.model.entity.Patient;
import com.app.docnote.repository.PatientRepository;
import com.app.docnote.repository.TestRepository;
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
        patientRepository.save(patient);
        testRepository.save(test);
    }
}
