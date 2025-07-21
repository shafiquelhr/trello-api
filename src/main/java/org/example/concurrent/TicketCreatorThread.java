package org.example.concurrent;

import org.example.entities.*;
import org.example.enums.TicketStatus;
import org.example.services.TicketService;
import org.example.services.impl.TicketServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;

//Task: simulate a background ticket creator
public class TicketCreatorThread extends Thread {

    /*
    private final Developer dev;
    private final User admin;
    private final TicketService ticketService = new TicketServiceImpl();

    //constructor
    public TicketCreatorThread(Developer dev, User admin) {
        super("TicketCreator");          //thread name
        this.dev = dev;
        this.admin = admin;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            Ticket t = new Ticket(100, dev, admin,
                    "Auto#" + i, "Created in thread", TicketStatus.TODO,
                    LocalDateTime.now(), LocalDate.now().plusDays(2));
            ticketService.addTicket(t);
            System.out.println(getName() + " added " + t.getTicketTitle());
            try { Thread.sleep(500); } catch (InterruptedException ignored) {}
        }
    }

    */
}
