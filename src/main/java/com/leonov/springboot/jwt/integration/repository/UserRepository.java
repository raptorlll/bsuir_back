package com.leonov.springboot.jwt.integration.repository;

import com.leonov.springboot.jwt.integration.domain.Role;
import com.leonov.springboot.jwt.integration.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAllByRolesIs(Role role);
}
