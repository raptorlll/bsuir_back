package com.leonov.springboot.jwt.integration.service.impl;

import com.leonov.springboot.jwt.integration.service.EmailService;
import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

@Component
public class EmailServiceImpl implements EmailService {
    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    Configuration freemarkerConfiguration;

    @Value("${isProduction}")
    private boolean isProductionEnv;

    @Value("${testEmail}")
    private String testEmail;

    @Async
    public void sendSimpleMessage(String to, String subject) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(isProductionEnv ? to : testEmail);
            message.setSubject(subject);
            message.setText(templateContent);

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void sendSimpleMessageUsingTemplate(String to, String subject, SimpleMailMessage template, String ...templateArgs) {
        String text = String.format(template.getText(), templateArgs);
        sendSimpleMessage(to, subject);
    }

    @Override
    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            // pass 'true' to the constructor to create a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String templateContent = "";

    public EmailServiceImpl setContent(String templateName, Map<String, Object> model){
        StringBuffer content = new StringBuffer();

        try{
            content.append(FreeMarkerTemplateUtils.processTemplateIntoString(
                    freemarkerConfiguration.getTemplate(templateName + ".txt"), model));
            templateContent =  content.toString();
        }catch(Exception e){
            System.out.println("Exception occured while processing fmtemplate:"+e.getMessage());
        }

        return this;
    }
}
