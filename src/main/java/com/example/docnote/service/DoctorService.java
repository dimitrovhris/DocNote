package com.example.docnote.service;

import com.example.docnote.model.DTO.DoctorLoginDTO;
import com.example.docnote.model.DTO.DoctorRegisterDTO;

public interface DoctorService {

    void register(DoctorRegisterDTO doctorRegisterDTO);

    boolean confirmPassword(DoctorRegisterDTO doctorRegisterDTO);
    boolean containsPhone(DoctorRegisterDTO doctorRegisterDTO);
    boolean containsUsername(DoctorRegisterDTO doctorRegisterDTO);
    boolean containsEmail(DoctorRegisterDTO doctorRegisterDTO);
    boolean validLogin(DoctorLoginDTO doctorLoginDTO);
}
