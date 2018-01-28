package com.nouhoun.springboot.jwt.integration.repository;

import com.nouhoun.springboot.jwt.integration.domain.ConsultantInformation;
import org.springframework.data.repository.CrudRepository;

public interface ConsultantInformationRepository extends CrudRepository<ConsultantInformation, Long> {

}
