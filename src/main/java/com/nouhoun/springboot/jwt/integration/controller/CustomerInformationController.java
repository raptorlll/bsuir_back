package com.nouhoun.springboot.jwt.integration.controller;

import com.nouhoun.springboot.jwt.integration.domain.CustomerInformation;
import com.nouhoun.springboot.jwt.integration.service.CrudServiceInterface;
import com.nouhoun.springboot.jwt.integration.service.CustomerInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer_information")
public class    CustomerInformationController extends CrudAbstract<CustomerInformation, Long> {
    @Autowired
    private CustomerInformationService customerInformationService;

    @Override
    public CrudServiceInterface<CustomerInformation, Long> getService() {
        return customerInformationService;
    }
}
