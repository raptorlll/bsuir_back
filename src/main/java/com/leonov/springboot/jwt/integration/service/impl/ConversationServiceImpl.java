package com.leonov.springboot.jwt.integration.service.impl;

import com.leonov.springboot.jwt.integration.domain.Conversation;
import com.leonov.springboot.jwt.integration.domain.ConversationStatus;
import com.leonov.springboot.jwt.integration.domain.ConversationStatusHistory;
import com.leonov.springboot.jwt.integration.enums.ConversationStatusesEnum;
import com.leonov.springboot.jwt.integration.repository.ConversationRepository;
import com.leonov.springboot.jwt.integration.repository.ConversationStatusHistoryRepository;
import com.leonov.springboot.jwt.integration.repository.ConversationStatusRepository;
import com.leonov.springboot.jwt.integration.service.ConversationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ConversationServiceImpl extends CrudSeviceAbstract<Conversation, Long>
        implements ConversationService {
    @Autowired
    protected ConversationRepository repository;

    @Autowired
    protected ConversationStatusHistoryRepository historyRepository;

    @Autowired
    protected ConversationStatusRepository statusRepository;

    @Override
    protected CrudRepository<Conversation, Long> getRepository() {
        return repository;
    }

    @Override
    public void setConversatonStatus(Conversation conversation, ConversationStatusesEnum conversationStatusesEnum) {
        ConversationStatus conversationStatus = statusRepository.findByNameIs(conversationStatusesEnum.getValue());

        ConversationStatusHistory conversationStatusHistory = new ConversationStatusHistory();
        conversationStatusHistory.setConversation(conversation);
        conversationStatusHistory.setConversationStatuses(conversationStatus);

        historyRepository.save(conversationStatusHistory);
    }
}
