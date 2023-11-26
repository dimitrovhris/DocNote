package com.example.docnote.model.entity;

import com.example.docnote.model.enums.UserRoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Table(name = "roles")
@Entity
public class UserRole extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRole() {

    }

    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(UserRoleEnum role) {
        this.role = role;
    }

    public UserRole(UserRoleEnum role) {
        this.role = role;
    }
}
