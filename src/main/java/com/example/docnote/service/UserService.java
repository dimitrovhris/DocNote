package com.example.docnote.service;

import com.example.docnote.model.DTO.UserRegisterDTO;
import com.example.docnote.model.entity.UserEntity;

import java.util.List;

public interface UserService {

    void register(UserRegisterDTO doctorRegisterDTO);
    boolean confirmPassword(UserRegisterDTO doctorRegisterDTO);
    boolean containsEgn(UserRegisterDTO doctorRegisterDTO);
    boolean containsUsername(UserRegisterDTO doctorRegisterDTO);
    boolean containsEmail(UserRegisterDTO doctorRegisterDTO);
    void remove(UserEntity user);
    List<UserEntity> findAdmins();
    void removeAsAdmin(Long userId);
    List<UserEntity> findNotApproved();
    UserEntity findFirstByUsername(String username);
    UserEntity findById(Long id);
    void approve(Long id);
}
