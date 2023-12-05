package com.app.docnote.util;


import com.app.docnote.model.DTO.PatientAddDTO;
import com.app.docnote.model.DTO.UserRegisterDTO;
import com.app.docnote.repository.*;
import com.app.docnote.service.UserService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class TestDataService {
    private UserService userService;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final SicknessLeaveDocumentRepository sicknessLeaveDocumentRepository;
    private final TestRepository testRepository;

    public TestDataService(UserService userService, UserRepository userRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository, SicknessLeaveDocumentRepository sicknessLeaveDocumentRepository, TestRepository testRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.sicknessLeaveDocumentRepository = sicknessLeaveDocumentRepository;

        this.testRepository = testRepository;
    }
    public void initUsers(){
        UserRegisterDTO userRegisterDto = new UserRegisterDTO(
                "Georgi", "Georgiev", "Ivanov",
                "goshoWrong", "0000000001", "gogo@abvg.bg",
                "Stara Zagora Bg", "123gosho", "123gosho");

        userService.register(userRegisterDto);
        userService.addAdmin(getIdByUsername("goshoWrong"));

    }
    public void tearDownDB(){
        userRepository.deleteAll();
        patientRepository.deleteAll();
        appointmentRepository.deleteAll();
        sicknessLeaveDocumentRepository.deleteAll();
        testRepository.deleteAll();
    }
    public Long getIdByUsername(String username){
        return userService.findFirstByUsername(username).getId();
    }
}
