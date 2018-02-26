package com.leonov.springboot.jwt.integration.repository;

import com.leonov.springboot.jwt.integration.domain.Conversation;
import org.springframework.data.repository.CrudRepository;

public interface ConversationRepository extends CrudRepository<Conversation, Long> {

}
