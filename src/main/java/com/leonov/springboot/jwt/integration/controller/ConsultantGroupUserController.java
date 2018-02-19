package com.leonov.springboot.jwt.integration.controller;

import com.leonov.springboot.jwt.integration.domain.ConsultantGroupUser;
import com.leonov.springboot.jwt.integration.domain.User;
import com.leonov.springboot.jwt.integration.service.ConsultantGroupUserService;
import com.leonov.springboot.jwt.integration.service.CrudServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/consultant_group_user")
public class ConsultantGroupUserController extends CrudAbstractAuthUser<ConsultantGroupUser, Long> {
    @Autowired
    private ConsultantGroupUserService service;

    @Override
    public CrudServiceInterface<ConsultantGroupUser, Long> getService() {
        return service;
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public Collection<ConsultantGroupUser> getItems() {
        User userCurrent = getCurrentUser();
        boolean isConsultant = isConsultant();

        return super.getItems()
                .stream()
                .map(consultantGroupUser -> {
                    if(consultantGroupUser.getVideoTarif() == null){
                        consultantGroupUser.setVideoTarif(consultantGroupUser.getConsultantGroup().getVideoTarif());
                    }

                    if(consultantGroupUser.getConversationTarif() == null){
                        consultantGroupUser.setConversationTarif(consultantGroupUser.getConsultantGroup().getConversationTarif());
                    }

                    return consultantGroupUser;
                })
                .filter(consultantGroupUser -> {
                    if(!isConsultant){
                        return true;
                    }

                    if(consultantGroupUser.getUser().getId().equals(userCurrent.getId())){
                        return true;
                    }

                    return false;
                })
                .collect(Collectors.toSet());
    }
}
