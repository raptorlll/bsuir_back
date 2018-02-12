package com.leonov.springboot.jwt.integration.repository;

import com.leonov.springboot.jwt.integration.domain.CustomerInformation;
import org.springframework.data.repository.CrudRepository;

public interface CustomerInformationRepository extends CrudRepository<CustomerInformation, Long> {

}
