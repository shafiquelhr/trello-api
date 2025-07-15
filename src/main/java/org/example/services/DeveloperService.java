package org.example.services;

import org.example.entities.Developer;
import java.util.List;

public interface DeveloperService {
    void addDeveloper(Developer developer);
    List<Developer> getAllDevelopers();
}
