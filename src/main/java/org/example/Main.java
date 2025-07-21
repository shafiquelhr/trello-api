package org.example;

import org.example.entities.*;
import org.example.enums.Role;
import org.example.enums.TicketStatus;
import org.example.services.BoardService;
import org.example.services.DeveloperService;
import org.example.services.TicketService;
import org.example.services.UserService;
import org.example.services.impl.BoardServiceImpl;
import org.example.services.impl.DeveloperServiceImpl;
import org.example.services.impl.TicketServiceImpl;
import org.example.services.impl.UserServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1. Initialize service layer
        UserService userService = new UserServiceImpl();
        DeveloperService devService = new DeveloperServiceImpl();
        TicketService ticketService = new TicketServiceImpl();
        BoardService boardService = new BoardServiceImpl();

        // 2. Create and save users (admin and lead)
        User admin = new User(0, "Mark", Role.ADMIN, null, "admin@example.com",
                "mark", "hashed_pass12@21", LocalDateTime.now(), null, null,
                true, "000-000-0000", null, null, null);

        User lead = new User(1, "Cuban", Role.LEAD, "LHRs", "lead@example.com",
                "cube", "hashed_pass", LocalDateTime.now(), null, null,
                true, "111-111-1111", null, null, null);

        //userService.addUser(admin);
        //userService.addUser(lead);
        //userService.getAllUsers();

        // 3. Create and save developer
        Developer dev = new Developer(0, "Madvaki", Role.INTERNEE, null,
                "dev@example.com", "duadsql", "hashed_pass",
                LocalDateTime.now(), null, null, true, null, null, null, null,
                6, "SQL, JDBC, Postgres", 50, 0.0,
                "Available", null, null, lead, null, null);

        //devService.addDeveloper(dev);
        //devService.getAllDevelopers();

        // 4. Create and save tickets
        Ticket t1 = new Ticket(
                0, "Fix SQL FK Integrity bugs",
                "SQLIntegrityException on login",
                TicketStatus.TODO,
                dev.getId(),
                admin.getId(),
                LocalDateTime.now(),
                LocalDate.now().plusDays(7),
                null, "High", 10, 0,
                false, null, 0
        );

        Ticket t2 = new Ticket(
                1, "Demo Multithreads",
                "Clean up JDBC code and shift it to concurrency",
                TicketStatus.IN_PROGRESS,
                dev.getId(),
                lead.getId(),
                LocalDateTime.now(),
                LocalDate.now().plusDays(7),
                null, "Medium", 8, 0,
                false, null, 0
        );

        ticketService.addTicket(t1);
        ticketService.addTicket(t2);

        /*
        // 5. Build and print ticket boards
        boardService.buildBoards();
        boardService.printAllBoards();

        // 6. Optionally filter by status
        System.out.println("\n--- Tickets in TODO Board ---");
        boardService.getBoard(TicketStatus.TODO).printAllTickets();
        */
    }
}