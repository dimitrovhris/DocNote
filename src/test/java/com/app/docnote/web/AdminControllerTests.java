package com.app.docnote.web;

import com.app.docnote.model.entity.UserEntity;
import com.app.docnote.model.entity.UserRole;
import com.app.docnote.model.enums.UserRoleEnum;
import com.app.docnote.repository.UserRepository;
import com.app.docnote.util.TestDataService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
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
    private UserRepository userRepository;

    @Autowired
    private TestDataService testDataService;

    Long userId;
    @BeforeAll
    void setUp(){
        testDataService.initUsers();
        userId = testDataService.getIdByUsername("goshoWrong");
    }
    @AfterAll
    void tearDown(){
        testDataService.tearDownDB();
    }

    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN","ROLE_USER"})
    void testMangeWebsitePage() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/manage-website"))
                .andExpect(status().isOk())
                .andExpect(view().name("manage-website"));
    }
    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_USER", "ROLE_ADMIN"})
    void testWaitingRegistrationsPage() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/manage-website/waiting-registrations"))
                .andExpect(status().isOk())
                .andExpect(view().name("waiting-registrations"))
                .andExpect(model().attributeExists("user", "notApprovedUsers"));
    }
    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_USER", "ROLE_ADMIN"})
    void testAdminsPage() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/manage-website/admins"))
                .andExpect(status().isOk())
                .andExpect(view().name("admins"))
                .andExpect(model().attributeExists("admins"));
    }
    @Test
    @WithMockUser(username = "admin", authorities = {"ROLE_USER", "ROLE_ADMIN"})
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
    @WithMockUser(username = "admin", authorities = {"ROLE_ADMIN", "ROLE_USER"})
    void testRemoveAdmin() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders.post("/manage-website/remove-admin/" + userId))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/manage-website/admins"));
    }






    private static UserEntity createTestUser(){
        UserEntity user = new UserEntity(
                "firstName", "surname", "lastName", "wrongUsername",
                "1234567890", "wrong@email.com", "Nowhere nowhere", "123456", false);
        user.getRoles().add(new UserRole(UserRoleEnum.USER));
        user.getRoles().add(new UserRole(UserRoleEnum.ADMIN));
        return user;
    }

}
