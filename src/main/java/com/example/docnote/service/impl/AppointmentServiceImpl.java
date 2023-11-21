package com.example.docnote.service.impl;

import com.example.docnote.model.DTO.AppointmentAddDTO;
import com.example.docnote.model.entity.Appointment;
import com.example.docnote.model.entity.Patient;
import com.example.docnote.repository.AppointmentRepository;
import com.example.docnote.repository.PatientRepository;
import com.example.docnote.service.AppointmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;


    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.modelMapper = new ModelMapper();

    }

    @Override
    public void add(AppointmentAddDTO appointmentAddDTO, Long id) {
        Appointment appointment = modelMapper.map(appointmentAddDTO, Appointment.class);
        appointment.setPatient(patientRepository.findById(id).get());
        Patient patient = patientRepository.findById(id).get();
        patient.getAppointments().add(appointment);
        patientRepository.save(patient);
        appointmentRepository.save(appointment);
    }
}
