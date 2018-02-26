package com.leonov.springboot.jwt.integration.service;

import com.leonov.springboot.jwt.integration.domain.User;

import java.util.List;

public interface UserService extends CrudServiceInterface<User, Long> {
    User findByUsername(String username);
    List<User> findAllUsers();
    List<User> findAllCustomers();
    List<User> findAllConsultants();
    User findOne(Long id);
    User save(User u);
}
