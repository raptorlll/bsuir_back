package com.leonov.springboot.jwt.integration.service;

import com.leonov.springboot.jwt.integration.domain.User;

import java.util.List;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();

//    List<RandomCity> findAllRandomCities();
}
