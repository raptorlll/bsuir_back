package com.nouhoun.springboot.jwt.integration.domain;

import javax.persistence.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collection;

@Entity
@Table(name = "customer_information", schema = "back", catalog = "")
public class CustomerInformation {
    private Long id;
    private String name;
    private String lastname;
    private Date birthData;
    private byte[] additionalInformation;
    private Byte primary;
    private Collection<Conversation> conversationsById;
    private User userByUserId;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "lastname", nullable = true, length = 50)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
    public byte[] getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(byte[] additionalInformation) {
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
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (birthData != null ? !birthData.equals(that.birthData) : that.birthData != null) return false;
        if (!Arrays.equals(additionalInformation, that.additionalInformation)) return false;
        if (primary != null ? !primary.equals(that.primary) : that.primary != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (birthData != null ? birthData.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(additionalInformation);
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
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}
