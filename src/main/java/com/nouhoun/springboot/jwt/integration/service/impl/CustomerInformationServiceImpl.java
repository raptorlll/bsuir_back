package com.nouhoun.springboot.jwt.integration.service.impl;

import com.nouhoun.springboot.jwt.integration.domain.CustomerInformation;
import com.nouhoun.springboot.jwt.integration.domain.User;
import com.nouhoun.springboot.jwt.integration.repository.CustomerInformationRepository;
import com.nouhoun.springboot.jwt.integration.repository.UserRepository;
import com.nouhoun.springboot.jwt.integration.service.CustomerInformationService;
import com.nouhoun.springboot.jwt.integration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerInformationServiceImpl extends CrudSeviceAbstract<CustomerInformation, Long> implements CustomerInformationService {
    @Autowired
    protected CustomerInformationRepository repository;

    @Override
    protected CrudRepository<CustomerInformation, Long> getRepository() {
        return repository;
    }
}
