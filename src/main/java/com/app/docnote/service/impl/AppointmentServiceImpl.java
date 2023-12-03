package com.app.docnote.service.impl;

import com.app.docnote.model.entity.Appointment;
import com.app.docnote.service.AppointmentService;
import com.app.docnote.model.DTO.AppointmentAddDTO;
import com.app.docnote.model.entity.Patient;
import com.app.docnote.repository.AppointmentRepository;
import com.app.docnote.repository.PatientRepository;
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

    @Override
    public Appointment findById(Long id) {
        return appointmentRepository.findById(id).get();
    }
}
