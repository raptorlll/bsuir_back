package com.nouhoun.springboot.jwt.integration.service.impl;

import com.nouhoun.springboot.jwt.integration.domain.ConsultantGroup;
import com.nouhoun.springboot.jwt.integration.domain.CustomerInformation;
import com.nouhoun.springboot.jwt.integration.repository.ConsultantGroupRepository;
import com.nouhoun.springboot.jwt.integration.repository.CustomerInformationRepository;
import com.nouhoun.springboot.jwt.integration.service.ConsultantGroupService;
import com.nouhoun.springboot.jwt.integration.service.CustomerInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultantGroupServiceImpl extends CrudSeviceAbstract<ConsultantGroup, Long>
        implements ConsultantGroupService {
    @Autowired
    protected ConsultantGroupRepository repository;

    @Override
    protected CrudRepository<ConsultantGroup, Long> getRepository() {
        return repository;
    }
}
