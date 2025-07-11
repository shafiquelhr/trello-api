package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.Role;
import org.example.enums.TicketStatus;

@Getter
@Setter
public class User {

    private String name;
//    private Ticket ticket;
    private Role role;

    public User(String name, Role role) {
        this.name = name;
//        this.ticket = ticket;
        this.role = role;
    }
}
