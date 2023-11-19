package com.docnote.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.Set;

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

    @NotNull
    @Column(unique = true)
    private String phone;

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

    @OneToOne
    private Doctor doctor;

    @OneToMany
    private Set<Appointment> appointments;

    @OneToMany
    private Set<SicknessLeaveDocument> sicknessLeaveDocuments;

    @OneToMany
    private Set<Test> tests;

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
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<SicknessLeaveDocument> getSicknessLeaveDocuments() {
        return sicknessLeaveDocuments;
    }

    public void setSicknessLeaveDocuments(Set<SicknessLeaveDocument> sicknessLeaveDocuments) {
        this.sicknessLeaveDocuments = sicknessLeaveDocuments;
    }

    public Set<Test> getTests() {
        return tests;
    }

    public void setTests(Set<Test> tests) {
        this.tests = tests;
    }
}
