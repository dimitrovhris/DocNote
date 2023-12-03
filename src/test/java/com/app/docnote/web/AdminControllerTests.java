package com.app.docnote.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTests {
    @Autowired
    private MockMvc mockMvc;

//    private UserEntity userEntity;
//
//    @BeforeEach
//    void setUp(){
//        userEntity = createTestUser();
//
//    }

//    private static UserEntity createTestUser(){
//        UserEntity user = new UserEntity(
//                "firstName", "surname", "lastName", "wrongUsername",
//                "1234567890", "wrong@email.com", "Nowhere nowhere", "123456", false);
//        user.getRoles().add(new UserRole(UserRoleEnum.USER));
//        user.getRoles().add(new UserRole(UserRoleEnum.ADMIN));
//        return user;
//    }

}
