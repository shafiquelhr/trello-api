package org.example.dao;

import org.example.utils.DBConnection;
import org.example.enums.Role;
import org.example.entities.Developer;
import org.example.entities.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeveloperDao {

    private final UserDao userDao = new UserDao();

    // Add developer to the DB (adds to both users and developers table)
    public void addDeveloper(Developer developer) {
        // Add user part first (if not already inserted)
        if (developer.getId() == 0) {
            int userId = userDao.addUser(developer); // Dev extends User
            developer.setId(userId);
        }

        String sql = """
                    INSERT INTO developers (
                        user_id,
                        months_of_experience,
                        skills,
                        team_leader_id,
                        hourly_rate,
                        availability_status,
                        github_url,
                        linkedIn_url,
                        resume_text,
                        certifications
                    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection conn = DBConnection.createDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, developer.getId());
            stmt.setInt(2, developer.getMonthsOfExperience());

            // Handle skills safely
            stmt.setString(3, developer.getSkills());

            // Handle nullable teamLeaderId (via object or raw field)
            if (developer.getTeamLeader() != null) {
                stmt.setInt(4, developer.getTeamLeaderId());
            } else if (developer.getTeamLeaderId() != 0) {
                stmt.setInt(4, developer.getTeamLeaderId());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            stmt.setDouble(5, developer.getHourlyRate());
            stmt.setString(6, developer.getAvailabilityStatus());
            stmt.setString(7, developer.getGithubUrl());
            stmt.setString(8, developer.getLinkedInUrl());
            stmt.setString(9, developer.getResumeText());
            stmt.setString(10, developer.getCertifications());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all developers from DB
    public List<Developer> getAllDevelopers() {
        List<Developer> developers = new ArrayList<>();

        String sql = """
                    SELECT d.*,
                           u.name, u.role, u.project_under_management,
                           u.email, u.username, u.password_hash,
                           u.created_at, u.updated_at, u.last_login,
                           u.is_active, u.phone, u.avatar_url,
                           u.bio, u.location
                    FROM developers d
                    JOIN users u ON d.user_id = u.id
                """;

        try (Connection conn = DBConnection.createDbConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int userId = rs.getInt("user_id");
                String name = rs.getString("name");
                Role role = Role.valueOf(rs.getString("role"));
                String project = rs.getString("project_under_management");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String passwordHash = rs.getString("password_hash");

                LocalDateTime createdAt = rs.getTimestamp("created_at") != null
                        ? rs.getTimestamp("created_at").toLocalDateTime() : null;
                LocalDateTime updatedAt = rs.getTimestamp("updated_at") != null
                        ? rs.getTimestamp("updated_at").toLocalDateTime() : null;
                LocalDateTime lastLogin = rs.getTimestamp("last_login") != null
                        ? rs.getTimestamp("last_login").toLocalDateTime() : null;

                boolean isActive = rs.getBoolean("is_active");
                String phone = rs.getString("phone");
                String avatarUrl = rs.getString("avatar_url");
                String bio = rs.getString("bio");
                String location = rs.getString("location");

                // Developer-specific fields
                int experience = rs.getInt("months_of_experience");
                String skills = rs.getString("skills");
                int teamLeaderId = rs.getInt("team_leader_id");
                double hourlyRate = rs.getDouble("hourly_rate");
                String availabilityStatus = rs.getString("availability_status");
                String githubUrl = rs.getString("github_url");
                String linkedInUrl = rs.getString("linkedIn_url"); // correct column name
                String resumeText = rs.getString("resume_text");
                String certifications = rs.getString("certifications");

                // Optional team leader reference
                User teamLeader = userDao.getUserById(teamLeaderId);

                Developer dev = new Developer(
                        userId, name, role, project,
                        email, username, passwordHash,
                        createdAt, updatedAt, lastLogin,
                        isActive, phone, avatarUrl, bio, location,
                        experience, skills, teamLeaderId,
                        hourlyRate, availabilityStatus,
                        githubUrl, linkedInUrl, teamLeader,
                        resumeText, certifications
                );

                developers.add(dev);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return developers;
    }
}

