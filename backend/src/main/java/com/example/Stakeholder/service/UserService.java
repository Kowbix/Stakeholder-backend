package com.example.Stakeholder.service;

import com.example.Stakeholder.entity.RegisterToken;
import com.example.Stakeholder.entity.User;
import com.example.Stakeholder.enums.AccountRole;
import com.example.Stakeholder.repository.BetRepository;
import com.example.Stakeholder.repository.RegisterTokenRepository;
import com.example.Stakeholder.repository.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@NoArgsConstructor
public class UserService implements UserDetailsService {

    private final static  String USER_NOT_FOUND_MSG = "User with %s not found";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterTokenRepository registerTokenRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private BetRepository betRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public boolean userExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public User singUpUser(User user) throws IOException {
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setAccountRole(AccountRole.USER);
        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        RegisterToken registerToken = new RegisterToken(token, user);

        registerTokenRepository.save(registerToken);

        String htmlContent = emailSenderService.readHtmlTemplate("templateRegisterMail.html");

        htmlContent = htmlContent.replace("{name}", user.getFirstName());
        htmlContent = htmlContent.replace("{token}", token);


        emailSenderService.sendMail(user.getEmail(), "Confirm registration", htmlContent);

        return user;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }



}
