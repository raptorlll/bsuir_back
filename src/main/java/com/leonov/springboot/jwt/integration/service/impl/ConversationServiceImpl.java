package com.leonov.springboot.jwt.integration.service.impl;

import com.leonov.springboot.jwt.integration.domain.Conversation;
import com.leonov.springboot.jwt.integration.repository.ConversationRepository;
import com.leonov.springboot.jwt.integration.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ConversationServiceImpl extends CrudSeviceAbstract<Conversation, Long>
        implements ConversationService {
    @Autowired
    protected ConversationRepository repository;

    @Override
    protected CrudRepository<Conversation, Long> getRepository() {
        return repository;
    }
}
