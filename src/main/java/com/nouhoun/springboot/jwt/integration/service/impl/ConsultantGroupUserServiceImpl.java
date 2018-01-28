package com.nouhoun.springboot.jwt.integration.service.impl;

import com.nouhoun.springboot.jwt.integration.domain.ConsultantGroup;
import com.nouhoun.springboot.jwt.integration.domain.ConsultantGroupUser;
import com.nouhoun.springboot.jwt.integration.repository.ConsultantGroupRepository;
import com.nouhoun.springboot.jwt.integration.repository.ConsultantGroupUserRepository;
import com.nouhoun.springboot.jwt.integration.service.ConsultantGroupService;
import com.nouhoun.springboot.jwt.integration.service.ConsultantGroupUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ConsultantGroupUserServiceImpl extends CrudSeviceAbstract<ConsultantGroupUser, Long>
        implements ConsultantGroupUserService {
    @Autowired
    protected ConsultantGroupUserRepository repository;

    @Override
    protected CrudRepository<ConsultantGroupUser, Long> getRepository() {
        return repository;
    }
}
