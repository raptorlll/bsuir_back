package com.leonov.springboot.jwt.integration.controller;

import com.leonov.springboot.jwt.integration.domain.User;
import com.leonov.springboot.jwt.integration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

abstract public class CrudAbstractAuthUser<T, K>
        extends CrudAbstract<T, K> {
    @Autowired
    private UserService userService;
    protected Authentication getUserInformation(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return  authentication;
    }

    protected Long getCurrentUserId(){
        try {
            return getCurrentUser().getId();
        }catch (Exception e){
            return 0L;
        }
    }

    protected User getCurrentUser() {
        return (User) userService.findByUsername((String) getUserInformation().getPrincipal());
    }
}
