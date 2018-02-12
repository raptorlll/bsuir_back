package com.leonov.springboot.jwt.integration.service.impl;

import com.leonov.springboot.jwt.integration.domain.ConsultantInformation;
import com.leonov.springboot.jwt.integration.repository.ConsultantInformationRepository;
import com.leonov.springboot.jwt.integration.service.ConsultantInformationService;
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
