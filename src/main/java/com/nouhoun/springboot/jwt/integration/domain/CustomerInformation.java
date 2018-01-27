package com.nouhoun.springboot.jwt.integration.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nouhoun.springboot.jwt.integration.deserializer.UserDeserializer;

import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "customer_information", schema = "back", catalog = "")
public class CustomerInformation {
    private Long id;
    private Date birthData;
    private String additionalInformation;
    private Byte primary;
    private Collection<Conversation> conversationsById;
    private User user;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Basic
    @Column(name = "birth_data", nullable = true)
    public Date getBirthData() {
        return birthData;
    }

    public void setBirthData(Date birthData) {
        this.birthData = birthData;
    }

    @Basic
    @Column(name = "additional_information", nullable = true)
    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    @Basic
    @Column(name = "primary", nullable = false)
    public Byte getPrimary() {
        return primary;
    }

    public void setPrimary(Byte primary) {
        this.primary = primary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerInformation that = (CustomerInformation) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (birthData != null ? !birthData.equals(that.birthData) : that.birthData != null) return false;
//        if (!Arrays.equals(additionalInformation, that.additionalInformation)) return false;
        if (primary != null ? !primary.equals(that.primary) : that.primary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (birthData != null ? birthData.hashCode() : 0);
//        result = 31 * result + Arrays.hashCode(additionalInformation);
        result = 31 * result + (primary != null ? primary.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "customerInformationByCustomerInformationId")
    public Collection<Conversation> getConversationsById() {
        return conversationsById;
    }

    public void setConversationsById(Collection<Conversation> conversationsById) {
        this.conversationsById = conversationsById;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonDeserialize(using = UserDeserializer.class)
//    @JsonProperty("u")
    public User getUser() {
        return user;
    }

    public void setUser(User userByUserId) {
        this.user = userByUserId;
    }
}
