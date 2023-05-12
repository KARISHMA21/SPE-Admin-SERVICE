package com.admin_service.bean.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name="patient")
//@DependsOn("dataSource")
//public class PatientEntity implements UserDetails {
public class PatientEntity{




    @Id
    private String pid;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "pid")

    private String name;
    private String uniqueid;
    private String dob;
    private int age;
    private String gender;
    private BigInteger phone;
    private String address;
    private String email;
    private Boolean minor_incapacitated;
    private Boolean disabled;
    private Boolean haswebappaccess;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniqueid() {
        return uniqueid;
    }

    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BigInteger getPhone() {
        return phone;
    }

    public void setPhone(BigInteger phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public PatientEntity getGuardian() {
        return guardian;
    }

    public void setGuardian(PatientEntity guardian) {
        this.guardian = guardian;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getMinor_incapacitated() {
        return minor_incapacitated;
    }

    public void setMinor_incapacitated(Boolean minor_incapacitated) {
        this.minor_incapacitated = minor_incapacitated;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getHaswebappaccess() {
        return haswebappaccess;
    }

    public void setHaswebappaccess(Boolean haswebappaccess) {
        this.haswebappaccess = haswebappaccess;
    }



    @ManyToOne
    @JoinColumn(name="guardian_id", nullable = true)
    private PatientEntity guardian;


//    public List<EntityReg> getEntityIds() {
//        return entityIds;
//    }
//
//    public void setEntityIds(List<EntityReg> entityIds) {
//        this.entityIds = entityIds;
//    }
@ManyToMany(mappedBy = "patientIds")

Set<EntityReg> entityIds = new HashSet<>();

    public Set<EntityReg> getEntityIds() {
        return entityIds;
    }

    public void setEntityIds(Set<EntityReg> entityIds) {
        this.entityIds = entityIds;
    }



    public PatientEntity() {
    }



    @Override
    public String toString() {
        return "Patient{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", uniqueid=" + uniqueid +
                ", dob='" + dob + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", minor_incapacitated=" + minor_incapacitated +
                ", disabled=" + disabled +
                ", haswebappaccess=" + haswebappaccess +
//                ", guardian_id="+guardian.pid+
//                ", PatientEntity=" + gaurdian_id +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
                '}';
    }
}
