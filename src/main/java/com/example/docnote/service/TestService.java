package com.example.docnote.service;

import com.example.docnote.model.DTO.TestAddDTO;

public interface TestService {
    void add(TestAddDTO testAddDTO, Long id);
}
