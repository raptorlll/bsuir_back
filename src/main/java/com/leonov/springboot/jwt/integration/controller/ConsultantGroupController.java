package com.leonov.springboot.jwt.integration.controller;

import com.leonov.springboot.jwt.integration.domain.ConsultantGroup;
import com.leonov.springboot.jwt.integration.domain.ConsultantGroupUser;
import com.leonov.springboot.jwt.integration.domain.User;
import com.leonov.springboot.jwt.integration.service.ConsultantGroupService;
import com.leonov.springboot.jwt.integration.service.CrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consultant_group")
public class ConsultantGroupController extends CrudAbstractAuthUser<ConsultantGroup, Long> {
    @Autowired
    private ConsultantGroupService service;

    @Override
    public CrudServiceInterface<ConsultantGroup, Long> getService() {
        return service;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Collection<ConsultantGroup> getItems() {
        User userCurrent = getCurrentUser();
        boolean isConsultant = isConsultant();

        return super.getItems()
                .stream()
                .filter(consultantGroup -> {
                    if (!isConsultant) {
                        return true;
                    }

                    for(ConsultantGroupUser cgu : userCurrent.getConsultantGroupUsersById()){
                        if(cgu.getConsultantGroup().getId().equals(consultantGroup.getId())){
                            return true;
                        }
                    }

                    return false;
                })
                .collect(Collectors.toSet());
    }
}
