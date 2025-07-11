package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.TicketStatus;

@Getter
@Setter
public class Ticket {

    private int ticketId;
    private Developer assignedTo;
    private Lead assignedBy;
    private String ticketTitle;
    private String ticketDescription;
    private TicketStatus ticketStatus;
    private String ticketCreatedAt;
    private String deadline;

    public Ticket(
            int ticketId, Developer assignedTo, Lead assignedBy, String ticketTitle,
            String ticketDescription, TicketStatus ticketStatus, String ticketCreatedAt,
            String deadline)
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


}
