package com.leonov.springboot.jwt.integration.service.impl;

import com.leonov.springboot.jwt.integration.domain.ConversationStatus;
import com.leonov.springboot.jwt.integration.repository.ConversationStatusRepository;
import com.leonov.springboot.jwt.integration.service.ConversationStatusService;
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
