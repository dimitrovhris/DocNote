package com.docnote.service.impl;

import com.docnote.model.DTO.DoctorLoginDTO;
import com.docnote.model.DTO.DoctorRegisterDTO;
import com.docnote.model.entity.Doctor;
import com.docnote.repository.DoctorRepository;
import com.docnote.service.DoctorService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    private PasswordEncoder passwordEncoder;
    private DoctorRepository doctorRepository;

    public DoctorServiceImpl(PasswordEncoder passwordEncoder, DoctorRepository doctorRepository) {
        this.passwordEncoder = passwordEncoder;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void register(DoctorRegisterDTO doctorRegisterDTO) {
        Doctor doctor = new Doctor();

        doctor.setFirstName(doctorRegisterDTO.getFirstName());
        doctor.setSurname(doctorRegisterDTO.getSurname());
        doctor.setLastName(doctorRegisterDTO.getLastName());
        doctor.setUsername(doctorRegisterDTO.getUsername());
        doctor.setEmail(doctorRegisterDTO.getEmail());
        doctor.setPhone(doctorRegisterDTO.getPhone());
        doctor.setAddress(doctorRegisterDTO.getAddress());

        doctor.setPassword(passwordEncoder.encode(doctorRegisterDTO.getPassword()));
        doctorRepository.save(doctor);
    }

    @Override
    public boolean confirmPassword(DoctorRegisterDTO doctorRegisterDTO) {
        if(doctorRegisterDTO.getPassword()!= null){
        return doctorRegisterDTO.getPassword().equals(doctorRegisterDTO.getConfirmPassword());
        }
        return false;
    }

    @Override
    public boolean containsPhone(DoctorRegisterDTO doctorRegisterDTO) {
        return doctorRepository.findFirstByPhone(doctorRegisterDTO.getPhone()).isPresent();
    }

    @Override
    public boolean containsUsername(DoctorRegisterDTO doctorRegisterDTO) {
        return doctorRepository.findFirstByUsername(doctorRegisterDTO.getUsername()).isPresent();
    }

    @Override
    public boolean containsEmail(DoctorRegisterDTO doctorRegisterDTO) {
        return doctorRepository.findFirstByEmail(doctorRegisterDTO.getEmail()).isPresent();
    }

    @Override
    public boolean validLogin(DoctorLoginDTO doctorLoginDTO) {
        Optional<Doctor> foundDoctor = doctorRepository.findFirstByEmailOrUsername(doctorLoginDTO.getEmailOrUsername(), doctorLoginDTO.getEmailOrUsername());

        return foundDoctor.filter(doctor -> passwordEncoder.matches(doctorLoginDTO.getPassword(), doctor.getPassword())).isPresent();
    }

}
