package com.nouhoun.springboot.jwt.integration.domain;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;

@Entity
@Table(name = "conversation_video", schema = "back", catalog = "")
public class ConversationVideo {
    private Long id;
    private Time duration;
    private String externalLink;
    private Collection<ConversationMessage> conversationMessagesById;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "duration", nullable = true)
    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    @Basic
    @Column(name = "external_link", nullable = true, length = 50)
    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConversationVideo that = (ConversationVideo) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) return false;
        if (externalLink != null ? !externalLink.equals(that.externalLink) : that.externalLink != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (externalLink != null ? externalLink.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "conversationVideoByConversationVideoId")
    public Collection<ConversationMessage> getConversationMessagesById() {
        return conversationMessagesById;
    }

    public void setConversationMessagesById(Collection<ConversationMessage> conversationMessagesById) {
        this.conversationMessagesById = conversationMessagesById;
    }
}
