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

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataService testDataService;

    Long userId;
    @BeforeEach
    void setUp(){
        testDataService.tearDownDB();
        testDataService.initAdmins();
        testDataService.initUsers();
        userId = testDataService.getIdByUsername("goshoWrong");
    }
    @AfterEach
    void tearDown(){
        testDataService.tearDownDB();
    }

    @Test
    @WithMockUser(username = "goshoWrong", authorities = {"ROLE_ADMIN","ROLE_USER"})
    void testMangeWebsitePage() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/manage-website"))
                .andExpect(status().isOk())
                .andExpect(view().name("manage-website"));
    }
    @Test
    @WithMockUser(username = "goshoWrong", authorities = {"ROLE_USER", "ROLE_ADMIN"})
    void testWaitingRegistrationsPage() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/manage-website/waiting-registrations"))
                .andExpect(status().isOk())
                .andExpect(view().name("waiting-registrations"))
                .andExpect(model().attributeExists("user", "notApprovedUsers"));
    }
    @Test
    @WithMockUser(username = "goshoWrong", authorities = {"ROLE_USER", "ROLE_ADMIN"})
    void testAdminsPage() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/manage-website/admins"))
                .andExpect(status().isOk())
                .andExpect(view().name("admins"))
                .andExpect(model().attributeExists("admins"));
    }
    @Test
    @WithMockUser(username = "goshoWrong", authorities = {"ROLE_USER", "ROLE_ADMIN"})
    void testSeeUsersPage() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/manage-website/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attributeExists("notAdmins"));

        mockMvc
                .perform(MockMvcRequestBuilders.get("/manage-website/users-successfully-added-admin"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attributeExists("notAdmins", "addedAdmin"));

        mockMvc
                .perform(MockMvcRequestBuilders.get("/manage-website/users-successfully-removed-user"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attributeExists("removedUser"));
    }
    @Test
    @WithMockUser(username = "goshoWrong", authorities = {"ROLE_ADMIN", "ROLE_USER"})
    void testRemoveAdmin() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders.post("/manage-website/remove-admin/" + userId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/manage-website/admins"));
    }
    @Test
    @WithMockUser(username = "goshoWrong", authorities = {"ROLE_ADMIN", "ROLE_USER"})
    void testAddAdmin() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders.post("/manage-website/add-admin/" + userId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/manage-website/users-successfully-added-admin"));
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
