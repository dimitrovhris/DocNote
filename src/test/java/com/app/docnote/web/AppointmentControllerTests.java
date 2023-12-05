package com.app.docnote.web;

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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AppointmentControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataService testDataService;

    Long userId;
    @BeforeEach
    void setUp(){
        testDataService.initUsers();
        userId = testDataService.getIdByUsername("goshoWrong");
    }
    @AfterEach
    void tearDown(){
        testDataService.tearDownDB();
    }

    @Test
    @WithMockUser(username = "goshoWrong", authorities = {"ROLE_ADMIN", "ROLE_USER"})
    void testRemoveUser() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders.post("/manage-website/remove-user/" + userId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/manage-website/users-successfully-removed-user"));
    }


}
