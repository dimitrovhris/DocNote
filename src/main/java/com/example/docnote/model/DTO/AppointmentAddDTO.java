package com.example.docnote.model.DTO;


import com.example.docnote.model.enums.TreatmentType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentAddDTO {

    @NotNull
    @PastOrPresent
    private LocalDate visitDate;

    @NotNull
    private LocalTime hour;

    @NotEmpty
    private String diagnosis;

    @NotEmpty
    private String symptoms;

    @Enumerated
    private TreatmentType treatmentType;

    private String prescription;


    private boolean sicknessLeaveDocument;

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(LocalDate visitDate) {
        this.visitDate = visitDate;
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public TreatmentType getTreatmentType() {
        return treatmentType;
    }

    public void setTreatmentType(TreatmentType treatmentType) {
        this.treatmentType = treatmentType;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public boolean isSicknessLeaveDocument() {
        return sicknessLeaveDocument;
    }

    public void setSicknessLeaveDocument(boolean sicknessLeaveDocument) {
        this.sicknessLeaveDocument = sicknessLeaveDocument;
    }
}
