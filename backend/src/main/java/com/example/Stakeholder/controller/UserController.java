package com.example.Stakeholder.controller;

import com.example.Stakeholder.entity.FootballMatch;
import com.example.Stakeholder.entity.RegisterToken;
import com.example.Stakeholder.entity.User;
import com.example.Stakeholder.repository.RegisterTokenRepository;
import com.example.Stakeholder.response.UserResponse;
import com.example.Stakeholder.service.MatchService;
import com.example.Stakeholder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MatchService matchService;

    @Autowired
    private HttpSession httpSession;

    @PostMapping("/api/v1/registration")
    public ResponseEntity<?> registerUser(@RequestBody User user) throws IOException {

        if(userService.userExistByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body("User with mail " + user.getEmail() + " exist.");
        }

        user = userService.singUpUser(user);

        return ResponseEntity.ok(user);
    }



}
