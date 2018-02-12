package com.leonov.springboot.jwt.integration.controller;

import com.leonov.springboot.jwt.integration.domain.ConversationStatus;
import com.leonov.springboot.jwt.integration.service.ConversationStatusService;
import com.leonov.springboot.jwt.integration.service.CrudServiceInterface;
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
