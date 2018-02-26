package com.leonov.springboot.jwt.integration.repository;

import com.leonov.springboot.jwt.integration.domain.ConversationStatus;
import com.leonov.springboot.jwt.integration.domain.ConversationStatusHistory;
import org.springframework.data.repository.CrudRepository;

public interface ConversationStatusHistoryRepository extends CrudRepository<ConversationStatusHistory, Long> {

}
