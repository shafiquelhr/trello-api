package org.example.dao;

import org.example.entities.User;
import org.example.enums.Role;
import org.example.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public int addUser(User user) {

        String sql;
        boolean isLead = user.getRole() == Role.LEAD;

        if (isLead) {
            sql = "INSERT INTO users (name, role, project_under_management) VALUES (?, ?, ?)";
        } else {
            sql = "INSERT INTO users (name, role) VALUES (?, ?)";
        }

        try (Connection conn = DBConnection.createDbConnection();
             // Add Statement.RETURN_GENERATED_KEYS to fix the SQL error
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getRole().name());

            if (isLead) {
                stmt.setString(3, user.getProjectUnderManagement());
            }

            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    int id = keys.getInt(1);
                    user.setId(id);          // ✅ set back into object
                    return id;               //   (optional return)
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        String sql = "SELECT * FROM users";

        try (Connection conn = DBConnection.createDbConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                Role role = Role.valueOf(rs.getString("role"));
                String project = rs.getString("project_under_management");

                User user;

                if (role == Role.LEAD) {
                    user = new User(name, role, project);
                } else {
                    user = new User(name, role);
                }

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }
}
