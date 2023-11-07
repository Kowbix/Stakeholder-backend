package com.example.Stakeholder.service;

import com.example.Stakeholder.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;


    @Test
    public void whenValidUserEmail_thanUserShouldBeFound(){
        String userEmail = "kacperkowbel63@gmail.com";
        User user = (User) userService.loadUserByUsername(userEmail);

        assertEquals(userEmail, user.getEmail());
    }

}