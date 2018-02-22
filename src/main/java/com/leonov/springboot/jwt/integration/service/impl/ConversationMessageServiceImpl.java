package com.leonov.springboot.jwt.integration.service.impl;

import com.leonov.springboot.jwt.integration.domain.Conversation;
import com.leonov.springboot.jwt.integration.domain.ConversationMessage;
import com.leonov.springboot.jwt.integration.repository.ConversationMessageRepository;
import com.leonov.springboot.jwt.integration.service.ConversationMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationMessageServiceImpl extends CrudSeviceAbstract<ConversationMessage, Long>
        implements ConversationMessageService {
    @Autowired
    protected ConversationMessageRepository repository;

    @Override
    protected CrudRepository<ConversationMessage, Long> getRepository() {
        return repository;
    }

    @Override
    public List<ConversationMessage> findAllByConversationIs(Conversation conversation) {
        return repository.findAllByConversationIs(conversation);
    }
}
