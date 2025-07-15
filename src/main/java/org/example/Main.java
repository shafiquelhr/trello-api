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

        // services objs
        UserService userService = new UserServiceImpl();
        TicketService ticketService = new TicketServiceImpl();
        DeveloperService devService = new DeveloperServiceImpl();
        BoardService boardService = new BoardServiceImpl();

        // add users
        User admin = new User("Rafael Leo", Role.ADMIN);
        User lead = new User("Masterati Ben", Role.LEAD, "GENCO");

        //userService.addUser(admin);
        //userService.addUser(lead);

        //userService.getAllUsers();

        // add developer first to assign them tickets
        Developer dev = new Developer("Shafique Java", Role.INTERNEE,
                6, new String[]{"Java", "Python", "Databases"}, lead);
//        devService.addDeveloper(dev);
        List<Developer> developers = devService.getAllDevelopers();

        for (Developer d : developers) {
            System.out.println(d);
        }


        // now creating tickets
        Ticket t1 = new Ticket(0, dev, admin, "Fix login bug",
                "NullPointer on login", TicketStatus.TODO,
                LocalDateTime.now(), LocalDate.now().plusDays(3));

        /** SHALLOW copy (just another reference)
         *
         Ticket t2 = t1; // just referreing to t1 meaning getting a shallow copy.
         t2.setTicketTitle("Different title"); // this will change the title
         System.out.println(t1.getTicketTitle());  // new title: "Different title"
         **/

        Ticket t2 = new Ticket(1, dev, lead, "Refactor DAO layer",
                "Clean up JDBC code", TicketStatus.IN_PROGRESS,
                LocalDateTime.now(), LocalDate.now().plusDays(7));

        //ticketService.addTicket(t1);
        //ticketService.addTicket(t2);

        // building and displaying boards
        //boardService.buildBoards();
        //boardService.printAllBoards();

        // fetching only todo board
        //System.out.println("\n--- Tickets in TODO ----");
        //boardService.getBoard(TicketStatus.TODO).printAllTickets();
    }
}