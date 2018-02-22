package com.leonov.springboot.jwt.integration.service;

import com.leonov.springboot.jwt.integration.domain.Conversation;
import com.leonov.springboot.jwt.integration.domain.ConversationMessage;

import java.util.List;

public interface ConversationMessageService extends CrudServiceInterface<ConversationMessage, Long> {
    public List<ConversationMessage> findAllByConversationIs(Conversation conversation);
}
