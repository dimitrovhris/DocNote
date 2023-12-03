package com.app.docnote.service.impl;

import com.app.docnote.service.UserService;
import com.app.docnote.model.DTO.UserRegisterDTO;
import com.app.docnote.model.entity.UserEntity;
import com.app.docnote.model.entity.UserRole;
import com.app.docnote.model.enums.UserRoleEnum;
import com.app.docnote.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        if (userRegisterDTO.getPassword() != null) {
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
    public void remove(Long id) {
        UserEntity user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    @Override
    public List<UserEntity> findAdmins() {
        List<UserEntity> adminList = new ArrayList<>();
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity user : users) {
            List<UserRole> currentRoles = user.getRoles();
            for (UserRole userRole : currentRoles) {
                if (userRole.getRole().equals(UserRoleEnum.ADMIN)) {
                    adminList.add(user);
                }
            }
        }
        return adminList;
    }

    @Override
    public void removeAsAdmin(Long userId) {
        UserEntity adminToRemove = userRepository.findById(userId).get();
        if (userId != 1) {
            adminToRemove.getRoles().remove(1);
        }
        userRepository.save(adminToRemove);
    }

    @Override
    public List<UserEntity> findNotApproved() {
        return userRepository.findByApprovedFalse();
    }

    @Override
    public UserEntity findFirstByUsername(String username) {
        return userRepository.findFirstByUsername(username).get();
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void approve(Long id) {
        UserEntity user = userRepository.findById(id).get();
        user.setApproved(true);
        userRepository.save(user);
    }

    @Override
    public List<UserEntity> findNotAdmins() {
        List<UserEntity> notAdmins = new ArrayList<>();
        for (UserEntity user : userRepository.findAll()) {
            List<UserRole> currentRoles = user.getRoles();
            if (currentRoles.size() == 1 && user.isApproved()) {
                notAdmins.add(user);
            }
        }
        return notAdmins;
    }

    @Override
    public void addAdmin(Long id) {
        UserEntity user = userRepository.findById(id).get();
        user.getRoles().add(new UserRole(UserRoleEnum.ADMIN));
        userRepository.save(user);
    }
}
