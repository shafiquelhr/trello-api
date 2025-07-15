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
        return developerDao.getAllDevelopers();
    }
}
