package com.leonov.springboot.jwt.integration.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;

public interface EmailService {
    @Async
    void sendSimpleMessage(String to, String subject);

    void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template, String... templateArgs);

    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);

    public EmailService setContent(String templateName, Map<String, Object> model);
}
