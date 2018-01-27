package com.nouhoun.springboot.jwt.integration.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "conversation_status_history", schema = "back", catalog = "")
public class ConversationStatusHistory {
    private Long id;
    private Timestamp dateTime;
    private Conversation conversationByConversationId;
    private ConversationStatus conversationStatusByConversationStatusId;

    @Id
    @Column(name = "id", nullable = false) @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "date_time", nullable = false)
    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConversationStatusHistory that = (ConversationStatusHistory) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "conversation_id", referencedColumnName = "id", nullable = false)
    public Conversation getConversationByConversationId() {
        return conversationByConversationId;
    }

    public void setConversationByConversationId(Conversation conversationByConversationId) {
        this.conversationByConversationId = conversationByConversationId;
    }

    @ManyToOne
    @JoinColumn(name = "conversation_status_id", referencedColumnName = "id", nullable = false)
    public ConversationStatus getConversationStatusByConversationStatusId() {
        return conversationStatusByConversationStatusId;
    }

    public void setConversationStatusByConversationStatusId(ConversationStatus conversationStatusByConversationStatusId) {
        this.conversationStatusByConversationStatusId = conversationStatusByConversationStatusId;
    }
}
