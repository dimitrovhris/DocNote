package com.example.docnote.service.impl;

import com.example.docnote.model.DTO.UserRegisterDTO;
import com.example.docnote.model.entity.UserEntity;
import com.example.docnote.model.entity.UserRole;
import com.example.docnote.model.enums.UserRoleEnum;
import com.example.docnote.repository.UserRepository;
import com.example.docnote.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        UserEntity user = new UserEntity();

        user.setFirstName(userRegisterDTO.getFirstName());
        user.setSurname(userRegisterDTO.getSurname());
        user.setLastName(userRegisterDTO.getLastName());
        user.setUsername(userRegisterDTO.getUsername());
        user.setEmail(userRegisterDTO.getEmail());
        user.setEgn(userRegisterDTO.getEgn());
        user.setAddress(userRegisterDTO.getAddress());

        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        user.setApproved(false);
        user.getRoles().add(new UserRole(UserRoleEnum.USER));
        userRepository.save(user);
        
    }

    @Override
    public boolean confirmPassword(UserRegisterDTO userRegisterDTO) {
        if(userRegisterDTO.getPassword()!= null){
        return userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword());
        }
        return false;
    }

    @Override
    public boolean containsEgn(UserRegisterDTO userRegisterDTO) {
        return userRepository.findFirstByEgn(userRegisterDTO.getEgn()).isPresent();
    }

    @Override
    public boolean containsUsername(UserRegisterDTO userRegisterDTO) {
        return userRepository.findFirstByUsername(userRegisterDTO.getUsername()).isPresent();
    }

    @Override
    public boolean containsEmail(UserRegisterDTO userRegisterDTO) {
        return userRepository.findFirstByEmail(userRegisterDTO.getEmail()).isPresent();
    }

    @Override
    public void remove(UserEntity user) {
        userRepository.delete(user);
    }

}
