package org.example.services.impl;

import org.example.dao.DeveloperDao;
import org.example.entities.Developer;
import org.example.services.DeveloperService;

import java.util.List;

public class DeveloperServiceImpl implements DeveloperService {

    private final DeveloperDao developerDao = new DeveloperDao();

    //Runtime polymorphism
    @Override
    public void addDeveloper(Developer developer) {
        developerDao.addDeveloper(developer);
    }

    //Runtime polymorphism
    @Override
    public List<Developer> getAllDevelopers() {
        List<Developer> developers = developerDao.getAllDevelopers();

        if (developers.isEmpty()) {
            System.out.println("No developers found.");
            return developers;
        }

        // CSV Header
        System.out.println("ID, Name, Role, Email, Username, Skills, Experience, GitHub, LinkedIn, TeamLeaderID, HourlyRate, Availability, Location, Bio, Certifications");

        for (Developer dev : developers) {
            String row = String.join(", ",
                    String.valueOf(dev.getId()),
                    quote(dev.getName()),
                    dev.getRole().name(),
                    quote(dev.getEmail()),
                    quote(dev.getUsername()),
                    quote(dev.getSkills()),
                    String.valueOf(dev.getMonthsOfExperience()),
                    quote(dev.getGithubUrl()),
                    quote(dev.getLinkedInUrl()),
                    dev.getTeamLeader() != null ? String.valueOf(dev.getTeamLeader().getId()) :
                            (dev.getTeamLeaderId() != 0 ? String.valueOf(dev.getTeamLeaderId()) : "null"),
                    String.valueOf(dev.getHourlyRate()),
                    quote(dev.getAvailabilityStatus()),
                    quote(dev.getLocation()),
                    quote(dev.getBio()),
                    quote(dev.getCertifications())
            );

            System.out.println(row);
        }
        return developers;
    }

    private String quote(Object value) {
        return "\"" + (value == null ? "" : value.toString()) + "\"";
    }

}
