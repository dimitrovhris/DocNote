package com.app.docnote.service.impl;

import com.app.docnote.model.entity.UserEntity;
import com.app.docnote.model.entity.UserRole;
import com.app.docnote.model.enums.UserRoleEnum;
import com.app.docnote.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DocNoteUserDetailsServiceTests {
    private DocNoteUserDetailsService serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void setUp(){
        serviceToTest = new DocNoteUserDetailsService(mockUserRepository);
    }

    @Test
    void testUserNotFound(){

        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("wrongUsername"));
    }
    @Test
    void testUserFoundException(){
        //Arrange
        UserEntity testUser = createTestUser();
        when(mockUserRepository.findFirstByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        //Act
        UserDetails userDetails = serviceToTest.loadUserByUsername(testUser.getUsername());

        //Assert
        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals(testUser.getUsername(), userDetails.getUsername(), "Username is not populated properly");
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword(), "Password is not populated properly");
        Assertions.assertEquals(2, userDetails.getAuthorities().size(), "Authorities size is not as long as it need to be");
        Assertions.assertTrue(containsAuthority(userDetails, "ROLE_" + UserRoleEnum.ADMIN), "The user is not admin");
        Assertions.assertTrue(containsAuthority(userDetails, "ROLE_" + UserRoleEnum.USER), "The user is not user");
    }


    private boolean containsAuthority(UserDetails userDetails, String expectedAuthority){
        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(a -> expectedAuthority.equals(a.getAuthority()));
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
