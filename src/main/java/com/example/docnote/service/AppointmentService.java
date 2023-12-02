package com.example.docnote.service;

import com.example.docnote.model.DTO.AppointmentAddDTO;
import com.example.docnote.model.entity.Appointment;

public interface AppointmentService {
    void add(AppointmentAddDTO appointmentAddDTO, Long id);
    Appointment findById(Long id);
}
