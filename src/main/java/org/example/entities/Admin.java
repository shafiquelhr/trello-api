package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.Role;
import org.example.enums.TicketStatus;

import java.util.List;

@Getter
@Setter
public class Admin extends User {

    Role role =  Role.ADMIN;

    public Admin(String name, Role role) {
        super(name, role);
    }

}
