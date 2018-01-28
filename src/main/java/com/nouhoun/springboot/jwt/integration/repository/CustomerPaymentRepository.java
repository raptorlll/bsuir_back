package com.nouhoun.springboot.jwt.integration.repository;

import com.nouhoun.springboot.jwt.integration.domain.Conversation;
import com.nouhoun.springboot.jwt.integration.domain.CustomerPayment;
import org.springframework.data.repository.CrudRepository;

public interface CustomerPaymentRepository extends CrudRepository<CustomerPayment, Long> {

}
