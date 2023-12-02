package com.example.docnote.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "documents")
public class SicknessLeaveDocument extends BaseEntity{


    @Column(name = "date_start")
    private LocalDate dateStart;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    @NotNull
    @Column(name = "employer_first_name")
    private String employerFirstName;

    @NotNull
    @Column(name = "employer_last_name")
    private String employerLastName;

    @Column(name = "company_name")
    private String companyName;

    @NotNull
    private String reason;

    @ManyToOne
    private Patient patient;

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getEmployerFirstName() {
        return employerFirstName;
    }

    public void setEmployerFirstName(String employerFirstName) {
        this.employerFirstName = employerFirstName;
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
