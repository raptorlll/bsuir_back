package com.nouhoun.springboot.jwt.integration.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nouhoun.springboot.jwt.integration.deserializer.ConsultantGroupDeserializer;
import com.nouhoun.springboot.jwt.integration.deserializer.UserDeserializer;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "consultant_group_user", schema = "back", catalog = "")
public class ConsultantGroupUser {
    private Long id;
    private Byte status;
    private Integer videoTarif;
    private Integer conversationTarif;
    private User user;
    private ConsultantGroup consultantGroup;
    private Collection<ConsultantInformation> consultantInformation;
    private Collection<Conversation> conversations;

    @Id
    @Column(name = "id", nullable = false) @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "status", nullable = true)
    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    @Basic
    @Column(name = "video_tarif", nullable = true)
    public Integer getVideoTarif() {
        return videoTarif;
    }

    public void setVideoTarif(Integer videoTarif) {
        this.videoTarif = videoTarif;
    }

    @Basic
    @Column(name = "conversation_tarif", nullable = true)
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

        ConsultantGroupUser that = (ConsultantGroupUser) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (videoTarif != null ? !videoTarif.equals(that.videoTarif) : that.videoTarif != null) return false;
        if (conversationTarif != null ? !conversationTarif.equals(that.conversationTarif) : that.conversationTarif != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (videoTarif != null ? videoTarif.hashCode() : 0);
        result = 31 * result + (conversationTarif != null ? conversationTarif.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    @JsonDeserialize(using = UserDeserializer.class)
//    @JsonIdentityInfo(
//            generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn(name = "consultant_grout_id", referencedColumnName = "id", nullable = false)
    @JsonDeserialize(using = ConsultantGroupDeserializer.class)
//    @JsonIdentityInfo(
//            generator = ObjectIdGenerators.PropertyGenerator.class,
//            property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
    public ConsultantGroup getConsultantGroup() {
        return consultantGroup;
    }

    public void setConsultantGroup(ConsultantGroup consultantGroupByConsultantGroutId) {
        this.consultantGroup = consultantGroupByConsultantGroutId;
    }

    @OneToMany(mappedBy = "consultantGroupUser")
    @JsonIgnore
//    @JoinColumn(name = "user_id", referencedColumnName = "consultant_group_user_id", nullable = false)
    public Collection<ConsultantInformation> getConsultantInformation() {
        return consultantInformation;
    }

    public void setConsultantInformation(Collection<ConsultantInformation> consultantInformation) {
        this.consultantInformation = consultantInformation;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "consultantGroupUserByConsultantGroupUserId")
    public Collection<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(Collection<Conversation> conversationsById) {
        this.conversations = conversationsById;
    }
}
