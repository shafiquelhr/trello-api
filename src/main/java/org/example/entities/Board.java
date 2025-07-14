package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.TicketStatus;
import java.util.List;
import java.util.stream.Collectors;

import java.util.List;

@Getter @Setter
public class Board {

    TicketStatus status;
    List<Ticket> tickets;

    public Board(TicketStatus status, List<Ticket> tickets) {
        this.status = status;
        this.tickets = tickets;
    }

    //getListOfTicketsByStatus
    public String getFirstTicket() {
        //format a ticket
        return "Ticket ID: " + tickets.get(0).getTicketId() + "\n" +
                "assigned To: " + tickets.get(0).getAssignedTo().getName() + ", Role: " + tickets.get(0).getAssignedTo().getRole() + "\n" +
                "assigned By: " + tickets.get(0).getAssignedBy().getName() + ", Role: " + tickets.get(0).getAssignedBy().getRole()+ "\n" +
                "Ticket Title: " + tickets.get(0).getTicketTitle() + "\n" +
                "Description: " + tickets.get(0).getTicketDescription() + "\n" +
                "Status: " + tickets.get(0).getTicketStatus() + "\n" +
                "Created At: " + tickets.get(0).getTicketCreatedAt() + "\n" +
                "Deadline: " + tickets.get(0).getDeadline();
    }

    // print all tickets in board
    public void printAllTickets() {
        System.out.println("----- Board: " + status + " -----");
        for (Ticket ticket : tickets) {
            System.out.println("Ticket ID: " + ticket.getTicketId());
            System.out.println("Assigned To: " + ticket.getAssignedTo().getName());
            System.out.println("Assigned By: " + ticket.getAssignedBy().getName());
            System.out.println("Title: " + ticket.getTicketTitle());
            System.out.println("Status: " + ticket.getTicketStatus());
            System.out.println("Deadline: " + ticket.getDeadline());
            System.out.println("-----------------------------");
        }
    }

    // helper method: Filter tickets by status
    public List<Ticket> getTicketsByStatus(TicketStatus status) {
        return tickets.stream()
                .filter(ticket -> ticket.getTicketStatus() == status)
                .collect(Collectors.toList());
    }
}
