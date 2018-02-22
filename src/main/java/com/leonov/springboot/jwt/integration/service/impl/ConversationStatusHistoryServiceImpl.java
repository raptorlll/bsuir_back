package com.leonov.springboot.jwt.integration.service.impl;

import com.leonov.springboot.jwt.integration.domain.ConversationStatus;
import com.leonov.springboot.jwt.integration.domain.ConversationStatusHistory;
import com.leonov.springboot.jwt.integration.repository.ConversationStatusHistoryRepository;
import com.leonov.springboot.jwt.integration.repository.ConversationStatusRepository;
import com.leonov.springboot.jwt.integration.service.ConversationStatusHistoryService;
import com.leonov.springboot.jwt.integration.service.ConversationStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ConversationStatusHistoryServiceImpl extends CrudSeviceAbstract<ConversationStatusHistory, Long>
        implements ConversationStatusHistoryService{
    @Autowired
    protected ConversationStatusHistoryRepository repository;

    @Override
    protected CrudRepository<ConversationStatusHistory, Long> getRepository() {
        return repository;
    }
}
