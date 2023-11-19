package com.docnote.util;

import com.docnote.model.entity.Doctor;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class CurrentUser {
    private Doctor doctor;

    private boolean isLogged;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public void setLogged(boolean logged) {
        isLogged = logged;
    }
}
