package com.nouhoun.springboot.jwt.integration.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.nouhoun.springboot.jwt.integration.deserializer.ConsultantGroupUserDeserializer;
import com.nouhoun.springboot.jwt.integration.deserializer.UserDeserializer;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "consultant_information", schema = "back", catalog = "")
public class ConsultantInformation {
    private Long id;
    private String education;
    private String degree;
    private String licenseNumber;
    private String licenseFile;
    private Date licenseUntil;
    private Time availableFrom;
    private Time availableUntil;
    private ConsultantGroupUser consultantGroupUser;

    @ManyToOne
    @JoinColumn(name="consultant_group_user_id")
    @JsonDeserialize(using = ConsultantGroupUserDeserializer.class)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    public ConsultantGroupUser getConsultantGroupUser() {
        return consultantGroupUser;
    }

    public void setConsultantGroupUser(ConsultantGroupUser consultantGroupUser) {
        this.consultantGroupUser = consultantGroupUser;
    }

    @Id
    @Column(name = "id", nullable = false) @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "education", nullable = false, length = 50)
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Basic
    @Column(name = "degree", nullable = false, length = 50)
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Basic
    @Column(name = "license_number", nullable = false, length = 50)
    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Basic
    @Column(name = "license_file", nullable = true, length = 50)
    public String getLicenseFile() {
        return licenseFile;
    }

    public void setLicenseFile(String licenseFile) {
        this.licenseFile = licenseFile;
    }

    @Basic
    @Column(name = "license_until", nullable = false)
    public Date getLicenseUntil() {
        return licenseUntil;
    }

    public void setLicenseUntil(Date licenseUntil) {
        this.licenseUntil = licenseUntil;
    }

    @Basic
    @Column(name = "available_from", nullable = false)
    public Time getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(Time availableFrom) {
        this.availableFrom = availableFrom;
    }

    @Basic
    @Column(name = "available_until", nullable = false)
    public Time getAvailableUntil() {
        return availableUntil;
    }

    public void setAvailableUntil(Time availableUntil) {
        this.availableUntil = availableUntil;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ConsultantInformation that = (ConsultantInformation) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (education != null ? !education.equals(that.education) : that.education != null) return false;
        if (degree != null ? !degree.equals(that.degree) : that.degree != null) return false;
        if (licenseNumber != null ? !licenseNumber.equals(that.licenseNumber) : that.licenseNumber != null)
            return false;
        if (licenseFile != null ? !licenseFile.equals(that.licenseFile) : that.licenseFile != null) return false;
        if (licenseUntil != null ? !licenseUntil.equals(that.licenseUntil) : that.licenseUntil != null) return false;
        if (availableFrom != null ? !availableFrom.equals(that.availableFrom) : that.availableFrom != null)
            return false;
        if (availableUntil != null ? !availableUntil.equals(that.availableUntil) : that.availableUntil != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (education != null ? education.hashCode() : 0);
        result = 31 * result + (degree != null ? degree.hashCode() : 0);
        result = 31 * result + (licenseNumber != null ? licenseNumber.hashCode() : 0);
        result = 31 * result + (licenseFile != null ? licenseFile.hashCode() : 0);
        result = 31 * result + (licenseUntil != null ? licenseUntil.hashCode() : 0);
        result = 31 * result + (availableFrom != null ? availableFrom.hashCode() : 0);
        result = 31 * result + (availableUntil != null ? availableUntil.hashCode() : 0);
        return result;
    }
}
