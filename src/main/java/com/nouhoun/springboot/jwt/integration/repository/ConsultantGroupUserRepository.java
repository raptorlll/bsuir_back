package com.nouhoun.springboot.jwt.integration.repository;

import com.nouhoun.springboot.jwt.integration.domain.ConsultantGroup;
import com.nouhoun.springboot.jwt.integration.domain.ConsultantGroupUser;
import org.springframework.data.repository.CrudRepository;

public interface ConsultantGroupUserRepository extends CrudRepository<ConsultantGroupUser, Long> {

}
