package com.app.docnote.model.DTO;

import java.time.LocalDate;

public class DocumentAddDTO {

    private LocalDate dateStart;

    private LocalDate dateEnd;

    private String employerFirstName;

    private String employerLastName;

    private String companyName;

    private String reason;

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
}
