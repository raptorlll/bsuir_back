package com.leonov.springboot.jwt.integration.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.leonov.springboot.jwt.integration.deserializer.ConversationDeserializer;
import com.leonov.springboot.jwt.integration.deserializer.UserDeserializer;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "conversation_message", schema = "back", catalog = "")
public class ConversationMessage {
    private Long id;
    private String message;
    private Byte isConsultantMessage;
    private Timestamp dateTime;
    private String attachedFile;
    private Conversation conversation;
    private Time videoDuration;
    private String videoExternalLink;

    @Id
    @Column(name = "id", nullable = false) @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
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
    @JsonDeserialize(using = ConversationDeserializer.class)
    public Conversation getConversation() {
        return conversation;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    @Basic
    @Column(name = "video_duration", nullable = true)
    public Time getVideoDuration() {
        return videoDuration;
    }

    public void setVideoDuration(Time duration) {
        this.videoDuration = duration;
    }

    @Basic
    @Column(name = "video_external_link", nullable = true, length = 50)
    public String getVideoExternalLink() {
        return videoExternalLink;
    }

    public void setVideoExternalLink(String externalLink) {
        this.videoExternalLink = externalLink;
    }

}
