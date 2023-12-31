package com.app.docnote.model.DTO;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserRegisterDTO {

    @Size(min = 2)
    @NotEmpty
    private String firstName;

    @Size(min = 2)
    @NotEmpty
    private String surname;

    @Size(min = 2)
    @NotEmpty
    private String lastName;

    @NotEmpty
    @Size(min = 5)
    private String username;

    @NotEmpty
    @Size(min = 10, max = 10)
    private String egn;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min = 10)
    private String address;

    @NotEmpty
    @Size(min = 6)
    private String password;

    @NotEmpty
    private String confirmPassword;

    public UserRegisterDTO(String firstName, String surname, String lastName, String username, String egn, String email, String address, String password, String confirmPassword) {
        this.firstName = firstName;
        this.surname = surname;
        this.lastName = lastName;
        this.username = username;
        this.egn = egn;
        this.email = email;
        this.address = address;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public UserRegisterDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public String getLastName() {
        return lastName;
    }



    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
