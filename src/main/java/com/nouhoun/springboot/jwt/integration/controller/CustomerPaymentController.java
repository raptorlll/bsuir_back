package com.nouhoun.springboot.jwt.integration.controller;

import com.nouhoun.springboot.jwt.integration.domain.Conversation;
import com.nouhoun.springboot.jwt.integration.domain.CustomerPayment;
import com.nouhoun.springboot.jwt.integration.service.ConversationService;
import com.nouhoun.springboot.jwt.integration.service.CrudServiceInterface;
import com.nouhoun.springboot.jwt.integration.service.CustomerPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer_payment")
public class CustomerPaymentController extends CrudAbstract<CustomerPayment, Long> {
    @Autowired
    private CustomerPaymentService service;

    @Override
    public CrudServiceInterface<CustomerPayment, Long> getService() {
        return service;
    }
}
