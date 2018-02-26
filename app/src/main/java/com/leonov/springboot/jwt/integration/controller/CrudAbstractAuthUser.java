package com.leonov.springboot.jwt.integration.controller;

import com.leonov.springboot.jwt.integration.domain.User;
import com.leonov.springboot.jwt.integration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Set;
import java.util.stream.Collectors;

abstract public class CrudAbstractAuthUser<T, K>
        extends CrudAbstract<T, K> {
    @Autowired
    private UserService userService;
    protected Authentication getUserInformation(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return  authentication;
    }
    private Set<String> getUserRoles(){
        Authentication authentication = getUserInformation();

        Set<String> roles = authentication
                .getAuthorities()
                .stream()
                .map(r -> r.getAuthority())
                .collect(Collectors.toSet());

        return roles;
    }

    protected boolean isAdmin(){
        for(String role : getUserRoles()){
            if (role.equals("ADMIN_USER")){
                return true;
            }
        }

        return false;
    }

    protected boolean isCustomer(){
        for(String role : getUserRoles()){
            if (role.equals("CUSTOMER")){
                return true;
            }
        }

        return false;
    }

    protected boolean isConsultant(){
        for(String role : getUserRoles()){
            if (role.equals("CONSULTANT")){
                return true;
            }
        }

        return false;
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
