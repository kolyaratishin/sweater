package com.sweater.service;

import com.sweater.models.Role;
import com.sweater.models.User;
import com.sweater.repositories.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {
    @Autowired
    UserService userService;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private MailSender mailSender;
    @Test
    public void addUser() {
        User user = new User();
        user.setEmail("some@mail.com");
        boolean isUserCreated = userService.addUser(user);
        assertTrue(isUserCreated);
        assertNotNull(user.getActivationCode());
        assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(userRepository,Mockito.times(1)).save(user);
        Mockito.verify(mailSender,Mockito.times(1))
                .send(ArgumentMatchers.eq(user.getEmail()),
                        ArgumentMatchers.eq("Activation code"),
                        ArgumentMatchers.contains("Welcome to Sweater"));
    }
    @Test
    public void addUserFailTest(){
        User user = new User();
        user.setUsername("John");

        Mockito.doReturn(new User())
                .when(userRepository)
                .findByUsername("John");
        boolean isUserCreated = userService.addUser(user);
        assertFalse(isUserCreated);

        Mockito.verify(userRepository,Mockito.times(0)).save(ArgumentMatchers.any(User.class));
        Mockito.verify(mailSender,Mockito.times(0))
                .send(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.anyString());
    }
    @Test
    public void activateUser(){
        User user = new User();
        user.setActivationCode("bingo");

        Mockito.doReturn(user)
                .when(userRepository)
                .findByActivationCode("activate");

        boolean isUserActivated = userService.activateUser("activate");

        assertTrue(isUserActivated);
        assertNull(user.getActivationCode());

        Mockito.verify(userRepository,Mockito.times(1)).save(user);
    }

    @Test
    public void activateUserFailTest(){
        boolean isUserActivated = userService.activateUser("activate");

        assertFalse(isUserActivated);

        Mockito.verify(userRepository,Mockito.times(0)).save(ArgumentMatchers.any(User.class));
    }

}