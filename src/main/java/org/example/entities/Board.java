package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.TicketStatus;

import java.util.List;

@Getter
@Setter
public class Board {

    private TicketStatus status;
    private List<Ticket> tickets;

    public Board(TicketStatus status, List<Ticket> tickets) {
        this.status = status;
        this.tickets = tickets;
    }

    // print all tickets in the board (handles nulls as well)
    public void printAllTickets() {
        System.out.println("----- Board: " + status + " -----");
        for (Ticket ticket : tickets) {
            System.out.println("Ticket ID: " + ticket.getTicketId());

            // Safely print assignedTo name if object is set, else show ID
            if (ticket.getAssignedTo() != null) {
                System.out.println("Assigned To: " + ticket.getAssignedTo().getName());
            } else {
                System.out.println("Assigned To ID: " + ticket.getAssignedToId());
            }

            // Safely print assignedBy name if object is set, else show ID
            if (ticket.getAssignedBy() != null) {
                System.out.println("Assigned By: " + ticket.getAssignedBy().getName());
            } else {
                System.out.println("Assigned By ID: " + ticket.getAssignedById());
            }

            System.out.println("Title: " + ticket.getTitle());
            System.out.println("Status: " + ticket.getStatus());
            System.out.println("Created At: " + ticket.getCreatedAt());
            System.out.println("Deadline: " + ticket.getDeadline());

            System.out.println("Priority: " + ticket.getPriority());
            System.out.println("Estimated Hours: " + ticket.getEstimatedHours());
            System.out.println("Actual Hours: " + ticket.getActualHours());

            if (ticket.isBlocked()) {
                System.out.println("⚠️ Blocked: " + ticket.getBlockedReason());
            }

            System.out.println("Comments Count: " + ticket.getCommentsCount());
            System.out.println("-----------------------------");
        }
    }
}
