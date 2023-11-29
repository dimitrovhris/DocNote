package com.example.docnote.repository;

import com.example.docnote.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findFirstByEgn(String phone);
    Optional<UserEntity> findFirstByUsername(String username);
    Optional<UserEntity> findFirstByEmail(String email);
    Optional<UserEntity> findFirstByEmailOrUsername(String email, String username);

    List<UserEntity> findByApprovedFalse();
}
