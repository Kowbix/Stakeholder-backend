package com.example.Stakeholder.controller;

import com.example.Stakeholder.entity.Point;
import com.example.Stakeholder.entity.RegisterToken;
import com.example.Stakeholder.entity.User;
import com.example.Stakeholder.repository.UserRepository;
import com.example.Stakeholder.service.PointService;
import com.example.Stakeholder.service.RegisterTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("http://localhost:3000/")
public class AuthController {

    @Autowired
    private RegisterTokenService registerTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PointService pointService;

    @GetMapping("/register-token/{registryToken}")
    public ResponseEntity<String> activateAccountByToken(@PathVariable String registryToken) {

        Optional<RegisterToken> token = registerTokenService.findTokenByToken(registryToken);

        if(token.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The token has not be found");
        }

        if(token.get().getExpireDate().before(new Date())){
            registerTokenService.deleteToken(token.get());

            return ResponseEntity.status(HttpStatus.OK).body("The token is expired");
        }

        User activeUser = token.get().getUser();
        activeUser.setEnabled(true);
        activeUser.setLocked(false);
        activeUser = userRepository.save(activeUser);

        pointService.savePoint(new Point(0, activeUser));

        registerTokenService.deleteToken(token.get());

        return ResponseEntity.status(HttpStatus.OK).body("The account has benn activated");
    }


}
