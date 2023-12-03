package com.app.docnote.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{
    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    private String surname;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String username;

    @Column(unique = true, nullable = false)
    private String egn;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull
    @Column(nullable = false)
    private String address;

    @NotNull
    private String password;

    @NotNull
    private boolean approved;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
            private List<UserRole> roles = new ArrayList<>();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
        this.egn = egn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    private List<Patient> patients;


    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public UserEntity(String firstName, String surname, String lastName, String username, String egn, String email, String address, String password, boolean approved, List<UserRole> roles, List<Patient> patients) {
        this.firstName = firstName;
        this.surname = surname;
        this.lastName = lastName;
        this.username = username;
        this.egn = egn;
        this.email = email;
        this.address = address;
        this.password = password;
        this.approved = approved;
        this.roles = roles;
        this.patients = patients;
    }
    public UserEntity(String firstName, String surname, String lastName, String username, String egn, String email, String address, String password, boolean approved) {
        this.firstName = firstName;
        this.surname = surname;
        this.lastName = lastName;
        this.username = username;
        this.egn = egn;
        this.email = email;
        this.address = address;
        this.password = password;
        this.approved = approved;
        this.roles = new ArrayList<>();
        this.patients = new ArrayList<>();
    }

    public UserEntity() {
    }
}
