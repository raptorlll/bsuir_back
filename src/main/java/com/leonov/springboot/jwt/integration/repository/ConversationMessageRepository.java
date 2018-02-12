package com.leonov.springboot.jwt.integration.repository;

import com.leonov.springboot.jwt.integration.domain.ConversationMessage;
import org.springframework.data.repository.CrudRepository;

public interface ConversationMessageRepository extends CrudRepository<ConversationMessage, Long> {

}
