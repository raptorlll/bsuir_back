package com.nouhoun.springboot.jwt.integration.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "consultant_group", schema = "back", catalog = "")
public class ConsultantGroup {
    private Long id;
    private String name;
    private String description;
    private Integer videoTarif;
    private Integer conversationTarif;
    private Collection<ConsultantGroupUser> consultantGroupUsersById;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "video_tarif", nullable = false)
    public Integer getVideoTarif() {
        return videoTarif;
    }

    public void setVideoTarif(Integer videoTarif) {
        this.videoTarif = videoTarif;
    }

    @Basic
    @Column(name = "conversation_tarif", nullable = false)
    public Integer getConversationTarif() {
        return conversationTarif;
    }

    public void setConversationTarif(Integer conversationTarif) {
        this.conversationTarif = conversationTarif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsultantGroup that = (ConsultantGroup) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (videoTarif != null ? !videoTarif.equals(that.videoTarif) : that.videoTarif != null) return false;
        if (conversationTarif != null ? !conversationTarif.equals(that.conversationTarif) : that.conversationTarif != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (videoTarif != null ? videoTarif.hashCode() : 0);
        result = 31 * result + (conversationTarif != null ? conversationTarif.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "consultantGroupByConsultantGroutId")
    public Collection<ConsultantGroupUser> getConsultantGroupUsersById() {
        return consultantGroupUsersById;
    }

    public void setConsultantGroupUsersById(Collection<ConsultantGroupUser> consultantGroupUsersById) {
        this.consultantGroupUsersById = consultantGroupUsersById;
    }
}
