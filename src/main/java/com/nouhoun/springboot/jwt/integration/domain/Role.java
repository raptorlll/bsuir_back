package com.nouhoun.springboot.jwt.integration.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Role {
    private Long id;
    private String description;
    private String roleName;
    private Collection<UserRole> userRolesById;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    @Column(name = "role_name", nullable = true, length = 255)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;
        if (description != null ? !description.equals(role.description) : role.description != null) return false;
        if (roleName != null ? !roleName.equals(role.roleName) : role.roleName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Collection<UserRole> getUserRolesById() {
        return userRolesById;
    }

    public void setUserRolesById(Collection<UserRole> userRolesById) {
        this.userRolesById = userRolesById;
    }
}
