package com.leonov.springboot.jwt.integration.service.impl;

import com.leonov.springboot.jwt.integration.domain.Conversation;
import com.leonov.springboot.jwt.integration.domain.CustomerPayment;
import com.leonov.springboot.jwt.integration.repository.CustomerPaymentRepository;
import com.leonov.springboot.jwt.integration.service.CustomerPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerPaymentServiceImpl extends CrudSeviceAbstract<CustomerPayment, Long>
        implements CustomerPaymentService {
    @Autowired
    protected CustomerPaymentRepository repository;

    @Override
    protected CrudRepository<CustomerPayment, Long> getRepository() {
        return repository;
    }

    @Override
    public List<CustomerPayment> findAllByConversationIs(Conversation conversation) {
        return repository.findAllByConversationIs(conversation);
    }
}
