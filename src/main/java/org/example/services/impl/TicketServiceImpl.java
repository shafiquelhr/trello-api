package org.example.services.impl;

import org.example.dao.TicketDao;
import org.example.entities.Ticket;
import org.example.enums.TicketStatus;
import org.example.services.TicketService;

import java.util.List;

public class TicketServiceImpl implements TicketService {

    private final TicketDao ticketDao = new TicketDao();

    @Override
    public void addTicket(Ticket t) {
        ticketDao.addTicket(t);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketDao.getAllTickets();
    }

    @Override
    public List<Ticket> getTicketsByStatus(TicketStatus status) {
        return ticketDao.getTicketsByStatus(status);
    }
}
