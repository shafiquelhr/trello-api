package org.example.dao;

import org.example.entities.User;
import org.example.enums.Role;
import org.example.utils.DBConnection;

import java.sql.*;

public class UserDao {

    public void addUser(User user) {
        String sql;

        boolean isLead = user.getRole() == Role.LEAD;

        if (isLead) {
            sql = "INSERT INTO users (name, role, project_under_management) VALUES (?, ?, ?)";
        } else {
            sql = "INSERT INTO users (name, role) VALUES (?, ?)";
        }

        try (Connection conn = DBConnection.createDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getRole().name());

            if (isLead) {
                stmt.setString(3, ((User) user).getProjectUnderManagement());
            }

            int rows = stmt.executeUpdate();
            System.out.println(rows + " row(s) inserted.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllUsers() {
        try (Connection conn = DBConnection.createDbConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String role = resultSet.getString("role");
                String projectUnderManagement = resultSet.getString("project_under_management");

                if ("LEAD".equals(role)) {
                    System.out.println("ID: " + id + ", Name: " + name + ", Role: " + role + ", Project: " + projectUnderManagement);
                } else {
                    System.out.println("ID: " + id + ", Name: " + name + ", Role: " + role);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}