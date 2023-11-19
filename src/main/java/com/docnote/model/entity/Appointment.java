package com.docnote.model.entity;

import com.docnote.model.enums.TreatmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
public class Appointment extends BaseEntity {
    @PastOrPresent
    @NotNull
    @Column(name = "visit_date")
    private LocalDate visitDate;

    @NotNull
    private LocalTime hour;

    @NotNull
    private String symptoms;

    @NotNull
    private String diagnosis;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TreatmentType type;

    @NotNull
    @Column(name = "prescribed_medicine")
    private String prescribedMedicine;

    @OneToOne
    private Patient patient;

    @OneToOne
    private SicknessLeaveDocument sicknessLeaveDocument;

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

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public TreatmentType getType() {
        return type;
    }

    public void setType(TreatmentType type) {
        this.type = type;
    }

    public String getPrescribedMedicine() {
        return prescribedMedicine;
    }

    public void setPrescribedMedicine(String prescribedMedicine) {
        this.prescribedMedicine = prescribedMedicine;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public SicknessLeaveDocument getSicknessLeaveDocument() {
        return sicknessLeaveDocument;
    }

    public void setSicknessLeaveDocument(SicknessLeaveDocument sicknessLeaveDocument) {
        this.sicknessLeaveDocument = sicknessLeaveDocument;
    }
}
