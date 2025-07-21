package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.dao.UserDao;
import org.example.enums.Role;
import org.example.enums.TicketStatus;
import org.example.utils.DBConnection;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class Ticket {

    private int ticketId;
    private User assignedTo; //Association: They are passed to Ticket but not owned by it.
    private User assignedBy; //Association: Once again User is passed to ticket but not owned by it.
    private String ticketTitle;
    private String ticketDescription;
    private TicketStatus ticketStatus;
    private LocalDateTime ticketCreatedAt;
    private LocalDate deadline;

    // Compile-time polymorphism (constructor overloading)
    public Ticket() {}

    public Ticket(
            int ticketId, User assignedTo, User assignedBy, String ticketTitle, // Changed from Developer to User
            String ticketDescription, TicketStatus ticketStatus, LocalDateTime ticketCreatedAt,
            LocalDate deadline)
    {
        this.ticketId = ticketId;
        this.assignedTo = assignedTo;
        this.assignedBy = assignedBy;
        this.ticketTitle = ticketTitle;
        this.ticketDescription = ticketDescription;
        this.ticketStatus = ticketStatus;
        this.ticketCreatedAt = ticketCreatedAt;
        this.deadline = deadline;
    }

    //fixing the assignedTo and assignedBy ID issues
    public static Ticket fromResultSet(ResultSet rs) throws SQLException {
        Ticket t = new Ticket();
        t.setTicketId(rs.getInt("ticket_id"));
        
        // fetch assigned users using their IDs
        int assignedToId = rs.getInt("assigned_to");
        int assignedById = rs.getInt("assigned_by");
        
        // fetch the actual user objects
        User assignedTo = getUserById(assignedToId);
        User assignedBy = getUserById(assignedById);
        
        // no need for casting now
        t.setAssignedTo(assignedTo);
        t.setAssignedBy(assignedBy);
        
        t.setTicketTitle(rs.getString("title"));
        t.setTicketDescription(rs.getString("description"));
        t.setTicketStatus(TicketStatus.valueOf(rs.getString("status")));
        t.setTicketCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        t.setDeadline(rs.getDate("deadline").toLocalDate());
        return t;
    }
    
    // helper method to get user by ID
    private static User getUserById(int userId) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DBConnection.createDbConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String roleStr = rs.getString("role");
                    String project = rs.getString("project_under_management");
                    
                    Role role = Role.valueOf(roleStr);
                    
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
        // Return empty user if not found
        return new User("Unknown: USER NOT FOUND", Role.DEVELOPER);
    }
}
