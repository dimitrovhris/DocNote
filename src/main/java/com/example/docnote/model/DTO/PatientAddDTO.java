package com.example.docnote.model.DTO;

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
}
