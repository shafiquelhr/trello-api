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
    private String title;
    private String description;
    private TicketStatus status;
    private int assignedToId;
    private int assignedById;
    private LocalDateTime createdAt;
    private LocalDate deadline;
    private LocalDateTime updatedAt;
    private String priority;
    private int estimatedHours;
    private int actualHours;
    private boolean isBlocked;
    private String blockedReason;
    private int commentsCount;

    // Optional (if needed in service layer)
    private User assignedTo;
    private User assignedBy;

    //new constructor
    public Ticket(int ticketId, String title, String description, TicketStatus status,
                  int assignedToId, int assignedById, LocalDateTime createdAt, LocalDate deadline,
                  LocalDateTime updatedAt, String priority, int estimatedHours, int actualHours,
                  boolean isBlocked, String blockedReason, int commentsCount) {
        this.ticketId = ticketId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.assignedToId = assignedToId;
        this.assignedById = assignedById;
        this.createdAt = createdAt;
        this.deadline = deadline;
        this.updatedAt = updatedAt;
        this.priority = priority;
        this.estimatedHours = estimatedHours;
        this.actualHours = actualHours;
        this.isBlocked = isBlocked;
        this.blockedReason = blockedReason;
        this.commentsCount = commentsCount;
    }

    //new result set that fixed that assignedTo and assignedBy ID issues;
    public static Ticket fromResultSet(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setTicketId(rs.getInt("ticket_id"));
        ticket.setTitle(rs.getString("title"));
        ticket.setDescription(rs.getString("description"));
        ticket.setStatus(TicketStatus.valueOf(rs.getString("status")));

        ticket.setAssignedToId(rs.getInt("assigned_to"));
        ticket.setAssignedById(rs.getInt("assigned_by"));

        ticket.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        ticket.setDeadline(rs.getDate("deadline").toLocalDate());

        Timestamp updatedTimestamp = rs.getTimestamp("updated_at");
        if (updatedTimestamp != null) {
            ticket.setUpdatedAt(updatedTimestamp.toLocalDateTime());
        }

        ticket.setPriority(rs.getString("priority"));
        ticket.setEstimatedHours(rs.getInt("estimated_hours"));
        ticket.setActualHours(rs.getInt("actual_hours"));
        ticket.setBlocked(rs.getBoolean("is_blocked"));
        ticket.setBlockedReason(rs.getString("blocked_reason"));
        ticket.setCommentsCount(rs.getInt("comments_count"));

        // Optional - fetch User objects using service/DAO layer
        // ticket.setAssignedTo(getUserById(ticket.getAssignedToId()));
        // ticket.setAssignedBy(getUserById(ticket.getAssignedById()));

        return ticket;
    }

    // Compile-time polymorphism (constructor overloading)
    public Ticket() {
    }
}