package com.nouhoun.springboot.jwt.integration.controller;

import com.nouhoun.springboot.jwt.integration.domain.Conversation;
import com.nouhoun.springboot.jwt.integration.domain.ConversationStatus;
import com.nouhoun.springboot.jwt.integration.service.ConversationService;
import com.nouhoun.springboot.jwt.integration.service.ConversationStatusService;
import com.nouhoun.springboot.jwt.integration.service.CrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conversation_status")
public class ConversationStatusController extends CrudAbstract<ConversationStatus, Long> {
    @Autowired
    private ConversationStatusService service;

    @Override
    public CrudServiceInterface<ConversationStatus, Long> getService() {
        return service;
    }
}
