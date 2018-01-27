package com.nouhoun.springboot.jwt.integration.service.impl;

import com.nouhoun.springboot.jwt.integration.domain.CustomerInformation;
import com.nouhoun.springboot.jwt.integration.domain.User;
import com.nouhoun.springboot.jwt.integration.repository.CustomerInformationRepository;
import com.nouhoun.springboot.jwt.integration.repository.UserRepository;
import com.nouhoun.springboot.jwt.integration.service.CustomerInformationService;
import com.nouhoun.springboot.jwt.integration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by nydiarra on 07/05/17.
 */
@Service
public class CustomerInformationServiceImpl implements CustomerInformationService {
    @Autowired
    private CustomerInformationRepository customerInformationRepository;

    @Override
    public List<CustomerInformation> findAll() {
        return (List<CustomerInformation>)customerInformationRepository.findAll();
    }

    @Override
    public CustomerInformation save(CustomerInformation u) {
        return customerInformationRepository.save(u);
    }
}
