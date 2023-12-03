package com.app.docnote.service;

import com.app.docnote.model.DTO.DocumentAddDTO;

public interface SicknessLeaveDocumentService {
    void add(DocumentAddDTO documentAddDTO, Long id);
}
