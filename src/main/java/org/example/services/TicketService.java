package org.example.services;

import org.example.entities.Ticket;
import org.example.enums.TicketStatus;

import java.util.List;

public interface TicketService {
    void addTicket(Ticket t);
    List<Ticket> getAllTickets();
    List<Ticket> getTicketsByStatus(TicketStatus status);
}
