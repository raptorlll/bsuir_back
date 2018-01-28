package com.nouhoun.springboot.jwt.integration.controller;

import com.nouhoun.springboot.jwt.integration.domain.Conversation;
import com.nouhoun.springboot.jwt.integration.service.ConversationService;
import com.nouhoun.springboot.jwt.integration.service.CrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conversation")
public class ConversationController extends CrudAbstract<Conversation, Long> {
    @Autowired
    private ConversationService service;

    @Override
    public CrudServiceInterface<Conversation, Long> getService() {
        return service;
    }
}
