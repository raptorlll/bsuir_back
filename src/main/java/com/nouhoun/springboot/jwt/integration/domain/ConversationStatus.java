package com.nouhoun.springboot.jwt.integration.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "conversation_status", schema = "back", catalog = "")
public class ConversationStatus {
    private Long id;
    private String name;
    private String description;
    private Collection<ConversationStatusHistory> conversationStatusHistories;

    @Id
    @Column(name = "id", nullable = false) @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 50)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConversationStatus that = (ConversationStatus) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "conversationStatuses")
    public Collection<ConversationStatusHistory> getConversationStatusHistories() {
        return conversationStatusHistories;
    }

    public void setConversationStatusHistories(Collection<ConversationStatusHistory> conversationStatusHistories) {
        this.conversationStatusHistories = conversationStatusHistories;
    }
}
