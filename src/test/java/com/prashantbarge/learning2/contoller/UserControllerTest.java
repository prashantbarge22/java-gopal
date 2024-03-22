package com.prashantbarge.learning2.contoller;

import com.prashantbarge.learning2.models.User;
import com.prashantbarge.learning2.service.UserService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

@SpringBootTest
class UserControllerTest {


    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    void createUser() {
        // Arrange
        User userToSave = new User();
        userToSave.setFirstName("Prashant");
        userToSave.setLastName("Barge");
        userToSave.setMail("prashantbarge22@gmail.com");
        userToSave.setId(1l);

        // Mocking the behavior of userService.save
        Mockito.when(userService.save(userToSave)).thenReturn(userToSave);

        // Act
        User createdUser = userController.createUser(userToSave);
        
        // Assert or perform further verifications based on your requirements
        assert Objects.equals(createdUser.getId(), userToSave.getId());
    }
}