package org.example.entities;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.Role;

@Getter
@Setter
public class Developer extends User {

    private int monthsOfExperience;
    private String[] skills;
    private User teamLeader; // assume teamLeader also has ID

    private User exampleTeamLeader = new User("Shafique Lead", Role.LEAD, "ProjectX");
    /** The Developer owns the User object internally
     * and is responsible for its creation and lifecycle.
     * So this is indicating a strong has-a relationship, Composition. **/

    public Developer(String name, Role role, int monthsOfExperience, String[] skills, User teamLeader) {
        super(name, role);
        this.monthsOfExperience = monthsOfExperience;
        this.skills = skills;
        this.teamLeader = teamLeader;
    }
}
