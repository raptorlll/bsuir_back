package com.nouhoun.springboot.jwt.integration.service;

import com.nouhoun.springboot.jwt.integration.domain.Role;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface RoleService {
    Role findByRoleName(String roleName);
}
