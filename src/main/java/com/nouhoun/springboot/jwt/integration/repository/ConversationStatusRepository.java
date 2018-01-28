package com.nouhoun.springboot.jwt.integration.repository;

import com.nouhoun.springboot.jwt.integration.domain.ConsultantGroup;
import com.nouhoun.springboot.jwt.integration.domain.ConversationStatus;
import org.springframework.data.repository.CrudRepository;

public interface ConversationStatusRepository extends CrudRepository<ConversationStatus, Long> {

}
