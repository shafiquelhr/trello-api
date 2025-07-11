package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.TicketStatus;

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
                "assigned To: " + tickets.get(0).getAssignedTo() + "\n" +
                "assigned By: " + tickets.get(0).getAssignedBy() + "\n" +
                "Title: " + tickets.get(0).getTicketTitle() + "\n" +
                "Description: " + tickets.get(0).getTicketDescription() + "\n" +
                "Status: " + tickets.get(0).getTicketStatus() + "\n" +
                "Created At: " + tickets.get(0).getTicketCreatedAt() + "\n" +
                "Deadline: " + tickets.get(0).getDeadline();
    }
}
