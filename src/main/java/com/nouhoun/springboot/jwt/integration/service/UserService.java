package com.nouhoun.springboot.jwt.integration.service;

import com.nouhoun.springboot.jwt.integration.domain.RandomCity;
import com.nouhoun.springboot.jwt.integration.domain.User;

import java.util.List;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface UserService {
    User findByUsername(String username);

    List<User> findAllUsers();

    User save(User u);
//    User findByUsername(String username);
}
