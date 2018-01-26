package com.nouhoun.springboot.jwt.integration.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Conversation {
    private Long id;
    private Byte active;
    private ConsultantGroupUser consultantGroupUserByConsultantGroupUserId;
    private CustomerInformation customerInformationByCustomerInformationId;
    private Collection<ConversationMessage> conversationMessagesById;
    private Collection<ConversationStatusHistory> conversationStatusHistoriesById;
    private Collection<CustomerPayment> customerPaymentsById;

    @Id
    @Column(name = "id", nullable = false)
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

    @ManyToOne
    @JoinColumn(name = "consultant_group_user_id", referencedColumnName = "id", nullable = false)
    public ConsultantGroupUser getConsultantGroupUserByConsultantGroupUserId() {
        return consultantGroupUserByConsultantGroupUserId;
    }

    public void setConsultantGroupUserByConsultantGroupUserId(ConsultantGroupUser consultantGroupUserByConsultantGroupUserId) {
        this.consultantGroupUserByConsultantGroupUserId = consultantGroupUserByConsultantGroupUserId;
    }

    @ManyToOne
    @JoinColumn(name = "customer_information_id", referencedColumnName = "id", nullable = false)
    public CustomerInformation getCustomerInformationByCustomerInformationId() {
        return customerInformationByCustomerInformationId;
    }

    public void setCustomerInformationByCustomerInformationId(CustomerInformation customerInformationByCustomerInformationId) {
        this.customerInformationByCustomerInformationId = customerInformationByCustomerInformationId;
    }

    @OneToMany(mappedBy = "conversationByConversationId")
    public Collection<ConversationMessage> getConversationMessagesById() {
        return conversationMessagesById;
    }

    public void setConversationMessagesById(Collection<ConversationMessage> conversationMessagesById) {
        this.conversationMessagesById = conversationMessagesById;
    }

    @OneToMany(mappedBy = "conversationByConversationId")
    public Collection<ConversationStatusHistory> getConversationStatusHistoriesById() {
        return conversationStatusHistoriesById;
    }

    public void setConversationStatusHistoriesById(Collection<ConversationStatusHistory> conversationStatusHistoriesById) {
        this.conversationStatusHistoriesById = conversationStatusHistoriesById;
    }

    @OneToMany(mappedBy = "conversationByConversationId")
    public Collection<CustomerPayment> getCustomerPaymentsById() {
        return customerPaymentsById;
    }

    public void setCustomerPaymentsById(Collection<CustomerPayment> customerPaymentsById) {
        this.customerPaymentsById = customerPaymentsById;
    }
}
