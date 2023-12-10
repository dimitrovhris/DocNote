package com.app.docnote.web;

import com.app.docnote.model.DTO.PatientAddDTO;
import com.app.docnote.util.TestDataService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PatientControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataService testDataService;

    Long userId;
    @BeforeEach
    void setUp(){
        testDataService.tearDownDB();
        testDataService.initUsers();
        userId = testDataService.getIdByUsername("goshoWrong");
    }
    @AfterEach
    void tearDown(){
        testDataService.tearDownDB();
    }

    @Test
    @WithMockUser(username = "goshoWrong", authorities = {"ROLE_USER"})
    void testAddPatientPage() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/patient/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("add-patient"))
                .andExpect(model().attributeExists("patientAddDTO", "patientService"));
    }
    @Test
    @WithMockUser(username = "goshoWrong", authorities = {"ROLE_USER"})
    void testAddPatientPostPage() throws Exception{
        PatientAddDTO patientAddDTO = testDataService.createPatientAddDTO();
        mockMvc
                .perform(MockMvcRequestBuilders.post("/patient/add")
                        .param("firstName", patientAddDTO.getFirstName())
                        .param("surname", patientAddDTO.getSurname())
                        .param("lastName", patientAddDTO.getLastName())
                        .param("egn", patientAddDTO.getEgn())
                        .param("birthday", String.valueOf(patientAddDTO.getBirthday()))
                        .param("height", String.valueOf(patientAddDTO.getHeight()))
                        .param("weight", String.valueOf(patientAddDTO.getWeight()))
                        .param("address", String.valueOf(patientAddDTO.getAddress())))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/home"));



    }
}
