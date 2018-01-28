package com.nouhoun.springboot.jwt.integration.service.impl;

import com.nouhoun.springboot.jwt.integration.domain.ConsultantGroupUser;
import com.nouhoun.springboot.jwt.integration.domain.ConsultantInformation;
import com.nouhoun.springboot.jwt.integration.repository.ConsultantGroupUserRepository;
import com.nouhoun.springboot.jwt.integration.repository.ConsultantInformationRepository;
import com.nouhoun.springboot.jwt.integration.service.ConsultantGroupUserService;
import com.nouhoun.springboot.jwt.integration.service.ConsultantInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultantInformationServiceImpl extends CrudSeviceAbstract<ConsultantInformation, Long>
        implements ConsultantInformationService {
    @Autowired
    protected ConsultantInformationRepository repository;

    @Override
    protected CrudRepository<ConsultantInformation, Long> getRepository() {
        return repository;
    }
}
