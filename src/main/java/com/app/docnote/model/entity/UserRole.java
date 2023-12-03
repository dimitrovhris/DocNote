package com.app.docnote.model.entity;

import com.app.docnote.model.enums.UserRoleEnum;
import jakarta.persistence.*;

import java.util.List;

@Table(name = "roles")
@Entity
public class UserRole extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    @ManyToMany
    private List<UserEntity> users;

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
