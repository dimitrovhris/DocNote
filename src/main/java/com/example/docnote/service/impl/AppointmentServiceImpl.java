package com.example.docnote.service.impl;

import com.example.docnote.model.DTO.AppointmentAddDTO;
import com.example.docnote.model.entity.Appointment;
import com.example.docnote.repository.AppointmentRepository;
import com.example.docnote.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void add(AppointmentAddDTO appointmentAddDTO) {
        Appointment appointment = modelMapper.map(appointmentAddDTO, Appointment.class);

    }
}
