package com.app.docnote.model.entity;

import com.app.docnote.model.enums.TreatmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jdk.jfr.BooleanFlag;

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
    @Column(name = "treatment_type")
    private TreatmentType treatmentType;

    @NotNull
    @Column(name = "prescription")
    private String prescription;


    @ManyToOne
    private Patient patient;

    @BooleanFlag
    @Column(name= "is_patient_worker")
    private boolean worker;

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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public SicknessLeaveDocument getSicknessLeaveDocument() {
        return sicknessLeaveDocument;
    }

    public boolean isWorker() {
        return worker;
    }

    public void setWorker(boolean worker) {
        this.worker = worker;
    }

    public void setSicknessLeaveDocument(SicknessLeaveDocument sicknessLeaveDocument) {
        this.sicknessLeaveDocument = sicknessLeaveDocument;
    }
}
