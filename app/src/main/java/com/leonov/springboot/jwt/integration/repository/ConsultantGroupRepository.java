package com.leonov.springboot.jwt.integration.repository;

import com.leonov.springboot.jwt.integration.domain.ConsultantGroup;
import org.springframework.data.repository.CrudRepository;

public interface ConsultantGroupRepository extends CrudRepository<ConsultantGroup, Long> {

}
