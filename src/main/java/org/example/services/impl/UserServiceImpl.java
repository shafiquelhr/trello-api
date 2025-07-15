package org.example.services.impl;

import org.example.dao.UserDao;
import org.example.entities.User;
import org.example.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDao();

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void getAllUsers() {
        List<User> users = userDao.getAllUsers();

        for (User user : users) {
            if (user.getRole().name().equals("LEAD")) {
                System.out.println("Name: " + user.getName() + ", Role: " + user.getRole() +
                        ", Project: " + user.getProjectUnderManagement());
            } else {
                System.out.println("Name: " + user.getName() + ", Role: " + user.getRole());
            }
        }
    }
}
