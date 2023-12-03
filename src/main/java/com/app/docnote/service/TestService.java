package com.app.docnote.service;

import com.app.docnote.model.DTO.TestAddDTO;

public interface TestService {
    void add(TestAddDTO testAddDTO, Long id);
}
