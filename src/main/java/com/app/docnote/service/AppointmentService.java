package com.app.docnote.service;

import com.app.docnote.model.DTO.AppointmentAddDTO;
import com.app.docnote.model.entity.Appointment;

public interface AppointmentService {
    void add(AppointmentAddDTO appointmentAddDTO, Long id);
    Appointment findById(Long id);
}
