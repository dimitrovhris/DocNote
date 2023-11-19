package com.docnote.service;

import com.docnote.model.DTO.DoctorLoginDTO;
import com.docnote.model.DTO.DoctorRegisterDTO;
import com.docnote.repository.DoctorRepository;

public interface DoctorService {

    void register(DoctorRegisterDTO doctorRegisterDTO);

    boolean confirmPassword(DoctorRegisterDTO doctorRegisterDTO);
    boolean containsPhone(DoctorRegisterDTO doctorRegisterDTO);
    boolean containsUsername(DoctorRegisterDTO doctorRegisterDTO);
    boolean containsEmail(DoctorRegisterDTO doctorRegisterDTO);
    boolean validLogin(DoctorLoginDTO doctorLoginDTO);
}
