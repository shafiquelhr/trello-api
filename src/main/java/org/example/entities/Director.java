package org.example.entities;

import org.example.enums.Role;
import org.example.enums.TicketStatus;

public class Director extends User {

    public Director(String name, Role role) {
        super(name, role);
    }
}
