package com.example.docnote.service;

import com.example.docnote.model.DTO.DocumentAddDTO;

public interface SicknessLeaveDocumentService {
    void add(DocumentAddDTO documentAddDTO, Long id);
}
