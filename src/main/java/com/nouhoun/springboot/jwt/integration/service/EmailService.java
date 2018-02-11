package com.nouhoun.springboot.jwt.integration.service;

import com.nouhoun.springboot.jwt.integration.domain.Conversation;

import com.nouhoun.springboot.jwt.integration.service.impl.EmailServiceImpl;
import org.springframework.mail.SimpleMailMessage;

import java.util.Map;

public interface EmailService {
    void sendSimpleMessage(String to, String subject);

    void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template, String... templateArgs);

    void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);

    public EmailService setContent(String templateName, Map<String, Object> model);
}
