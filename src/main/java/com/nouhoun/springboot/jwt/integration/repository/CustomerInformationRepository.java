package com.nouhoun.springboot.jwt.integration.repository;

import com.nouhoun.springboot.jwt.integration.domain.CustomerInformation;
import com.nouhoun.springboot.jwt.integration.domain.Role;
import org.springframework.data.repository.CrudRepository;

public interface CustomerInformationRepository extends CrudRepository<CustomerInformation, Long> {

}
