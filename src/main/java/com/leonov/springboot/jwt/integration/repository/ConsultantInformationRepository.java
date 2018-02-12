package com.leonov.springboot.jwt.integration.repository;

import com.leonov.springboot.jwt.integration.domain.ConsultantInformation;
import org.springframework.data.repository.CrudRepository;

public interface ConsultantInformationRepository extends CrudRepository<ConsultantInformation, Long> {

}
