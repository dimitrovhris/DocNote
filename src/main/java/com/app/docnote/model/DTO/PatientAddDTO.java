package com.app.docnote.model.DTO;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class PatientAddDTO {

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Size(min = 10, max = 10)
    private String egn;

    @PastOrPresent
    @NotNull
    private LocalDate birthday;

    @Positive
    @NotNull
    private int height;

    @Positive
    @NotNull
    private int weight;

    @NotEmpty
    private String address;

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

    public String getEgn() {
        return egn;
    }

    public void setEgn(String egn) {
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

    public PatientAddDTO(String firstName, String surname, String lastName, String egn, LocalDate birthday, int height, int weight, String address) {
        this.firstName = firstName;
        this.surname = surname;
        this.lastName = lastName;
        this.egn = egn;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.address = address;
    }

    public PatientAddDTO() {
    }
}
