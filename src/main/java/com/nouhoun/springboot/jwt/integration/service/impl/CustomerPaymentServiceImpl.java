package com.nouhoun.springboot.jwt.integration.service.impl;

import com.nouhoun.springboot.jwt.integration.domain.CustomerPayment;
import com.nouhoun.springboot.jwt.integration.repository.CustomerPaymentRepository;
import com.nouhoun.springboot.jwt.integration.service.CustomerPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerPaymentServiceImpl extends CrudSeviceAbstract<CustomerPayment, Long>
        implements CustomerPaymentService {
    @Autowired
    protected CustomerPaymentRepository repository;

    @Override
    protected CrudRepository<CustomerPayment, Long> getRepository() {
        return repository;
    }
}
