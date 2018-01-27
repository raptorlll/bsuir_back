package com.nouhoun.springboot.jwt.integration.service;

import com.nouhoun.springboot.jwt.integration.domain.CustomerInformation;
import com.nouhoun.springboot.jwt.integration.domain.User;

import java.util.List;

public interface CustomerInformationService {
    List<CustomerInformation> findAll();

    CustomerInformation save(CustomerInformation u);
}
