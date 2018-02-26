package com.leonov.springboot.jwt.integration.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.leonov.springboot.jwt.integration.deserializer.ConsultantGroupUserDeserializer;
import com.leonov.springboot.jwt.integration.deserializer.CustomerInformationDeserializer;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Conversation {
    private Long id;
    private Byte active;
    private int messagesCount;
    private ConsultantGroupUser consultantGroupUser;
    private CustomerInformation customerInformation;
    private Collection<ConversationMessage> conversationMessages;
    private Collection<ConversationStatusHistory> conversationStatusHistories;
    private Collection<CustomerPayment> customerPayments;

    @Transient
    public int getMessagesCount() {
        return messagesCount;
    }

    public void setMessagesCount(int messagesCount) {
        this.messagesCount = messagesCount;
    }

    @Id
    @Column(name = "id", nullable = false) @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "active", nullable = false)
    public Byte getActive() {
        return active;
    }

    public void setActive(Byte active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Conversation that = (Conversation) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (active != null ? !active.equals(that.active) : that.active != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (active != null ? active.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "consultant_group_user_id", nullable = false)
    @JsonDeserialize(using = ConsultantGroupUserDeserializer.class)
    public ConsultantGroupUser getConsultantGroupUser() {
        return consultantGroupUser;
    }

    public void setConsultantGroupUser(ConsultantGroupUser consultantGroupUser) {
        this.consultantGroupUser = consultantGroupUser;
    }

    @OneToOne
    @JoinColumn(name = "customer_information_id", referencedColumnName = "id", nullable = false)
    @JsonDeserialize(using = CustomerInformationDeserializer.class)
    public CustomerInformation getCustomerInformation() {
        return customerInformation;
    }

    public void setCustomerInformation(CustomerInformation customerInformation) {
        this.customerInformation = customerInformation;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "conversation")
    public Collection<ConversationMessage> getConversationMessages() {
        return conversationMessages;
    }

    public void setConversationMessages(Collection<ConversationMessage> conversationMessages) {
        this.conversationMessages = conversationMessages;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "conversation")
    public Collection<ConversationStatusHistory> getConversationStatusHistories() {
        return conversationStatusHistories;
    }

    public void setConversationStatusHistories(Collection<ConversationStatusHistory> conversationStatusHistories) {
        this.conversationStatusHistories = conversationStatusHistories;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "conversation")
    public Collection<CustomerPayment> getCustomerPayments() {
        return customerPayments;
    }

    public void setCustomerPayments(Collection<CustomerPayment> customerPayments) {
        this.customerPayments = customerPayments;
    }
}
