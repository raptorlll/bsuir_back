package com.leonov.springboot.jwt.integration.repository;

import com.leonov.springboot.jwt.integration.domain.ConversationStatus;
import org.springframework.data.repository.CrudRepository;

public interface ConversationStatusRepository extends CrudRepository<ConversationStatus, Long> {

}
