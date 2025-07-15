package org.example.services.impl;

import org.example.entities.Board;
import org.example.entities.Ticket;
import org.example.enums.TicketStatus;
import org.example.services.BoardService;
import org.example.services.TicketService;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardServiceImpl implements BoardService {

    private final TicketService ticketService = new TicketServiceImpl();
    private Map<TicketStatus, Board> cache;           // lazy‑loaded

    @Override
    public Map<TicketStatus, Board> buildBoards() {
        List<Ticket> all = ticketService.getAllTickets();
        Map<TicketStatus, List<Ticket>> grouped =
                all.stream().collect(Collectors.groupingBy(Ticket::getTicketStatus,
                        () -> new EnumMap<>(TicketStatus.class),
                        Collectors.toList()));

        // create Board objects
        cache = new EnumMap<>(TicketStatus.class);
        for (TicketStatus st : TicketStatus.values()) {
            List<Ticket> list = grouped.getOrDefault(st, List.of());
            cache.put(st, new Board(st, list));
        }
        return cache;
    }

    @Override
    public Board getBoard(TicketStatus status) {
        if (cache == null) buildBoards();
        return cache.get(status);
    }

    @Override
    public void printAllBoards() {
        if (cache == null) buildBoards();
        cache.values().forEach(Board::printAllTickets);
    }
}
