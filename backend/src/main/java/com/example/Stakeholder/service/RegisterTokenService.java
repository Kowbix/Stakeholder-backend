package com.example.Stakeholder.service;

import com.example.Stakeholder.entity.RegisterToken;
import com.example.Stakeholder.repository.RegisterTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegisterTokenService {

    @Autowired
    private RegisterTokenRepository registerTokenRepository;

    public void deleteToken(RegisterToken token) {
        registerTokenRepository.delete(token);
    }

    public Optional<RegisterToken> findTokenByToken(String token){
        return registerTokenRepository.findRegisterTokenByToken(token);
    }


}
