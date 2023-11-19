package com.docnote.repository;

import com.docnote.model.DTO.DoctorRegisterDTO;
import com.docnote.model.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findFirstByPhone(String phone);
    Optional<Doctor> findFirstByUsername(String username);
    Optional<Doctor> findFirstByEmail(String email);
    Optional<Doctor> findFirstByEmailOrUsername(String email, String username);
}
