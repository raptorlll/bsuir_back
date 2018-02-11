package com.nouhoun.springboot.jwt.integration.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nouhoun.springboot.jwt.integration.domain.*;
import com.nouhoun.springboot.jwt.integration.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.nouhoun.springboot.jwt.integration.JsonModels.UserJson;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController extends CrudAbstract<User, Long>  {
    @Value("${security.encoding-strength}")
    private Integer encodingStrength;

    @Autowired
    private UserService userService;

    @Autowired
    private  CrudServiceInterface<User, Long> service;

    @Autowired
    private RoleService roleService;

    @Autowired
    private CustomerInformationService customerInformationService;

    @Autowired
    private EmailService emailService;

    @Override
    public CrudServiceInterface<User, Long> getService() {
        return service;
    }

    @GetMapping("/email")
    public String sendEmail(){
        Map<String, Object> model = new HashMap<>();
        User u = new User();
        u.setLastName("Test");
        model.put("user", u);

        emailService.setContent("login", model)
                .sendSimpleMessage("leon-polq@yandex.ru", "Hello");

        return "Ok";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public UserJson registerUserAccount(@RequestBody UserJson userJson) {
        ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(encodingStrength);
        List<User> us = userService.findAllUsers() ;
        User user = new User();

        user.setFirstName(userJson.getFirstName());
        user.setLastName(userJson.getLastName());
        user.setUsername(userJson.getUsername());
        user.setEmail(userJson.getEmail());
        user.setPassword(passwordEncoder.encodePassword(userJson.getEmail(), ""));

        List<Role> roles = new ArrayList<Role>();

        if (userJson.getRoles().size()!= 0) {

            for (String roleName : userJson.getRoles()) {
                roles.add(roleService.findByRoleName(roleName));
            }
        }

        user.setRoles(roles);

        userService.save(user);
        userJson.setId(user.getId());
        return userJson;
    }
}
