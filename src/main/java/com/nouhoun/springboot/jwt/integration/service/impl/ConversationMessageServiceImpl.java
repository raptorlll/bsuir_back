package com.nouhoun.springboot.jwt.integration.service.impl;

import com.nouhoun.springboot.jwt.integration.domain.ConversationMessage;
import com.nouhoun.springboot.jwt.integration.domain.CustomerPayment;
import com.nouhoun.springboot.jwt.integration.repository.ConversationMessageRepository;
import com.nouhoun.springboot.jwt.integration.repository.CustomerPaymentRepository;
import com.nouhoun.springboot.jwt.integration.service.ConversationMessageService;
import com.nouhoun.springboot.jwt.integration.service.CustomerPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ConversationMessageServiceImpl extends CrudSeviceAbstract<ConversationMessage, Long>
        implements ConversationMessageService {
    @Autowired
    protected ConversationMessageRepository repository;

    @Override
    protected CrudRepository<ConversationMessage, Long> getRepository() {
        return repository;
    }
}
