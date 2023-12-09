package com.app.docnote.service;

import com.app.docnote.model.DTO.UserRegisterDTO;
import com.app.docnote.model.entity.UserEntity;

import java.util.List;

public interface UserService {

    void register(UserRegisterDTO doctorRegisterDTO);
    boolean confirmPassword(UserRegisterDTO doctorRegisterDTO);
    boolean containsEgn(UserRegisterDTO doctorRegisterDTO);
    boolean containsUsername(UserRegisterDTO doctorRegisterDTO);
    boolean containsEmail(UserRegisterDTO doctorRegisterDTO);
    void remove(Long id);
    List<UserEntity> findAdmins();
    void removeAsAdmin(Long userId);
    List<UserEntity> findNotApproved();
    UserEntity findFirstByUsername(String username);
    UserEntity findById(Long id);
    void approve(Long id);
    List<UserEntity> findNotAdmins();
    void addAdmin(Long id);
    void removeByUsername(String username);

    boolean isAuthenticated();

    boolean isUserAuthorized(String requestURI);
}
