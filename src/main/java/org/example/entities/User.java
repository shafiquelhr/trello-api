package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.Role;

@Getter
@Setter
public class User {

    private String name;
    private int id;
    private Role role;
    private String projectUnderManagement; // only used if role == LEAD

    public User(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    // constructor for Team Lead so he can set projectUnderManagement.s
    public User(String name, Role role, String projectUnderManagement) {
        this.name = name;
        this.role = role;
        this.projectUnderManagement = projectUnderManagement;
    }
}