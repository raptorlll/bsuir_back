package com.leonov.springboot.jwt.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonov.springboot.jwt.integration.domain.ConversationMessage;
import com.leonov.springboot.jwt.integration.service.ConversationMessageService;
import com.leonov.springboot.jwt.integration.service.CrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/conversation_message")
public class ConversationMessageController extends CrudAbstract<ConversationMessage, Long> {
    @Autowired
    private ConversationMessageService service;

    @Autowired
    private ObjectMapper jacksonObjectMapper;
    
    @Override
    public CrudServiceInterface<ConversationMessage, Long> getService() {
        return service;
    }


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
}
