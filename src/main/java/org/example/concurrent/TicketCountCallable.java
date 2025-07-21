package org.example.concurrent;

import org.example.enums.TicketStatus;
import org.example.services.TicketService;
import org.example.services.impl.TicketServiceImpl;

import java.util.concurrent.Callable;
/*
public class TicketCountCallable implements Callable<Integer> {
    /*
    private final TicketStatus status;

    public TicketCountCallable(TicketStatus status) {
        this.status = status;
    }

    @Override
    public Integer call() {
        TicketService service = new TicketServiceImpl();
        int count = service.getTicketsByStatus(status).size();
        System.out.println(Thread.currentThread().getName() +
                " counted " + count + " tickets in " + status);
        return count;
    }

}
*/