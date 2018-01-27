package com.nouhoun.springboot.jwt.integration.controller;

import com.nouhoun.springboot.jwt.integration.domain.ConsultantGroup;
import com.nouhoun.springboot.jwt.integration.domain.CustomerInformation;
import com.nouhoun.springboot.jwt.integration.service.ConsultantGroupService;
import com.nouhoun.springboot.jwt.integration.service.CrudServiceInterface;
import com.nouhoun.springboot.jwt.integration.service.CustomerInformationService;
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
