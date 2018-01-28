package com.nouhoun.springboot.jwt.integration.repository;

import com.nouhoun.springboot.jwt.integration.domain.Conversation;
import com.nouhoun.springboot.jwt.integration.domain.ConversationMessage;
import org.springframework.data.repository.CrudRepository;

public interface ConversationMessageRepository extends CrudRepository<ConversationMessage, Long> {

}
