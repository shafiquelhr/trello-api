package org.example.services;

import org.example.entities.Board;
import org.example.enums.TicketStatus;

import java.util.Map;

public interface BoardService {
    Map<TicketStatus, Board> buildBoards();                        // all boards
    Board getBoard(TicketStatus status);                           // one board
    void printAllBoards();                                         // helper
}
