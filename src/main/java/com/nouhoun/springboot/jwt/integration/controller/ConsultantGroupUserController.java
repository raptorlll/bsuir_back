package com.nouhoun.springboot.jwt.integration.controller;

import com.nouhoun.springboot.jwt.integration.domain.ConsultantGroup;
import com.nouhoun.springboot.jwt.integration.domain.ConsultantGroupUser;
import com.nouhoun.springboot.jwt.integration.service.ConsultantGroupService;
import com.nouhoun.springboot.jwt.integration.service.ConsultantGroupUserService;
import com.nouhoun.springboot.jwt.integration.service.CrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultant_group_user")
public class ConsultantGroupUserController extends CrudAbstract<ConsultantGroupUser, Long> {
    @Autowired
    private ConsultantGroupUserService service;

    @Override
    public CrudServiceInterface<ConsultantGroupUser, Long> getService() {
        return service;
    }
}
