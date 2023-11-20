package com.example.docnote.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "treatments")
public class SicknessLeaveDocument extends BaseEntity{



    @Column(name = "sick_leave_start")
    private LocalDate sickLeaveStart;

    @FutureOrPresent
    @Column(name = "sick_leave_end")
    private LocalDate sickLeaveEnd;

    @NotNull
    @Column(name = "employer_name")
    private String employerName;

    @NotNull
    @Column(name = "employer_last_name")
    private String employerLastName;

    @Column(name = "company_name")
    private String companyName;

    @NotNull
    private String reason;

    @OneToOne
    private Patient patient;

    public LocalDate getSickLeaveStart() {
        return sickLeaveStart;
    }

    public void setSickLeaveStart(LocalDate sickLeaveStart) {
        this.sickLeaveStart = sickLeaveStart;
    }

    public LocalDate getSickLeaveEnd() {
        return sickLeaveEnd;
    }

    public void setSickLeaveEnd(LocalDate sickLeaveEnd) {
        this.sickLeaveEnd = sickLeaveEnd;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getEmployerLastName() {
        return employerLastName;
    }

    public void setEmployerLastName(String employerLastName) {
        this.employerLastName = employerLastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
