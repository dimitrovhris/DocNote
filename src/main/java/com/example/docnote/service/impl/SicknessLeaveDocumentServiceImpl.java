package com.example.docnote.service.impl;

import com.example.docnote.model.DTO.DocumentAddDTO;
import com.example.docnote.model.entity.Patient;
import com.example.docnote.model.entity.SicknessLeaveDocument;
import com.example.docnote.repository.PatientRepository;
import com.example.docnote.repository.SicknessLeaveDocumentRepository;
import com.example.docnote.service.SicknessLeaveDocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SicknessLeaveDocumentServiceImpl implements SicknessLeaveDocumentService {
    private final ModelMapper modelMapper;
    private final SicknessLeaveDocumentRepository documentRepository;
    private final PatientRepository patientRepository;

    public SicknessLeaveDocumentServiceImpl(SicknessLeaveDocumentRepository documentRepository, PatientRepository patientRepository) {
        this.documentRepository = documentRepository;
        this.patientRepository = patientRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void add(DocumentAddDTO documentAddDTO, Long id) {
        SicknessLeaveDocument document = modelMapper.map(documentAddDTO, SicknessLeaveDocument.class);
        Patient patient = patientRepository.findById(id).get();
        document.setPatient(patient);
        patient.getSicknessLeaveDocuments().add(document);
        documentRepository.save(document);
        patientRepository.save(patient);
    }
}
