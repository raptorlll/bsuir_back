package com.leonov.springboot.jwt.integration.controller;

import com.leonov.springboot.jwt.integration.JsonModels.UserJson;
import com.leonov.springboot.jwt.integration.domain.Role;
import com.leonov.springboot.jwt.integration.domain.User;
import com.leonov.springboot.jwt.integration.service.*;
import com.leonov.springboot.jwt.integration.domain.*;
import com.leonov.springboot.jwt.integration.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController extends CrudAbstract<User, Long>  {
    @Value("${security.encoding-strength}")
    private Integer encodingStrength;

    @Autowired
    private UserService userService;

    @Autowired
    private CrudServiceInterface<User, Long> service;

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

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user", user);

        try {
            emailService.setContent("registration", parameters)
                    .sendSimpleMessage(user.getEmail(), "You successfuly registered in Awesome Consultant");
        } catch (Exception e) {
            System.out.println("Email error");
        }

        userJson.setId(user.getId());
        return userJson;
    }
}
