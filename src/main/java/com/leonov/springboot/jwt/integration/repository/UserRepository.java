package com.leonov.springboot.jwt.integration.repository;

import com.leonov.springboot.jwt.integration.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
