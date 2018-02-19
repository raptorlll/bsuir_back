package com.leonov.springboot.jwt.integration.service.impl;

import com.leonov.springboot.jwt.integration.domain.User;
import com.leonov.springboot.jwt.integration.repository.UserRepository;
import com.leonov.springboot.jwt.integration.service.RoleService;
import com.leonov.springboot.jwt.integration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends CrudSeviceAbstract<User, Long> implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public List<User> findAllCustomers() {
        return userRepository.findAllByRolesIs(roleService.findByRoleName("CUSTOMER"));
    }

    @Override
    public List<User> findAllConsultants() {
        return userRepository.findAllByRolesIs(roleService.findByRoleName("CONSULTANT"));
    }

    @Override
    public User save(User u) {
        return userRepository.save(u);
    }

    @Override
    protected CrudRepository<User, Long> getRepository() {
        return userRepository;
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findOne(id);
    }
}
