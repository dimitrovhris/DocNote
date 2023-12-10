package com.app.docnote.util;


import com.app.docnote.model.DTO.PatientAddDTO;
import com.app.docnote.model.DTO.UserRegisterDTO;
import com.app.docnote.model.entity.UserEntity;
import com.app.docnote.model.entity.UserRole;
import com.app.docnote.model.enums.UserRoleEnum;
import com.app.docnote.repository.*;
import com.app.docnote.service.PatientService;
import com.app.docnote.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Component
public class TestDataService {
    private final UserService userService;
    private final UserRepository userRepository;
    private final PatientService patientService;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final SicknessLeaveDocumentRepository sicknessLeaveDocumentRepository;
    private final TestRepository testRepository;

    public TestDataService(UserService userService, UserRepository userRepository, PatientService patientService, PatientRepository patientRepository, AppointmentRepository appointmentRepository, SicknessLeaveDocumentRepository sicknessLeaveDocumentRepository, TestRepository testRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.patientService = patientService;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.sicknessLeaveDocumentRepository = sicknessLeaveDocumentRepository;
        this.testRepository = testRepository;
    }
    public void initAdmins(){
        long conditions = userRepository.count();
        if(conditions == 0){
            UserEntity admin = new UserEntity("Admin", "Adminov", "Adminov", "admin", "0000000000", "admin@admin.bg", "Bulgaria, Sofia", "$2a$10$tLvO0mvPt1tTJ5kGLKp90OTEWfCG1CR.HnSbNec6txQx5/VmMg9Ii", true, new ArrayList<>(), new ArrayList<>());
            admin.getRoles().add(new UserRole(UserRoleEnum.ADMIN));
            admin.getRoles().add(new UserRole(UserRoleEnum.USER));
            userRepository.save(admin);
        }
    }
    public void initUsers(){
        userService.register(createUserRegisterDTO());
        userService.addAdmin(getIdByUsername("goshoWrong"));
    }
    public void tearDownDB(){
        appointmentRepository.deleteAll();
        sicknessLeaveDocumentRepository.deleteAll();
        testRepository.deleteAll();
        patientRepository.deleteAll();
        userRepository.deleteAll();
    }
    public Long getIdByUsername(String username){
        return userService.findFirstByUsername(username).getId();
    }
    private static UserRegisterDTO createUserRegisterDTO(){
        return  new UserRegisterDTO(
                "Georgi", "Georgiev", "Ivanov",
                "goshoWrong", "0000000001", "gogo@abvg.bg",
                "Stara Zagora Bg", "123gosho", "123gosho");
    }
    public PatientAddDTO createPatientAddDTO(){
        String str = "2001-01-01";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthday = LocalDate.parse(str, formatter);
        return new PatientAddDTO(
                "Petur", "Petrov", "Ivanov",
                "0000000002",birthday , 179, 79, "Sofia Bulgaria"
        );
    }
}
