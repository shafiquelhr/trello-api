package org.example.dao;

import org.example.entities.Ticket;
import org.example.entities.User;
import org.example.enums.TicketStatus;
import org.example.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDao {

    private static final String INSERT_SQL = """
            INSERT INTO tickets
                (assigned_to, assigned_by, title, description, status, created_at, deadline,
                 updated_at, priority, estimated_hours, actual_hours, is_blocked,
                 blocked_reason, comments_count)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

    private static final String SELECT_ALL_SQL = "SELECT * FROM tickets";

    private static final String SELECT_BY_STATUS_SQL = "SELECT * FROM tickets WHERE status = ?";
    
    private static final String CHECK_USER_EXISTS_SQL = "SELECT COUNT(*) FROM users WHERE id = ?";

    public void addTicket(Ticket t) {
        Connection conn = null;
        try {
            conn = DBConnection.createDbConnection();
            
            // Begin transaction
            conn.setAutoCommit(false);
            
            // Validate that assigned users exist before adding ticket
            if (!userExists(conn, t.getAssignedToId())) {
                System.err.println("Error: User with ID " + t.getAssignedToId() + " does not exist. Cannot assign ticket.");
                return;
            }
            
            if (!userExists(conn, t.getAssignedById())) {
                System.err.println("Error: User with ID " + t.getAssignedById() + " does not exist. Cannot set as assigner.");
                return;
            }
            
            // Now proceed with ticket insertion
            try (PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, t.getAssignedToId());
                ps.setInt(2, t.getAssignedById());
                ps.setString(3, t.getTitle());
                ps.setString(4, t.getDescription());
                ps.setString(5, t.getStatus().name());
                ps.setTimestamp(6, Timestamp.valueOf(t.getCreatedAt()));
                ps.setDate(7, Date.valueOf(t.getDeadline()));
                ps.setTimestamp(8, t.getUpdatedAt() != null ? Timestamp.valueOf(t.getUpdatedAt()) : null);
                ps.setString(9, t.getPriority());
                ps.setInt(10, t.getEstimatedHours());
                ps.setInt(11, t.getActualHours());
                ps.setBoolean(12, t.isBlocked());
                ps.setString(13, t.getBlockedReason());
                ps.setInt(14, t.getCommentsCount());

                ps.executeUpdate();

                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        t.setTicketId(keys.getInt(1));
                    }
                }
                
                // Commit transaction
                conn.commit();
                System.out.println("Ticket added successfully with ID: " + t.getTicketId());
            }

        } catch (SQLException e) {
            // Rollback transaction on error
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            // Reset auto-commit and close connection
            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    // Helper method to check if a user exists
    private boolean userExists(Connection conn, int userId) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(CHECK_USER_EXISTS_SQL)) {
            ps.setInt(1, userId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    public List<Ticket> getAllTickets() {
        return fetchTickets(SELECT_ALL_SQL, null);
    }

    public List<Ticket> getTicketsByStatus(TicketStatus status) {
        return fetchTickets(SELECT_BY_STATUS_SQL, status);
    }

    /* ---------- private helpers ---------- */
    private List<Ticket> fetchTickets(String sql, TicketStatus status) {
        List<Ticket> tickets = new ArrayList<>();

        try (Connection conn = DBConnection.createDbConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            if (status != null) ps.setString(1, status.name());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Ticket t = Ticket.fromResultSet(rs);   // factory method in Ticket
                    tickets.add(t);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
