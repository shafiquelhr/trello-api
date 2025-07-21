package org.example.dao;

import org.example.enums.Role;
import org.example.entities.User;
import org.example.utils.DBConnection;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    // Add user to the DB
    public int addUser(User user) {
        String sql = "INSERT INTO users (" +
                "name, role, project_under_management, email, username, password_hash, " +
                "created_at, updated_at, last_login, is_active, phone, avatar_url, bio, location) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.createDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getRole().name());
            stmt.setString(3, user.getProjectUnderManagement());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getUsername());
            stmt.setString(6, user.getPasswordHash());

            stmt.setTimestamp(7, user.getCreatedAt() != null ? Timestamp.valueOf(user.getCreatedAt()) : null);
            stmt.setTimestamp(8, user.getUpdatedAt() != null ? Timestamp.valueOf(user.getUpdatedAt()) : null);
            stmt.setTimestamp(9, user.getLastLogin() != null ? Timestamp.valueOf(user.getLastLogin()) : null);

            stmt.setBoolean(10, user.isActive());
            stmt.setString(11, user.getPhone());
            stmt.setString(12, user.getAvatarUrl());
            stmt.setString(13, user.getBio());
            stmt.setString(14, user.getLocation());

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    user.setId(id);
                    return id;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // Get all users from DB
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = DBConnection.createDbConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Role role = Role.valueOf(rs.getString("role"));
                String project = rs.getString("project_under_management");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String password_hash = rs.getString("password_hash");

                LocalDateTime createdAt = rs.getTimestamp("created_at") != null ?
                        rs.getTimestamp("created_at").toLocalDateTime() : null;

                LocalDateTime updatedAt = rs.getTimestamp("updated_at") != null ?
                        rs.getTimestamp("updated_at").toLocalDateTime() : null;

                LocalDateTime lastLogin = rs.getTimestamp("last_login") != null ?
                        rs.getTimestamp("last_login").toLocalDateTime() : null;

                boolean isActive = rs.getBoolean("is_active");
                String phone = rs.getString("phone");
                String avatarUrl = rs.getString("avatar_url");
                String bio = rs.getString("bio");
                String location = rs.getString("location");

                // Now pass all values to the constructor in order
                User user = new User(id, name, role, project,
                        email, username, password_hash,
                        createdAt, updatedAt, lastLogin,
                        isActive, phone, avatarUrl, bio, location);

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    // Optional helper: Get user by ID
    public User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DBConnection.createDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    Role role = Role.valueOf(rs.getString("role"));
                    String project = rs.getString("project_under_management");
                    String email = rs.getString("email");
                    String username = rs.getString("username");
                    String password_hash = rs.getString("password_hash");

                    LocalDateTime createdAt = rs.getTimestamp("created_at") != null ?
                            rs.getTimestamp("created_at").toLocalDateTime() : null;

                    LocalDateTime updatedAt = rs.getTimestamp("updated_at") != null ?
                            rs.getTimestamp("updated_at").toLocalDateTime() : null;

                    LocalDateTime lastLogin = rs.getTimestamp("last_login") != null ?
                            rs.getTimestamp("last_login").toLocalDateTime() : null;

                    boolean isActive = rs.getBoolean("is_active");
                    String phone = rs.getString("phone");
                    String avatarUrl = rs.getString("avatar_url");
                    String bio = rs.getString("bio");
                    String location = rs.getString("location");

                    return new User(userId, name, role, project,
                            email, username, password_hash,
                            createdAt, updatedAt, lastLogin,
                            isActive, phone, avatarUrl, bio, location);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // not found
    }
}
