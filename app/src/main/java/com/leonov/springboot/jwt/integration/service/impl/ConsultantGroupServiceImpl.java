package com.leonov.springboot.jwt.integration.service.impl;

import com.leonov.springboot.jwt.integration.domain.ConsultantGroup;
import com.leonov.springboot.jwt.integration.repository.ConsultantGroupRepository;
import com.leonov.springboot.jwt.integration.service.ConsultantGroupService;
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
