package com.nouhoun.springboot.jwt.integration.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "conversation_message", schema = "back", catalog = "")
public class ConversationMessage {
    private Long id;
    private String message;
    private Byte isConsultantMessage;
    private Timestamp dateTime;
    private String attachedFile;
    private Conversation conversationByConversationId;
    private ConversationVideo conversationVideoByConversationVideoId;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "message", nullable = false, length = -1)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "is_consultant_message", nullable = true)
    public Byte getIsConsultantMessage() {
        return isConsultantMessage;
    }

    public void setIsConsultantMessage(Byte isConsultantMessage) {
        this.isConsultantMessage = isConsultantMessage;
    }

    @Basic
    @Column(name = "date_time", nullable = false)
    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    @Basic
    @Column(name = "attached_file", nullable = true, length = 50)
    public String getAttachedFile() {
        return attachedFile;
    }

    public void setAttachedFile(String attachedFile) {
        this.attachedFile = attachedFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConversationMessage that = (ConversationMessage) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (isConsultantMessage != null ? !isConsultantMessage.equals(that.isConsultantMessage) : that.isConsultantMessage != null)
            return false;
        if (dateTime != null ? !dateTime.equals(that.dateTime) : that.dateTime != null) return false;
        if (attachedFile != null ? !attachedFile.equals(that.attachedFile) : that.attachedFile != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (isConsultantMessage != null ? isConsultantMessage.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (attachedFile != null ? attachedFile.hashCode() : 0);
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
    @JoinColumn(name = "conversation_video_id", referencedColumnName = "id")
    public ConversationVideo getConversationVideoByConversationVideoId() {
        return conversationVideoByConversationVideoId;
    }

    public void setConversationVideoByConversationVideoId(ConversationVideo conversationVideoByConversationVideoId) {
        this.conversationVideoByConversationVideoId = conversationVideoByConversationVideoId;
    }
}
