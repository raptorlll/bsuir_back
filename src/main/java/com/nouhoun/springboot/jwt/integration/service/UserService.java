package com.nouhoun.springboot.jwt.integration.service;

import com.nouhoun.springboot.jwt.integration.domain.User;

import java.util.List;

public interface UserService extends CrudServiceInterface<User, Long> {
    User findByUsername(String username);
    List<User> findAllUsers();
    User findOne(Long id);
    User save(User u);
}
