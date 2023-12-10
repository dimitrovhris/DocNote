package com.app.docnote.web;

import com.app.docnote.service.UserService;
import com.app.docnote.util.TestDataService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;


    private final UserService userService;
    @Autowired
    private TestDataService testDataService;

    @Autowired
    public UserControllerTests(UserService userService) {
        this.userService = userService;
    }

    @BeforeEach
    void setUp(){
        testDataService.tearDownDB();
        testDataService.initUsers();
    }
    @AfterEach
    void tearDown(){
        testDataService.tearDownDB();
    }

    @Test
    void testLoginPage() throws Exception {
        mockMvc
                .perform(get("/user/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
        mockMvc
                .perform(get("/user/login-error"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("invalidLogin"));
    }

    @Test
    void testRegisterPage() throws Exception {
        mockMvc
                .perform(get("/user/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));

        mockMvc
                .perform(post("/user/register")
                        .param("firstName", "Hristiyan")
                        .param("surname", "Plamenov")
                        .param("lastName", "Dimitrov")
                        .param("username", "wrongUsername")
                        .param("egn", "1212121213")
                        .param("email", "testEmail@mail.bg")
                        .param("address", "Stara Zagora Bulgaria")
                        .param("password", "abc1234")
                        .param("confirmPassword", "abc1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/login"));
        userService.removeByUsername("wrongUsername");

        mockMvc.perform(post("/user/register"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user/register"));
    }


}

