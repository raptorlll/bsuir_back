package com.nouhoun.springboot.jwt.integration.controller;

import com.nouhoun.springboot.jwt.integration.domain.Conversation;
import com.nouhoun.springboot.jwt.integration.domain.ConversationMessage;
import com.nouhoun.springboot.jwt.integration.service.ConversationMessageService;
import com.nouhoun.springboot.jwt.integration.service.ConversationService;
import com.nouhoun.springboot.jwt.integration.service.CrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conversation_message")
public class ConversationMessageController extends CrudAbstract<ConversationMessage, Long> {
    @Autowired
    private ConversationMessageService service;

    @Override
    public CrudServiceInterface<ConversationMessage, Long> getService() {
        return service;
    }
}
