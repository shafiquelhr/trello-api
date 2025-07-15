package org.example.dao;

import org.example.entities.Developer;
import org.example.entities.User;
import org.example.enums.Role;
import org.example.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDao {

    public void addDeveloper(Developer developer) {
        //pass by value

        // first, ensure the user row exists and has an id
        if (developer.getId() == 0) {
            new UserDao().addUser(developer);   // dev extends User, so we can reuse
        }

        // SQL Query to insert a new developer.
        String sql = "INSERT INTO developers (user_id, months_of_experience, skills, team_leader_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.createDbConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Use developer's ID (from User table) instead of name/role
            stmt.setInt(1, developer.getId());
            stmt.setInt(2, developer.getMonthsOfExperience());
            stmt.setString(3, String.join(",", developer.getSkills()));
            
            // Use team leader's ID instead of name
            stmt.setInt(4, developer.getTeamLeader().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Developer> getAllDevelopers() {
        List<Developer> developers = new ArrayList<>();

        // Join with users' table to get developer details
        String sql = "SELECT d.*, u.name, u.role FROM developers d " +
                     "JOIN users u ON d.user_id = u.id";

        try (Connection conn = DBConnection.createDbConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                Role role = Role.valueOf(rs.getString("role"));
                int exp = rs.getInt("months_of_experience");
                String[] skills = rs.getString("skills").split(",");
                
                // Get team leader information by ID
                int teamLeaderId = rs.getInt("team_leader_id");
                User teamLeader = getUserById(conn, teamLeaderId);
                
                Developer developer = new Developer(name, role, exp, skills, teamLeader);
                developer.setId(rs.getInt("user_id"));
                developers.add(developer);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return developers;
    }
    
    // Helper method to get user by ID
    private User getUserById(Connection conn, int userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    Role role = Role.valueOf(rs.getString("role"));
                    String project = rs.getString("project_under_management");
                    
                    User user = (role == Role.LEAD) ? 
                        new User(name, role, project) : 
                        new User(name, role);
                    
                    user.setId(userId);
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Default to empty user if not found
        return new User("Unknown", Role.DEVELOPER);
    }
}
