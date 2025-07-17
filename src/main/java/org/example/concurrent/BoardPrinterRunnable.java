package org.example.concurrent;

import org.example.entities.Board;
import org.example.enums.TicketStatus;
import org.example.services.BoardService;
import org.example.services.impl.BoardServiceImpl;

/** printing every board’s tickets based on ticket status
 * using Java's multithreading capabilities by impl.
 * Runnable interface **/
public class BoardPrinterRunnable implements Runnable {

    private final TicketStatus status;

    public BoardPrinterRunnable(TicketStatus status) {
        this.status = status;
    }

    @Override
    public void run() {
        BoardService boardService = new BoardServiceImpl();
        Board board = boardService.getBoard(status);
        System.out.println(Thread.currentThread().getName() + " printing " + status + " board");
        board.printAllTickets();
    }
}
