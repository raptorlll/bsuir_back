package com.leonov.springboot.jwt.integration.service.impl;

import com.leonov.springboot.jwt.integration.domain.ConsultantGroupUser;
import com.leonov.springboot.jwt.integration.repository.ConsultantGroupUserRepository;
import com.leonov.springboot.jwt.integration.service.ConsultantGroupUserService;
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
