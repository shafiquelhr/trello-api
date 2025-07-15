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
            (assigned_to, assigned_by, title, description, status, created_at, deadline)
        VALUES (?, ?, ?, ?, ?, ?, ?)
        """;

    private static final String SELECT_ALL_SQL =
            "SELECT * FROM tickets";

    private static final String SELECT_BY_STATUS_SQL =
            "SELECT * FROM tickets WHERE status = ?";

    public void addTicket(Ticket t) {
        // Ensure users are already in the database with valid IDs
        User assignedTo = t.getAssignedTo();
        User assignedBy = t.getAssignedBy();
        
        UserDao userDao = new UserDao();
        
        // Make sure the users have IDs (are saved in database)
        if (assignedTo.getId() == 0) {
            userDao.addUser(assignedTo);
        }
        
        if (assignedBy.getId() == 0) {
            userDao.addUser(assignedBy);
        }
        
        try (Connection conn = DBConnection.createDbConnection();
             // Add Statement.RETURN_GENERATED_KEYS to fix SQL error
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, t.getAssignedTo().getId());
            ps.setInt(2, t.getAssignedBy().getId());
            ps.setString(3, t.getTicketTitle());
            ps.setString(4, t.getTicketDescription());
            ps.setString(5, t.getTicketStatus().name());
            ps.setTimestamp(6, Timestamp.valueOf(t.getTicketCreatedAt()));
            ps.setDate(7, Date.valueOf(t.getDeadline()));

            ps.executeUpdate();
            
            // Set the ticket ID from generated keys
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    t.setTicketId(keys.getInt(1));
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

        try (Connection conn = DBConnection.createDbConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

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
