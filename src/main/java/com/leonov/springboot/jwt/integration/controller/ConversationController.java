package com.leonov.springboot.jwt.integration.controller;

import com.leonov.springboot.jwt.integration.domain.Conversation;
import com.leonov.springboot.jwt.integration.domain.ConversationMessage;
import com.leonov.springboot.jwt.integration.domain.ConversationStatusHistory;
import com.leonov.springboot.jwt.integration.domain.User;
import com.leonov.springboot.jwt.integration.enums.ConversationStatusesEnum;
import com.leonov.springboot.jwt.integration.service.ConversationService;
import com.leonov.springboot.jwt.integration.service.CrudServiceInterface;
import com.leonov.springboot.jwt.integration.service.EmailService;
import com.leonov.springboot.jwt.integration.service.FcmNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/conversation")
public class ConversationController extends CrudAbstractAuthUser<Conversation, Long> {
    @Autowired
    private ConversationService service;

    @Autowired
    private FcmNotificationService pushService;

    @Autowired
    private EmailService emailService;

    @Override
    public CrudServiceInterface<Conversation, Long> getService() {
        return service;
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('CUSTOMER')")
    public Conversation saveItem(@RequestBody Conversation information) {
        User user = getCurrentUser();

        Conversation conversation = super.saveItem(information);

        service.setConversatonStatus(conversation, ConversationStatusesEnum.OPEN);

        /* Send few push notifications to consultant */
        Map<String, Object> parameters = new HashMap<>();

        emailService.setContent("new_conversation", parameters)
                .sendSimpleMessage(user.getEmail(), "Have just created new conversation");

        pushService.sendPushMessageToDevice(
                conversation.getConsultantGroupUser().getUser(),
                "Hello, " + user.getFirstName() + " " + user.getLastName(),
                "Have just created new conversation"
        );

        return conversation;
    }

    @Override
    public Collection<Conversation> getItems() {
        User userCurrent = getCurrentUser();
        boolean isConsultant = isConsultant();
        boolean isAdmin = isAdmin();
        boolean isCustomer = isCustomer();

        return super.getItems()
                .stream()
                .filter(conversation -> {
                    if (isAdmin) {
                        return true;
                    }

                    if (
                        isConsultant
                        && conversation.getActive().intValue() == 1
                        && conversation.getConsultantGroupUser().getUser().getId().equals(userCurrent.getId())
                    ) {
                        return true;
                    }

                    if (
                        isCustomer
                        && conversation.getCustomerInformation().getUser().getId().equals(userCurrent.getId())
                    ) {
                        return true;
                    }


                    return false;
                })
                .map(conversation -> {
                    conversation.setMessagesCount(conversation.getConversationMessages().size());

                    return  conversation;
                })
                .collect(Collectors.toSet());
    }


    @PostMapping(value = "/close/{id}")
    public String close(@PathVariable(value = "id") Long id) {
        Conversation conversation = service.findOne(id);
        conversation.setActive(new Byte("0"));
        service.save(conversation);

        service.setConversatonStatus(conversation, ConversationStatusesEnum.CLOSED);

        return  "Closed";
    }

    @GetMapping(value = "/statuses/{id}")
    public String statuses(@PathVariable(value = "id") Long id) {
        Conversation conversation = service.findOne(id);
        Collection<ConversationStatusHistory> conversationStatusHistories = conversation.getConversationStatusHistories();

        StringBuffer stringBuffer = new StringBuffer();
        for (ConversationStatusHistory conversationStatusHistory : conversationStatusHistories){
            stringBuffer.append(conversationStatusHistory.getConversationStatuses().getDescription())
                    .append(" ")
                    .append(conversationStatusHistory.getDateTime().toString())
                    .append("\n");
        }
        String s = stringBuffer.toString();

        return  s;
    }

}
