package com.nouhoun.springboot.jwt.integration.controller;

import com.nouhoun.springboot.jwt.integration.domain.Role;
import com.nouhoun.springboot.jwt.integration.domain.User;
import com.nouhoun.springboot.jwt.integration.service.GenericService;
import com.nouhoun.springboot.jwt.integration.service.RoleService;
import com.nouhoun.springboot.jwt.integration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.nouhoun.springboot.jwt.integration.JsonModels.UserJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nydiarra on 06/05/17.
 */
@RestController
@RequestMapping("/user")
public class UserController {
//    @Autowired
//    private GenericService userService;

    @Value("${security.encoding-strength}")
    private Integer encodingStrength;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


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

//        if (!result.hasErrors()) {
//            registered = createUserAccount(accountDto, result);
//        }
//        if (registered == null) {
//            result.rejectValue("email", "message.regError");
//        }
        userJson.setId(user.getId());
        return userJson;
        // rest of the implementation
    }

}
