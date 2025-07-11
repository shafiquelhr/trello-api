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

//        System.out.println(osamaLead.getName() + ", is Team Lead.");

        Developer javaInternee = new Developer("Shafique", Role.INTERNEE, 2, skills, osamaLead);

//        System.out.println(javaInternee.getName() + ", is developer who'll be assigned the project, " + osamaLead.getProjectUnderManagement());

        List<Ticket> tickets = new ArrayList<Ticket>();

        Ticket javaTicket = new Ticket(1,
                javaInternee,
                osamaLead,
                "First Java Ticket",
                "This is my first Java ticket inspired by Trello",
                TicketStatus.TODO,
                "7 July", "14 July");

//        System.out.println(javaTicket.getTicketId() + ", is the ticket has been assigned to " + javaInternee.getName() + " under team lead, " + osamaLead.getName() +"for project,  ");

        tickets.add(javaTicket);

        Board toDoBoard = new Board(TicketStatus.TODO, tickets);

        System.out.println(toDoBoard.getFirstTicket());
    }
}