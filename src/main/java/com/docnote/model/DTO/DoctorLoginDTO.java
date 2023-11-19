package com.docnote.model.DTO;

public class DoctorLoginDTO {

    private String emailOrUsername;
    private String password;



    public String getEmailOrUsername() {
        return emailOrUsername;
    }

    public String getPassword() {
        return password;
    }

    public void setEmailOrUsername(String emailOrUsername) {
        this.emailOrUsername = emailOrUsername;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
