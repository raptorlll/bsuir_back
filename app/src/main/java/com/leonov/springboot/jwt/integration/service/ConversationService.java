package com.leonov.springboot.jwt.integration.service;

import com.leonov.springboot.jwt.integration.domain.Conversation;
import com.leonov.springboot.jwt.integration.enums.ConversationStatusesEnum;

public interface ConversationService extends CrudServiceInterface<Conversation, Long> {
    public void setConversatonStatus(Conversation conversation, ConversationStatusesEnum conversationStatusesEnum);
}
