package com.leonov.springboot.jwt.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonov.springboot.jwt.integration.domain.Conversation;
import com.leonov.springboot.jwt.integration.domain.ConversationMessage;
import com.leonov.springboot.jwt.integration.domain.User;
import com.leonov.springboot.jwt.integration.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/conversation_message")
public class ConversationMessageController extends CrudAbstractAuthUser<ConversationMessage, Long> {
    @Autowired
    private ConversationMessageService service;

    @Autowired
    private ConversationService serviceConversation;

    @Autowired
    private ObjectMapper jacksonObjectMapper;
    
    @Override
    public CrudServiceInterface<ConversationMessage, Long> getService() {
        return service;
    }

    @Autowired
    private FcmNotificationService pushService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ConversationMessage saveClientInformationWithFile(
            @RequestParam("data") String informationString,
            @RequestParam(value = "file",  required = false) MultipartFile file
    ) {
        ConversationMessage conversationMessage = null;

        try {
            conversationMessage = jacksonObjectMapper.readValue(informationString, ConversationMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (file==null || file.isEmpty()) {
            service.save(conversationMessage);
            return conversationMessage;
        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(ConsultantInformationController.UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            if (conversationMessage != null) {
                System.out.println("Logg");
                conversationMessage.setAttachedFile(file.getOriginalFilename());
                service.save(conversationMessage);
            }

            System.out.println("Log");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return conversationMessage;
    }

    @Override
    public ConversationMessage saveItem(@RequestBody ConversationMessage information) {
        information.setIsConsultantMessage(isCustomer() ? new Byte("0") : new Byte("1"));
//        Timestamp t = new Timestamp(System.currentTimeMillis());
//        information.setDateTime(new Date(t.getTime()));
        information.setDateTime(new Timestamp(System.currentTimeMillis()));

        ConversationMessage conversationMessage = super.saveItem(information);

        User user = isCustomer() ?
                conversationMessage.getConversation().getConsultantGroupUser().getUser() :
                conversationMessage.getConversation().getConsultantGroupUser().getUser();
        /* Send few push notifications to consultant */
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", conversationMessage.getMessage());
        parameters.put("group", conversationMessage.getConversation().getConsultantGroupUser().getConsultantGroup().getName());

        emailService.setContent("new_message", parameters)
                .sendSimpleMessage(user.getEmail(), "Have just received new message");

        pushService.sendPushMessageToDevice(
                user,
                "Hello, " + user.getFirstName() + " " + user.getLastName(),
                "Have just received new message"
        );

        return conversationMessage;
    }

    @GetMapping(value = "/conversation/{id}")
    public Collection<ConversationMessage> getItems(@PathVariable(value = "id") Long id) {
        Conversation conversation = serviceConversation.findOne(id);
        return  service.findAllByConversationIs(conversation);
    }
}
