package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.Role;

import java.time.LocalDateTime;

@Getter
@Setter
public class User {

    private int id;
    private String name;
    private Role role;
    private String projectUnderManagement; // used only if role == LEAD
    private String email;
    private String username;
    private String passwordHash;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLogin;
    private boolean isActive;
    private String phone;
    private String avatarUrl;
    private String bio;
    private String location;

    public User(int id, String name, Role role, String projectUnderManagement,
                String email, String username, String passwordHash,
                LocalDateTime createdAt, LocalDateTime updatedAt,
                LocalDateTime lastLogin, boolean isActive, String phone,
                String avatarUrl, String bio, String location) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.projectUnderManagement = projectUnderManagement;
        this.email = email;
        this.username = username;
        this.passwordHash = passwordHash;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.lastLogin = lastLogin;
        this.isActive = isActive;
        this.phone = phone;
        this.avatarUrl = avatarUrl;
        this.bio = bio;
        this.location = location;
    }

    // Optionally, add full constructors if needed for DTOs or DAO (hydration)?!?!
}
