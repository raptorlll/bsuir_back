package com.nouhoun.springboot.jwt.integration.controller;

import com.nouhoun.springboot.jwt.integration.domain.User;
import com.nouhoun.springboot.jwt.integration.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by nydiarra on 06/05/17.
 */
@RestController
@RequestMapping("/springjwt")
public class ResourceController {
    @Autowired
    private GenericService userService;
//
//    @RequestMapping(value ="/cities")
//    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
//    public List<RandomCity> getUser(){
//        return userService.findAllRandomCities();
//    }
//
//    @RequestMapping(value ="/test")
//    public List<RandomCity> getTest(){
//        return userService.findAllRandomCities();
//    }

    @RequestMapping(value ="/getuserroles")
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
    public Set<String> getuserroles(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Set<String> roles = authentication
                .getAuthorities()
                .stream()
                .map(r -> r.getAuthority())
                .collect(Collectors.toSet());

        return roles;
    }

    @RequestMapping(value ="/users", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public List<User> getUsers(){
        return userService.findAllUsers();
    }
}
