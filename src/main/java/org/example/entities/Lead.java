package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.Role;
import org.example.enums.TicketStatus;

@Getter
@Setter
public class Lead extends User{

    private String projectUnderManagement;

    public Lead(String name, Role role, String projectUnderManagement) {
        super(name, role);
        this.projectUnderManagement = projectUnderManagement;
    }
}
