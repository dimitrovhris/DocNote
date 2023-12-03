package com.app.docnote.init;

import com.app.docnote.model.entity.UserEntity;
import com.app.docnote.model.entity.UserRole;
import com.app.docnote.model.enums.UserRoleEnum;
import com.app.docnote.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AdminInit implements CommandLineRunner {
    private UserRepository userRepository;

    public AdminInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        long conditions = userRepository.count();
        if(conditions == 0){
            UserEntity admin = new UserEntity("Admin", "Adminov", "Adminov", "admin", "0000000000", "admin@admin.bg", "Bulgaria, Sofia", "$2a$10$tLvO0mvPt1tTJ5kGLKp90OTEWfCG1CR.HnSbNec6txQx5/VmMg9Ii", true, new ArrayList<>(), new ArrayList<>());
            admin.getRoles().add(new UserRole(UserRoleEnum.ADMIN));
            admin.getRoles().add(new UserRole(UserRoleEnum.USER));
            userRepository.save(admin);
        }

    }
}
