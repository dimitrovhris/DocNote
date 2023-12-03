package com.app.docnote.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetLoginPage() throws Exception{
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/user/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
    @Test
    void testGetLoginPageError() throws Exception{
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/user/login-error"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andExpect(model().attributeExists("invalidLogin"));
    }
    @Test
    void testGetRegisterPage() throws Exception{
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/user/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeExists("doctorRegisterDTO"))
                .andExpect(model().attributeExists("doctorService"));
    }
}
