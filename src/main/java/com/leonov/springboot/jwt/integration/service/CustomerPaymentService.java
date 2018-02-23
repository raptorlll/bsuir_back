package com.leonov.springboot.jwt.integration.service;

import com.leonov.springboot.jwt.integration.domain.Conversation;
import com.leonov.springboot.jwt.integration.domain.CustomerPayment;

import java.util.List;

public interface CustomerPaymentService extends CrudServiceInterface<CustomerPayment, Long> {
    public List<CustomerPayment> findAllByConversationIs(Conversation conversation);
}
