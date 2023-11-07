package com.example.Stakeholder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bartosz.dart@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public String readHtmlTemplate(String templateFileName) throws IOException {
        Resource resource = new ClassPathResource(templateFileName);
        byte[] bytes = Files.readAllBytes(Paths.get(resource.getURI()));
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
