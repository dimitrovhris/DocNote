package com.example.docnote.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient extends BaseEntity{

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    private String surname;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String egn;

    @PastOrPresent
    @NotNull
    private LocalDate birthday;

    @NotNull
    @Positive
    private int height;

    @NotNull
    @Positive
    private int weight;

    @NotNull
    private String address;

    @ManyToOne
    private UserEntity doctor;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Appointment> appointments;

    @OneToMany
    private List<SicknessLeaveDocument> sicknessLeaveDocuments;

    @OneToMany
    private List<Test> tests;

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

    public String getPhone() {
        return egn;
    }

    public void setPhone(String egn) {
        this.egn = egn;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(UserEntity doctor) {
        this.doctor = doctor;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public List<SicknessLeaveDocument> getSicknessLeaveDocuments() {
        return sicknessLeaveDocuments;
    }

    public void setSicknessLeaveDocuments(List<SicknessLeaveDocument> sicknessLeaveDocuments) {
        this.sicknessLeaveDocuments = sicknessLeaveDocuments;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
