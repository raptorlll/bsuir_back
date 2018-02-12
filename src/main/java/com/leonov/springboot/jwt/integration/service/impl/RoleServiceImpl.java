package com.leonov.springboot.jwt.integration.service.impl;

import com.leonov.springboot.jwt.integration.domain.Role;
import com.leonov.springboot.jwt.integration.repository.RoleRepository;
import com.leonov.springboot.jwt.integration.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
