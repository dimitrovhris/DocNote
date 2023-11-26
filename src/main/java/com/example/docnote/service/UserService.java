package com.example.docnote.service;

import com.example.docnote.model.DTO.UserRegisterDTO;

public interface UserService {

    void register(UserRegisterDTO doctorRegisterDTO);

    boolean confirmPassword(UserRegisterDTO doctorRegisterDTO);
    boolean containsPhone(UserRegisterDTO doctorRegisterDTO);
    boolean containsUsername(UserRegisterDTO doctorRegisterDTO);
    boolean containsEmail(UserRegisterDTO doctorRegisterDTO);

}
