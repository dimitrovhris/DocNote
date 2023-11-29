package com.example.docnote.service;

import com.example.docnote.model.DTO.UserRegisterDTO;
import com.example.docnote.model.entity.UserEntity;

public interface UserService {

    void register(UserRegisterDTO doctorRegisterDTO);

    boolean confirmPassword(UserRegisterDTO doctorRegisterDTO);
    boolean containsEgn(UserRegisterDTO doctorRegisterDTO);
    boolean containsUsername(UserRegisterDTO doctorRegisterDTO);
    boolean containsEmail(UserRegisterDTO doctorRegisterDTO);
    void remove(UserEntity user);

}
