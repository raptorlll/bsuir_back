package com.leonov.springboot.jwt.integration.repository;

import com.leonov.springboot.jwt.integration.domain.Conversation;
import com.leonov.springboot.jwt.integration.domain.CustomerPayment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerPaymentRepository extends CrudRepository<CustomerPayment, Long> {
    public List<CustomerPayment> findAllByConversationIs(Conversation conversation);
}
