package com.nouhoun.springboot.jwt.integration.service.impl;

import com.nouhoun.springboot.jwt.integration.domain.Conversation;
import com.nouhoun.springboot.jwt.integration.domain.ConversationStatus;
import com.nouhoun.springboot.jwt.integration.repository.ConversationRepository;
import com.nouhoun.springboot.jwt.integration.repository.ConversationStatusRepository;
import com.nouhoun.springboot.jwt.integration.service.ConversationService;
import com.nouhoun.springboot.jwt.integration.service.ConversationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ConversationStatusServiceImpl extends CrudSeviceAbstract<ConversationStatus, Long>
        implements ConversationStatusService {
    @Autowired
    protected ConversationStatusRepository repository;

    @Override
    protected CrudRepository<ConversationStatus, Long> getRepository() {
        return repository;
    }
}
