package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.entities.*;
import org.example.enums.Role;
import org.example.enums.TicketStatus;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {

        String[] skills = {"Java", "Spring Boot", "Databases"};

        Lead osamaLead = new Lead("Osama Azam", Role.LEAD, "Trello API");
        Developer javaInternee = new Developer("Shafique", Role.INTERNEE, 2, skills, osamaLead);
        Admin aliAdmin = new Admin("Ali", Role.ADMIN);
//        List<Ticket> tickets = new ArrayList<Ticket>();

        //  todo tickets board
        List<Ticket> todoTickets = new ArrayList<>();
        todoTickets.add(new Ticket(1, javaInternee, osamaLead, "Fix Login Issue", "Resolve user login bug", TicketStatus.TODO, "9 July", "14 July"));
        todoTickets.add(new Ticket(2, javaInternee, osamaLead, "Add Task Filtering", "Add filtering tasks by status", TicketStatus.TODO, "9 July", "16 July"));

        Board toDoBoard = new Board(TicketStatus.TODO, todoTickets);

        //  in review tickets board
        List<Ticket> inReviewTickets = new ArrayList<>();
        inReviewTickets.add(new Ticket(3, javaInternee, aliAdmin, "Improve Logging", "Add better logs to services", TicketStatus.IN_REVIEW, "8 July", "15 July"));
        inReviewTickets.add(new Ticket(4, javaInternee, osamaLead, "Refactor API Layer", "Refactor controller-service layer", TicketStatus.IN_REVIEW, "9 July", "17 July"));

        Board inReviewBoard = new Board(TicketStatus.IN_REVIEW, inReviewTickets);

        //  done tickets board
        List<Ticket> doneTickets = new ArrayList<>();
        doneTickets.add(new Ticket(5, javaInternee, osamaLead, "Project Setup", "Initialized Spring Boot project", TicketStatus.DONE, "6 July", "7 July"));
//        doneTickets.add(new Ticket(6, javaInternee, aliAdmin, "Initial Commit", "Pushed first working commit", TicketStatus.DONE, "7 July", "7 July"));

        Board doneBoard = new Board(TicketStatus.DONE, doneTickets);


        System.out.println("\n==== TODO BOARD ====");
        toDoBoard.printAllTickets();

        System.out.println("\n==== IN REVIEW BOARD ====");
        inReviewBoard.printAllTickets();

        System.out.println("\n==== DONE BOARD ====");
        doneBoard.printAllTickets();

        //log total number of tickets
        log.info("TODO Tickets Count: {}", toDoBoard.getTickets().size());
        log.info("IN REVIEW Tickets Count: {}", inReviewBoard.getTickets().size());
        log.info("DONE Tickets Count: {}", doneBoard.getTickets().size());
    }
}