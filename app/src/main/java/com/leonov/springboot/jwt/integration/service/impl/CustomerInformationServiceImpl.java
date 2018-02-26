package com.leonov.springboot.jwt.integration.service.impl;

import com.leonov.springboot.jwt.integration.domain.CustomerInformation;
import com.leonov.springboot.jwt.integration.repository.CustomerInformationRepository;
import com.leonov.springboot.jwt.integration.service.CustomerInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerInformationServiceImpl extends CrudSeviceAbstract<CustomerInformation, Long> implements CustomerInformationService {
    @Autowired
    protected CustomerInformationRepository repository;

    @Override
    protected CrudRepository<CustomerInformation, Long> getRepository() {
        return repository;
    }
}
