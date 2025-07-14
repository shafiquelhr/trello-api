package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.Role;

@Getter
@Setter
public class Developer extends User {

    //fields
    private int monthsOfExperience;
    private String[] skills;
    private User teamLeader;
    private Role role;


    public Developer(String name, Role role, int monthsOfExperience, String[] skills, User teamLeader) {
        super(name, role);
        this.monthsOfExperience = monthsOfExperience;
        this.skills = skills;
        this.teamLeader = teamLeader;
    }
}
