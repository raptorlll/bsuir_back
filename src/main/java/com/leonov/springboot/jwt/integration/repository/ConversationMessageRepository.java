package com.leonov.springboot.jwt.integration.repository;

import com.leonov.springboot.jwt.integration.domain.Conversation;
import com.leonov.springboot.jwt.integration.domain.ConversationMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConversationMessageRepository extends CrudRepository<ConversationMessage, Long> {
    List<ConversationMessage> findAllByConversationIs(Conversation conversation);
}
