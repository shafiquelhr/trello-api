package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.Role;

import java.time.LocalDateTime;
import java.util.Arrays;

@Getter
@Setter
public class Developer extends User {

    private int monthsOfExperience;
    private String skills; // stored as text in DB, comma-separated string
    private int teamLeaderId; // foreign key to User
    private User teamLeader;
    private double hourlyRate;
    private String availabilityStatus;
    private String githubUrl;
    private String linkedInUrl;
    private String resumeText;
    private String certifications;

    public Developer(int id,
                     String name, Role role,
                     String projectUnderManagement,
                     String email,
                     String username,
                     String passwordHash,
                     LocalDateTime createdAt,
                     LocalDateTime updatedAt,
                     LocalDateTime lastLogin,
                     boolean isActive,
                     String phone,
                     String avatarUrl,
                     String bio,
                     String location,
                     int monthsOfExperience,
                     String skills,
                     int teamLeaderId,
                     double hourlyRate,
                     String availabilityStatus,
                     String githubUrl,
                     String linkedInUrl,
                     User teamLeader,
                     String resumeText,
                     String certifications) {
        super(id, name, role, projectUnderManagement, email, username, passwordHash, createdAt, updatedAt, lastLogin, isActive, phone, avatarUrl, bio, location);
        this.monthsOfExperience = monthsOfExperience;
        this.skills = skills;
        this.teamLeaderId = teamLeaderId;
        this.teamLeader = teamLeader;
        this.hourlyRate = hourlyRate;
        this.availabilityStatus = availabilityStatus;
        this.githubUrl = githubUrl;
        this.linkedInUrl = linkedInUrl;
        this.resumeText = resumeText;
        this.certifications = certifications;
    }

}
