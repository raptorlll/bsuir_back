package com.leonov.springboot.jwt.integration.repository;

import com.leonov.springboot.jwt.integration.domain.CustomerPayment;
import org.springframework.data.repository.CrudRepository;

public interface CustomerPaymentRepository extends CrudRepository<CustomerPayment, Long> {

}
