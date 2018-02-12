package com.leonov.springboot.jwt.integration.controller;

import com.leonov.springboot.jwt.integration.domain.ConsultantGroup;
import com.leonov.springboot.jwt.integration.service.ConsultantGroupService;
import com.leonov.springboot.jwt.integration.service.CrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultant_group")
public class ConsultantGroupController extends CrudAbstract<ConsultantGroup, Long> {
    @Autowired
    private ConsultantGroupService service;

    @Override
    public CrudServiceInterface<ConsultantGroup, Long> getService() {
        return service;
    }
}
